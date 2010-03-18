/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：2007-9-4：增加用户口令格式判断</p>
 */
package base.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import base.constant.Constant;
import base.log.model.Log;
import base.user.model.BaseUser;
import base.user.service.BaseUserService;
import base.util.StringUtil;
import base.web.controller.BaseFormController;

/**
 * <p>
 * 类名: PasswordModifyController
 * </p>
 * <p>
 * 描述: 口令修改Controller
 * </p>
 */
public class PasswordModifyController extends BaseFormController {

	/**
	 * 描述: 人员Manager
	 */
	private BaseUserService baseUserService;

	/*
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	protected void onBind(HttpServletRequest request, Object o,
			BindException errors) throws Exception {
		PasswordModifyForm form = (PasswordModifyForm) o;
		if (form.getNewPassword() == null)
			form.setNewPassword("");
		// 判断新口令是否符合规定的格式
		if (!(StringUtil.isMatcherPattern(Constant.REGEXP_PASSWORD, form
				.getNewPassword().trim())))
			errors.reject("person.msg.invaidPasswordFormat", null, "");
		// 判断重复口令是否与新口令一致
		if (!form.getNewPassword().trim().equals(
				form.getNewPasswordRepeat().trim())) {
			errors.reject(
					"person.msg.repeatPasswordIsNotIdentialToNewPassword",
					null, "");
		}
	}

	/*
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		PasswordModifyForm form = (PasswordModifyForm) o;
		// 获取用户信息
		BaseUser person = baseUserService.findById(this.getCurrentUserInfo(request).getUser().getId());
		// 如用户信息不存在则转入提示页面
		if (person == null) {
			return new ModelAndView("systemMessage",
					Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
							"public.msg.dataDoesNotExist", request));
		}
		// 判断用户原口令输入是否正确，如不正确则转入提示页面
		// String oldPassword = MD5.md5(form.getOrignialPassword().trim());
		String oldPassword = form.getOrignialPassword().trim();
		if (!person.getPassword().equals(oldPassword)) {
			errors.reject("person.msg.invalidPassword");
			return showForm(request, response, errors);
		}
		// 更改口令
		person.setPassword(form.getNewPassword().trim());
		// person.setPassword(form.getNewPassword().trim());
		baseUserService.update(person);

		// 写操作日志
		Log log = new Log();
		log.setUser(this.getCurrentUserInfo(request).getUser());
		log.setLogObject("密码修改");
		log.setLogAction("修改");
		log.setDetail("修改用户密码");
		logService.save(log);

		return new ModelAndView(getSuccessView());
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