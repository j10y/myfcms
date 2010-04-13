/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 12, 2010</p>
 * <p>更新：</p>
 */
package com.bdzb.oa.expert.web.aciton;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.filechooser.FileSystemView;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.bdzb.oa.expert.model.Expert;
import com.bdzb.oa.expert.service.ExpertService;
import com.hxzy.base.model.Column;
import com.hxzy.base.model.Excel;
import com.hxzy.base.model.Row;
import com.hxzy.base.model.Sheet;
import com.hxzy.base.util.Pagination;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.base.web.window.Message;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class ExpertQuery extends ListWindow {

	@Autowired
	private ExpertService expertService;
	
	

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ListWindow#onCreate()
	 */
	@Override
	public void onCreate() {		
		super.onCreate();
		
		listbox.addEventListener("onDoubleClick", new EventListener(){

			public void onEvent(Event arg0) throws Exception {
				onDetail();				
			}
			
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ListWindow#onFind()
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Expert.class);

		if (StringUtils.hasText(search.getValue())) {
			LogicalExpression l1 = Restrictions.or(Restrictions.like("name", search.getValue(),
					MatchMode.ANYWHERE), Restrictions.like("titles", search.getValue(),
					MatchMode.ANYWHERE));

			LogicalExpression l2 = Restrictions.or(Restrictions.like("department", search
					.getValue(), MatchMode.ANYWHERE), Restrictions.like("telephone", search
					.getValue(), MatchMode.ANYWHERE));

			LogicalExpression l3 = Restrictions.or(l1, l2);

			detachedCriteria.add(l3);
		}

		Pagination pagination = expertService.findPageByCriteria(detachedCriteria,
				pg.getPageSize(), pg.getActivePage() + 1);
		pg.setTotalSize(pagination.getTotalCount());
		this.list = pagination;
		binder.loadComponent(listbox);

	}

	public void onAdd() {
		try {
			((Window) Executions.createComponents("/expert/expertAdd.zul", this, null)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onEdit() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}

		Object o = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("expert", o);

		try {
			((Window) Executions.createComponents("/expert/expertEdit.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onDelete() {
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}

		Set<Listitem> items = listbox.getSelectedItems();
		Set set = new HashSet();
		for (Listitem item : items) {
			set.add(item.getValue());
		}

		Map map = new HashMap();
		map.put("experts", set);
		try {
			((Window) Executions.createComponents("/expert/expertDelete.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void onOutput(ForwardEvent event) throws Exception {
		Excel excel = new Excel();
		if (listbox.getSelectedItem() == null) {
			Messagebox.show("请选择要导出报表的内容！", "提示信息", Messagebox.OK, Messagebox.INFORMATION);
		} else {
			Date dat = new Date();
			// dat.setTime(dat.getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String timeStr = sdf.format(dat);
			FileSystemView fsv = FileSystemView.getFileSystemView();
			fsv.getHomeDirectory();
			File f = new File(fsv.getHomeDirectory() + "//" + timeStr + ""
					+ listbox.getSelectedItem().getLabel() + ".xls");
			Sheet sheet = new Sheet();
			listbox.getSelectedItem().getValue();
			System.out.println(listbox.getSelectedItem().getLabel());
			sheet.setName(listbox.getSelectedItem().getLabel());
			Set set = listbox.getSelectedItems();

			Row headR = new Row();

			Listhead head = listbox.getListhead();

			List<Listheader> headers = head.getChildren();

			for (Listheader header : headers) {
				Column headC = new Column();
				headC.setColumnLabel(header.getLabel());
				headR.appendColumn(headC);
			}

			sheet.appendRow(headR);

			for (Iterator iter = set.iterator(); iter.hasNext();) {
				Row row = new Row();
				Listitem listitem = (Listitem) iter.next();
				List columnList = new ArrayList();
				for (int i = 0; i < listitem.getChildren().size(); i++) {
					Column column = new Column();
					column.setColumnLabel(((Listcell) listitem.getChildren().get(i)).getLabel());
					column.setColumnNum(i);
					columnList.add(column);
				}
				row.setColumnList(columnList);
				sheet.appendRow(row);
			}
			List list = new ArrayList();
			list.add(sheet);
			new Excel().write(list, new FileOutputStream(f));
			Messagebox.show("导出excel成功", "提示信息", Messagebox.OK, Messagebox.INFORMATION);
		}
	}
	
	public void onDetail(){
		if (listbox.getSelectedItem() == null) {
			Message.showInfo("请至少选择一个数据!");
			return;
		}

		Object o = listbox.getSelectedItem().getValue();

		Map map = new HashMap();
		map.put("expert", o);

		try {
			((Window) Executions.createComponents("/expert/expertDetail.zul", this, map)).doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
