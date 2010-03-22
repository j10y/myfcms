 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCImageRelaSchema extends Schema
 {
   private Long ID;
   private Long RelaID;
   private String RelaType;
   private Long OrderFlag;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("RelaID", 7, 1, 0, 0, true, true), 
     new SchemaColumn("RelaType", 1, 2, 20, 0, true, true), 
     new SchemaColumn("OrderFlag", 7, 3, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 4, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 5, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 6, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 7, 0, 0, false, false) };
   public static final String _TableCode = "ZCImageRela";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCImageRela values(?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCImageRela set ID=?,RelaID=?,RelaType=?,OrderFlag=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=? and RelaID=? and RelaType=?";
   protected static final String _DeleteSQL = "delete from ZCImageRela  where ID=? and RelaID=? and RelaType=?";
   protected static final String _FillAllSQL = "select * from ZCImageRela  where ID=? and RelaID=? and RelaType=?";
 
   public ZCImageRelaSchema()
   {
     this.TableCode = "ZCImageRela";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCImageRela values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCImageRela set ID=?,RelaID=?,RelaType=?,OrderFlag=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=? and RelaID=? and RelaType=?";
     this.DeleteSQL = "delete from ZCImageRela  where ID=? and RelaID=? and RelaType=?";
     this.FillAllSQL = "select * from ZCImageRela  where ID=? and RelaID=? and RelaType=?";
     this.HasSetFlag = new boolean[8];
   }
 
   protected Schema newInstance() {
     return new ZCImageRelaSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCImageRelaSet();
   }
 
   public ZCImageRelaSet query() {
     return query(null, -1, -1);
   }
 
   public ZCImageRelaSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCImageRelaSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCImageRelaSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCImageRelaSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.RelaID = null; else this.RelaID = new Long(v.toString()); return; }
     if (i == 2) { this.RelaType = ((String)v); return; }
     if (i == 3) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 4) { this.AddUser = ((String)v); return; }
     if (i == 5) { this.AddTime = ((Date)v); return; }
     if (i == 6) { this.ModifyUser = ((String)v); return; }
     if (i != 7) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.RelaID;
     if (i == 2) return this.RelaType;
     if (i == 3) return this.OrderFlag;
     if (i == 4) return this.AddUser;
     if (i == 5) return this.AddTime;
     if (i == 6) return this.ModifyUser;
     if (i == 7) return this.ModifyTime;
     return null;
   }
 
   public long getID()
   {
     if (this.ID == null) return 0L;
     return this.ID.longValue();
   }
 
   public void setID(long iD)
   {
     this.ID = new Long(iD);
   }
 
   public void setID(String iD)
   {
     if (iD == null) {
       this.ID = null;
       return;
     }
     this.ID = new Long(iD);
   }
 
   public long getRelaID()
   {
     if (this.RelaID == null) return 0L;
     return this.RelaID.longValue();
   }
 
   public void setRelaID(long relaID)
   {
     this.RelaID = new Long(relaID);
   }
 
   public void setRelaID(String relaID)
   {
     if (relaID == null) {
       this.RelaID = null;
       return;
     }
     this.RelaID = new Long(relaID);
   }
 
   public String getRelaType()
   {
     return this.RelaType;
   }
 
   public void setRelaType(String relaType)
   {
     this.RelaType = relaType;
   }
 
   public long getOrderFlag()
   {
     if (this.OrderFlag == null) return 0L;
     return this.OrderFlag.longValue();
   }
 
   public void setOrderFlag(long orderFlag)
   {
     this.OrderFlag = new Long(orderFlag);
   }
 
   public void setOrderFlag(String orderFlag)
   {
     if (orderFlag == null) {
       this.OrderFlag = null;
       return;
     }
     this.OrderFlag = new Long(orderFlag);
   }
 
   public String getAddUser()
   {
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 
   public String getModifyUser()
   {
     return this.ModifyUser;
   }
 
   public void setModifyUser(String modifyUser)
   {
     this.ModifyUser = modifyUser;
   }
 
   public Date getModifyTime()
   {
     return this.ModifyTime;
   }
 
   public void setModifyTime(Date modifyTime)
   {
     this.ModifyTime = modifyTime;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCImageRelaSchema
 * JD-Core Version:    0.5.3
 */