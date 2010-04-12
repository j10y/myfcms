/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 12, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.dict.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Textbox;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.TreeWindow;
import com.hxzy.common.dict.model.Dict;
import com.hxzy.common.dict.service.DictService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class DictEdit extends ActionWindow {

	@Autowired
	private DictService dictService;

	private Combobox combobox;

	private List<Dict> list;

	private Textbox name;

	private Textbox code;

	private Textbox remarks;

	private Dict dict = (Dict) Executions.getCurrent().getArg().get("dict");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		list = dictService.loadAll();

		name.setValue(dict.getName());
		code.setValue(dict.getCode());
		remarks.setValue(dict.getRemarks());

		binder.loadComponent(combobox);

		combobox.setItemRenderer(new ComboitemRenderer() {

			public void render(Comboitem item, Object o) throws Exception {

				Dict d = (Dict) o;

				if (!d.equals(dict)) {
					item.setValue(d);
					item.setLabel(d.getName());

					if (o.equals(dict.getParent())) {
						combobox.setSelectedItem(item);
					}
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
		Dict parent = null;
		if (combobox.getSelectedItem() != null) {
			parent = (Dict) combobox.getSelectedItem().getValue();
		}

		dict.setName(name.getValue());
		dict.setCode(code.getValue());
		dict.setRemarks(remarks.getValue());
		dict.setParent(parent);

		dictService.update(dict);

		((TreeWindow) this.getParent()).init();

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
	 * 返回 list
	 */
	public List<Dict> getList() {
		return list;
	}

	/**
	 * 设置 list
	 */
	public void setList(List<Dict> list) {
		this.list = list;
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
	 * 返回 code
	 */
	public Textbox getCode() {
		return code;
	}

	/**
	 * 设置 code
	 */
	public void setCode(Textbox code) {
		this.code = code;
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

}
