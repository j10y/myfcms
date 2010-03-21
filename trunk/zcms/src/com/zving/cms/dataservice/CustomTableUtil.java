package com.zving.cms.dataservice;

import com.zving.framework.data.DataAccess;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.pub.OrderUtil;
import com.zving.schema.ZCCustomTableColumnSchema;
import com.zving.schema.ZCCustomTableColumnSet;
import com.zving.schema.ZCCustomTableSchema;
import com.zving.schema.ZCCustomTableSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomTableUtil {
	public static int getTotal(ZCCustomTableSchema table, String wherePart) {
		if ((wherePart != null) && (!(wherePart.trim().toLowerCase().startsWith("where")))) {
			throw new RuntimeException("指定的wherePart不是以where开头的字符串");
		}

		DataAccess da = null;
		String code = table.getCode();
		if (table.getType().equals("Link")) {
			da = new DataAccess(OuterDatabase.getConnection(table.getDatabaseID()));
			code = table.getOldCode();
		} else {
			da = new DataAccess();
		}

		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from ");
		sb.append(code);
		if (wherePart != null) {
			sb.append(" ");
			sb.append(wherePart);
		}
		try {
			return Integer.parseInt(da.executeOneValue(new QueryBuilder(sb.toString())).toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				da.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public static int getTotal(long tableID) {
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(tableID);
		table.fill();
		return getTotal(table, null);
	}

	public static int getTotal(String tableCode, String wherePart) {
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setCode(tableCode);
		ZCCustomTableSet set = table.query();
		if ((set == null) || (set.size() < 1)) {
			return -1;
		}
		return getTotal(set.get(0), wherePart);
	}

	public static DataTable getData(long tableID) {
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(tableID);
		table.fill();
		return getData(table);
	}

	public static DataTable getData(String tableCode) {
		return getData(tableCode, null);
	}

	public static DataTable getData(String tableCode, QueryBuilder wherePart) {
		return getData(tableCode, wherePart, -1, -1);
	}

	public static DataTable getData(String tableCode, QueryBuilder wherePart, int pageSize,
			int pageIndex) {
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setCode(tableCode);
		ZCCustomTableSet set = table.query();
		if ((set == null) || (set.size() < 1)) {
			return null;
		}
		return getData(set.get(0), wherePart, pageSize, pageIndex);
	}

	public static DataTable getData(ZCCustomTableSchema table) {
		return getData(table, null);
	}

	public static DataTable getData(ZCCustomTableSchema table, String wherePart) {
		return getData(table, null, -1, -1);
	}

	public static DataTable getData(long tableID, int pageSize, int pageIndex) {
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(tableID);
		table.fill();
		return getData(table, null, pageSize, pageIndex);
	}

	public static DataTable getData(long tableID, QueryBuilder wherePart, int pageSize,
			int pageIndex) {
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(tableID);
		table.fill();
		return getData(table, wherePart, pageSize, pageIndex);
	}

	public static DataTable getData(ZCCustomTableSchema table, QueryBuilder wherePart,
			int pageSize, int pageIndex) {
		if ((wherePart != null) && (!(wherePart.getSQL().trim().toLowerCase().startsWith("where")))) {
			throw new RuntimeException("指定的wherePart不是以where开头的字符串");
		}

		DataAccess da = null;
		DataTable dt = null;
		String code = table.getCode();
		if (table.getType().equals("Link")) {
			da = new DataAccess(OuterDatabase.getConnection(table.getDatabaseID()));
			code = table.getOldCode();
		} else {
			da = new DataAccess();
		}

		if (wherePart != null)
			wherePart.setSQL("select * from " + code + " " + wherePart.getSQL());
		else {
			wherePart = new QueryBuilder("select * from " + code);
		}

		pageIndex = (pageIndex < 0) ? 0 : pageIndex;
		try {
			if (pageSize > 0) {
				dt = da.executePagedDataTable(wherePart, pageSize, pageIndex);
			} else {
				dt = da.executeDataTable(wherePart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				da.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dt;
	}

	public static DataTable executeDataTable(String tableCode, QueryBuilder qb) {
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setCode(tableCode);
		ZCCustomTableSet set = table.query();
		if ((set == null) || (set.size() < 1)) {
			return null;
		}
		return executeDataTable(set.get(0), qb);
	}

	public static DataTable executeDataTable(ZCCustomTableSchema table, QueryBuilder qb) {
		DataAccess da = null;
		DataTable dt = null;
		String code = table.getCode();
		if (table.getType().equals("Link")) {
			da = new DataAccess(OuterDatabase.getConnection(table.getDatabaseID()));
			code = table.getOldCode();
		} else {
			da = new DataAccess();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append(code);
		qb.setSQL("select * from " + code + " " + qb.getSQL());
		try {
			dt = da.executeDataTable(qb);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				da.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dt;
	}

	public static Object executeOneValue(String tableCode, QueryBuilder qb) {
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setCode(tableCode);
		ZCCustomTableSet set = table.query();
		if ((set == null) || (set.size() < 1)) {
			return null;
		}
		return executeOneValue(set.get(0), qb);
	}

	public static Object executeOneValue(ZCCustomTableSchema table, QueryBuilder qb) {
		DataAccess da = null;
		Object dt = null;
		if (table.getType().equals("Link"))
			da = new DataAccess(OuterDatabase.getConnection(table.getDatabaseID()));
		else {
			da = new DataAccess();
		}
		try {
			dt = da.executeOneValue(qb);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				da.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dt;
	}

	public static boolean updateData(ZCCustomTableSchema table, DataTable dt) {
		DataAccess da = null;
		String code = table.getCode();
		if (table.getType().equals("Link")) {
			da = new DataAccess(OuterDatabase.getConnection(table.getDatabaseID()));
			code = table.getOldCode();
		} else {
			da = new DataAccess();
		}
		try {
			da.setAutoCommit(false);
			ZCCustomTableColumnSet set = new ZCCustomTableColumnSchema().query(new QueryBuilder(
					"where TableID=?", table.getID()));

			QueryBuilder qb = new QueryBuilder("delete from " + code + " where 1=1 ");
			qb.setBatchMode(true);
			ArrayList list = new ArrayList(4);
			for (int i = 0; i < set.size(); ++i) {
				if ("Y".equals(set.get(i).getIsPrimaryKey())) {
					qb.appendSQLPart(" and " + set.get(i).getCode() + "=?");
					list.add(set.get(i).getCode());
				}
			}
			for (int i = 0; i < dt.getRowCount(); ++i) {
				for (int j = 0; j < list.size(); ++j) {
					String v = dt.getString(i, list.get(j).toString());
					if (dt.getDataColumn(list.get(j).toString() + "_Old") != null) {
						v = dt.getString(i, list.get(j).toString() + "_Old");
					}
					if (StringUtil.isEmpty(v)) {
						v = null;
					}
					qb.add(v);
				}
				qb.addBatch();
			}
			da.executeNoQuery(qb);

			StringBuffer sb = new StringBuffer("insert into " + code + "(");
			for (int j = 0; j < set.size(); ++j) {
				if (j != 0) {
					sb.append(",");
				}
				sb.append(set.get(j).getCode());
			}
			sb.append(") values (");
			for (int j = 0; j < set.size(); ++j) {
				if (j != 0) {
					sb.append(",");
				}
				sb.append("?");
			}
			sb.append(")");
			qb = new QueryBuilder(sb.toString());
			qb.setBatchMode(true);
			for (int i = 0; i < dt.getRowCount(); ++i) {
				for (int j = 0; j < set.size(); ++j) {
					ZCCustomTableColumnSchema column = set.get(j);
					String v = dt.getString(i, set.get(j).getCode());
					if (StringUtil.isEmpty(v)) {
						v = null;
						if ("Y".equals(set.get(j).getIsAutoID())) {
							v = String.valueOf(OrderUtil.getDefaultOrder());
						}
					}
					if (((("Y".equals(column.getIsMandatory())) || ("Y".equals(column
							.getIsPrimaryKey()))))
							&& (StringUtil.isEmpty(v))) {
						LogUtil.warn(column.getName() + "不能为空!\\n");
						return false;
					}

					int dataType = Integer.parseInt(column.getDataType());
					if (v != null) {
						if ((column.getMaxLength() != 0) && (v.length() < column.getMaxLength())) {
							LogUtil.warn(column.getName() + "数据过长，最大允许" + column.getMaxLength()
									+ "个字符!\\n");
							return false;
						}
						try {
							if (dataType == 0) {
								v = DateUtil.toDateTimeString(DateUtil.parseDateTime(v));
								if (v == null) {
									throw new SQLException("日期时间错误");
								}
							}
							if ((dataType == 8) || (dataType == 9)) {
								v = String.valueOf(new Double(Double.parseDouble(v)).intValue());
							}
							if (dataType == 7) {
								v = String.valueOf(new Double(Double.parseDouble(v)).longValue());
							}
							if (dataType == 5) {
								v = String.valueOf(new Double(Double.parseDouble(v)).floatValue());
							}
							if ((dataType == 4) || (dataType == 6) || (dataType == 3))
								v = String.valueOf(Double.parseDouble(v));
						} catch (Exception e) {
							LogUtil.warn(column.getName() + "数据不正确!\\n");
							try {
								da.setAutoCommit(true);
								da.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							return false;
						}
					}
					qb.add(v);
				}
				qb.addBatch();
			}
			da.executeNoQuery(qb);
			da.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				da.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			try {
				da.setAutoCommit(true);
				da.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.dataservice.CustomTableUtil JD-Core Version: 0.5.3
 */