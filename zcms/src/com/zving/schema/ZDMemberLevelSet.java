 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDMemberLevelSet extends SchemaSet
 {
   public ZDMemberLevelSet()
   {
     this(10, 0);
   }
 
   public ZDMemberLevelSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDMemberLevelSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDMemberLevel";
     this.Columns = ZDMemberLevelSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDMemberLevel values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDMemberLevel set ID=?,Name=?,Type=?,Discount=?,IsDefault=?,TreeLevel=?,Score=?,IsValidate=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZDMemberLevel  where ID=?";
     this.DeleteSQL = "delete from ZDMemberLevel  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDMemberLevelSet();
   }
 
   public boolean add(ZDMemberLevelSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDMemberLevelSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDMemberLevelSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDMemberLevelSchema get(int index) {
     ZDMemberLevelSchema tSchema = (ZDMemberLevelSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDMemberLevelSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDMemberLevelSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDMemberLevelSet
 * JD-Core Version:    0.5.3
 */