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

import com.hxzy.base.web.intercepter.Authenticatable;
import com.hxzy.base.web.intercepter.Authorizable;

/**
 * @author xiacc
 * 
 * ���������δ��ڻ��࣬���е����δ��ڼ̳и���
 */
public abstract class TreeWindow extends Window implements AfterCompose, Authenticatable,
		Authorizable {

	/**
	 * ���ؼ�
	 */
	protected Tree tree;

	/**
	 * ���ṹģ��
	 */
	protected TreeModel treeModel;

	/**
	 * ���������ݰ���
	 */
	protected AnnotateDataBinder binder;

	/**
	 * ����: ���ܴ���
	 */
	private String functionCode;

	/**
	 * ����: �Ƿ���Ҫ�����֤
	 */
	private String needAuthentication;

	/**
	 * ����: �Ƿ���Ҫ��Ȩ
	 */
	private String needAuthorization;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.intercepter.Authenticatable#needAuthentication()
	 */
	public boolean needAuthentication() {
		if ("false".equals(needAuthentication))
			return false;
		else
			return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.intercepter.Authorizable#needAuthorization()
	 */
	public boolean needAuthorization() {
		if ("false".equals(needAuthorization))
			return false;
		else
			return true;
	}

	/**
	 * ���� functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * ���� functionCode
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * ���� needAuthentication
	 */
	public String getNeedAuthentication() {
		return needAuthentication;
	}

	/**
	 * ���� needAuthentication
	 */
	public void setNeedAuthentication(String needAuthentication) {
		this.needAuthentication = needAuthentication;
	}

	/**
	 * ���� needAuthorization
	 */
	public String getNeedAuthorization() {
		return needAuthorization;
	}

	/**
	 * ���� needAuthorization
	 */
	public void setNeedAuthorization(String needAuthorization) {
		this.needAuthorization = needAuthorization;
	}

}
