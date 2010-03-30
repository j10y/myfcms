/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 30, 2010</p>
 * <p>更新：</p>
 */
package web;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;

import com.hxzy.base.util.Pagination;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class UserList implements Composer {

	protected Listbox listbox;

	private List list;

	// private BindingListModelList list;

	@Autowired
	private UserService userService;
	//
	private Textbox search;

	protected Paging pg;

	protected AnnotateDataBinder binder;

	public void doAfterCompose(Component component) throws Exception {
		// 将当前对象的变量和参数对象进行绑定
		Components.wireVariables(component, this);

		// 转发一个当前的事件方法到所对应名称的Component（）
		Components.addForwards(component, this);

		// 注册一个监听到当前的对象
		Events.addEventListeners(component, this);

	}

	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);

		if (StringUtils.hasText(search.getValue())) {
			// detachedCriteria.add(Restrictions.or(Restrictions.like("name",
			// search.getValue(),
			// MatchMode.ANYWHERE), Restrictions.like("code", search.getValue(),
			// MatchMode.ANYWHERE)));
			//
			// detachedCriteria.add(Restrictions.or(Restrictions.like("password",
			// search.getValue(),
			// MatchMode.ANYWHERE), Restrictions.like("code", search.getValue(),
			// MatchMode.ANYWHERE)));
			LogicalExpression l1 = Restrictions.or(Restrictions.like("name", search.getValue(),
					MatchMode.ANYWHERE), Restrictions.like("code", search.getValue(),
					MatchMode.ANYWHERE));
			LogicalExpression l2 = Restrictions.or(Restrictions.like("password", search.getValue(),
					MatchMode.ANYWHERE), Restrictions.like("code", search.getValue(),
					MatchMode.ANYWHERE));
			LogicalExpression l3 = Restrictions.or(l1, l2);

			detachedCriteria.add(l3);

		}
		Pagination pagination = userService.findPageByCriteria(detachedCriteria, 2, 1);

		this.list = new BindingListModelList(pagination.getList(), true);
		binder.loadComponent(listbox);

	}

	/**
	 * 返回 listbox
	 */
	public Listbox getListbox() {
		return listbox;
	}

	/**
	 * 设置 listbox
	 */
	public void setListbox(Listbox listbox) {
		this.listbox = listbox;
	}

	/**
	 * 返回 userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * 设置 userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 返回 search
	 */
	public Textbox getSearch() {
		return search;
	}

	/**
	 * 设置 search
	 */
	public void setSearch(Textbox search) {
		this.search = search;
	}

	/**
	 * 返回 pg
	 */
	public Paging getPg() {
		return pg;
	}

	/**
	 * 设置 pg
	 */
	public void setPg(Paging pg) {
		this.pg = pg;
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

	/**
	 * 返回 list
	 */
	public List getList() {
		return list;
	}

	/**
	 * 设置 list
	 */
	public void setList(List list) {
		this.list = list;
	}

}
