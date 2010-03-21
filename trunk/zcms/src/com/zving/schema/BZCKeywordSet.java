 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCKeywordSet extends SchemaSet
 {
   public BZCKeywordSet()
   {
     this(10, 0);
   }
 
   public BZCKeywordSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCKeywordSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCKeyword";
     this.Columns = BZCKeywordSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCKeyword values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCKeyword set ID=?,Keyword=?,SiteId=?,KeywordType=?,LinkUrl=?,LinkTarget=?,LinkAlt=?,HintCount=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCKeyword  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCKeyword  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCKeywordSet();
   }
 
   public boolean add(BZCKeywordSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCKeywordSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCKeywordSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCKeywordSchema get(int index) {
     BZCKeywordSchema tSchema = (BZCKeywordSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCKeywordSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCKeywordSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCKeywordSet
 * JD-Core Version:    0.5.3
 */