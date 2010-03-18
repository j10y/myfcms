/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
 */
package base.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import base.constant.Constant;
import base.log.model.Log;
import base.user.model.BaseUser;
import base.user.service.BaseUserService;
import base.util.StringUtil;
import base.web.controller.BaseController;

/**
 * <p>
 * 类名: PersonDeleteController
 * </p>
 * <p>
 * 描述: 人员删除Controller
 * </p>
 */
public class PersonDeleteController extends BaseController {

	/**
	 * 描述: 人员Manager
	 */
	private BaseUserService baseUserService;

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取角色ID
		Long id = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "id", "0"));
		BaseUser person = baseUserService.findById(id);
		// 判断要删除的记录是否存在，如不存在则转入提示页面
		if (person == null) {
			return new ModelAndView("systemMessage",
					Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
							"public.msg.dataDoesNotExist", request));
		}
		// 删除角色
		baseUserService.delete(id);

		// 写操作日志
		Log log = new Log();
		log.setUser(this.getCurrentUserInfo(request).getUser());
		log.setLogObject("用户管理");
		log.setLogAction("删除");
		log.setDetail("被删除的用户:" + person.getName());
		logService.save(log);

		return new ModelAndView("redirect:"
				+ RequestUtils.getStringParameter(request,
						Constant.ATTRIBUTE_RETURN_URL, ""));
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