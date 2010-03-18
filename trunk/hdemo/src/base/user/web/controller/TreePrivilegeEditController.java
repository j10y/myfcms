/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package base.user.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.quanda.common.constant.Constant;
import com.quanda.common.exception.ApplicationException;
import com.quanda.common.log.model.Log;
import com.quanda.common.person.model.Role;
import com.quanda.common.person.service.PrivilegeManager;
import com.quanda.common.person.service.RoleManager;
import com.quanda.common.tree.model.Tree;
import com.quanda.common.tree.service.TreeManager;
import com.quanda.common.util.StringUtil;
import com.quanda.common.web.controller.BaseFormController;

/**
 * <p>
 * ����: RolePrivilegeEditController
 * </p>
 * <p>
 * ����: ��ɫ��ĿȨ�ޱ༭Controller
 * </p>
 */
public class TreePrivilegeEditController extends BaseFormController {

	/**
	 * ����: ��ɫManager
	 */
	private RoleManager roleManager;

	/**
	 * ����: Ȩ��Manager
	 */
	private PrivilegeManager privilegeManager;

	/**
	 * ����: ��ĿManager
	 */
	private TreeManager treeManager;

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(HttpServletRequest request, Object o,
			Errors errors) throws Exception {
		Map map = new HashMap();
		// ȡ���νڵ�����(ȡ��Ŀ��ר��)
		HashMap treeMap = new HashMap();
		treeMap.put("parentId", new Long(0));
		List treeCategoryList = treeManager.findTreeByCondition(treeMap);
		map.put("treeCategoryList", treeCategoryList);

		// ȡָ�����͵�����ֵ
		treeMap = new HashMap();
		String getcategory = RequestUtils.getStringParameter(request,
				"getcategory", "newsChannel");
		map.put("getcategory", getcategory);
		// ��ȡվ��id
		// Long ChildsiteId = (Long) request.getSession().getAttribute(
		// "ChildsiteId");
		String publicFlag = "0";
		//treeMap.put("category", getcategory);
		treeMap.put("isLink", "0");
		// �õ�������������Ŀ��¼��
		List treeList = treeManager.findTreeByCondition(treeMap);
		treeList.remove(0);
		for (int i = 0; i < treeList.size(); i++) {
			Tree tree = (Tree) treeList.get(i);
			String clientNodeInfo = "1";
			boolean isHasChild = false;
			// �ж��Ƿ����ӽڵ�, true ��ʾ���ӽڵ� false ��ʾ���ӽڵ�
			isHasChild = treeManager.findTreeNodeList(tree.getItemId());
			if (isHasChild == true) {
				clientNodeInfo = "0";
			}
			tree.setClientNodeInfo(clientNodeInfo);
			// ��Ŀ���Ƹ�ʽ��
			tree.setItemText(treeManager.formatTreeText(tree.getLevel(), tree
					.getItemText()));
		}
		map.put("treeList", treeList);
		return map;
	}

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Long ChildsiteId = new Long(1);
		// ����һ���յ�Command����
		TreePrivilegeEditForm form = new TreePrivilegeEditForm();
		// ��ȡ��ɫ����
		Long roleId = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "roleId", "0"));
		// �ж�Ҫ��Ȩ�Ľ�ɫ�Ƿ���ڣ��粻������ת����ʾҳ��
		Role role = roleManager.findRoleById(roleId);
		if (role == null) {
			throw new ApplicationException("exception.msg.dataDoesNotExist");
		}
		// �γɽ�ɫ����
		request.setAttribute("role", role);
		Map map = new HashMap();
		map.put("roleId", roleId);
		map.put("ChildsiteId", ChildsiteId);
		Map rolePrivileges = privilegeManager.findRolePrivilegeByCondition(map);
		// ����form����
		if (roleId != null) {
			form.setRoleId(roleId.toString());
			form.setName(role.getRoleName());
			form.setPrivilege(rolePrivileges);
		}
		return form;
	}

	/*
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#showForm(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.springframework.validation.BindException, java.util.Map)
	 */
	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors, Map controlModel)
			throws Exception {
		// �ж�Ҫ��Ȩ�Ľ�ɫ�Ƿ���ڣ��粻������ת����ʾҳ��
		Role role = roleManager.findRoleById(new Long(RequestUtils
				.getStringParameter(request, "roleId", "")));
		if (role == null) {
			return new ModelAndView("systemMessage",
					Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
							"public.msg.dataDoesNotExist", request));
		}
		// �γɽ�ɫ����
		request.setAttribute("role", role);
		return showForm(request, errors, getFormView(), controlModel);
	}

	/*
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	protected void onBind(HttpServletRequest request, Object o,
			BindException errors) throws Exception {
		// ��Ȩ�޽��а�
		TreePrivilegeEditForm form = (TreePrivilegeEditForm) o;
		// ��ȡȨ��
		String[] privileges = RequestUtils.getStringParameters(request,
				"privileges");
		HashMap map = new HashMap();
		String privilegeId = null;
		for (int i = 0; i < privileges.length; i++) {
			privilegeId = privileges[i];
			if (privilegeId != null)
				map.put(privilegeId, privilegeId);
		}
		form.setPrivilege(map);
	}

	/*
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		Long ChildsiteId = (Long) request.getSession().getAttribute(
				"ChildsiteId");
		TreePrivilegeEditForm form = (TreePrivilegeEditForm) o;
		// �ж�Ҫ��Ȩ�Ľ�ɫ�Ƿ���ڣ��粻������ת����ʾҳ��
		Role role = roleManager.findRoleById(new Long(RequestUtils
				.getStringParameter(request, "roleId", "")));
		if (role == null) {
			return new ModelAndView("systemMessage",
					Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
							"public.msg.dataDoesNotExist", request));
		}
		// �����������
		ArrayList privileges = new ArrayList();
		Iterator it = form.getPrivilege().keySet().iterator();
		String privilegeId = null;
		while (it.hasNext()) {
			privilegeId = (String) it.next();
			privileges.add(privilegeId);
		}
		// ��������
		privilegeManager.grantPrivilegeToRole(role.getRoleId(), privileges,
				new Long(1));
		// д������־
		Log log = new Log();
		log.setUser(this.getCurrentUserInfo(request).getUser());
		log.setChildsiteId(this.getCurrentUserInfo(request).getUser()
				.getChildsiteId());
		log.setLogObject("��ɫ����");
		log.setLogAction("��ɫ��ĿȨ������");
		log.setDetail("��������ĿȨ�޵Ľ�ɫ:" + role.getRoleName());
		logManager.insertLog(log);

		return new ModelAndView("redirect:" + form.getReturnUrl());
	}

	/**
	 * ����: ���� privilegeManager
	 */
	public void setPrivilegeManager(PrivilegeManager privilegeManager) {
		this.privilegeManager = privilegeManager;
	}

	/**
	 * ����: ���� roleManager
	 */
	public RoleManager getRoleManager() {
		return roleManager;
	}

	/**
	 * ����: ���� roleManager
	 */
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	/**
	 * ����: ���� treeManager
	 */
	public TreeManager getTreeManager() {
		return treeManager;
	}

	/**
	 * ����: ���� treeManager
	 */
	public void setTreeManager(TreeManager treeManager) {
		this.treeManager = treeManager;
	}

	/**
	 * ����: ���� privilegeManager
	 */
	public PrivilegeManager getPrivilegeManager() {
		return privilegeManager;
	}
}