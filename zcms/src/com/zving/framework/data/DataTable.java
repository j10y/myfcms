package com.zving.framework.data;

import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Filter;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;

public class DataTable implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private boolean isWebMode;
	private DataRow[] rows;
	private DataColumn[] columns;

	public DataTable() {
		this.rows = new DataRow[0];
		this.columns = new DataColumn[0];
	}

	public DataTable(DataColumn[] types, Object[][] values) {
		if (types == null) {
			types = new DataColumn[0];
		}
		this.columns = null;
		this.rows = null;

		if (checkColumns(types)) {
			this.columns = types;
		}
		if (values != null) {
			this.rows = new DataRow[values.length];
			for (int i = 0; i < this.rows.length; ++i)
				this.rows[i] = new DataRow(this.columns, values[i]);
		} else {
			this.rows = new DataRow[0];
		}
	}

	public boolean checkColumns(DataColumn[] types) {
		if (types == null) {
			return false;
		}
		for (int i = 0; i < types.length; ++i) {
			String columnName = types[i].getColumnName();
			for (int j = 0; j < i; ++j) {
				if (columnName == null) {
					throw new RuntimeException("DataTable中第" + i + "列列名为null!");
				}
				if (columnName.equals(types[j].getColumnName())) {
					throw new RuntimeException("一个DataTable中不充许有重名的列:" + columnName);
				}
			}
		}
		return true;
	}

	public DataTable(ResultSet rs) {
		this(rs, 2147483647, 0);
	}

	public DataTable(ResultSet rs, int pageSize, int pageIndex) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			DataColumn[] types = new DataColumn[columnCount];
			for (int i = 1; i <= columnCount; ++i) {
				String name = rsmd.getColumnLabel(i);
				boolean b = rsmd.isNullable(i) == 1;
				DataColumn dc = new DataColumn();
				dc.setAllowNull(b);
				dc.setColumnName(name);

				int dataType = rsmd.getColumnType(i);
				if ((dataType == 1) || (dataType == 12) || (dataType == -1)) {
					dc.ColumnType = 1;
				} else if ((dataType == 93) || (dataType == 91)) {
					dc.ColumnType = 0;
				} else if (dataType == 3) {
					dc.ColumnType = 4;
				} else if (dataType == 6) {
					dc.ColumnType = 5;
				} else if (dataType == 4) {
					dc.ColumnType = 8;
				} else if (dataType == 5) {
					dc.ColumnType = 9;
				} else if (dataType == -5) {
					dc.ColumnType = 7;
				} else if (dataType == 2004) {
					dc.ColumnType = 2;
				} else if (dataType == 2005) {
					dc.ColumnType = 10;
				} else if (dataType == 2) {
					int dataScale = rsmd.getScale(i);
					int dataPrecision = rsmd.getPrecision(i);
					if (dataScale == 0) {
						if (dataPrecision == 0)
							dc.ColumnType = 3;
						else
							dc.ColumnType = 7;
					} else
						dc.ColumnType = 3;
				} else {
					dc.ColumnType = 1;
				}
				types[(i - 1)] = dc;
			}

			if (checkColumns(types)) {
				this.columns = types;
			}

			List list = new ArrayList();
			int index = 0;
			int begin = pageIndex * pageSize;
			int end = (pageIndex + 1) * pageSize;
			while (rs.next()) {
				if (index >= end) {
					break;
				}
				if (index >= begin) {
					Object[] t = new Object[columnCount];
					for (int j = 1; j <= columnCount; ++j) {
						if (this.columns[(j - 1)].getColumnType() == 10)
							t[(j - 1)] = LobUtil.clobToString(rs.getClob(j));
						else if (this.columns[(j - 1)].getColumnType() == 2)
							t[(j - 1)] = LobUtil.blobToBytes(rs.getBlob(j));
						else {
							t[(j - 1)] = rs.getObject(j);
						}
					}
					DataRow tmpRow = new DataRow(this.columns, t);
					list.add(tmpRow);
				}
				++index;
			}
			this.rows = new DataRow[list.size()];
			list.toArray(this.rows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteColumn(int columnIndex) {
		if (this.columns.length == 0) {
			return;
		}
		if ((columnIndex < 0) || ((this.columns != null) && (columnIndex >= this.columns.length))) {
			throw new RuntimeException("DataRow中没有指定的列：" + columnIndex);
		}
		this.columns = ((DataColumn[]) ArrayUtils.remove(this.columns, columnIndex));
		for (int i = 0; i < this.rows.length; ++i) {
			this.rows[i].columns = null;
			this.rows[i].columns = this.columns;
			this.rows[i].values = ArrayUtils.remove(this.rows[i].values, columnIndex);
		}
	}

	public void deleteColumn(String columnName) {
		if (this.columns.length == 0) {
			return;
		}
		for (int i = 0; i < this.columns.length; ++i)
			if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
				deleteColumn(i);
				return;
			}
	}

	public void insertColumn(String columnName) {
		insertColumn(new DataColumn(columnName, 1), null, this.columns.length);
	}

	public void insertColumn(String columnName, Object columnValue) {
		Object[] cv = new Object[this.rows.length];
		for (int i = 0; i < cv.length; ++i) {
			cv[i] = columnValue;
		}
		insertColumn(new DataColumn(columnName, 1), cv, this.columns.length);
	}

	public void insertColumns(String[] columnNames) {
		for (int i = 0; i < columnNames.length; ++i)
			insertColumn(new DataColumn(columnNames[i], 1), null, this.columns.length);
	}

	public void insertColumn(String columnName, Object[] columnValue) {
		insertColumn(new DataColumn(columnName, 1), columnValue, this.columns.length);
	}

	public void insertColumn(DataColumn dc) {
		insertColumn(dc, null, this.columns.length);
	}

	public void insertColumn(DataColumn dc, Object[] columnValue) {
		insertColumn(dc, columnValue, this.columns.length);
	}

	public void insertColumn(String columnName, Object[] columnValue, int index) {
		insertColumn(new DataColumn(columnName, 1), columnValue, index);
	}

	public void insertColumn(DataColumn dc, Object[] columnValue, int index) {
		if (index > this.columns.length) {
			throw new RuntimeException("DataRow中没有指定的列：" + index);
		}
		for (int i = 0; i < this.columns.length; ++i) {
			if (this.columns[i].getColumnName().equalsIgnoreCase(dc.getColumnName())) {
				throw new RuntimeException("DataTable中已经存在列：" + dc.getColumnName());
			}
		}
		this.columns = ((DataColumn[]) ArrayUtils.add(this.columns, index, dc));
		if (columnValue == null) {
			columnValue = new Object[this.rows.length];
		}
		if (this.rows.length == 0) {
			this.rows = new DataRow[columnValue.length];
			for (int i = 0; i < this.rows.length; ++i)
				this.rows[i] = new DataRow(this.columns, new Object[] { columnValue[i] });
		} else {
			for (int i = 0; i < this.rows.length; ++i) {
				this.rows[i].columns = null;
				this.rows[i].columns = this.columns;
				this.rows[i].values = ArrayUtils.add(this.rows[i].values, index, columnValue[i]);
			}
		}
	}

	public void insertRow(DataRow dr) {
		insertRow(dr, this.rows.length);
	}

	public void insertRow(DataRow dr, int index) {
		if (this.columns.length == 0) {
			this.columns = dr.columns;
		}
		insertRow(dr.getDataValues(), index);
	}

	public void insertRow(Object[] rowValue) {
		insertRow(rowValue, this.rows.length);
	}

	public void insertRow(Object[] rowValue, int index) {
		if (index > this.rows.length) {
			throw new RuntimeException(index + "超出范围，最大允许值为" + this.rows.length + "!");
		}
		if (rowValue != null) {
			if (this.columns.length == 0) {
				this.columns = new DataColumn[rowValue.length];
				for (int i = 0; i < this.columns.length; ++i) {
					this.columns[i] = new DataColumn("_Columns_" + i, 1);
				}
			}
			if (rowValue.length != this.columns.length) {
				throw new RuntimeException("新增行的列数为" + rowValue.length + "，要求的列数为"
						+ this.columns.length + "！");
			}
			for (int i = 0; i < this.columns.length; ++i) {
				if ((this.columns[i].ColumnType != 0) || (rowValue[i] == null)
						|| (Date.class.isInstance(rowValue[i])))
					continue;
				throw new RuntimeException("第" + i + "列必须是Date对象!");
			}
		} else {
			rowValue = new Object[this.columns.length];
		}
		DataRow[] newRows = new DataRow[this.rows.length + 1];
		System.arraycopy(this.rows, 0, newRows, 0, index);
		if (index < this.rows.length) {
			System.arraycopy(this.rows, index, newRows, index + 1, this.rows.length - index);
		}
		newRows[index] = new DataRow(this.columns, rowValue);
		this.rows = newRows;
	}

	public void deleteRow(int index) {
		if (index >= this.rows.length) {
			throw new RuntimeException(index + "超出范围，最大允许值为" + (this.rows.length - 1) + "!");
		}
		this.rows = ((DataRow[]) ArrayUtils.remove(this.rows, index));
	}

	public void deleteRow(DataRow dr) {
		for (int i = 0; i < this.rows.length; ++i) {
			if (dr == this.rows[i]) {
				deleteRow(i);
				return;
			}
		}
		throw new RuntimeException("指定的DataRow对象不属于此DataTable!");
	}

	public DataRow get(int rowIndex) {
		if ((rowIndex >= this.rows.length) || (rowIndex < 0)) {
			throw new RuntimeException("指定的行索引值超出范围");
		}
		return this.rows[rowIndex];
	}

	public void set(int rowIndex, int colIndex, Object value) {
		getDataRow(rowIndex).set(colIndex, value);
	}

	public void set(int rowIndex, String columnName, Object value) {
		getDataRow(rowIndex).set(columnName, value);
	}

	public void set(int rowIndex, int colIndex, int value) {
		getDataRow(rowIndex).set(colIndex, value);
	}

	public void set(int rowIndex, String columnName, int value) {
		getDataRow(rowIndex).set(columnName, value);
	}

	public void set(int rowIndex, int colIndex, long value) {
		getDataRow(rowIndex).set(colIndex, value);
	}

	public void set(int rowIndex, String columnName, long value) {
		getDataRow(rowIndex).set(columnName, value);
	}

	public void set(int rowIndex, int colIndex, double value) {
		getDataRow(rowIndex).set(colIndex, value);
	}

	public void set(int rowIndex, String columnName, double value) {
		getDataRow(rowIndex).set(columnName, value);
	}

	public Object get(int rowIndex, int colIndex) {
		return getDataRow(rowIndex).get(colIndex);
	}

	public Object get(int rowIndex, String columnName) {
		return getDataRow(rowIndex).get(columnName);
	}

	public String getString(int rowIndex, int colIndex) {
		return getDataRow(rowIndex).getString(colIndex);
	}

	public String getString(int rowIndex, String columnName) {
		return getDataRow(rowIndex).getString(columnName);
	}

	public int getInt(int rowIndex, int colIndex) {
		return getDataRow(rowIndex).getInt(colIndex);
	}

	public int getInt(int rowIndex, String columnName) {
		return getDataRow(rowIndex).getInt(columnName);
	}

	public long getLong(int rowIndex, int colIndex) {
		return getDataRow(rowIndex).getLong(colIndex);
	}

	public long getLong(int rowIndex, String columnName) {
		return getDataRow(rowIndex).getLong(columnName);
	}

	public double getDouble(int rowIndex, int colIndex) {
		return getDataRow(rowIndex).getDouble(colIndex);
	}

	public double getDouble(int rowIndex, String columnName) {
		return getDataRow(rowIndex).getDouble(columnName);
	}

	public Date getDate(int rowIndex, int colIndex) {
		return getDataRow(rowIndex).getDate(colIndex);
	}

	public Date getDate(int rowIndex, String columnName) {
		return getDataRow(rowIndex).getDate(columnName);
	}

	public DataRow getDataRow(int rowIndex) {
		if ((rowIndex >= this.rows.length) || (rowIndex < 0)) {
			throw new RuntimeException("指定的行索引值超出范围");
		}
		return this.rows[rowIndex];
	}

	public DataColumn getDataColumn(int columnIndex) {
		if ((columnIndex < 0) || (columnIndex >= this.columns.length)) {
			throw new RuntimeException("指定的列索引值超出范围");
		}
		return this.columns[columnIndex];
	}

	public DataColumn getDataColumn(String columnName) {
		for (int i = 0; i < this.columns.length; ++i) {
			if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
				return getDataColumn(i);
			}
		}
		return null;
	}

	public Object[] getColumnValues(int columnIndex) {
		if ((columnIndex < 0) || (columnIndex >= this.columns.length)) {
			throw new RuntimeException("指定的列索引值超出范围");
		}
		Object[] arr = new Object[getRowCount()];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = this.rows[i].values[columnIndex];
		}
		return arr;
	}

	public Object[] getColumnValues(String columnName) {
		for (int i = 0; i < this.columns.length; ++i) {
			if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
				return getColumnValues(i);
			}
		}
		return null;
	}

	public void sort(Comparator c) {
		Arrays.sort(this.rows, c);
	}

	public void sort(String columnName) {
		sort(columnName, "desc", false);
	}

	public void sort(String columnName, String order) {
		sort(columnName, order, false);
	}

	public void sort(String columnName, String order, final boolean isNumber) {
		final String cn = columnName;
		final String od = order;
		sort(new Comparator() {

			public int compare(Object obj1, Object obj2) {
				DataRow dr1 = (DataRow) obj1;
				DataRow dr2 = (DataRow) obj2;
				Object v1 = dr1.get(cn);
				Object v2 = dr2.get(cn);
				if ((v1 instanceof Number) && (v2 instanceof Number)) {
					Double d1 = ((Number) v1).doubleValue();
					Double d2 = ((Number) v2).doubleValue();
					if (d1 == d2)
						return 0;
					if (d1 > d2) {
						return (("asc".equalsIgnoreCase(od)) ? 1 : -1);
					}
					return (("asc".equalsIgnoreCase(od)) ? -1 : 1);
				}
				if ((v1 instanceof Date) && (v2 instanceof Date)) {
					Date d1 = (Date) v1;
					Date d2 = (Date) v1;
					if ("asc".equalsIgnoreCase(od)) {
						return d1.compareTo(d2);
					}
					return (-d1.compareTo(d2));
				}
				if (isNumber) {
					Double d1 = 0.0D;
					Double d2 = 0.0D;
					try {
						d1 = Double.parseDouble(String.valueOf(v1));
						d2 = Double.parseDouble(String.valueOf(v2));
					} catch (Exception localException) {
					}
					if (d1 == d2)
						return 0;
					if (d1 > d2) {
						return (("asc".equalsIgnoreCase(od)) ? -1 : 1);
					}
					return (("asc".equalsIgnoreCase(od)) ? 1 : -1);
				}

				int c = dr1.getString(cn).compareTo(dr2.getString(cn));
				if ("asc".equalsIgnoreCase(od)) {
					return c;
				}
				return (-c);
			}
		});
	}

	public DataTable filter(Filter filter) {
		List valueList = new ArrayList();
		for (int i = 0; i < this.rows.length; ++i) {
			if (filter.filter(this.rows[i])) {
				valueList.add(this.rows[i]);
			}
		}
		DataTable dt = new DataTable();
		dt.columns = this.columns;
		dt.rows = new DataRow[valueList.size()];
		valueList.toArray(dt.rows);
		dt.setWebMode(this.isWebMode);
		return dt;
	}

	public Object clone() {
		DataColumn[] dcs = new DataColumn[this.columns.length];
		for (int i = 0; i < this.columns.length; ++i) {
			dcs[i] = ((DataColumn) this.columns[i].clone());
		}
		DataTable dt = new DataTable();
		dt.columns = dcs;
		dt.rows = ((DataRow[]) this.rows.clone());
		dt.setWebMode(this.isWebMode);
		return dt;
	}

	public Mapx toMapx(String keyColumnName, String valueColumnName) {
		if (StringUtil.isEmpty(keyColumnName)) {
			throw new RuntimeException("不能存取列名为空的列");
		}
		if (StringUtil.isEmpty(valueColumnName)) {
			throw new RuntimeException("不能存取列名为空的列");
		}
		int keyIndex = 0;
		int valueIndex = 0;
		boolean keyFlag = false;
		boolean valueFlag = false;
		for (int i = 0; i < this.columns.length; ++i) {
			if (this.columns[i].getColumnName().equalsIgnoreCase(keyColumnName)) {
				keyIndex = i;
				keyFlag = true;
				if (valueFlag) {
					break;
				}
			}
			if (this.columns[i].getColumnName().equalsIgnoreCase(valueColumnName)) {
				valueIndex = i;
				valueFlag = true;
				if (keyFlag) {
					break;
				}
			}
		}

		return toMapx(keyIndex, valueIndex);
	}

	public Mapx toMapx(int keyColumnIndex, int valueColumnIndex) {
		if ((keyColumnIndex < 0) || (keyColumnIndex >= this.columns.length)) {
			throw new RuntimeException("DataRow中没有指定的列：" + keyColumnIndex);
		}
		if ((valueColumnIndex < 0) || (valueColumnIndex >= this.columns.length)) {
			throw new RuntimeException("DataRow中没有指定的列：" + valueColumnIndex);
		}
		Mapx map = new Mapx();
		for (int i = 0; i < this.rows.length; ++i) {
			Object key = this.rows[i].values[keyColumnIndex];
			if (key == null)
				map.put(key, this.rows[i].values[valueColumnIndex]);
			else {
				map.put(key.toString(), this.rows[i].values[valueColumnIndex]);
			}
		}
		return map;
	}

	public void decodeColumn(String colName, Map map) {
		for (int i = 0; i < this.columns.length; ++i)
			if (this.columns[i].getColumnName().equalsIgnoreCase(colName)) {
				decodeColumn(i, map);
				return;
			}
	}

	public void decodeColumn(int colIndex, Map map) {
		String newName = this.columns[colIndex].ColumnName + "Name";
		insertColumn(newName);
		for (int i = 0; i < getRowCount(); ++i) {
			String v = getString(i, colIndex);
			set(i, newName, map.get(v));
		}
	}

	public void decodeDateColumn(String colName) {
		for (int i = 0; i < this.columns.length; ++i)
			if (this.columns[i].getColumnName().equalsIgnoreCase(colName)) {
				decodeDateColumn(i);
				return;
			}
	}

	public void decodeDateColumn(int colIndex) {
		String newName = this.columns[colIndex].ColumnName + "Name";
		insertColumn(newName);
		for (int i = 0; i < getRowCount(); ++i) {
			String v = getString(i, colIndex);
			set(i, newName, DateUtil.toDisplayDateTime(v));
		}
	}

	public void union(DataTable anotherDT) {
		if (anotherDT.getRowCount() == 0) {
			return;
		}
		if (getRowCount() == 0) {
			this.rows = anotherDT.rows;
			return;
		}
		if (getColCount() != anotherDT.getColCount()) {
			throw new RuntimeException("两个DataTable的列数不一致，列数1：" + getColCount() + " ,列数2："
					+ anotherDT.getColCount());
		}
		int srcPos = this.rows.length;
		DataRow[] newRows = new DataRow[this.rows.length + anotherDT.getRowCount()];
		System.arraycopy(this.rows, 0, newRows, 0, srcPos);
		System.arraycopy(anotherDT.rows, 0, newRows, srcPos, anotherDT.getRowCount());
		this.rows = null;
		this.rows = newRows;
	}

	public int getRowCount() {
		return this.rows.length;
	}

	public int getColCount() {
		return this.columns.length;
	}

	public DataColumn[] getDataColumns() {
		return this.columns;
	}

	public boolean isWebMode() {
		return this.isWebMode;
	}

	public void setWebMode(boolean isWebMode) {
		this.isWebMode = isWebMode;
		for (int i = 0; i < this.rows.length; ++i)
			this.rows[i].setWebMode(isWebMode);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < this.columns.length; ++i) {
			if (i != 0) {
				sb.append("\t");
			}
			sb.append(this.columns[i].getColumnName());
		}
		sb.append("\n");
		for (int i = 0; i < this.rows.length; ++i) {
			if (i != 0) {
				sb.append("\n");
			}
			for (int j = 0; j < this.columns.length; ++j) {
				if (j != 0) {
					sb.append("\t");
				}
				sb.append(get(i, j));
			}
		}
		return sb.toString();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.data.DataTable JD-Core Version: 0.5.3
 */