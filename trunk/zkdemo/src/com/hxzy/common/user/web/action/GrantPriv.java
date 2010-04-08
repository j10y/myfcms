/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 8, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.PrivilegeService;
import com.hxzy.common.user.service.RoleService;

/**
 * @author xiacc
 * 
 * ������
 */
public class GrantPriv extends ActionWindow {

	@Autowired
	private RoleService roleService;

	@Autowired
	private PrivilegeService privilegeService;

	private TreeModel treeModel;

	private Role role = (Role) Executions.getCurrent().getArg().get("role");

	private Label roleName;

	private Tree tree;

	private AnnotateDataBinder binder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		treeModel = new SimpleTreeModel(createTree());
		roleName.setValue(role.getRoleName());

		binder = (AnnotateDataBinder) this.getVariable("binder", true);

		role = roleService.loadById(role.getId());
		final Set<Privilege> privs = role.getPrivileges();

		tree.setTreeitemRenderer(new TreeitemRenderer() {

			public void render(Treeitem item, Object data) throws Exception {
				if (data == null)
					return;
				Privilege p = null;

				SimpleTreeNode t = (SimpleTreeNode) data;
				p = (Privilege) t.getData();

				Treerow tr = new Treerow();
				item.setValue(p);

				item.setOpen(true);

				if (privs.contains(p)) {
					item.setCheckable(true);
				}

				tr.setParent(item);
				tr.appendChild(new Treecell(p.getPrivName()));
				tr.appendChild(new Treecell(p.getPrivCode()));

			}

		});

		binder.loadComponent(tree);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		Set<Privilege> privs = new HashSet<Privilege>();

		Set<Treeitem> items = tree.getSelectedItems();

		for (Treeitem item : items) {
			privs.add((Privilege) item.getValue());
		}

		role.setPrivileges(privs);
		roleService.update(role);

		this.onClose();

	}

	public SimpleTreeNode createTree() {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Privilege.class);

		detachedCriteria.add(Restrictions.isNull("parent"));
		List<Privilege> roots = privilegeService.findByCriteria(detachedCriteria);

		List<SimpleTreeNode> nodes = new ArrayList<SimpleTreeNode>();
		SimpleTreeNode root = appendChilden(null, roots);

		return root;
	}

	public SimpleTreeNode appendChilden(Privilege p, List<Privilege> childens) {
		if (childens == null) {
			return new SimpleTreeNode(p, childens);
		} else {
			List<SimpleTreeNode> nodes = new ArrayList<SimpleTreeNode>();
			SimpleTreeNode root = new SimpleTreeNode(p, nodes);

			for (Privilege child : childens) {
				SimpleTreeNode node = appendChilden(child, new ArrayList(child.getChildrens()));
				nodes.add(node);
			}
			return root;
		}
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

	/**
	 * ���� privilegeService
	 */
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	/**
	 * ���� privilegeService
	 */
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	/**
	 * ���� treeModel
	 */
	public TreeModel getTreeModel() {
		return treeModel;
	}

	/**
	 * ���� treeModel
	 */
	public void setTreeModel(TreeModel treeModel) {
		this.treeModel = treeModel;
	}

	/**
	 * ���� role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * ���� role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * ���� tree
	 */
	public Tree getTree() {
		return tree;
	}

	/**
	 * ���� tree
	 */
	public void setTree(Tree tree) {
		this.tree = tree;
	}

	/**
	 * ���� roleName
	 */
	public Label getRoleName() {
		return roleName;
	}

	/**
	 * ���� roleName
	 */
	public void setRoleName(Label roleName) {
		this.roleName = roleName;
	}

	/**
	 * ���� binder
	 */
	public AnnotateDataBinder getBinder() {
		return binder;
	}

	/**
	 * ���� binder
	 */
	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}

}
