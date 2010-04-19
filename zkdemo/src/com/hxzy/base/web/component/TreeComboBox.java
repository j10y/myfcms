/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 19, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.component;


import org.zkoss.zul.Combobox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class TreeComboBox extends Combobox {

	private Tree tree;

	public void setSelectedItem(Object o) {
		tree.setSelectedItem((Treeitem) o);
		this.setSelectedItem(o);
	}
	
	

}
