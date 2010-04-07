/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 7, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.window;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Window;

/**
 * @author xiacc
 * 
 * ������
 */
public abstract class TreeWindow extends Window implements AfterCompose {

	protected Tree tree;

	protected TreeModel treeModel;

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
				treeitemRenderer(item, data);
			}
		});

		init();
	}

	/**
	 * ���������ڵ���Ⱦ��
	 * 
	 * @param item
	 * @param data
	 */
	public abstract void treeitemRenderer(Treeitem item, Object data);

	/**
	 * ��������ʼ������
	 */
	public abstract void init();

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
