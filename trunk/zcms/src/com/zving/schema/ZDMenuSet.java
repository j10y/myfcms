 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDMenuSet extends SchemaSet
 {
   public ZDMenuSet()
   {
     this(10, 0);
   }
 
   public ZDMenuSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDMenuSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDMenu";
     this.Columns = ZDMenuSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDMenu values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDMenu set ID=?,ParentID=?,Name=?,Type=?,URL=?,Visiable=?,Icon=?,OrderFlag=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
     this.FillAllSQL = "select * from ZDMenu  where ID=?";
     this.DeleteSQL = "delete from ZDMenu  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDMenuSet();
   }
 
   public boolean add(ZDMenuSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDMenuSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDMenuSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDMenuSchema get(int index) {
     ZDMenuSchema tSchema = (ZDMenuSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDMenuSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDMenuSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDMenuSet
 * JD-Core Version:    0.5.3
 */