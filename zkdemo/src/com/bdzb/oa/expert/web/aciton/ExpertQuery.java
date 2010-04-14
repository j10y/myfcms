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
import java.io.IOException;
import java.io.InputStream;
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

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
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
import com.hxzy.common.dict.model.Dict;
import com.hxzy.common.dict.service.DictService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class ExpertQuery extends ListWindow {

	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private DictService dictService;

	private Fileupload fileupload;
	
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

		fileupload.addEventListener("onUpload", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				onUpload((UploadEvent) arg0);
			}

		});
		
		tree.setTreeitemRenderer(new TreeitemRenderer() {
			public void render(Treeitem item, Object data) throws Exception {
				treeitemRenderer(item, data);
			}
		});
		
		tree.addEventListener("onClick", new EventListener(){

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
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Expert.class);

		if (StringUtils.hasText(search.getValue())) {
			LogicalExpression l1 = Restrictions.or(Restrictions.like("name", search.getValue(),
					MatchMode.ANYWHERE), Restrictions.like("titles", search.getValue(),
					MatchMode.ANYWHERE));

			LogicalExpression l2 = Restrictions.or(l1, Restrictions.like("telephone", search
					.getValue(), MatchMode.ANYWHERE));
			
			LogicalExpression l3 = Restrictions.or(l2, Restrictions.like("department", search
					.getValue(), MatchMode.ANYWHERE));
			
			LogicalExpression l4 = Restrictions.or(l3, Restrictions.like("remarks", search
					.getValue(), MatchMode.ANYWHERE));		

			detachedCriteria.add(l4);
		}
		
		Treeitem item = tree.getSelectedItem();
		if(item != null){
			Dict d = (Dict) item.getValue();
			
			if(!d.getCode().equals("industryCategory")){
				detachedCriteria.add(Restrictions.eq("category", d));
			}
		}

		Pagination pagination = expertService.findPageByCriteria(detachedCriteria,
				pg.getPageSize(), pg.getActivePage() + 1);
		pg.setTotalSize(pagination.getTotalCount());
		this.list = pagination;
		binder.loadComponent(listbox);

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

		detachedCriteria.add(Restrictions.eq("code", "industryCategory"));
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

	public void onUpload(UploadEvent event) {
		Media media = event.getMedia();
		InputStream is = null;

		if (media.getFormat().equals("xls")) {
			try {
				is = media.getStreamData();
				Workbook wbk = Workbook.getWorkbook(is);

				jxl.Sheet rs = wbk.getSheet(0);

				if (!checkExcel(rs)) {
					return;
				}

				for (int i = 1; i < rs.getRows(); i++) {
					Cell[] cells = rs.getRow(i);

					Expert expert = new Expert();
					expert.setName(cells[1].getContents());
					expert.setTitles(cells[2].getContents());
					expert.setDepartment(cells[3].getContents());
					expert.setTelephone(cells[4].getContents());
					expert.setRemarks(cells[5].getContents());
					Dict dict = new Dict();
					//dict.setId(24L);
					expert.setCategory(dict);
					expertService.save(expert);
				}
				this.onFind();

			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} else {
			Message.showError("文件类型错误，请选择Excel类型！");
		}

	}

	private boolean checkExcel(jxl.Sheet rs) {
		int columns = rs.getColumns();
		if (columns != 6) {
			Message.showError("Excel列数错误，请按标准格式上传！");
			return false;
		}

		Cell[] cells = rs.getRow(0);
		if (!cells[0].getContents().equals("类别") || !cells[1].getContents().equals("姓名")
				|| !cells[2].getContents().equals("职称") || !cells[3].getContents().equals("工作单位")
				|| !cells[4].getContents().equals("联系电话") || !cells[5].getContents().equals("备注")) {
			Message.showError("Excel格式错误，请按标准格式上传！");
			return false;
		}
		return true;

	}

	public void onDetail() {
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
