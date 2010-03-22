 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCPaperPageSet extends SchemaSet
 {
   public BZCPaperPageSet()
   {
     this(10, 0);
   }
 
   public BZCPaperPageSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCPaperPageSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCPaperPage";
     this.Columns = BZCPaperPageSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCPaperPage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCPaperPage set ID=?,IssueID=?,PageNo=?,Title=?,PaperImage=?,PDFFile=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCPaperPage  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCPaperPage  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCPaperPageSet();
   }
 
   public boolean add(BZCPaperPageSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCPaperPageSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCPaperPageSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCPaperPageSchema get(int index) {
     BZCPaperPageSchema tSchema = (BZCPaperPageSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCPaperPageSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCPaperPageSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCPaperPageSet
 * JD-Core Version:    0.5.3
 */