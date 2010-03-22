 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAdPositionSet extends SchemaSet
 {
   public ZCAdPositionSet()
   {
     this(10, 0);
   }
 
   public ZCAdPositionSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAdPositionSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAdPosition";
     this.Columns = ZCAdPositionSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAdPosition values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAdPosition set ID=?,SiteID=?,PositionName=?,Code=?,Description=?,PositionType=?,PaddingTop=?,PaddingLeft=?,PositionWidth=?,PositionHeight=?,JsName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCAdPosition  where ID=?";
     this.DeleteSQL = "delete from ZCAdPosition  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAdPositionSet();
   }
 
   public boolean add(ZCAdPositionSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAdPositionSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAdPositionSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAdPositionSchema get(int index) {
     ZCAdPositionSchema tSchema = (ZCAdPositionSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAdPositionSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAdPositionSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAdPositionSet
 * JD-Core Version:    0.5.3
 */