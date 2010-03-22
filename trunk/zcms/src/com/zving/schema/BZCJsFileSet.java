 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCJsFileSet extends SchemaSet
 {
   public BZCJsFileSet()
   {
     this(10, 0);
   }
 
   public BZCJsFileSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCJsFileSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCJsFile";
     this.Columns = BZCJsFileSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCJsFile values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCJsFile set ID=?,SiteID=?,JsName=?,JsDesc=?,JsFileName=?,Param=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCJsFile  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCJsFile  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCJsFileSet();
   }
 
   public boolean add(BZCJsFileSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCJsFileSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCJsFileSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCJsFileSchema get(int index) {
     BZCJsFileSchema tSchema = (BZCJsFileSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCJsFileSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCJsFileSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCJsFileSet
 * JD-Core Version:    0.5.3
 */