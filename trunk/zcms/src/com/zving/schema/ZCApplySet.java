 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCApplySet extends SchemaSet
 {
   public ZCApplySet()
   {
     this(10, 0);
   }
 
   public ZCApplySet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCApplySet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCApply";
     this.Columns = ZCApplySchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCApply values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCApply set ID=?,SiteID=?,Name=?,Gender=?,BirthDate=?,Picture=?,Ethnicity=?,NativePlace=?,Political=?,CertNumber=?,Phone=?,Mobile=?,Address=?,Postcode=?,Email=?,ForeignLanguage=?,LanguageLevel=?,Authentification=?,PersonIntro=?,Honour=?,PracticeExperience=?,RegisteredPlace=?,EduLevel=?,University=?,Speacility=?,WillPosition=?,AuditUser=?,AuditStatus=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
     this.FillAllSQL = "select * from ZCApply  where ID=?";
     this.DeleteSQL = "delete from ZCApply  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCApplySet();
   }
 
   public boolean add(ZCApplySchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCApplySet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCApplySchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCApplySchema get(int index) {
     ZCApplySchema tSchema = (ZCApplySchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCApplySchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCApplySet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCApplySet
 * JD-Core Version:    0.5.3
 */