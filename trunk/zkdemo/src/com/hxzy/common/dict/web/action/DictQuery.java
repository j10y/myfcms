/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 12, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.dict.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import com.hxzy.base.web.window.Message;
import com.hxzy.base.web.window.TreeWindow;
import com.hxzy.common.dict.model.Dict;
import com.hxzy.common.dict.service.DictService;
import com.hxzy.common.user.web.action.PrivilegeQuery;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class DictQuery extends TreeWindow {

	@Autowired
	private DictService dictService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.TreeWindow#init()
	 */
	@Override
	public void init() {

		treeModel = new SimpleTreeModel(createTree());
		binder.loadComponent(tree);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.TreeWindow#treeitemRenderer(org.zkoss.zul.Treeitem,
	 *      java.lang.Object)
	 */
	@Override
	public void treeitemRenderer(Treeitem item, Object data) {

		if (data == null)
			return;
		Dict d = null;

		SimpleTreeNode t = (SimpleTreeNode) data;
		d = (Dict) t.getData();

		Treerow tr = new Treerow();
		item.setValue(d);
		if (d.getParent() == null) {
			item.setOpen(true);
		}
		tr.setParent(item);
		tr.appendChild(new Treecell(d.getName()));
		tr.appendChild(new Treecell(d.getCode()));
		tr.appendChild(new Treecell(d.getRemarks()));
	}

	public SimpleTreeNode createTree() {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dict.class);

		detachedCriteria.add(Restrictions.isNull("parent"));
		List<Dict> roots = dictService.findByCriteria(detachedCriteria);

		List<SimpleTreeNode> nodes = new ArrayList<SimpleTreeNode>();
		SimpleTreeNode root = appendChilden(null, roots);

		return root;
	}

	public SimpleTreeNode appendChilden(Dict d, List<Dict> childens) {
		if (childens == null) {
			return new SimpleTreeNode(d, childens);
		} else {
			List<SimpleTreeNode> nodes = new ArrayList<SimpleTreeNode>();
			SimpleTreeNode root = new SimpleTreeNode(d, nodes);

			for (Dict child : childens) {
				List<Dict> childs = new ArrayList();
				if (child.getChildrens() != null) {
					childs.addAll(child.getChildrens());
				}

				SimpleTreeNode node = appendChilden(child, childs);
				nodes.add(node);
			}
			return root;
		}
	}

	public void onAdd() {
		try {
			((Window) Executions.createComponents("/dict/dictAdd.zul", this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onEdit() {
		if (tree.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}

		Object o = tree.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("dict", o);

		try {
			((Window) Executions.createComponents("/dict/dictEdit.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onDelete() {
		if (tree.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}

		Set<Treeitem> items = tree.getSelectedItems();
		Set set = new HashSet();
		for (Treeitem item : items) {
			set.add(item.getValue());
		}

		Map map = new HashMap();
		map.put("dicts", set);
		try {
			((Window) Executions.createComponents("/dict/dictDelete.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
