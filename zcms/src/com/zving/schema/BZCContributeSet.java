 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCContributeSet extends SchemaSet
 {
   public BZCContributeSet()
   {
     this(10, 0);
   }
 
   public BZCContributeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCContributeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCContribute";
     this.Columns = BZCContributeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCContribute values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCContribute set ID=?,AuthorID=?,AritcleID=?,Memo=?,ContributeDate=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCContribute  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCContribute  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCContributeSet();
   }
 
   public boolean add(BZCContributeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCContributeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCContributeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCContributeSchema get(int index) {
     BZCContributeSchema tSchema = (BZCContributeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCContributeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCContributeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCContributeSet
 * JD-Core Version:    0.5.3
 */