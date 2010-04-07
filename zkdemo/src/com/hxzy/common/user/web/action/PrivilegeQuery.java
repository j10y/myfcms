/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 6, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import com.hxzy.base.web.window.Message;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.service.PrivilegeService;

/**
 * @author xiacc
 * 
 * ������
 */
public class PrivilegeQuery extends Window implements AfterCompose {

	@Autowired
	private PrivilegeService privilegeService;

	private Tree tree;

	private TreeModel treeModel;

	/**
	 * ���������ݰ���
	 */
	protected AnnotateDataBinder binder;

	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}

	public void onCreate() {
		binder = (AnnotateDataBinder) this.getVariable("binder", true);

		tree.setTreeitemRenderer(new TreeitemRenderer() {

			public void render(Treeitem item, Object data) throws Exception {
				if (data == null)
					return;
				Privilege p = null;

				SimpleTreeNode t = (SimpleTreeNode) data;
				p = (Privilege) t.getData();

				Treerow tr = new Treerow();
				item.setValue(p);
				tr.setParent(item);
				tr.appendChild(new Treecell(p.getPrivName()));
				tr.appendChild(new Treecell(p.getPrivCode()));
			}

		});
		init();
	}

	public void init() {
		treeModel = new SimpleTreeModel(createTree());
		tree.setModel(treeModel);
		binder.loadComponent(tree);
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

	public void onAdd() {
		List privileges = privilegeService.loadAll();

		Map map = new HashMap();
		map.put("privileges", privileges);

		try {
			((Window) Executions.createComponents("privilegeAdd.zul", PrivilegeQuery.this, map))
					.doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onDelete() {
		if (tree.getSelectedItem() == null) {
			Message.showInfo("������ѡ��һ������!");
			return;
		}

		Set<Treeitem> items = tree.getSelectedItems();
		Set privileges = new HashSet();
		for (Treeitem item : items) {
			privileges.add(item.getValue());
		}

		Map map = new HashMap();
		map.put("privileges", privileges);
		try {
			((Window) Executions.createComponents("privilegeDelete.zul", PrivilegeQuery.this, map))
					.doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onEdit() {
		if (tree.getSelectedItem() == null) {
			Message.showInfo("������ѡ��һ������!");
			return;
		}

		Object privilege = tree.getSelectedItem().getValue();
		List privileges = privilegeService.loadAll();
		
		Map map = new HashMap();
		map.put("privilege", privilege);
		map.put("privileges", privileges);


		try {
			((Window) Executions.createComponents("privilegeEdit.zul", PrivilegeQuery.this, map))
					.doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
