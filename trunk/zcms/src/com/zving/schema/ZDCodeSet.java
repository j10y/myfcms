 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDCodeSet extends SchemaSet
 {
   public ZDCodeSet()
   {
     this(10, 0);
   }
 
   public ZDCodeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDCodeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDCode";
     this.Columns = ZDCodeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDCode values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDCode set CodeType=?,ParentCode=?,CodeValue=?,CodeName=?,CodeOrder=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where CodeType=? and ParentCode=? and CodeValue=?";
     this.FillAllSQL = "select * from ZDCode  where CodeType=? and ParentCode=? and CodeValue=?";
     this.DeleteSQL = "delete from ZDCode  where CodeType=? and ParentCode=? and CodeValue=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDCodeSet();
   }
 
   public boolean add(ZDCodeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDCodeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDCodeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDCodeSchema get(int index) {
     ZDCodeSchema tSchema = (ZDCodeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDCodeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDCodeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDCodeSet
 * JD-Core Version:    0.5.3
 */