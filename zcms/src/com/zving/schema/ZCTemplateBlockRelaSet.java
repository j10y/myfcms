 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCTemplateBlockRelaSet extends SchemaSet
 {
   public ZCTemplateBlockRelaSet()
   {
     this(10, 0);
   }
 
   public ZCTemplateBlockRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCTemplateBlockRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCTemplateBlockRela";
     this.Columns = ZCTemplateBlockRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCTemplateBlockRela values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCTemplateBlockRela set SiteID=?,FileName=?,BlockID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where SiteID=? and FileName=? and BlockID=?";
     this.FillAllSQL = "select * from ZCTemplateBlockRela  where SiteID=? and FileName=? and BlockID=?";
     this.DeleteSQL = "delete from ZCTemplateBlockRela  where SiteID=? and FileName=? and BlockID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCTemplateBlockRelaSet();
   }
 
   public boolean add(ZCTemplateBlockRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCTemplateBlockRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCTemplateBlockRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCTemplateBlockRelaSchema get(int index) {
     ZCTemplateBlockRelaSchema tSchema = (ZCTemplateBlockRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCTemplateBlockRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCTemplateBlockRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCTemplateBlockRelaSet
 * JD-Core Version:    0.5.3
 */