/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 19, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.component;


import org.zkoss.zul.Combobox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;

/**
 * @author xiacc
 * 
 * ������
 */
public class TreeComboBox extends Combobox {

	private Tree tree;

	public void setSelectedItem(Object o) {
		tree.setSelectedItem((Treeitem) o);
		this.setSelectedItem(o);
	}
	
	

}
