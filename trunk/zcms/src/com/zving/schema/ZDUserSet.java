 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDUserSet extends SchemaSet
 {
   public ZDUserSet()
   {
     this(10, 0);
   }
 
   public ZDUserSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDUserSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDUser";
     this.Columns = ZDUserSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDUser values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDUser set UserName=?,RealName=?,Password=?,BranchInnerCode=?,IsBranchAdmin=?,Status=?,Type=?,Email=?,Tel=?,Mobile=?,Prop1=?,Prop2=?,Prop6=?,Prop5=?,Prop4=?,Prop3=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where UserName=?";
     this.FillAllSQL = "select * from ZDUser  where UserName=?";
     this.DeleteSQL = "delete from ZDUser  where UserName=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDUserSet();
   }
 
   public boolean add(ZDUserSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDUserSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDUserSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDUserSchema get(int index) {
     ZDUserSchema tSchema = (ZDUserSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDUserSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDUserSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDUserSet
 * JD-Core Version:    0.5.3
 */