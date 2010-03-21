package com.zving.framework.orm;

import com.zving.framework.User;
import com.zving.framework.data.DBConn;
import com.zving.framework.data.DataAccess;
import com.zving.framework.data.DataCollection;
import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.LobUtil;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.logging.Log;

public abstract class Schema implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	protected String TableCode;
	protected SchemaColumn[] Columns;
	protected String InsertAllSQL;
	protected String UpdateAllSQL;
	protected String FillAllSQL;
	protected String DeleteSQL;
	protected String NameSpace;
	protected int bConnFlag = 0;

	protected boolean bOperateFlag = false;
	protected int[] operateColumnOrders;
	protected boolean[] HasSetFlag;
	protected transient DataAccess mDataAccess;

	public boolean insert() {
		if (this.bConnFlag == 0) {
			this.mDataAccess = new DataAccess();
		}
		PreparedStatement pstmt = null;
		DBConn conn;
		int count;
		try {
			conn = this.mDataAccess.getConnection();
			pstmt = conn.prepareStatement(this.InsertAllSQL, 1003, 1007);
			for (int i = 0; i < this.Columns.length; ++i) {
				SchemaColumn sc = this.Columns[i];
				if ((sc.isMandatory()) && (getV(i) == null)) {
					throw new SQLException("表" + this.TableCode + "的列" + sc.getColumnName()
							+ "不能为空");
				}

				Object v = getV(i);
				SchemaUtil.setParam(sc, pstmt, conn, i, v);
			}
			count = pstmt.executeUpdate();
			conn.setLastSuccessExecuteTime(System.currentTimeMillis());
			return (count == 1);
		} catch (Exception e) {
			e.printStackTrace();
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
		System.out.println(sql);
		try {
			DBConn conn = this.mDataAccess.getConnection();
			pstmt = conn.prepareStatement(sql, 1003, 1007);
			Object v;
			if (this.bOperateFlag)
				for (int i = 0; i < this.operateColumnOrders.length; ++i) {
					v = getV(this.operateColumnOrders[i]);
					SchemaUtil.setParam(this.Columns[this.operateColumnOrders[i]], pstmt, conn,
							this.operateColumnOrders[i], v);
				}
			else {
				for (int i = 0; i < this.Columns.length; ++i) {
					v = getV(i);
					SchemaUtil.setParam(this.Columns[i], pstmt, conn, i, v);
				}
			}
			int i = 0;
			for (int j = 0; i < this.Columns.length; ++i) {
				SchemaColumn sc = this.Columns[i];
				if (sc.isPrimaryKey()) {
					v = getV(sc.getColumnOrder());
					if (v == null) {
						LogUtil.getLogger().warn(
								"不满足Update的条件，" + this.TableCode + "Schema的" + sc.getColumnName()
										+ "为空");
						return false;
					}
					if (this.bOperateFlag)
						SchemaUtil.setParam(this.Columns[i], pstmt, conn, j
								+ this.operateColumnOrders.length, v);
					else {
						SchemaUtil.setParam(this.Columns[i], pstmt, conn, j + this.Columns.length,
								v);
					}

					++j;
				}
			}
			pstmt.executeUpdate();
			conn.setLastSuccessExecuteTime(System.currentTimeMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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

	public boolean delete() {
		if (this.bConnFlag == 0) {
			this.mDataAccess = new DataAccess();
		}
		PreparedStatement pstmt = null;
		try {
			DBConn conn = this.mDataAccess.getConnection();
			pstmt = conn.prepareStatement(this.DeleteSQL, 1003, 1007);
			int i = 0;
			for (int j = 0; i < this.Columns.length; ++i) {
				SchemaColumn sc = this.Columns[i];
				if (sc.isPrimaryKey()) {
					Object v = getV(sc.getColumnOrder());
					if (v == null) {
						LogUtil.warn("不满足delete的条件，" + this.TableCode + "Schema的"
								+ sc.getColumnName() + "为空");
						return false;
					}
					SchemaUtil.setParam(this.Columns[i], pstmt, conn, j, v);

					++j;
				}
			}
			pstmt.executeUpdate();
			conn.setLastSuccessExecuteTime(System.currentTimeMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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

	public boolean backup() {
		return backup(null, null);
	}

	public boolean backup(String backupOperator, String backupMemo) {
		try {
			backupOperator = (StringUtil.isEmpty(backupOperator)) ? User.getUserName()
					: backupOperator;
			backupOperator = (StringUtil.isEmpty(backupOperator)) ? "admin" : backupOperator;

			Class c = Class.forName("com.zving.schema.B" + this.TableCode + "Schema");
			Schema bSchema = (Schema) c.newInstance();
			int i = 0;
			for (; i < this.Columns.length; ++i) {
				bSchema.setV(i, getV(i));
			}
			bSchema.setV(i, SchemaUtil.getBackupNo());
			bSchema.setV(i + 1, backupOperator);
			bSchema.setV(i + 2, new Date());
			bSchema.setV(i + 3, backupMemo);
			if (this.bConnFlag == 0) {
				return bSchema.insert();
			}
			bSchema.setDataAccess(this.mDataAccess);
			return bSchema.insert();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
		} catch (Exception e) {
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
			Schema bSchema = (Schema) c.newInstance();
			int i = 0;
			for (; i < this.Columns.length; ++i) {
				bSchema.setV(i, getV(i));
			}
			bSchema.setV(i, SchemaUtil.getBackupNo());
			bSchema.setV(i + 1, backupOperator);
			bSchema.setV(i + 2, new Date());
			bSchema.setV(i + 3, backupMemo);
			if (this.bConnFlag == 0) {
				this.mDataAccess = new DataAccess();
				this.bConnFlag = 1;
				try {
					this.mDataAccess.setAutoCommit(false);
					delete();
					bSchema.setDataAccess(this.mDataAccess);
					bSchema.insert();
					this.mDataAccess.commit();
					return true;
				} catch (SQLException e) {
					LogUtil.getLogger().warn("操作表" + this.TableCode + "时发生错误!");
					e.printStackTrace();
					try {
						this.mDataAccess.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					return false;
				} finally {
					this.bConnFlag = 0;
					try {
						this.mDataAccess.setAutoCommit(true);
						this.mDataAccess.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			if (!(delete())) {
				return false;
			}
			bSchema.setDataAccess(this.mDataAccess);
			return bSchema.insert();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean fill() {
		String sql = this.FillAllSQL;
		if (this.bOperateFlag) {
			StringBuffer sb = new StringBuffer("select ");
			for (int i = 0; i < this.operateColumnOrders.length; ++i) {
				if (i == 0) {
					sb.append(this.Columns[this.operateColumnOrders[i]].getColumnName());
				} else {
					sb.append(",");
					sb.append(this.Columns[this.operateColumnOrders[i]].getColumnName());
				}
			}
			sb.append(sql.substring(sql.indexOf(" from")));
			sql = sb.toString();
		}
		if (this.bConnFlag == 0) {
			this.mDataAccess = new DataAccess();
		}
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			DBConn conn = this.mDataAccess.getConnection();
			pstmt = conn.prepareStatement(sql, 1003, 1007);
			int i = 0;
			for (int j = 0; i < this.Columns.length; ++i) {
				SchemaColumn sc = this.Columns[i];
				if (sc.isPrimaryKey()) {
					Object v = getV(sc.getColumnOrder());
					if (v == null) {
						throw new RuntimeException("不满足fill的条件，" + this.TableCode + "Schema的"
								+ sc.getColumnName() + "为空");
					}
					if (this.Columns[i].getColumnType() == 0)
						pstmt.setTimestamp(j + 1, new Timestamp(((Date) v).getTime()));
					else {
						pstmt.setObject(j + 1, v);
					}

					++j;
				}
			}
			rs = pstmt.executeQuery();
			conn.setLastSuccessExecuteTime(System.currentTimeMillis());
			if (rs.next()) {
				if (bOperateFlag)
					for (i = 0; i < operateColumnOrders.length; i++)
						if (Columns[operateColumnOrders[i]].getColumnType() == 10) {
							if (conn.getDBType().equals("ORACLE") || conn.getDBType().equals("DB2"))
								setV(operateColumnOrders[i], LobUtil
										.clobToString(rs.getClob(i + 1)));
							else
								setV(operateColumnOrders[i], rs.getObject(i + 1));
						} else if (Columns[operateColumnOrders[i]].getColumnType() == 2)
							setV(operateColumnOrders[i], LobUtil.blobToBytes(rs.getBlob(i + 1)));
						else
							setV(operateColumnOrders[i], rs.getObject(i + 1));

				else
					for (i = 0; i < Columns.length; i++)
						if (Columns[i].getColumnType() == 10) {
							if (conn.getDBType().equals("ORACLE") || conn.getDBType().equals("DB2"))
								setV(i, LobUtil.clobToString(rs.getClob(i + 1)));
							else
								setV(i, rs.getObject(i + 1));
						} else if (Columns[i].getColumnType() == 2)
							setV(i, LobUtil.blobToBytes(rs.getBlob(i + 1)));
						else
							setV(i, rs.getObject(i + 1));

			}
			return true;
		} catch (Exception e) {
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (this.bConnFlag == 0)
				try {
					this.mDataAccess.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public SchemaSet querySet(QueryBuilder qb, int pageSize, int pageIndex) {
		if ((qb != null) && (!(qb.getSQL().trim().toLowerCase().startsWith("where")))) {
			throw new RuntimeException("QueryBuilder中的SQL语句不是以where开头的字符串");
		}

		if (this.bConnFlag == 0) {
			this.mDataAccess = new DataAccess();
		}
		DBConn conn = this.mDataAccess.getConnection();
		StringBuffer sb = new StringBuffer("select ");
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			if (this.bOperateFlag) {
				for (int i = 0; i < this.operateColumnOrders.length; ++i) {
					if (i != 0) {
						sb.append(",");
					}
					sb.append(this.Columns[this.operateColumnOrders[i]].getColumnName());
				}
			} else if (conn.getDBType().equals("MSSQL")) {
				for (int i = 0; i < this.Columns.length; ++i) {
					if (i != 0) {
						sb.append(",");
					}
					sb.append(this.Columns[i].getColumnName());
				}
			} else
				sb.append("*");

			sb.append(" from " + this.TableCode);
			if (qb == null) {
				boolean firstFlag = true;
				qb = new QueryBuilder();
				for (int i = 0; i < this.Columns.length; ++i) {
					SchemaColumn sc = this.Columns[i];
					if (!(isNull(sc))) {
						if (firstFlag) {
							sb.append(" where ");
							sb.append(sc.getColumnName());
							sb.append("=?");
							firstFlag = false;
						} else {
							sb.append(" and ");
							sb.append(sc.getColumnName());
							sb.append("=?");
						}
						Object v = getV(sc.getColumnOrder());
						qb.add(v);
					}
				}
			} else {
				sb.append(" ");
				sb.append(qb.getSQL());
			}

			qb.setSQL(sb.toString());
			String pageSQL = qb.getSQL();
			if (pageSize > 0) {
				pageSQL = DataAccess.getPagedSQL(conn, qb, pageSize, pageIndex);
			}

			pageIndex = (pageIndex < 0) ? 0 : pageIndex;

			pstmt = conn.prepareStatement(pageSQL);
			DataAccess.setParams(pstmt, qb, conn);
			rs = pstmt.executeQuery();

			if ((pageSize > 0) && (!(conn.getDBType().equals("MSSQL2000")))) {
				qb.getParams().remove(qb.getParams().size() - 1);
				qb.getParams().remove(qb.getParams().size() - 1);
			}

			conn.setLastSuccessExecuteTime(System.currentTimeMillis());
			SchemaSet set = newSet();
			while (rs.next()) {
				Schema schema = newInstance();
				int i;
				if (this.bOperateFlag) {
					for (i = 0; i < this.operateColumnOrders.length; ++i) {
						if (this.Columns[this.operateColumnOrders[i]].getColumnType() == 10) {
							if ((conn.getDBType().equals("ORACLE"))
									|| (conn.getDBType().equals("DB2")))
								schema.setV(this.operateColumnOrders[i], LobUtil.clobToString(rs
										.getClob(i + 1)));
							else
								schema.setV(this.operateColumnOrders[i], rs.getObject(i + 1));
						} else if (this.Columns[this.operateColumnOrders[i]].getColumnType() == 2)
							schema.setV(this.operateColumnOrders[i], LobUtil.blobToBytes(rs
									.getBlob(i + 1)));
						else
							schema.setV(this.operateColumnOrders[i], rs.getObject(i + 1));
					}
				} else {
					for (i = 0; i < this.Columns.length; ++i) {
						if (this.Columns[i].getColumnType() == 10) {
							if ((conn.getDBType().equals("ORACLE"))
									|| (conn.getDBType().equals("DB2")))
								schema.setV(i, LobUtil.clobToString(rs.getClob(i + 1)));
							else
								schema.setV(i, rs.getObject(i + 1));
						} else if (this.Columns[i].getColumnType() == 2)
							schema.setV(i, LobUtil.blobToBytes(rs.getBlob(i + 1)));
						else {
							schema.setV(i, rs.getObject(i + 1));
						}
					}
				}
				set.add(schema);
			}
			set.setOperateColumns(this.operateColumnOrders);
			return set;
		} catch (Exception e) {
			LogUtil.getLogger().error("操作表" + this.TableCode + "时发生错误!");
			e.printStackTrace();
			return null;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pstmt = null;
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (this.bConnFlag == 0) {
				conn = null;
				try {
					this.mDataAccess.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		this.operateColumnOrders = colOrder;
		this.bOperateFlag = true;
	}

	public void setDataAccess(DataAccess dAccess) {
		this.mDataAccess = dAccess;
		this.bConnFlag = 1;
	}

	protected boolean isNull(SchemaColumn sc) {
		return (getV(sc.getColumnOrder()) == null);
	}

	public void setValue(Mapx map) {
		Object value = null;
		Object key = null;
		Object[] ks = map.keyArray();
		Object[] vs = map.valueArray();
		for (int i = 0; i < map.size(); ++i) {
			value = vs[i];
			key = ks[i];
			for (int j = 0; j < this.Columns.length; ++j) {
				SchemaColumn sc = this.Columns[j];
				if ((key == null) || (!(key.toString().equalsIgnoreCase(sc.getColumnName()))))
					continue;
				try {
					int type = sc.getColumnType();
					if (type == 0)
						if ((value != null) && (!("".equals(value)))) {
							setV(j, DateUtil.parseDateTime(value.toString()));
						}
					if (type == 6) {
						setV(j, new Double(value.toString()));
					}
					if (type == 5) {
						setV(j, new Float(value.toString()));
					}
					if (type == 7) {
						setV(j, new Long(value.toString()));
					}
					if (type == 8) {
						setV(j, new Integer(value.toString()));
					}
					setV(j, value);
				} catch (Exception localException) {
				}
			}
		}
	}

	public void setValue(DataCollection dc) {
		String value = null;
		String key = null;
		Object[] ks = dc.keyArray();
		Object[] vs = dc.valueArray();
		for (int i = 0; i < dc.size(); ++i) {
			if ((!(vs[i] instanceof String)) && (vs[i] != null)) {
				continue;
			}
			value = (String) vs[i];
			key = (String) ks[i];
			for (int j = 0; j < this.Columns.length; ++j) {
				SchemaColumn sc = this.Columns[j];
				if (!(key.equalsIgnoreCase(sc.getColumnName())))
					continue;
				try {
					int type = sc.getColumnType();
					if (type == 0) {
						if ((value != null) && (!("".equals(value))))
							if (DateUtil.isTime(value.toString())) {
								setV(j, DateUtil.parseDateTime(value.toString(), "HH:mm:ss"));
							} else {
								setV(j, DateUtil.parseDateTime(value.toString()));
							}
					}
					if (type == 6) {
						setV(j, new Double(value.toString()));
					}
					if (type == 5) {
						setV(j, new Float(value.toString()));
					}
					if (type == 7) {
						setV(j, new Long(value.toString()));
					}
					if (type == 8) {
						setV(j, new Integer(value.toString()));
					}
					setV(j, value);
				} catch (Exception localException) {
				}
			}
		}
	}

	public void setValue(DataRow dr) {
		String value = null;
		String key = null;
		boolean webMode = dr.isWebMode();
		dr.setWebMode(false);
		for (int i = 0; i < dr.getColumnCount(); ++i) {
			value = dr.getString(i);
			key = dr.getDataColumns()[i].getColumnName();
			for (int j = 0; j < this.Columns.length; ++j) {
				SchemaColumn sc = this.Columns[j];
				if (!(key.equalsIgnoreCase(sc.getColumnName())))
					continue;
				try {
					int type = sc.getColumnType();
					if (type == 0)
						if ((value != null) && (!("".equals(value)))) {
							setV(j, DateUtil.parseDateTime(value.toString()));
						}
					if (type == 6) {
						setV(j, new Double(value.toString()));
					}
					if (type == 5) {
						setV(j, new Float(value.toString()));
					}
					if (type == 7) {
						setV(j, new Long(value.toString()));
					}
					if (type == 8) {
						setV(j, new Integer(value.toString()));
					}
					setV(j, value);
				} catch (Exception localException) {
				}
			}

		}

		dr.setWebMode(webMode);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (int i = 0; i < this.Columns.length; ++i) {
			sb.append(this.Columns[i].getColumnName());
			sb.append(":");
			sb.append(getV(i));
			sb.append(" ");
		}
		sb.append("}");
		return sb.toString();
	}

	public Object clone() {
		Schema s = newInstance();
		SchemaUtil.copyFieldValue(this, s);
		return s;
	}

	public Mapx toMapx() {
		Mapx map = new Mapx();
		for (int i = 0; i < this.Columns.length; ++i) {
			map.put(this.Columns[i].getColumnName(), getV(i));
		}
		return map;
	}

	public Mapx toMapx(boolean toLowerCase) {
		Mapx map = new Mapx();
		for (int i = 0; i < this.Columns.length; ++i) {
			String colName = this.Columns[i].getColumnName();
			if (toLowerCase) {
				colName = colName.toLowerCase();
			}
			map.put(colName, getV(i));
		}
		return map;
	}

	public DataRow toDataRow() {
		int len = this.Columns.length;
		DataColumn[] dcs = new DataColumn[len];
		Object[] values = new Object[len];
		for (int i = 0; i < len; ++i) {
			DataColumn dc = new DataColumn();
			dc.setColumnName(this.Columns[i].getColumnName());
			dc.setColumnType(this.Columns[i].getColumnType());
			dcs[i] = dc;
			values[i] = getV(i);
		}
		return new DataRow(dcs, values);
	}

	public int getColumnCount() {
		return this.Columns.length;
	}

	public abstract void setV(int paramInt, Object paramObject);

	public abstract Object getV(int paramInt);

	protected abstract Schema newInstance();

	protected abstract SchemaSet newSet();
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.orm.Schema JD-Core Version: 0.5.3
 */