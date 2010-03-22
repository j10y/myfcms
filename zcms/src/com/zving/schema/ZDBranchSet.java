 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDBranchSet extends SchemaSet
 {
   public ZDBranchSet()
   {
     this(10, 0);
   }
 
   public ZDBranchSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDBranchSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDBranch";
     this.Columns = ZDBranchSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDBranch values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDBranch set BranchInnerCode=?,BranchCode=?,ParentInnerCode=?,Type=?,OrderFlag=?,Name=?,TreeLevel=?,IsLeaf=?,Phone=?,Fax=?,Manager=?,Leader1=?,Leader2=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where BranchInnerCode=?";
     this.FillAllSQL = "select * from ZDBranch  where BranchInnerCode=?";
     this.DeleteSQL = "delete from ZDBranch  where BranchInnerCode=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDBranchSet();
   }
 
   public boolean add(ZDBranchSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDBranchSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDBranchSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDBranchSchema get(int index) {
     ZDBranchSchema tSchema = (ZDBranchSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDBranchSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDBranchSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDBranchSet
 * JD-Core Version:    0.5.3
 */