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
