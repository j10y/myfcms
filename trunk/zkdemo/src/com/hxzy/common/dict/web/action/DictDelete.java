/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 12, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.dict.web.action;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.TreeWindow;
import com.hxzy.common.dict.model.Dict;
import com.hxzy.common.dict.service.DictService;

/**
 * @author xiacc
 * 
 * ������
 */
public class DictDelete extends ActionWindow {

	@Autowired
	private DictService dictService;

	private Set<Dict> dicts = (Set<Dict>) Executions.getCurrent().getArg().get("dicts");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		for (Dict d : dicts) {
			dictService.delete(d);
		}

		((TreeWindow) this.getParent()).init();
	}

}
