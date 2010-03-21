 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCApplySet extends SchemaSet
 {
   public BZCApplySet()
   {
     this(10, 0);
   }
 
   public BZCApplySet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCApplySet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCApply";
     this.Columns = BZCApplySchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCApply values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCApply set ID=?,SiteID=?,Name=?,Gender=?,BirthDate=?,Picture=?,Ethnicity=?,NativePlace=?,Political=?,CertNumber=?,Phone=?,Mobile=?,Address=?,Postcode=?,Email=?,ForeignLanguage=?,LanguageLevel=?,Authentification=?,PersonIntro=?,Honour=?,PracticeExperience=?,RegisteredPlace=?,EduLevel=?,University=?,Speacility=?,WillPosition=?,AuditUser=?,AuditStatus=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCApply  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCApply  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCApplySet();
   }
 
   public boolean add(BZCApplySchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCApplySet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCApplySchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCApplySchema get(int index) {
     BZCApplySchema tSchema = (BZCApplySchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCApplySchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCApplySet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCApplySet
 * JD-Core Version:    0.5.3
 */