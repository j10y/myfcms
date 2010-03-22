 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCThemeSet extends SchemaSet
 {
   public ZCThemeSet()
   {
     this(10, 0);
   }
 
   public ZCThemeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCThemeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCTheme";
     this.Columns = ZCThemeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCTheme values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCTheme set ID=?,SiteID=?,ForumID=?,Subject=?,IPAddress=?,Type=?,Status=?,LastPost=?,LastPoster=?,ViewCount=?,ReplyCount=?,OrderFlag=?,VerifyFlag=?,TopTheme=?,Best=?,Bright=?,Applydel=?,LastPostTime=?,OrderTime=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCTheme  where ID=?";
     this.DeleteSQL = "delete from ZCTheme  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCThemeSet();
   }
 
   public boolean add(ZCThemeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCThemeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCThemeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCThemeSchema get(int index) {
     ZCThemeSchema tSchema = (ZCThemeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCThemeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCThemeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCThemeSet
 * JD-Core Version:    0.5.3
 */