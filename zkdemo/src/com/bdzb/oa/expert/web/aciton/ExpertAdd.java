/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 2, 2010</p>
 * <p>更新：</p>
 */
package com.bdzb.oa.expert.web.aciton;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zul.Combobox;
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
 * 描述：
 */
public class ExpertAdd extends ActionWindow {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		list = dictService.findByProperty("parent.code", "industryCategory");
		binder.loadComponent(combobox);
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
		
		Expert expert = new Expert();
		expert.setName(name.getValue());
		expert.setCategory(category);
		expert.setTitles(titles.getValue());
		expert.setTelephone(telephone.getValue());
		expert.setRemarks(remarks.getValue());
		expert.setDepartment(department.getValue());
		
		expertService.save(expert);

		((ListWindow) this.getParent()).onFind();
	}

	/**
	 * 返回 expertService
	 */
	public ExpertService getExpertService() {
		return expertService;
	}

	/**
	 * 设置 expertService
	 */
	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
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
	 * 返回 name
	 */
	public Textbox getName() {
		return name;
	}

	/**
	 * 设置 name
	 */
	public void setName(Textbox name) {
		this.name = name;
	}

	/**
	 * 返回 titles
	 */
	public Textbox getTitles() {
		return titles;
	}

	/**
	 * 设置 titles
	 */
	public void setTitles(Textbox titles) {
		this.titles = titles;
	}

	/**
	 * 返回 department
	 */
	public Textbox getDepartment() {
		return department;
	}

	/**
	 * 设置 department
	 */
	public void setDepartment(Textbox department) {
		this.department = department;
	}

	/**
	 * 返回 telephone
	 */
	public Textbox getTelephone() {
		return telephone;
	}

	/**
	 * 设置 telephone
	 */
	public void setTelephone(Textbox telephone) {
		this.telephone = telephone;
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
	
	

}
