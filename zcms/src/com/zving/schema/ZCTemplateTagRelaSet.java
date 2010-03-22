 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCTemplateTagRelaSet extends SchemaSet
 {
   public ZCTemplateTagRelaSet()
   {
     this(10, 0);
   }
 
   public ZCTemplateTagRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCTemplateTagRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCTemplateTagRela";
     this.Columns = ZCTemplateTagRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCTemplateTagRela values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCTemplateTagRela set ID=?,TemplateID=?,SiteID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=? and TemplateID=?";
     this.FillAllSQL = "select * from ZCTemplateTagRela  where ID=? and TemplateID=?";
     this.DeleteSQL = "delete from ZCTemplateTagRela  where ID=? and TemplateID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCTemplateTagRelaSet();
   }
 
   public boolean add(ZCTemplateTagRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCTemplateTagRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCTemplateTagRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCTemplateTagRelaSchema get(int index) {
     ZCTemplateTagRelaSchema tSchema = (ZCTemplateTagRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCTemplateTagRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCTemplateTagRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCTemplateTagRelaSet
 * JD-Core Version:    0.5.3
 */