 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCPaperSet extends SchemaSet
 {
   public BZCPaperSet()
   {
     this(10, 0);
   }
 
   public BZCPaperSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCPaperSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCPaper";
     this.Columns = BZCPaperSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCPaper values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCPaper set ID=?,SiteID=?,Name=?,Alias=?,CoverImage=?,Period=?,OpenFlag=?,Total=?,CurrentYear=?,CurrentPeriodNum=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCPaper  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCPaper  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCPaperSet();
   }
 
   public boolean add(BZCPaperSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCPaperSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCPaperSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCPaperSchema get(int index) {
     BZCPaperSchema tSchema = (BZCPaperSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCPaperSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCPaperSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCPaperSet
 * JD-Core Version:    0.5.3
 */