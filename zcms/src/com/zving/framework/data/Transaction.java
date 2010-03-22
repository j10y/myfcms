package com.zving.framework.data;

import com.zving.framework.orm.Schema;
import com.zving.framework.orm.SchemaSet;
import com.zving.framework.utility.Mapx;
import java.sql.SQLException;

public class Transaction {
	public static final int INSERT = 1;
	public static final int UPDATE = 2;
	public static final int DELETE = 3;
	public static final int BACKUP = 4;
	public static final int DELETE_AND_BACKUP = 5;
	public static final int DELETE_AND_INSERT = 6;
	public static final int SQL = 4;
	private int bConnFlag = 0;
	private DataAccess mDataAccess;
	private Mapx mapx = new Mapx();
	private String BackupOperator;
	private String BackupMemo;
	private String ExceptionMessage;

	public void setDataAccess(DataAccess dAccess) {
		this.mDataAccess = dAccess;
		this.bConnFlag = 1;
	}

	public void add(QueryBuilder qb) {
		this.mapx.put(qb, new Integer(4));
	}

	public void add(Schema schema, int type) {
		this.mapx.put(schema, new Integer(type));
	}

	public void add(SchemaSet set, int type) {
		this.mapx.put(set, new Integer(type));
	}

	public boolean commit() {
		return commit(true);
	}

	public boolean commit(boolean setAutoCommitStatus) {
		if (this.bConnFlag == 0) {
			this.mDataAccess = new DataAccess();
		}
		boolean NoErrFlag = true;
		Object[] ks;
		Object[] vs;
		int i;
		try {
			if ((this.bConnFlag == 0) || (setAutoCommitStatus)) {
				this.mDataAccess.setAutoCommit(false);
			}
			ks = this.mapx.keyArray();
			vs = this.mapx.valueArray();
			i = 0;
			Object obj = ks[i];
			int type = ((Integer) vs[i]).intValue();
			if (obj instanceof QueryBuilder) {
				this.mDataAccess.executeNoQuery((QueryBuilder) obj);
			} else {
				if (obj instanceof Schema) {
					Schema s = (Schema) obj;
					s.setDataAccess(this.mDataAccess);
					if (type == 1) {
						if (s.insert())
							NoErrFlag = false;
					}
					while (true) {
						if (type == 2) {
							if (s.update())
								NoErrFlag = false;
						}

						if (type == 3) {
							if (s.delete())
								NoErrFlag = false;
						}

						if (type == 4) {
							if (s.backup(this.BackupOperator, this.BackupMemo))
								NoErrFlag = false;
						}

						if (type == 5) {
							if (s.deleteAndBackup(this.BackupOperator, this.BackupMemo))
								NoErrFlag = false;
						}

						if ((type != 6) || (s.deleteAndInsert()))
							NoErrFlag = false;
					}
				}

				if (obj instanceof SchemaSet) {
					SchemaSet s = (SchemaSet) obj;
					s.setDataAccess(this.mDataAccess);
					if (type == 1) {
						if (!(s.insert())) {
							NoErrFlag = false;
							return false;
						}
					} else if (type == 2) {
						if (!(s.update())) {
							NoErrFlag = false;
							return false;
						}
					} else if (type == 3) {
						if (!(s.delete())) {
							NoErrFlag = false;
							return false;
						}
					} else if (type == 4) {
						if (!(s.backup(this.BackupOperator, this.BackupMemo))) {
							NoErrFlag = false;
							return false;
						}
					} else if (type == 5) {
						if (!(s.deleteAndBackup(this.BackupOperator, this.BackupMemo))) {
							NoErrFlag = false;
							return false;
						}
					} else if ((type == 6) && (!(s.deleteAndInsert()))) {
						NoErrFlag = false;

						if (!(NoErrFlag))
							try {
								this.mDataAccess.rollback();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						try {
							if ((this.bConnFlag == 0) || (setAutoCommitStatus))
								this.mDataAccess.setAutoCommit(true);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if (this.bConnFlag != 0)
							;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.ExceptionMessage = e.getMessage();
			NoErrFlag = false;
			return false;
		} finally {
			if (!(NoErrFlag))
				try {
					this.mDataAccess.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			try {
				if ((this.bConnFlag == 0) || (setAutoCommitStatus))
					this.mDataAccess.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (this.bConnFlag == 0)
				try {
					this.mDataAccess.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		if (!(NoErrFlag))
			try {
				this.mDataAccess.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try {
			if ((this.bConnFlag == 0) || (setAutoCommitStatus))
				this.mDataAccess.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (this.bConnFlag == 0) {
			try {
				this.mDataAccess.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public void clear() {
		this.mapx.clear();
	}

	public String getExceptionMessage() {
		return this.ExceptionMessage;
	}

	public String getBackupMemo() {
		return this.BackupMemo;
	}

	public void setBackupMemo(String backupMemo) {
		this.BackupMemo = backupMemo;
	}

	public String getBackupOperator() {
		return this.BackupOperator;
	}

	public void setBackupOperator(String backupOperator) {
		this.BackupOperator = backupOperator;
	}

	public Mapx getOperateMap() {
		return this.mapx;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.data.Transaction JD-Core Version: 0.5.3
 */