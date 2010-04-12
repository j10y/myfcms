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
public class Row {
	private List<Column> columnList = new ArrayList();

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	public void appendColumn(Column column) {
		columnList.add(column);
	}
}
