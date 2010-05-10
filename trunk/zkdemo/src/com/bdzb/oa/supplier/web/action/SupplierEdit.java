/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 2, 2010</p>
 * <p>更新：</p>
 */
package com.bdzb.oa.supplier.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Textbox;

import com.bdzb.oa.supplier.model.Supplier;
import com.bdzb.oa.supplier.service.SupplierService;
import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.dict.model.Dict;
import com.hxzy.common.dict.service.DictService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class SupplierEdit extends ActionWindow {

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private DictService dictService;

	private Combobox combobox;

	private Textbox compnayName;

	private Textbox legalPerson;

	private Textbox reference;

	private Textbox address;

	private Textbox contacts;

	private Textbox remarks;

	private List list;

	private Supplier supplier = (Supplier) Executions.getCurrent().getArg().get("supplier");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {

		compnayName.setValue(supplier.getCompanyName());
		legalPerson.setValue(supplier.getLegalPerson());
		reference.setValue(supplier.getReference());
		address.setValue(supplier.getAddress());
		contacts.setValue(supplier.getContacts());
		remarks.setValue(supplier.getRemarks());

		list = dictService.findByProperty("parent.code", "productCategory");
		binder.loadComponent(combobox);

		supplier = supplierService.loadById(supplier.getId());

		combobox.setItemRenderer(new ComboitemRenderer() {

			public void render(Comboitem item, Object o) throws Exception {
				Dict d = (Dict) o;
				item.setValue(d);
				item.setLabel(d.getName());
				Dict category = supplier.getCategory();

				if (d.equals(category)) {
					combobox.setSelectedItem(item);
				}

			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		Dict category = null;
		if (combobox.getSelectedItem() != null) {
			category = (Dict) combobox.getSelectedItem().getValue();
		}

		supplier.setCompanyName(compnayName.getValue());
		supplier.setCategory(category);
		supplier.setLegalPerson(legalPerson.getValue());
		supplier.setReference(reference.getValue());
		supplier.setContacts(contacts.getValue());
		supplier.setAddress(address.getValue());
		supplier.setRemarks(remarks.getValue());

		supplierService.update(supplier);

		((ListWindow) this.getParent()).onFind();
	}

	/**
	 * 返回 supplierService
	 */
	public SupplierService getSupplierService() {
		return supplierService;
	}

	/**
	 * 设置 supplierService
	 */
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	/**
	 * 返回 dictService
	 */
	public DictService getDictService() {
		return dictService;
	}

	/**
	 * 设置 dictService
	 */
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}

	/**
	 * 返回 combobox
	 */
	public Combobox getCombobox() {
		return combobox;
	}

	/**
	 * 设置 combobox
	 */
	public void setCombobox(Combobox combobox) {
		this.combobox = combobox;
	}

	
	/**
	 * 返回 remarks
	 */
	public Textbox getRemarks() {
		return remarks;
	}

	/**
	 * 设置 remarks
	 */
	public void setRemarks(Textbox remarks) {
		this.remarks = remarks;
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

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		return "修改"+supplier.getCompanyName();
	}

}
