/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 9, 2010</p>
 * <p>���£�</p>
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
 * ����������У��Ȩ�޵�Button�࣬���е���ҪУ��Ȩ�޵İ�ťʹ�ø���
 */
public class Button extends org.zkoss.zul.Button {

	/**
	 * Ȩ�޴���
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
	 * ���� priv
	 */
	public String getPriv() {
		return priv;
	}

	/**
	 * ���� priv
	 */
	public void setPriv(String priv) {
		this.priv = priv;
	}

}
