 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCPostSet extends SchemaSet
 {
   public ZCPostSet()
   {
     this(10, 0);
   }
 
   public ZCPostSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCPostSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCPost";
     this.Columns = ZCPostSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCPost values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCPost set ID=?,SiteID=?,ForumID=?,ThemeID=?,First=?,Subject=?,Message=?,IP=?,Status=?,IsHidden=?,Invisible=?,VerifyFlag=?,Layer=?,ApplyDel=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCPost  where ID=?";
     this.DeleteSQL = "delete from ZCPost  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCPostSet();
   }
 
   public boolean add(ZCPostSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCPostSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCPostSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCPostSchema get(int index) {
     ZCPostSchema tSchema = (ZCPostSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCPostSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCPostSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCPostSet
 * JD-Core Version:    0.5.3
 */