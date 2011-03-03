/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 6, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.TreeWindow;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.service.PrivilegeService;

/**
 * @author xiacc
 * 
 * ������
 */
public class PrivilegeAdd extends ActionWindow {

	@Autowired
	private PrivilegeService privilegeService;

	private List<Privilege> privileges = (List<Privilege>) Executions.getCurrent().getArg().get(
			"privileges");

	private Textbox privName;

	private Textbox privCode;
	
	/**
	 * ���ؼ�
	 */
	protected Tree tree;

	/**
	 * ���ṹģ��
	 */
	protected TreeModel treeModel;
	
	private Bandbox bd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		tree.setTreeitemRenderer(new TreeitemRenderer() {
			public void render(Treeitem item, Object data) throws Exception {
				treeitemRenderer(item, data);
			}
		});
		treeModel = new SimpleTreeModel(createTree());
		binder.loadComponent(tree);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		Privilege parent = null;
		if (tree.getSelectedItem() != null) {
			parent = (Privilege) tree.getSelectedItem().getValue();
		}

		Privilege privilege = new Privilege();
		privilege.setPrivName(privName.getValue());
		privilege.setPrivCode(privCode.getValue());
		privilege.setParent(parent);

		privilegeService.save(privilege);

		((TreeWindow) this.getParent()).init();
	}
	
	public SimpleTreeNode createTree() {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Privilege.class);

		detachedCriteria.add(Restrictions.isNull("parent"));
		List<Privilege> roots = privilegeService.findByCriteria(detachedCriteria);
//		List<Privilege> roots = privileges;

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
				List<Privilege> childs = new ArrayList();
				if(child.getChildrens() != null){
					childs.addAll(child.getChildrens());
				}
				
				SimpleTreeNode node = appendChilden(child, childs);
				nodes.add(node);
			}
			return root;
		}
	}
	
	public void treeitemRenderer(Treeitem item, Object data) {
		if (data == null)
			return;
		Privilege p = null;

		SimpleTreeNode t = (SimpleTreeNode) data;
		p = (Privilege) t.getData();

		Treerow tr = new Treerow();
		item.setValue(p);
		if (p.getParent() == null) {
			item.setOpen(true);
		}
		tr.setParent(item);
		tr.appendChild(new Treecell(p.getPrivName()));		
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
	 * ���� privileges
	 */
	public List<Privilege> getPrivileges() {
		return privileges;
	}

	/**
	 * ���� privileges
	 */
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	/**
	 * ���� privName
	 */
	public Textbox getPrivName() {
		return privName;
	}

	/**
	 * ���� privName
	 */
	public void setPrivName(Textbox privName) {
		this.privName = privName;
	}

	/**
	 * ���� privCode
	 */
	public Textbox getPrivCode() {
		return privCode;
	}

	/**
	 * ���� privCode
	 */
	public void setPrivCode(Textbox privCode) {
		this.privCode = privCode;
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		return "����"+privName.getText();
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
	
	

}
