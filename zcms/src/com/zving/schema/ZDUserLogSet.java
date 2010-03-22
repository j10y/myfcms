 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDUserLogSet extends SchemaSet
 {
   public ZDUserLogSet()
   {
     this(10, 0);
   }
 
   public ZDUserLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDUserLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDUserLog";
     this.Columns = ZDUserLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDUserLog values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDUserLog set UserName=?,LogID=?,IP=?,LogType=?,SubType=?,LogMessage=?,Memo=?,AddTime=? where UserName=? and LogID=?";
     this.FillAllSQL = "select * from ZDUserLog  where UserName=? and LogID=?";
     this.DeleteSQL = "delete from ZDUserLog  where UserName=? and LogID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDUserLogSet();
   }
 
   public boolean add(ZDUserLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDUserLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDUserLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDUserLogSchema get(int index) {
     ZDUserLogSchema tSchema = (ZDUserLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDUserLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDUserLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDUserLogSet
 * JD-Core Version:    0.5.3
 */