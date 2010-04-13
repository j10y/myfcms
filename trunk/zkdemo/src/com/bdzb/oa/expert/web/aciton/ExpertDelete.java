/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 12, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.expert.web.aciton;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;

import com.bdzb.oa.expert.model.Expert;
import com.bdzb.oa.expert.service.ExpertService;
import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;

/**
 * @author xiacc
 * 
 * ������
 */
public class ExpertDelete extends ActionWindow {

	@Autowired
	private ExpertService expertService;

	private Set<Expert> experts = (Set<Expert>) Executions.getCurrent().getArg().get("experts");

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
		for (Expert exp : experts) {
			expertService.delete(exp);
		}

		((ListWindow) this.getParent()).onFind();
	}

}
