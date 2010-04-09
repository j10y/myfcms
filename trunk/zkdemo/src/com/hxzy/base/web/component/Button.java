/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 9, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.component;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;

import com.hxzy.base.constant.Constant;
import com.hxzy.common.user.model.UserInfo;

/**
 * @author xiacc
 * 
 * 描述：可以校验权限的Button类，所有的需要校验权限的按钮使用该类
 */
public class Button extends org.zkoss.zul.Button {

	/**
	 * 权限代码
	 */
	private String priv;

	public void onCreate() {

		HttpSession session = (HttpSession) Sessions.getCurrent().getNativeSession();

		UserInfo userInfo = (UserInfo) session.getAttribute(Constant.ATTRIBUTE_USER_INFO);

		if (userInfo != null) {
			Map userFunPriv = userInfo.getUserFunPriv();

			if (priv != null && !userFunPriv.containsKey((priv))) {
				this.setDisabled(true);
			}
		}
	}
	

	/**
	 * 返回 priv
	 */
	public String getPriv() {
		return priv;
	}

	/**
	 * 设置 priv
	 */
	public void setPriv(String priv) {
		this.priv = priv;
	}

}
