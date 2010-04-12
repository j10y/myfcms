/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 12, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class Sheet {

	private List<Row> rowList = new ArrayList();
	private String name;

	public List<Row> getRowList() {
		return rowList;
	}

	public void setRowList(List<Row> rowList) {
		this.rowList = rowList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void appendRow(Row row) {
		rowList.add(row);
	}

}
