/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 30, 2010</p>
 * <p>���£�</p>
 */
package test;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Window;

/**
 * @author xiacc
 * 
 * ������
 */
public class UserList extends Window implements AfterCompose {

	private Listbox listbox;

	private List list;

	private AnnotateDataBinder binder;

	private Paging pg;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.ext.AfterCompose#afterCompose()
	 */
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}

	public void onCreate() {
		binder = (AnnotateDataBinder) this.getVariable("binder", true);
		
		pg.addEventListener(org.zkoss.zul.event.ZulEvents.ON_PAGING, new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				find();
			}

		});
		
		find();
	}

	public void find() {
		List users = new ArrayList();

		for (int i = 0; i < pg.getPageSize(); i++) {
			User user = new User();
			user.setId(new Long(i + pg.getActivePage() + 1));
			user.setUsername("user" + pg.getActivePage() + 1);

			users.add(user);

		}
		
		list = users;
		pg.setTotalSize(1000);
		binder.loadComponent(listbox);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.util.Composer#doAfterCompose(org.zkoss.zk.ui.Component)
	 */
	public void doAfterCompose(Component comp) throws Exception {
		Components.wireVariables(comp, this);
		Components.addForwards(comp, this);

	}

	class User {
		private Long id;

		private String username;

		/**
		 * ���� id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * ���� id
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * ���� username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * ���� username
		 */
		public void setUsername(String username) {
			this.username = username;
		}

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
