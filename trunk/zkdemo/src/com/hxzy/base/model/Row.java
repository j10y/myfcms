/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 12, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.base.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiacc
 * 
 * ������
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
