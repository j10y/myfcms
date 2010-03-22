 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDColumnSet extends SchemaSet
 {
   public ZDColumnSet()
   {
     this(10, 0);
   }
 
   public ZDColumnSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDColumnSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDColumn";
     this.Columns = ZDColumnSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDColumn values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDColumn set ID=?,SiteID=?,Name=?,Code=?,VerifyType=?,MaxLength=?,InputType=?,DefaultValue=?,ListOption=?,HTML=?,IsMandatory=?,OrderFlag=?,RowSize=?,ColSize=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZDColumn  where ID=?";
     this.DeleteSQL = "delete from ZDColumn  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDColumnSet();
   }
 
   public boolean add(ZDColumnSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDColumnSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDColumnSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDColumnSchema get(int index) {
     ZDColumnSchema tSchema = (ZDColumnSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDColumnSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDColumnSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDColumnSet
 * JD-Core Version:    0.5.3
 */