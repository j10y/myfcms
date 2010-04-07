/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 7, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.action;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.TreeWindow;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.service.PrivilegeService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class PrivilegeDelete extends ActionWindow {
	
	@Autowired
	private PrivilegeService privilegeService;
	
	private Set<Privilege> privileges = (Set<Privilege>)Executions.getCurrent().getArg().get("privileges");    

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {

	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		for(Privilege p:privileges){
			privilegeService.delete(p);
		}
		
		((TreeWindow)this.getParent()).init();
		this.onClose();	
	}

}
