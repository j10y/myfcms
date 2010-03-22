 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCStatItemSet extends SchemaSet
 {
   public ZCStatItemSet()
   {
     this(10, 0);
   }
 
   public ZCStatItemSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCStatItemSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCStatItem";
     this.Columns = ZCStatItemSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCStatItem values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCStatItem set SiteID=?,Period=?,Type=?,SubType=?,Item=?,Count1=?,Count2=?,Count3=?,Count4=?,Count5=?,Count6=?,Count7=?,Count8=?,Count9=?,Count10=?,Count11=?,Count12=?,Count13=?,Count14=?,Count15=?,Count16=?,Count17=?,Count18=?,Count19=?,Count20=?,Count21=?,Count22=?,Count23=?,Count24=?,Count25=?,Count26=?,Count27=?,Count28=?,Count29=?,Count30=?,Count31=? where SiteID=? and Period=? and Type=? and SubType=? and Item=?";
     this.FillAllSQL = "select * from ZCStatItem  where SiteID=? and Period=? and Type=? and SubType=? and Item=?";
     this.DeleteSQL = "delete from ZCStatItem  where SiteID=? and Period=? and Type=? and SubType=? and Item=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCStatItemSet();
   }
 
   public boolean add(ZCStatItemSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCStatItemSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCStatItemSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCStatItemSchema get(int index) {
     ZCStatItemSchema tSchema = (ZCStatItemSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCStatItemSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCStatItemSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCStatItemSet
 * JD-Core Version:    0.5.3
 */