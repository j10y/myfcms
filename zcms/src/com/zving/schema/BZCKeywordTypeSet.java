 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCKeywordTypeSet extends SchemaSet
 {
   public BZCKeywordTypeSet()
   {
     this(10, 0);
   }
 
   public BZCKeywordTypeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCKeywordTypeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCKeywordType";
     this.Columns = BZCKeywordTypeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCKeywordType values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCKeywordType set ID=?,SiteID=?,TypeName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCKeywordType  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCKeywordType  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCKeywordTypeSet();
   }
 
   public boolean add(BZCKeywordTypeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCKeywordTypeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCKeywordTypeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCKeywordTypeSchema get(int index) {
     BZCKeywordTypeSchema tSchema = (BZCKeywordTypeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCKeywordTypeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCKeywordTypeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCKeywordTypeSet
 * JD-Core Version:    0.5.3
 */