 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCMagazineIssueSet extends SchemaSet
 {
   public ZCMagazineIssueSet()
   {
     this(10, 0);
   }
 
   public ZCMagazineIssueSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCMagazineIssueSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCMagazineIssue";
     this.Columns = ZCMagazineIssueSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCMagazineIssue values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCMagazineIssue set ID=?,MagazineID=?,Year=?,PeriodNum=?,CoverImage=?,CoverTemplate=?,PublishDate=?,Status=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCMagazineIssue  where ID=?";
     this.DeleteSQL = "delete from ZCMagazineIssue  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCMagazineIssueSet();
   }
 
   public boolean add(ZCMagazineIssueSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCMagazineIssueSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCMagazineIssueSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCMagazineIssueSchema get(int index) {
     ZCMagazineIssueSchema tSchema = (ZCMagazineIssueSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCMagazineIssueSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCMagazineIssueSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCMagazineIssueSet
 * JD-Core Version:    0.5.3
 */