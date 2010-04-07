/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 30, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.intercepter;

import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Richlet;
import org.zkoss.zk.ui.http.SerializableUiFactory;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zk.ui.sys.RequestInfo;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

/**
 * @author xiacc
 * 
 * 描述：自动装载监听器
 */
public class DelegatingVariableResolverSerializableUiFactory extends SerializableUiFactory {

	@Override
	public Page newPage(RequestInfo ri, PageDefinition pagedef, String path) {
		Page newPage = super.newPage(ri, pagedef, path);
		newPage.addVariableResolver(new DelegatingVariableResolver());
		return newPage;
	}

	@Override
	public Page newPage(RequestInfo ri, Richlet richlet, String path) {
		Page newPage = super.newPage(ri, richlet, path);
		newPage.addVariableResolver(new DelegatingVariableResolver());
		return newPage;
	}

}
