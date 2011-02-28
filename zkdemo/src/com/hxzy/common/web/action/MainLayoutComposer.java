/* MainLayoutComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 12, 2008 3:10:06 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 3.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */
package com.hxzy.common.web.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.BookmarkEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * @author jumperchen
 * 
 */
public class MainLayoutComposer extends GenericForwardComposer implements MainLayoutAPI {
	private static final Log log = Log.lookup(MainLayoutComposer.class);

	Textbox searchBox;

	Listbox itemList;

	Include xcontents;

	Div header;

	Button _selected;

	public MainLayoutComposer() {
		

	}

	public void onLogOut(ForwardEvent event) {
		try {
			((Window) Executions.createComponents("/logOut.zul",null, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Map getCategoryMap() {
		return MainWebAppInit.getCateMap();
	}

	public void onCategorySelect(ForwardEvent event) {
		Button btn = (Button) event.getOrigin().getTarget();
		Listitem item = null;
		if (_selected != btn) {
			_selected = btn;
		} else {
			item = itemList.getSelectedItem();
		}
		String href = getCategory(_selected.getId()).getHref();
		if (href != null) {
			Executions.getCurrent().sendRedirect(href);
		} else {
			itemList.setModel(getSelectedModel());
			if (item != null) {
				itemList.renderAll();
				((Listitem) itemList.getFellow(item.getId())).setSelected(true);
			}
		}
	}

	public void onSelect$itemList(SelectEvent event) {
		Listitem item = itemList.getSelectedItem();

		if (item != null) {

			// sometimes the item is unloaded.
			if (!item.isLoaded()) {
				itemList.renderItem(item);
			}

			setSelectedCategory(item);
			xcontents.setSrc(((MainItem) item.getValue()).getFile());
		}
	}

	public void onMainCreate(Event event) {
		final Execution exec = Executions.getCurrent();
		final String id = exec.getParameter("id");
		Listitem item = null;
		if (id != null) {
			try {
				final LinkedList list = new LinkedList();
				final MainItem[] items = getItems();
				for (int i = 0; i < items.length; i++) {
					if (items[i].getId().equals(id))
						list.add(items[i]);
				}
				if (!list.isEmpty()) {
					itemList.setModel(new ListModelList(list));
					itemList.renderAll();
					item = (Listitem) self.getFellow(id);
					setSelectedCategory(item);
				}
			} catch (ComponentNotFoundException ex) { // ignore
			}
		}

		if (item == null) {
			item = (Listitem) self.getFellow("expertQuery");
			setSelectedCategory(item);
		}
		xcontents.setSrc(((MainItem) item.getValue()).getFile());

		itemList.selectItem(item);
	}

	private void setSelectedCategory(Listitem item) {
		MainItem di = (MainItem) item.getValue();
		_selected = (Button) self.getFellow(di.getCateId());
		String deselect = _selected != null ? "jq('#" + _selected.getUuid()
				+ "').addClass('demo-seld').siblings().removeClass('demo-seld');" : "";
		Clients.evalJavaScript(deselect);
		item.getDesktop().setBookmark(item.getId());
	}

	private MainItem[] getItems() {
		LinkedList items = new LinkedList();
		Category[] categories = getCategories();
		for (int i = 0; i < categories.length; i++) {
			items.addAll(categories[i].getItems());
		}
		return (MainItem[]) items.toArray(new MainItem[] {});
	}

	public Category[] getCategories() {
		return (Category[]) getCategoryMap().values().toArray(new Category[] {});
	}

	public ListitemRenderer getItemRenderer() {
		return _defRend;
	}

	private static final ListitemRenderer _defRend = new ItemRender();

	private static class ItemRender implements ListitemRenderer, java.io.Serializable {
		public void render(Listitem item, Object data) {
			MainItem di = (MainItem) data;
			Listcell lc = new Listcell();
			item.setValue(di);
			lc.setHeight("30px");
			lc.setImage(di.getIcon());
			item.setId(di.getId());
			lc.setLabel(di.getLabel());
			lc.setParent(item);
		}
	};

	private Category getCategory(String cateId) {
		return (Category) getCategoryMap().get(cateId);
	}

	public ListModel getSelectedModel() {
		Category cate = _selected == null ? getCategories()[0] : getCategory(_selected.getId());
		return new ListModelList(cate.getItems());
	}

	// Composer Implementation
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		Events.postEvent("onMainCreate", comp, null);
	}
}
