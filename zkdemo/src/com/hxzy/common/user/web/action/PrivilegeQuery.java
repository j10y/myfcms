/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 6, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
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

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.service.PrivilegeService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class PrivilegeQuery extends ActionWindow {

	@Autowired
	private PrivilegeService privilegeService;

	private Tree tree;

	private TreeModel treeModel;

	public void onCreate() {
		binder = (AnnotateDataBinder) this.getVariable("binder", true);

		tree.setTreeitemRenderer(new TreeitemRenderer() {

			public void render(Treeitem item, Object data) throws Exception {
				if (data == null)
					return;
				Privilege p = null;

				
				while(data instanceof SimpleTreeNode){
					data = ((SimpleTreeNode) data).getData();
				}
				p = (Privilege) data;

				Treerow tr = new Treerow();
				tr.setParent(item);
				tr.appendChild(new Treecell(p.getPrivName()));
				tr.appendChild(new Treecell(p.getPrivCode()));
			}

		});

		treeModel = new SimpleTreeModel(createTree());
		tree.setModel(treeModel);
		binder.loadComponent(tree);
	}

	public SimpleTreeNode createTree() {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Privilege.class);

		detachedCriteria.add(Restrictions.isNull("parent"));

		List<Privilege> roots = privilegeService.findByCriteria(detachedCriteria);

		List<SimpleTreeNode> nodes = new ArrayList<SimpleTreeNode>();

		SimpleTreeNode root = new SimpleTreeNode(null, nodes);

		for (Privilege p : roots) {
			SimpleTreeNode node = appendChilden(p);
			nodes.add(node);
		}
		return root;
	}

	public SimpleTreeNode appendChilden(Privilege p) {

		if (p.getChildrens() == null) {
			return new SimpleTreeNode(p, null);
		} else {
			List<SimpleTreeNode> nodes = new ArrayList<SimpleTreeNode>();
			SimpleTreeNode root = new SimpleTreeNode(p, nodes);

			for (Privilege child : p.getChildrens()) {
				SimpleTreeNode node = new SimpleTreeNode(appendChilden(child), new ArrayList(child
						.getChildrens()));
				nodes.add(node);
			}
			return root;
		}
	}
	
	public void onAdd(){
		try {
			((Window)Executions.createComponents("privilegeAdd.zul", PrivilegeQuery.this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		// TODO Auto-generated method stub

	}

}
