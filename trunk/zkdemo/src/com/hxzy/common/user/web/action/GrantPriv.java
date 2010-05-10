/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 8, 2010</p>
 * <p>更新：</p>
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
 * 描述：
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
	 * 返回 roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * 设置 roleService
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 返回 privilegeService
	 */
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	/**
	 * 设置 privilegeService
	 */
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	/**
	 * 返回 treeModel
	 */
	public TreeModel getTreeModel() {
		return treeModel;
	}

	/**
	 * 设置 treeModel
	 */
	public void setTreeModel(TreeModel treeModel) {
		this.treeModel = treeModel;
	}

	/**
	 * 返回 role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * 设置 role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * 返回 tree
	 */
	public Tree getTree() {
		return tree;
	}

	/**
	 * 设置 tree
	 */
	public void setTree(Tree tree) {
		this.tree = tree;
	}

	/**
	 * 返回 roleName
	 */
	public Label getRoleName() {
		return roleName;
	}

	/**
	 * 设置 roleName
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
		
		return "分配权限"+sb.toString()+"给"+role.getRoleName();
	}
}
