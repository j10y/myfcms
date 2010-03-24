/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 24, 2010</p>
 * <p>���£�</p>
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
 * ������
 */
public interface Controller extends Composer, ComposerExt {

	public void doAfterCompose(Component comp) throws Exception;

	ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo)
			throws java.lang.Exception;
	
	void doBeforeComposeChildren(Component comp) throws java.lang.Exception;
	
}
