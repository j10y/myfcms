 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAdminGroupSet extends SchemaSet
 {
   public ZCAdminGroupSet()
   {
     this(10, 0);
   }
 
   public ZCAdminGroupSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAdminGroupSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAdminGroup";
     this.Columns = ZCAdminGroupSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAdminGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAdminGroup set GroupID=?,SiteID=?,AllowEditUser=?,AllowForbidUser=?,AllowEditForum=?,AllowVerfyPost=?,AllowDel=?,AllowEdit=?,Hide=?,RemoveTheme=?,MoveTheme=?,TopTheme=?,BrightTheme=?,UpOrDownTheme=?,BestTheme=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where GroupID=?";
     this.FillAllSQL = "select * from ZCAdminGroup  where GroupID=?";
     this.DeleteSQL = "delete from ZCAdminGroup  where GroupID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAdminGroupSet();
   }
 
   public boolean add(ZCAdminGroupSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAdminGroupSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAdminGroupSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAdminGroupSchema get(int index) {
     ZCAdminGroupSchema tSchema = (ZCAdminGroupSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAdminGroupSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAdminGroupSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAdminGroupSet
 * JD-Core Version:    0.5.3
 */