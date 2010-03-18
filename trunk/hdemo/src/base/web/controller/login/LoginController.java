/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-4</p>
 * <p>更新：</p>
 */
package base.web.controller.login;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import base.constant.Constant;
import base.user.model.BaseUser;
import base.user.model.Privilege;
import base.user.model.Role;
import base.user.model.UserInfo;
import base.user.service.BaseUserService;
import base.user.service.PrivilegeService;
import base.user.service.RoleService;
import base.util.DateUtil;
import base.util.WebAppUtil;
import base.web.controller.BaseFormController;

public class LoginController extends BaseFormController {

	/**
	 * 描述: 人员Manager
	 */
	private BaseUserService baseUserService;

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		LoginForm formInfo = (LoginForm) o;
		BaseUser user = baseUserService.findByProperty("code", formInfo.getCode().trim()).get(0);
		String password = formInfo.getPassword().trim();
		// 没有该用户的时候
		if (user == null) {
			errors.reject("login.msg.invalidUser");
			return showForm(request, response, errors);
		}
		// 用户不为空，但帐号被锁定，判断锁定时间是否超过30分钟，如果超过则解锁
		if (user != null && user.getIsLocked().longValue() == 1L) {
			if (compareLockTime(user.getLockedTime())) {
				user.setIsLocked(new Long(0));
				user.setLockedTime(null);
				baseUserService.update(user);
			} else {
				errors.reject("login.msg.userIsLocked");
				return showForm(request, response, errors);
			}
		}
		// 用户不为空，帐号没有被锁定，但密码不正确
		// 如果登陆次数大于等于5次，则锁定该帐号，并提示帐号已经锁定；否则将登陆次数放到Session中
		if (user != null && user.getIsLocked().longValue() == 0L
				&& !(password.equals(user.getPassword()))) {
			// 取登陆次数，如果上次有登陆过，则取出+1，否则次数=1
			int loginFrequency = 1;
			Map loginMap = null;
			// 从session中取用户列表
			if (request.getSession().getAttribute("loginMap") != null)
				loginMap = (Map) request.getSession().getAttribute("loginMap");
			else
				loginMap = new HashMap();
			if (request.getSession().getAttribute("loginMap") != null
					&& loginMap.get(user.getCode()) != null)
				loginFrequency = ((Integer) loginMap.get(user.getCode()))
						.intValue() + 1;

			// 判断是否超过5次
			if (loginFrequency >= 5) {
				user.setIsLocked(new Long(1));
				user.setLockedTime(DateUtil.getNowPreciseToMin());
				baseUserService.update(user);
				loginMap.put(user.getCode(), null);
				request.getSession().setAttribute("loginMap", loginMap);
				errors.reject("login.msg.userLocking");
				return showForm(request, response, errors);
			} else {
				loginMap.put(user.getCode(), new Integer(loginFrequency));
				request.getSession().setAttribute("loginMap", loginMap);
				String[] login = new String[1];
				login[0] = new Integer(5 - loginFrequency).toString();
				errors.reject("login.msg.passwordErrorFrequency", login, "");
				return showForm(request, response, errors);
			}
		}

		// 正式登陆
		// 更新用户最后上线时间与上线次数
		user.setLoginFrequency(new Long(user.getLoginFrequency().intValue() + 1));
		user.setLastTime(DateUtil.getNowPreciseToMin());
		baseUserService.update(user);
		
		
		
		//用户信息放入UserInfo中
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        
        userInfo.setRoles(user.getRoles());
        
        // 获取用户功能权限
        Map<String,Privilege> privileges = new HashMap<String,Privilege>();
        
        for(Role role:user.getRoles()){
        	for(Privilege p:role.getPrivileges()){
        		privileges.put(p.getPrivCode(), p);
        	}
        }
        
        userInfo.setUserFunPriv(privileges);
        
        
        // 将用户放入Session
        request.getSession().setAttribute(Constant.ATTRIBUTE_USER_INFO,
                userInfo);

        // 设置用户登录标志
        request.getSession().setAttribute(WebAppUtil.LOGIN_FLAG,
                WebAppUtil.LOGINED);

        return new ModelAndView(getSuccessView());
	}

	/**
	 * 描述: 判断是否到解锁时间
	 * 
	 * @param lockedTime
	 * @return
	 */
	private boolean compareLockTime(Date lockedTime) {
		Date nowTime = DateUtil.getNowPreciseToMin();
		if (DateUtil.MinthAddInt(lockedTime, -30).before(nowTime))
			return true;
		else
			return false;
	}

	/**
	 * 返回 baseUserService
	 */
	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	/**
	 * 设置 baseUserService
	 */
	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}
}