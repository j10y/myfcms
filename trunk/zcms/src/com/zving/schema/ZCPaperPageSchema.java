 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCPaperPageSchema extends Schema
 {
   private Long ID;
   private Long IssueID;
   private String PageNo;
   private String Title;
   private String PaperImage;
   private String PDFFile;
   private String Memo;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("IssueID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("PageNo", 1, 2, 20, 0, false, false), 
     new SchemaColumn("Title", 1, 3, 100, 0, false, false), 
     new SchemaColumn("PaperImage", 1, 4, 100, 0, false, false), 
     new SchemaColumn("PDFFile", 1, 5, 100, 0, false, false), 
     new SchemaColumn("Memo", 1, 6, 1000, 0, false, false), 
     new SchemaColumn("Prop1", 1, 7, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 8, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 9, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 10, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 11, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 12, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 13, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 14, 0, 0, false, false) };
   public static final String _TableCode = "ZCPaperPage";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCPaperPage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCPaperPage set ID=?,IssueID=?,PageNo=?,Title=?,PaperImage=?,PDFFile=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCPaperPage  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCPaperPage  where ID=?";
 
   public ZCPaperPageSchema()
   {
     this.TableCode = "ZCPaperPage";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCPaperPage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCPaperPage set ID=?,IssueID=?,PageNo=?,Title=?,PaperImage=?,PDFFile=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCPaperPage  where ID=?";
     this.FillAllSQL = "select * from ZCPaperPage  where ID=?";
     this.HasSetFlag = new boolean[15];
   }
 
   protected Schema newInstance() {
     return new ZCPaperPageSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCPaperPageSet();
   }
 
   public ZCPaperPageSet query() {
     return query(null, -1, -1);
   }
 
   public ZCPaperPageSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCPaperPageSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCPaperPageSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCPaperPageSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.IssueID = null; else this.IssueID = new Long(v.toString()); return; }
     if (i == 2) { this.PageNo = ((String)v); return; }
     if (i == 3) { this.Title = ((String)v); return; }
     if (i == 4) { this.PaperImage = ((String)v); return; }
     if (i == 5) { this.PDFFile = ((String)v); return; }
     if (i == 6) { this.Memo = ((String)v); return; }
     if (i == 7) { this.Prop1 = ((String)v); return; }
     if (i == 8) { this.Prop2 = ((String)v); return; }
     if (i == 9) { this.Prop3 = ((String)v); return; }
     if (i == 10) { this.Prop4 = ((String)v); return; }
     if (i == 11) { this.AddUser = ((String)v); return; }
     if (i == 12) { this.AddTime = ((Date)v); return; }
     if (i == 13) { this.ModifyUser = ((String)v); return; }
     if (i != 14) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.IssueID;
     if (i == 2) return this.PageNo;
     if (i == 3) return this.Title;
     if (i == 4) return this.PaperImage;
     if (i == 5) return this.PDFFile;
     if (i == 6) return this.Memo;
     if (i == 7) return this.Prop1;
     if (i == 8) return this.Prop2;
     if (i == 9) return this.Prop3;
     if (i == 10) return this.Prop4;
     if (i == 11) return this.AddUser;
     if (i == 12) return this.AddTime;
     if (i == 13) return this.ModifyUser;
     if (i == 14) return this.ModifyTime;
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
 
   public long getIssueID()
   {
     if (this.IssueID == null) return 0L;
     return this.IssueID.longValue();
   }
 
   public void setIssueID(long issueID)
   {
     this.IssueID = new Long(issueID);
   }
 
   public void setIssueID(String issueID)
   {
     if (issueID == null) {
       this.IssueID = null;
       return;
     }
     this.IssueID = new Long(issueID);
   }
 
   public String getPageNo()
   {
     return this.PageNo;
   }
 
   public void setPageNo(String pageNo)
   {
     this.PageNo = pageNo;
   }
 
   public String getTitle()
   {
     return this.Title;
   }
 
   public void setTitle(String title)
   {
     this.Title = title;
   }
 
   public String getPaperImage()
   {
     return this.PaperImage;
   }
 
   public void setPaperImage(String paperImage)
   {
     this.PaperImage = paperImage;
   }
 
   public String getPDFFile()
   {
     return this.PDFFile;
   }
 
   public void setPDFFile(String pDFFile)
   {
     this.PDFFile = pDFFile;
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
   }
 
   public String getProp1()
   {
     return this.Prop1;
   }
 
   public void setProp1(String prop1)
   {
     this.Prop1 = prop1;
   }
 
   public String getProp2()
   {
     return this.Prop2;
   }
 
   public void setProp2(String prop2)
   {
     this.Prop2 = prop2;
   }
 
   public String getProp3()
   {
     return this.Prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.Prop3 = prop3;
   }
 
   public String getProp4()
   {
     return this.Prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.Prop4 = prop4;
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
 * Qualified Name:     com.zving.schema.ZCPaperPageSchema
 * JD-Core Version:    0.5.3
 */