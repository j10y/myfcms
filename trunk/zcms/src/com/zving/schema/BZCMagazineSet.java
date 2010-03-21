 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCMagazineSet extends SchemaSet
 {
   public BZCMagazineSet()
   {
     this(10, 0);
   }
 
   public BZCMagazineSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCMagazineSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCMagazine";
     this.Columns = BZCMagazineSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCMagazine values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCMagazine set ID=?,SiteID=?,Name=?,Alias=?,CoverImage=?,CoverTemplate=?,OpenFlag=?,Period=?,Total=?,CurrentYear=?,CurrentPeriodNum=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCMagazine  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCMagazine  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCMagazineSet();
   }
 
   public boolean add(BZCMagazineSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCMagazineSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCMagazineSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCMagazineSchema get(int index) {
     BZCMagazineSchema tSchema = (BZCMagazineSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCMagazineSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCMagazineSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCMagazineSet
 * JD-Core Version:    0.5.3
 */