/* DemoItem.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 12, 2008 10:39:23 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 3.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.hxzy.common.web.action;

/**
 * @author jumperchen
 */
public class MainItem implements java.io.Serializable {
	private String _id;
	private String _cateId;
	private String _icon;
	private String _file;
	private String _label;
	public MainItem(String id, String cateId, String file, String icon, String label) {
		_id = id;
		_cateId = cateId;
		_icon = icon;
		_file = file;
		_label = label;
	}
	public String getId() {
		return _id;
	}
	public String getCateId() {
		return _cateId;
	}
	public String getIcon() {
		return _icon;
	}
	public String getFile() {
		return _file;
	}
	public String getLabel() {
		return _label;
	}	
	public String toString() {
		return "[MainItem:" + _id +", "+_file+']';
	}
}
