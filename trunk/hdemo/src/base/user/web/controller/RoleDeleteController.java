/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package base.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import base.constant.Constant;
import base.log.model.Log;
import base.user.model.Role;
import base.user.service.RoleService;
import base.util.StringUtil;
import base.web.controller.BaseController;

/**
 * <p>
 * ����: RoleDeleteController
 * </p>
 * <p>
 * ����: ��ɫɾ��Controller
 * </p>
 */
public class RoleDeleteController extends BaseController {

	/**
	 * ����: ��ɫManager
	 */
	private RoleService roleService;

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ��ȡ��ɫID
		Long roleId = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "roleId", "0"));
		Role role = roleService.findById(roleId);
		// �ж�Ҫɾ���ļ�¼�Ƿ���ڣ��粻������ת����ʾҳ��
		if (role == null) {
			return new ModelAndView("systemMessage",
					Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
							"public.msg.dataDoesNotExist", request));
		}
		// ɾ����ɫ
		roleService.delete(roleId);

		// д������־
		Log log = new Log();
		log.setUser(this.getCurrentUserInfo(request).getUser());
		log.setLogObject("��ɫ����");
		log.setLogAction("ɾ��");
		log.setDetail("��ɾ���Ľ�ɫ:" + role.getRoleName());
		logService.save(log);

		return new ModelAndView("redirect:"
				+ RequestUtils.getStringParameter(request,
						Constant.ATTRIBUTE_RETURN_URL, ""));
	}

	/**
	 * ���� roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * ���� roleService
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	
}
