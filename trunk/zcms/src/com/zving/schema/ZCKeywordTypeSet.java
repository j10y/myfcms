 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCKeywordTypeSet extends SchemaSet
 {
   public ZCKeywordTypeSet()
   {
     this(10, 0);
   }
 
   public ZCKeywordTypeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCKeywordTypeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCKeywordType";
     this.Columns = ZCKeywordTypeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCKeywordType values(?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCKeywordType set ID=?,SiteID=?,TypeName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCKeywordType  where ID=?";
     this.DeleteSQL = "delete from ZCKeywordType  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCKeywordTypeSet();
   }
 
   public boolean add(ZCKeywordTypeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCKeywordTypeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCKeywordTypeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCKeywordTypeSchema get(int index) {
     ZCKeywordTypeSchema tSchema = (ZCKeywordTypeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCKeywordTypeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCKeywordTypeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCKeywordTypeSet
 * JD-Core Version:    0.5.3
 */