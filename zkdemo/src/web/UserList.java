/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 30, 2010</p>
 * <p>���£�</p>
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
 * ������
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
		// ����ǰ����ı����Ͳ���������а�
		Components.wireVariables(component, this);

		// ת��һ����ǰ���¼�����������Ӧ���Ƶ�Component����
		Components.addForwards(component, this);

		// ע��һ����������ǰ�Ķ���
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
	 * ���� listbox
	 */
	public Listbox getListbox() {
		return listbox;
	}

	/**
	 * ���� listbox
	 */
	public void setListbox(Listbox listbox) {
		this.listbox = listbox;
	}

	/**
	 * ���� userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * ���� userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * ���� search
	 */
	public Textbox getSearch() {
		return search;
	}

	/**
	 * ���� search
	 */
	public void setSearch(Textbox search) {
		this.search = search;
	}

	/**
	 * ���� pg
	 */
	public Paging getPg() {
		return pg;
	}

	/**
	 * ���� pg
	 */
	public void setPg(Paging pg) {
		this.pg = pg;
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

	/**
	 * ���� list
	 */
	public List getList() {
		return list;
	}

	/**
	 * ���� list
	 */
	public void setList(List list) {
		this.list = list;
	}

}
