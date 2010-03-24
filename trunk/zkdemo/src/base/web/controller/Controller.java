/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 24, 2010</p>
 * <p>更新：</p>
 */
package base.web.controller;

import java.awt.Component;

import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.ComposerExt;

/**
 * @author xiacc
 * 
 * 描述：
 */
public interface Controller extends Composer, ComposerExt {

	public void doAfterCompose(Component comp) throws Exception;

	ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo)
			throws java.lang.Exception;
	
	void doBeforeComposeChildren(Component comp) throws java.lang.Exception;
	
}
