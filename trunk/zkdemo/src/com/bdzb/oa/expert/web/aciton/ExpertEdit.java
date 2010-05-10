/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 2, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.expert.web.aciton;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Textbox;

import com.bdzb.oa.expert.model.Expert;
import com.bdzb.oa.expert.service.ExpertService;
import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.dict.model.Dict;
import com.hxzy.common.dict.service.DictService;

/**
 * @author xiacc
 * 
 * ������
 */
public class ExpertEdit extends ActionWindow {

	@Autowired
	private ExpertService expertService;

	@Autowired
	private DictService dictService;

	private Combobox combobox;

	private Textbox name;

	private Textbox titles;

	private Textbox department;

	private Textbox telephone;

	private Textbox remarks;

	private List list;

	private Expert expert = (Expert) Executions.getCurrent().getArg().get("expert");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		
		name.setValue(expert.getName());
		titles.setValue(expert.getTitles());
		department.setValue(expert.getDepartment());
		telephone.setValue(expert.getTelephone());
		remarks.setValue(expert.getRemarks());
		
		list = dictService.findByProperty("parent.code", "industryCategory");
		binder.loadComponent(combobox);
		
		expert = expertService.loadById(expert.getId());

		combobox.setItemRenderer(new ComboitemRenderer() {

			public void render(Comboitem item, Object o) throws Exception {
				Dict d = (Dict) o;
				item.setValue(d);
				item.setLabel(d.getName());
				Dict category = expert.getCategory();
				
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

		expert.setName(name.getValue());
		expert.setCategory(category);
		expert.setTitles(titles.getValue());
		expert.setTelephone(telephone.getValue());
		expert.setRemarks(remarks.getValue());
		expert.setDepartment(department.getValue());

		expertService.update(expert);

		((ListWindow) this.getParent()).onFind();
	}

	/**
	 * ���� expertService
	 */
	public ExpertService getExpertService() {
		return expertService;
	}

	/**
	 * ���� expertService
	 */
	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}

	/**
	 * ���� dictService
	 */
	public DictService getDictService() {
		return dictService;
	}

	/**
	 * ���� dictService
	 */
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}

	/**
	 * ���� combobox
	 */
	public Combobox getCombobox() {
		return combobox;
	}

	/**
	 * ���� combobox
	 */
	public void setCombobox(Combobox combobox) {
		this.combobox = combobox;
	}

	/**
	 * ���� name
	 */
	public Textbox getName() {
		return name;
	}

	/**
	 * ���� name
	 */
	public void setName(Textbox name) {
		this.name = name;
	}

	/**
	 * ���� titles
	 */
	public Textbox getTitles() {
		return titles;
	}

	/**
	 * ���� titles
	 */
	public void setTitles(Textbox titles) {
		this.titles = titles;
	}

	/**
	 * ���� department
	 */
	public Textbox getDepartment() {
		return department;
	}

	/**
	 * ���� department
	 */
	public void setDepartment(Textbox department) {
		this.department = department;
	}

	/**
	 * ���� telephone
	 */
	public Textbox getTelephone() {
		return telephone;
	}

	/**
	 * ���� telephone
	 */
	public void setTelephone(Textbox telephone) {
		this.telephone = telephone;
	}

	/**
	 * ���� remarks
	 */
	public Textbox getRemarks() {
		return remarks;
	}

	/**
	 * ���� remarks
	 */
	public void setRemarks(Textbox remarks) {
		this.remarks = remarks;
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

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		return "�޸�"+expert.getName();
	}

}
