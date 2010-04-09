/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 7, 2010</p>
 * <p>更新：</p>
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
 * 描述：树形窗口基类，所有的树形窗口继承该类
 */
public abstract class TreeWindow extends Window implements AfterCompose, Authenticatable,
		Authorizable {

	/**
	 * 树控件
	 */
	protected Tree tree;

	/**
	 * 树结构模型
	 */
	protected TreeModel treeModel;

	/**
	 * 描述：数据绑定器
	 */
	protected AnnotateDataBinder binder;

	/**
	 * 描述: 功能代码
	 */
	private String functionCode;

	/**
	 * 描述: 是否需要身份验证
	 */
	private String needAuthentication;

	/**
	 * 描述: 是否需要授权
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
	 * 描述：树节点渲染器
	 * 
	 * @param item
	 * @param data
	 */
	public abstract void treeitemRenderer(Treeitem item, Object data);

	/**
	 * 描述：初始化函数
	 */
	public abstract void init();

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
	 * 返回 binder
	 */
	public AnnotateDataBinder getBinder() {
		return binder;
	}

	/**
	 * 设置 binder
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
	 * 返回 functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * 设置 functionCode
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * 返回 needAuthentication
	 */
	public String getNeedAuthentication() {
		return needAuthentication;
	}

	/**
	 * 设置 needAuthentication
	 */
	public void setNeedAuthentication(String needAuthentication) {
		this.needAuthentication = needAuthentication;
	}

	/**
	 * 返回 needAuthorization
	 */
	public String getNeedAuthorization() {
		return needAuthorization;
	}

	/**
	 * 设置 needAuthorization
	 */
	public void setNeedAuthorization(String needAuthorization) {
		this.needAuthorization = needAuthorization;
	}

}
