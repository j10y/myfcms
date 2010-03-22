 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCTemplateSet extends SchemaSet
 {
   public ZCTemplateSet()
   {
     this(10, 0);
   }
 
   public ZCTemplateSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCTemplateSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCTemplate";
     this.Columns = ZCTemplateSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCTemplate values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCTemplate set ID=?,Code=?,SiteID=?,Name=?,FileName=?,Type=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCTemplate  where ID=?";
     this.DeleteSQL = "delete from ZCTemplate  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCTemplateSet();
   }
 
   public boolean add(ZCTemplateSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCTemplateSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCTemplateSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCTemplateSchema get(int index) {
     ZCTemplateSchema tSchema = (ZCTemplateSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCTemplateSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCTemplateSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCTemplateSet
 * JD-Core Version:    0.5.3
 */