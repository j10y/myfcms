 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCContributeSet extends SchemaSet
 {
   public ZCContributeSet()
   {
     this(10, 0);
   }
 
   public ZCContributeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCContributeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCContribute";
     this.Columns = ZCContributeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCContribute values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCContribute set ID=?,AuthorID=?,AritcleID=?,Memo=?,ContributeDate=?,Prop1=?,Prop2=?,Prop3=?,Prop4=? where ID=?";
     this.FillAllSQL = "select * from ZCContribute  where ID=?";
     this.DeleteSQL = "delete from ZCContribute  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCContributeSet();
   }
 
   public boolean add(ZCContributeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCContributeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCContributeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCContributeSchema get(int index) {
     ZCContributeSchema tSchema = (ZCContributeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCContributeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCContributeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCContributeSet
 * JD-Core Version:    0.5.3
 */