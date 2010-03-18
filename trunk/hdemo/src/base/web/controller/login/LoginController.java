/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-4</p>
 * <p>���£�</p>
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
	 * ����: ��ԱManager
	 */
	private BaseUserService baseUserService;

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		LoginForm formInfo = (LoginForm) o;
		BaseUser user = baseUserService.findByProperty("code", formInfo.getCode().trim()).get(0);
		String password = formInfo.getPassword().trim();
		// û�и��û���ʱ��
		if (user == null) {
			errors.reject("login.msg.invalidUser");
			return showForm(request, response, errors);
		}
		// �û���Ϊ�գ����ʺű��������ж�����ʱ���Ƿ񳬹�30���ӣ�������������
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
		// �û���Ϊ�գ��ʺ�û�б������������벻��ȷ
		// �����½�������ڵ���5�Σ����������ʺţ�����ʾ�ʺ��Ѿ����������򽫵�½�����ŵ�Session��
		if (user != null && user.getIsLocked().longValue() == 0L
				&& !(password.equals(user.getPassword()))) {
			// ȡ��½����������ϴ��е�½������ȡ��+1���������=1
			int loginFrequency = 1;
			Map loginMap = null;
			// ��session��ȡ�û��б�
			if (request.getSession().getAttribute("loginMap") != null)
				loginMap = (Map) request.getSession().getAttribute("loginMap");
			else
				loginMap = new HashMap();
			if (request.getSession().getAttribute("loginMap") != null
					&& loginMap.get(user.getCode()) != null)
				loginFrequency = ((Integer) loginMap.get(user.getCode()))
						.intValue() + 1;

			// �ж��Ƿ񳬹�5��
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

		// ��ʽ��½
		// �����û��������ʱ�������ߴ���
		user.setLoginFrequency(new Long(user.getLoginFrequency().intValue() + 1));
		user.setLastTime(DateUtil.getNowPreciseToMin());
		baseUserService.update(user);
		
		
		
		//�û���Ϣ����UserInfo��
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        
        userInfo.setRoles(user.getRoles());
        
        // ��ȡ�û�����Ȩ��
        Map<String,Privilege> privileges = new HashMap<String,Privilege>();
        
        for(Role role:user.getRoles()){
        	for(Privilege p:role.getPrivileges()){
        		privileges.put(p.getPrivCode(), p);
        	}
        }
        
        userInfo.setUserFunPriv(privileges);
        
        
        // ���û�����Session
        request.getSession().setAttribute(Constant.ATTRIBUTE_USER_INFO,
                userInfo);

        // �����û���¼��־
        request.getSession().setAttribute(WebAppUtil.LOGIN_FLAG,
                WebAppUtil.LOGINED);

        return new ModelAndView(getSuccessView());
	}

	/**
	 * ����: �ж��Ƿ񵽽���ʱ��
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
	 * ���� baseUserService
	 */
	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	/**
	 * ���� baseUserService
	 */
	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}
}