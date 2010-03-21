 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCAdminGroupSchema extends Schema
 {
   private Long GroupID;
   private Long SiteID;
   private String AllowEditUser;
   private String AllowForbidUser;
   private String AllowEditForum;
   private String AllowVerfyPost;
   private String AllowDel;
   private String AllowEdit;
   private String Hide;
   private String RemoveTheme;
   private String MoveTheme;
   private String TopTheme;
   private String BrightTheme;
   private String UpOrDownTheme;
   private String BestTheme;
   private String prop1;
   private String prop2;
   private String prop3;
   private String prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("GroupID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, false, false), 
     new SchemaColumn("AllowEditUser", 1, 2, 2, 0, false, false), 
     new SchemaColumn("AllowForbidUser", 1, 3, 2, 0, false, false), 
     new SchemaColumn("AllowEditForum", 1, 4, 2, 0, false, false), 
     new SchemaColumn("AllowVerfyPost", 1, 5, 2, 0, false, false), 
     new SchemaColumn("AllowDel", 1, 6, 2, 0, false, false), 
     new SchemaColumn("AllowEdit", 1, 7, 2, 0, false, false), 
     new SchemaColumn("Hide", 1, 8, 2, 0, false, false), 
     new SchemaColumn("RemoveTheme", 1, 9, 2, 0, false, false), 
     new SchemaColumn("MoveTheme", 1, 10, 2, 0, false, false), 
     new SchemaColumn("TopTheme", 1, 11, 2, 0, false, false), 
     new SchemaColumn("BrightTheme", 1, 12, 2, 0, false, false), 
     new SchemaColumn("UpOrDownTheme", 1, 13, 2, 0, false, false), 
     new SchemaColumn("BestTheme", 1, 14, 2, 0, false, false), 
     new SchemaColumn("prop1", 1, 15, 50, 0, false, false), 
     new SchemaColumn("prop2", 1, 16, 50, 0, false, false), 
     new SchemaColumn("prop3", 1, 17, 50, 0, false, false), 
     new SchemaColumn("prop4", 1, 18, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 19, 100, 0, true, false), 
     new SchemaColumn("AddTime", 0, 20, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 21, 100, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 22, 0, 0, false, false) };
   public static final String _TableCode = "ZCAdminGroup";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCAdminGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCAdminGroup set GroupID=?,SiteID=?,AllowEditUser=?,AllowForbidUser=?,AllowEditForum=?,AllowVerfyPost=?,AllowDel=?,AllowEdit=?,Hide=?,RemoveTheme=?,MoveTheme=?,TopTheme=?,BrightTheme=?,UpOrDownTheme=?,BestTheme=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where GroupID=?";
   protected static final String _DeleteSQL = "delete from ZCAdminGroup  where GroupID=?";
   protected static final String _FillAllSQL = "select * from ZCAdminGroup  where GroupID=?";
 
   public ZCAdminGroupSchema()
   {
     this.TableCode = "ZCAdminGroup";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCAdminGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAdminGroup set GroupID=?,SiteID=?,AllowEditUser=?,AllowForbidUser=?,AllowEditForum=?,AllowVerfyPost=?,AllowDel=?,AllowEdit=?,Hide=?,RemoveTheme=?,MoveTheme=?,TopTheme=?,BrightTheme=?,UpOrDownTheme=?,BestTheme=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where GroupID=?";
     this.DeleteSQL = "delete from ZCAdminGroup  where GroupID=?";
     this.FillAllSQL = "select * from ZCAdminGroup  where GroupID=?";
     this.HasSetFlag = new boolean[23];
   }
 
   protected Schema newInstance() {
     return new ZCAdminGroupSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCAdminGroupSet();
   }
 
   public ZCAdminGroupSet query() {
     return query(null, -1, -1);
   }
 
   public ZCAdminGroupSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCAdminGroupSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCAdminGroupSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCAdminGroupSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.GroupID = null; else this.GroupID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.AllowEditUser = ((String)v); return; }
     if (i == 3) { this.AllowForbidUser = ((String)v); return; }
     if (i == 4) { this.AllowEditForum = ((String)v); return; }
     if (i == 5) { this.AllowVerfyPost = ((String)v); return; }
     if (i == 6) { this.AllowDel = ((String)v); return; }
     if (i == 7) { this.AllowEdit = ((String)v); return; }
     if (i == 8) { this.Hide = ((String)v); return; }
     if (i == 9) { this.RemoveTheme = ((String)v); return; }
     if (i == 10) { this.MoveTheme = ((String)v); return; }
     if (i == 11) { this.TopTheme = ((String)v); return; }
     if (i == 12) { this.BrightTheme = ((String)v); return; }
     if (i == 13) { this.UpOrDownTheme = ((String)v); return; }
     if (i == 14) { this.BestTheme = ((String)v); return; }
     if (i == 15) { this.prop1 = ((String)v); return; }
     if (i == 16) { this.prop2 = ((String)v); return; }
     if (i == 17) { this.prop3 = ((String)v); return; }
     if (i == 18) { this.prop4 = ((String)v); return; }
     if (i == 19) { this.AddUser = ((String)v); return; }
     if (i == 20) { this.AddTime = ((Date)v); return; }
     if (i == 21) { this.ModifyUser = ((String)v); return; }
     if (i != 22) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.GroupID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.AllowEditUser;
     if (i == 3) return this.AllowForbidUser;
     if (i == 4) return this.AllowEditForum;
     if (i == 5) return this.AllowVerfyPost;
     if (i == 6) return this.AllowDel;
     if (i == 7) return this.AllowEdit;
     if (i == 8) return this.Hide;
     if (i == 9) return this.RemoveTheme;
     if (i == 10) return this.MoveTheme;
     if (i == 11) return this.TopTheme;
     if (i == 12) return this.BrightTheme;
     if (i == 13) return this.UpOrDownTheme;
     if (i == 14) return this.BestTheme;
     if (i == 15) return this.prop1;
     if (i == 16) return this.prop2;
     if (i == 17) return this.prop3;
     if (i == 18) return this.prop4;
     if (i == 19) return this.AddUser;
     if (i == 20) return this.AddTime;
     if (i == 21) return this.ModifyUser;
     if (i == 22) return this.ModifyTime;
     return null;
   }
 
   public long getGroupID()
   {
     if (this.GroupID == null) return 0L;
     return this.GroupID.longValue();
   }
 
   public void setGroupID(long groupID)
   {
     this.GroupID = new Long(groupID);
   }
 
   public void setGroupID(String groupID)
   {
     if (groupID == null) {
       this.GroupID = null;
       return;
     }
     this.GroupID = new Long(groupID);
   }
 
   public long getSiteID()
   {
     if (this.SiteID == null) return 0L;
     return this.SiteID.longValue();
   }
 
   public void setSiteID(long siteID)
   {
     this.SiteID = new Long(siteID);
   }
 
   public void setSiteID(String siteID)
   {
     if (siteID == null) {
       this.SiteID = null;
       return;
     }
     this.SiteID = new Long(siteID);
   }
 
   public String getAllowEditUser()
   {
     return this.AllowEditUser;
   }
 
   public void setAllowEditUser(String allowEditUser)
   {
     this.AllowEditUser = allowEditUser;
   }
 
   public String getAllowForbidUser()
   {
     return this.AllowForbidUser;
   }
 
   public void setAllowForbidUser(String allowForbidUser)
   {
     this.AllowForbidUser = allowForbidUser;
   }
 
   public String getAllowEditForum()
   {
     return this.AllowEditForum;
   }
 
   public void setAllowEditForum(String allowEditForum)
   {
     this.AllowEditForum = allowEditForum;
   }
 
   public String getAllowVerfyPost()
   {
     return this.AllowVerfyPost;
   }
 
   public void setAllowVerfyPost(String allowVerfyPost)
   {
     this.AllowVerfyPost = allowVerfyPost;
   }
 
   public String getAllowDel()
   {
     return this.AllowDel;
   }
 
   public void setAllowDel(String allowDel)
   {
     this.AllowDel = allowDel;
   }
 
   public String getAllowEdit()
   {
     return this.AllowEdit;
   }
 
   public void setAllowEdit(String allowEdit)
   {
     this.AllowEdit = allowEdit;
   }
 
   public String getHide()
   {
     return this.Hide;
   }
 
   public void setHide(String hide)
   {
     this.Hide = hide;
   }
 
   public String getRemoveTheme()
   {
     return this.RemoveTheme;
   }
 
   public void setRemoveTheme(String removeTheme)
   {
     this.RemoveTheme = removeTheme;
   }
 
   public String getMoveTheme()
   {
     return this.MoveTheme;
   }
 
   public void setMoveTheme(String moveTheme)
   {
     this.MoveTheme = moveTheme;
   }
 
   public String getTopTheme()
   {
     return this.TopTheme;
   }
 
   public void setTopTheme(String topTheme)
   {
     this.TopTheme = topTheme;
   }
 
   public String getBrightTheme()
   {
     return this.BrightTheme;
   }
 
   public void setBrightTheme(String brightTheme)
   {
     this.BrightTheme = brightTheme;
   }
 
   public String getUpOrDownTheme()
   {
     return this.UpOrDownTheme;
   }
 
   public void setUpOrDownTheme(String upOrDownTheme)
   {
     this.UpOrDownTheme = upOrDownTheme;
   }
 
   public String getBestTheme()
   {
     return this.BestTheme;
   }
 
   public void setBestTheme(String bestTheme)
   {
     this.BestTheme = bestTheme;
   }
 
   public String getProp1()
   {
     return this.prop1;
   }
 
   public void setProp1(String prop1)
   {
     this.prop1 = prop1;
   }
 
   public String getProp2()
   {
     return this.prop2;
   }
 
   public void setProp2(String prop2)
   {
     this.prop2 = prop2;
   }
 
   public String getProp3()
   {
     return this.prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.prop3 = prop3;
   }
 
   public String getProp4()
   {
     return this.prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.prop4 = prop4;
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
 * Qualified Name:     com.zving.schema.ZCAdminGroupSchema
 * JD-Core Version:    0.5.3
 */