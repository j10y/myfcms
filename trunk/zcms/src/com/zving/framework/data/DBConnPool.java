package com.zving.framework.data;

import com.zving.framework.Config;
import com.zving.framework.utility.Mapx;

public class DBConnPool {
	protected static Mapx PoolMap = new Mapx();

	public static DBConn getConnection() {
		return getConnection(null, false);
	}

	public static DBConn getConnection(boolean bLongTimeFlag) {
		return getConnection(null, bLongTimeFlag);
	}

	public static DBConn getConnection(String poolName) {
		return getConnection(poolName, false);
	}

	public static DBConnConfig getDBConnConfig() {
		return getDBConnConfig(null);
	}

	public static DBConnConfig getDBConnConfig(String poolName) {
		if ((poolName == null) || (poolName.equals(""))) {
			poolName = "Default";
		}
		poolName = poolName + ".";
		Object o = PoolMap.get(poolName);
		DBConnPoolImpl pool = null;
		if (o == null) {
			if (Config.getValue("Database." + poolName + "Type") != null) {
				pool = new DBConnPoolImpl(poolName);
				PoolMap.put(poolName, pool);
			} else {
				throw new RuntimeException("指定的连接池不存在:" + poolName);
			}
		}

		pool = (DBConnPoolImpl) o;

		return pool.getDBConnConfig();
	}

	public static DBConn getConnection(String poolName, boolean bLongTimeFlag) {
		if ((poolName == null) || (poolName.equals(""))) {
			poolName = "Default";
		}
		poolName = poolName + ".";
		Object o = PoolMap.get(poolName);
		DBConnPoolImpl pool = null;
		if (o == null) {
			synchronized (DBConnPool.class) {
				if (Config.getValue("Database." + poolName + "Type") != null) {
					pool = new DBConnPoolImpl(poolName);
					PoolMap.put(poolName, pool);
				} else {
					throw new RuntimeException("指定的连接池不存在:" + poolName);
				}
			}
		} else {
			pool = (DBConnPoolImpl) o;
		}
		return pool.getConnection(bLongTimeFlag);
	}

	public static Mapx getPoolMap() {
		return PoolMap;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.data.DBConnPool JD-Core Version: 0.5.3
 */