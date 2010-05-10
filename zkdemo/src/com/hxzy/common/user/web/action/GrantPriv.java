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
import org.zkoss.zul.Label;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.PrivilegeService;
import com.hxzy.common.user.service.RoleService;

// http://zh.zkoss.org/forum/listDiscussion/2
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		treeModel = new SimpleTreeModel(createTree());
		roleName.setValue(role.getRoleName());

		role = roleService.loadById(role.getId());

		tree.setTreeitemRenderer(new TreeitemRenderer() {

			public void render(Treeitem item, Object data) throws Exception {
				if (data == null)
					return;

				SimpleTreeNode t = (SimpleTreeNode) data;
				Privilege p = (Privilege) t.getData();

				Treerow tr = new Treerow();
				item.setValue(p);

				if (role.getPrivileges().contains(p)) {
					item.setSelected(true);
				}

				item.setOpen(true);

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
			Privilege p =(Privilege) item.getValue();
			privs.add(p);
		}

		role.setPrivileges(privs);
		roleService.update(role);		
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

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		Set<Treeitem> items = tree.getSelectedItems();
		
		StringBuilder sb = new StringBuilder();
		
		for (Treeitem item : items) {
			Privilege p =(Privilege) item.getValue();
			sb.append(p.getPrivName());
			sb.append(",");
		}
		
		return "����Ȩ��"+sb.toString()+"��"+role.getRoleName();
	}
}
