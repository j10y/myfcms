package com.hxzy.common.log.web.action;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.log.model.Log;
import com.hxzy.common.log.service.LogService;

/**
 * @author xiacc
 * @version 1.0
 * @since 1.0
 * @description 
 */

public class LogDelete extends ActionWindow {	

	@Autowired
	private LogService logService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onBind() {		
				
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		//logService.
		
		((ListWindow)this.getParent()).onFind();
	}


	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		return "删除所有日志信息";
	}

}
