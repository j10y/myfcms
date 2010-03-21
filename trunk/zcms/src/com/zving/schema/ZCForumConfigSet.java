 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCForumConfigSet extends SchemaSet
 {
   public ZCForumConfigSet()
   {
     this(10, 0);
   }
 
   public ZCForumConfigSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCForumConfigSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCForumConfig";
     this.Columns = ZCForumConfigSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCForumConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCForumConfig set ID=?,Name=?,SiteID=?,Subdomains=?,Des=?,TempCloseFlag=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCForumConfig  where ID=?";
     this.DeleteSQL = "delete from ZCForumConfig  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCForumConfigSet();
   }
 
   public boolean add(ZCForumConfigSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCForumConfigSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCForumConfigSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCForumConfigSchema get(int index) {
     ZCForumConfigSchema tSchema = (ZCForumConfigSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCForumConfigSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCForumConfigSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCForumConfigSet
 * JD-Core Version:    0.5.3
 */