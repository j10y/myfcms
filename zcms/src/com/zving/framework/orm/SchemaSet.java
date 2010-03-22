package com.zving.framework.orm;

import com.zving.framework.User;
import com.zving.framework.data.DBConn;
import com.zving.framework.data.DataAccess;
import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.utility.Filter;
import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.StringUtil;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public abstract class SchemaSet implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private Schema[] elementData;
	private int elementCount;
	private int capacityIncrement;
	protected String TableCode;
	protected String NameSpace;
	protected SchemaColumn[] Columns;
	protected String InsertAllSQL;
	protected String UpdateAllSQL;
	protected String FillAllSQL;
	protected String DeleteSQL;
	protected int bConnFlag;
	protected boolean bOperateFlag;
	protected int[] operateColumnOrders;
	protected transient DataAccess mDataAccess;

	protected SchemaSet(int initialCapacity, int capacityIncrement) {
		this.bConnFlag = 0;

		this.bOperateFlag = false;

		if (initialCapacity < 0) {
			throw new RuntimeException("SchemaSet的初始容量不能小于0");
		}
		this.elementData = new Schema[initialCapacity];
		this.capacityIncrement = capacityIncrement;
		this.elementCount = 0;
	}

	protected SchemaSet(int initialCapacity) {
		this(initialCapacity, 0);
	}

	protected SchemaSet() {
		this(10);
	}

	public void setDataAccess(DataAccess dAccess) {
		this.mDataAccess = dAccess;
		this.bConnFlag = 1;
	}

	public boolean add(Schema s) {
		if ((s == null) || (s.TableCode != this.TableCode)) {
			LogUtil.warn("传入的参数不是一个" + this.TableCode + "Schema");
			return false;
		}
		ensureCapacityHelper(this.elementCount + 1);
		this.elementData[this.elementCount] = s;
		this.elementCount += 1;
		return true;
	}

	public boolean add(SchemaSet aSet) {
		if (aSet == null) {
			return false;
		}
		int n = aSet.size();
		ensureCapacityHelper(this.elementCount + n);
		for (int i = 0; i < n; ++i) {
			this.elementData[(this.elementCount + i)] = aSet.getObject(i);
		}
		this.elementCount += n;
		return true;
	}

	public boolean remove(Schema aSchema) {
		if (aSchema == null) {
			return false;
		}
		for (int i = 0; i < this.elementCount; ++i) {
			if (aSchema.equals(this.elementData[i])) {
				int j = this.elementCount - i - 1;
				if (j > 0) {
					System.arraycopy(this.elementData, i + 1, this.elementData, i, j);
				}
				this.elementCount -= 1;
				this.elementData[this.elementCount] = null;
				return true;
			}
		}
		return false;
	}

	public boolean removeRange(int index, int length) {
		if ((index < 0) || (length < 0) || (index + length > this.elementCount)) {
			return false;
		}
		if (this.elementCount > index + length) {
			System.arraycopy(this.elementData, index + length, this.elementData, index, length);
		}
		for (int i = 0; i < length; ++i) {
			this.elementData[(this.elementCount - i - 1)] = null;
		}
		this.elementCount -= length;
		return true;
	}

	public void clear() {
		for (int i = 0; i < this.elementCount; ++i) {
			this.elementData[i] = null;
		}
		this.elementCount = 0;
	}

	public boolean isEmpty() {
		return (this.elementCount == 0);
	}

	public Schema getObject(int index) {
		if (index > this.elementCount) {
			throw new RuntimeException("SchemaSet索引过大," + index);
		}
		return this.elementData[index];
	}

	public boolean set(int index, Schema aSchema) {
		if (index > this.elementCount) {
			throw new RuntimeException("SchemaSet索引过大," + index);
		}
		this.elementData[index] = aSchema;
		return true;
	}

	public boolean set(SchemaSet aSet) {
		this.elementData = aSet.elementData;
		this.elementCount = aSet.elementCount;
		this.capacityIncrement = aSet.capacityIncrement;
		return true;
	}

	public int size() {
		return this.elementCount;
	}

	private void ensureCapacityHelper(int minCapacity) {
		int oldCapacity = this.elementData.length;
		if (minCapacity > oldCapacity) {
			Object[] oldData = this.elementData;
			int newCapacity = (this.capacityIncrement > 0) ? oldCapacity + this.capacityIncrement
					: oldCapacity * 2;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			this.elementData = new Schema[newCapacity];
			System.arraycopy(oldData, 0, this.elementData, 0, this.elementCount);
		}
	}

	public boolean insert() {
		if (this.bConnFlag == 0) {
			this.mDataAccess = new DataAccess();
		}
		PreparedStatement pstmt = null;
		try {
			DBConn conn = this.mDataAccess.getConnection();
			boolean autoComit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(this.InsertAllSQL, 1003, 1007);
			for (int k = 0; k < this.elementCount; ++k) {
				for (int i = 0; i < this.Columns.length; ++i) {
					Schema schema = this.elementData[k];
					SchemaColumn sc = this.Columns[i];
					if ((sc.isMandatory()) && (schema.getV(i) == null)) {
						LogUtil.warn("表" + this.TableCode + "的列" + sc.getColumnName() + "不能为空");
						return false;
					}

					Object v = schema.getV(i);
					SchemaUtil.setParam(sc, pstmt, conn, i, v);
				}
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			if (this.bConnFlag == 0) {
				conn.commit();
			}
			conn.setAutoCommit(autoComit);
			conn.setLastSuccessExecuteTime(System.currentTimeMillis());
			return true;
		} catch (Throwable e) {

			return false;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pstmt = null;
			}
			if (this.bConnFlag == 0)
				try {
					this.mDataAccess.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public boolean update() {
		String sql = this.UpdateAllSQL;
		if (this.bOperateFlag) {
			StringBuffer sb = new StringBuffer("update " + this.TableCode + " set ");
			for (int i = 0; i < this.operateColumnOrders.length; ++i) {
				if (i != 0) {
					sb.append(",");
				}
				sb.append(this.Columns[this.operateColumnOrders[i]].getColumnName());
				sb.append("=?");
			}
			sb.append(sql.substring(sql.indexOf(" where")));
			sql = sb.toString();
		}
		if (this.bConnFlag == 0) {
			this.mDataAccess = new DataAccess();
		}
		PreparedStatement pstmt = null;
		try {
			DBConn conn = this.mDataAccess.getConnection();
			boolean autoComit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql, 1003, 1007);
			for (int k = 0; k < this.elementCount; ++k) {
				Schema schema = this.elementData[k];
				if (this.bOperateFlag)
					for (int i = 0; i < this.operateColumnOrders.length; ++i) {
						Object v = schema.getV(this.operateColumnOrders[i]);
						SchemaUtil.setParam(this.Columns[this.operateColumnOrders[i]], pstmt, conn,
								this.operateColumnOrders[i], v);
					}
				else {
					for (int i = 0; i < this.Columns.length; ++i) {
						Object v = schema.getV(i);
						SchemaUtil.setParam(this.Columns[i], pstmt, conn, i, v);
					}
				}
				int i = 0;
				for (int j = 0; i < this.Columns.length; ++i) {
					SchemaColumn sc = this.Columns[i];
					if (sc.isPrimaryKey()) {
						Object v = schema.getV(sc.getColumnOrder());
						if (v == null) {
							LogUtil.warn("不满足Update的条件，" + this.TableCode + "Schema的"
									+ sc.getColumnName() + "为空");
							return false;
						}
						if (this.bOperateFlag)
							SchemaUtil.setParam(this.Columns[i], pstmt, conn, j
									+ this.operateColumnOrders.length, v);
						else {
							SchemaUtil.setParam(this.Columns[i], pstmt, conn, j
									+ this.Columns.length, v);
						}

						++j;
					}
				}
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			if (this.bConnFlag == 0) {
				conn.commit();
			}
			conn.setAutoCommit(autoComit);
			conn.setLastSuccessExecuteTime(System.currentTimeMillis());
			return true;
		} catch (Throwable e) {
			LogUtil.warn("操作表" + this.TableCode + "时发生错误!");
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pstmt = null;
			}
			if (this.bConnFlag == 0) {
				try {
					this.mDataAccess.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean delete() {
		if (this.bConnFlag == 0) {
			this.mDataAccess = new DataAccess();
		}
		PreparedStatement pstmt = null;
		try {
			DBConn conn = this.mDataAccess.getConnection();
			boolean autoComit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(this.DeleteSQL, 1003, 1007);
			for (int k = 0; k < this.elementCount; ++k) {
				Schema schema = this.elementData[k];
				int i = 0;
				for (int j = 0; i < this.Columns.length; ++i) {
					SchemaColumn sc = this.Columns[i];
					if (sc.isPrimaryKey()) {
						Object v = schema.getV(sc.getColumnOrder());
						if (v == null) {
							LogUtil.warn("不满足delete的条件，" + this.TableCode + "Schema的"
									+ sc.getColumnName() + "为空");
							return false;
						}
						SchemaUtil.setParam(this.Columns[i], pstmt, conn, j, v);

						++j;
					}
				}
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.setLastSuccessExecuteTime(System.currentTimeMillis());
			if (this.bConnFlag == 0) {
				conn.commit();
			}
			conn.setAutoCommit(autoComit);
			return true;
		} catch (Throwable e) {
			LogUtil.warn("操作表" + this.TableCode + "时发生错误!");
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pstmt = null;
			}
			if (this.bConnFlag == 0) {
				try {
					this.mDataAccess.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean deleteAndInsert() {
		if (this.bConnFlag == 1) {
			if (!(delete())) {
				return false;
			}
			return insert();
		}
		this.mDataAccess = new DataAccess();
		this.bConnFlag = 1;
		try {
			this.mDataAccess.setAutoCommit(false);
			delete();
			insert();
			this.mDataAccess.commit();
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
			try {
				this.mDataAccess.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			try {
				this.mDataAccess.setAutoCommit(true);
				this.mDataAccess.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.mDataAccess = null;
			this.bConnFlag = 0;
		}
	}

	public boolean deleteAndBackup() {
		return deleteAndBackup(null, null);
	}

	public boolean deleteAndBackup(String backupOperator, String backupMemo) {
		try {
			backupOperator = (StringUtil.isEmpty(backupOperator)) ? User.getUserName()
					: backupOperator;
			backupOperator = (StringUtil.isEmpty(backupOperator)) ? "admin" : backupOperator;

			Class c = Class.forName("com.zving.schema.B" + this.TableCode + "Schema");
			Class s = Class.forName("com.zving.schema.B" + this.TableCode + "Set");
			SchemaSet bSet = (SchemaSet) s.newInstance();
			Date now = new Date();
			for (int k = 0; k < this.elementCount; ++k) {
				Schema schema = this.elementData[k];
				Schema bSchema = (Schema) c.newInstance();
				int i = 0;
				for (; i < this.Columns.length; ++i) {
					bSchema.setV(i, schema.getV(i));
				}
				bSchema.setV(i, SchemaUtil.getBackupNo());
				bSchema.setV(i + 1, backupOperator);
				bSchema.setV(i + 2, now);
				bSchema.setV(i + 3, backupMemo);
				bSet.add(bSchema);
			}
			if (this.bConnFlag == 1) {
				bSet.setDataAccess(this.mDataAccess);
				if (!(delete())) {
					return true;
				}
				bSet.insert();
				return true;
			}
			this.mDataAccess = new DataAccess();
			this.bConnFlag = 1;
			bSet.setDataAccess(this.mDataAccess);
			try {
				this.mDataAccess.setAutoCommit(false);
				delete();
				bSet.insert();
				this.mDataAccess.commit();
				return true;
			} catch (Throwable e) {
				e.printStackTrace();
				try {
					this.mDataAccess.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				return false;
			} finally {
				try {
					this.mDataAccess.setAutoCommit(true);
					this.mDataAccess.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.mDataAccess = null;
				this.bConnFlag = 0;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean backup() {
		return backup(null, null);
	}

	public boolean backup(String backupOperator, String backupMemo) {
		try {
			backupOperator = (StringUtil.isEmpty(backupOperator)) ? User.getUserName()
					: backupOperator;
			backupOperator = (StringUtil.isEmpty(backupOperator)) ? "admin" : backupOperator;

			Class c = Class.forName("com.zving.schema.B" + this.TableCode + "Schema");
			Class s = Class.forName("com.zving.schema.B" + this.TableCode + "Set");
			SchemaSet bSet = (SchemaSet) s.newInstance();
			Date now = new Date();
			for (int k = 0; k < this.elementCount; ++k) {
				Schema schema = this.elementData[k];
				Schema bSchema = (Schema) c.newInstance();
				int i = 0;
				for (; i < this.Columns.length; ++i) {
					bSchema.setV(i, schema.getV(i));
				}
				bSchema.setV(i, SchemaUtil.getBackupNo());
				bSchema.setV(i + 1, backupOperator);
				bSchema.setV(i + 2, now);
				bSchema.setV(i + 3, backupMemo);
				bSet.add(bSchema);
			}
			if (this.bConnFlag == 1) {
				bSet.setDataAccess(this.mDataAccess);
			}
			return bSet.insert();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setOperateColumns(String[] colNames) {
		if ((colNames == null) || (colNames.length == 0)) {
			this.bOperateFlag = false;
			return;
		}
		this.operateColumnOrders = new int[colNames.length];
		int i = 0;
		for (int k = 0; i < colNames.length; ++i) {
			boolean flag = false;
			for (int j = 0; j < this.Columns.length; ++j) {
				if (colNames[i].toString().toLowerCase().equals(
						this.Columns[j].getColumnName().toLowerCase())) {
					this.operateColumnOrders[k] = j;
					++k;
					flag = true;
					break;
				}
			}
			if (!(flag)) {
				throw new RuntimeException("指定的列名" + colNames[i] + "不正确");
			}
		}
		this.bOperateFlag = true;
	}

	public void setOperateColumns(int[] colOrder) {
		if ((colOrder == null) || (colOrder.length == 0)) {
			this.bOperateFlag = false;
			return;
		}
		for (int i = 0; i < this.elementCount; ++i) {
			this.elementData[i].setOperateColumns(colOrder);
		}
		this.operateColumnOrders = colOrder;
		this.bOperateFlag = true;
	}

	public DataTable toDataTable() {
		DataColumn dcs[];
		Object values[][];
		DataTable dt;
		if (bOperateFlag) {
			dcs = new DataColumn[operateColumnOrders.length];
			values = new Object[elementCount][Columns.length];
			for (int i = 0; i < operateColumnOrders.length; i++) {
				DataColumn dc = new DataColumn();
				dc.setColumnName(Columns[operateColumnOrders[i]].getColumnName());
				dc.setColumnType(Columns[operateColumnOrders[i]].getColumnType());
				dcs[i] = dc;
			}

			for (int i = 0; i < elementCount; i++) {
				for (int j = 0; j < operateColumnOrders.length; j++)
					values[i][j] = elementData[i].getV(operateColumnOrders[j]);

			}

			dt = new DataTable(dcs, values);
			return dt;
		}
		dcs = new DataColumn[Columns.length];
		values = new Object[elementCount][Columns.length];
		for (int i = 0; i < Columns.length; i++) {
			DataColumn dc = new DataColumn();
			dc.setColumnName(Columns[i].getColumnName());
			dc.setColumnType(Columns[i].getColumnType());
			dcs[i] = dc;
		}

		for (int i = 0; i < elementCount; i++) {
			for (int j = 0; j < Columns.length; j++)
				values[i][j] = elementData[i].getV(j);

		}

		DataTable i = new DataTable(dcs, values);
		return i;
	}

	public Object clone() {
		SchemaSet set = newInstance();
		for (int i = 0; i < size(); ++i) {
			set.add((Schema) this.elementData[i].clone());
		}
		return set;
	}

	public void sort(Comparator c) {
		Schema[] newData = new Schema[this.elementCount];
		System.arraycopy(this.elementData, 0, newData, 0, this.elementCount);
		Arrays.sort(newData, c);
		this.elementData = newData;
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
				DataRow dr1 = ((Schema) obj1).toDataRow();
				DataRow dr2 = ((Schema) obj2).toDataRow();
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

	public SchemaSet filter(Filter filter) {
		SchemaSet set = newInstance();
		for (int i = 0; i < this.elementData.length; ++i) {
			if (filter.filter(this.elementData[i])) {
				set.add((Schema) this.elementData[i].clone());
			}
		}
		return set;
	}

	protected abstract SchemaSet newInstance();
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.orm.SchemaSet JD-Core Version: 0.5.3
 */