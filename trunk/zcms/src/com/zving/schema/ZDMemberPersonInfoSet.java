 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDMemberPersonInfoSet extends SchemaSet
 {
   public ZDMemberPersonInfoSet()
   {
     this(10, 0);
   }
 
   public ZDMemberPersonInfoSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDMemberPersonInfoSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDMemberPersonInfo";
     this.Columns = ZDMemberPersonInfoSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDMemberPersonInfo values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDMemberPersonInfo set UserName=?,NickName=?,Birthday=?,QQ=?,MSN=?,Tel=?,Mobile=?,Address=?,ZipCode=? where UserName=?";
     this.FillAllSQL = "select * from ZDMemberPersonInfo  where UserName=?";
     this.DeleteSQL = "delete from ZDMemberPersonInfo  where UserName=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDMemberPersonInfoSet();
   }
 
   public boolean add(ZDMemberPersonInfoSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDMemberPersonInfoSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDMemberPersonInfoSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDMemberPersonInfoSchema get(int index) {
     ZDMemberPersonInfoSchema tSchema = (ZDMemberPersonInfoSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDMemberPersonInfoSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDMemberPersonInfoSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDMemberPersonInfoSet
 * JD-Core Version:    0.5.3
 */