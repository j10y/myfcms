 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCPaperIssueSet extends SchemaSet
 {
   public ZCPaperIssueSet()
   {
     this(10, 0);
   }
 
   public ZCPaperIssueSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCPaperIssueSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCPaperIssue";
     this.Columns = ZCPaperIssueSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCPaperIssue values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCPaperIssue set ID=?,PaperID=?,Year=?,PeriodNum=?,CoverImage=?,CoverTemplate=?,PublishDate=?,Status=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCPaperIssue  where ID=?";
     this.DeleteSQL = "delete from ZCPaperIssue  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCPaperIssueSet();
   }
 
   public boolean add(ZCPaperIssueSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCPaperIssueSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCPaperIssueSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCPaperIssueSchema get(int index) {
     ZCPaperIssueSchema tSchema = (ZCPaperIssueSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCPaperIssueSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCPaperIssueSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCPaperIssueSet
 * JD-Core Version:    0.5.3
 */