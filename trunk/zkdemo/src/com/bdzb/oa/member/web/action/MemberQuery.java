package com.bdzb.oa.member.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import com.hxzy.base.util.Pagination;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.base.web.window.Message;
import com.hxzy.common.dict.model.Dict;
import com.hxzy.common.dict.service.DictService;
import com.bdzb.oa.member.model.Member;
import com.bdzb.oa.member.service.MemberService;

/**
 * @author xiacc
 * @version 1.0
 * @since 1.0
 * @description 会员
 */

public class MemberQuery extends ListWindow {

	@Autowired
	private MemberService memberService;

	@Autowired
	private DictService dictService;

	/**
	 * 树控件
	 */
	private Tree tree;

	/**
	 * 树结构模型
	 */
	private TreeModel treeModel;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ListWindow#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();

		tree.setTreeitemRenderer(new TreeitemRenderer() {
			public void render(Treeitem item, Object data) throws Exception {
				treeitemRenderer(item, data);
			}
		});

		tree.addEventListener("onClick", new EventListener() {

			public void onEvent(Event evt) throws Exception {
				onFind();
			}

		});

		treeModel = new SimpleTreeModel(createTree());

		binder.loadComponent(tree);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ListWindow#onFind()
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Member.class);

		if (StringUtils.hasText(search.getValue())) {
			LogicalExpression l1 = Restrictions.or(Restrictions.like("companyName", search
					.getValue(), MatchMode.ANYWHERE), Restrictions.like("s.name",
					search.getValue(), MatchMode.ANYWHERE));
			LogicalExpression l2 = Restrictions.or(l1, Restrictions.like("contacts", search
					.getValue(), MatchMode.ANYWHERE));

			detachedCriteria.createAlias("category", "s").add(l2);
		}

		Treeitem item = tree.getSelectedItem();
		if (item != null) {
			Dict d = (Dict) item.getValue();

			if (!d.getCode().equals("productCategory")) {
				detachedCriteria.add(Restrictions.eq("category", d));
			}
		}

		Pagination pagination = memberService.findPageByCriteria(detachedCriteria,
				pg.getPageSize(), pg.getActivePage() + 1);
		pg.setTotalSize(pagination.getTotalCount());
		this.list = pagination;
		binder.loadComponent(listbox);

	}

	public void onDelete() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("");
			return;
		}

		Set<Listitem> items = listbox.getSelectedItems();
		Set members = new HashSet();
		for (Listitem item : items) {
			members.add(item.getValue());
		}

		Map map = new HashMap();
		map.put("members", members);
		try {
			((Window) Executions.createComponents("/member/memberDelete.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onEdit() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("");
			return;
		}

		Object member = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("member", member);

		try {
			((Window) Executions.createComponents("/member/memberEdit.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onAdd() {
		try {
			((Window) Executions.createComponents("/member/memberAdd.zul", this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onDetail() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}

		Object o = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("member", o);

		try {
			((Window) Executions.createComponents("/member/memberDetail.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

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
	}

	public SimpleTreeNode createTree() {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dict.class);

		detachedCriteria.add(Restrictions.eq("code", "productCategory"));
		detachedCriteria.addOrder(Order.asc("id"));
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

}
