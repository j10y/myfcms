 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCThemeSet extends SchemaSet
 {
   public BZCThemeSet()
   {
     this(10, 0);
   }
 
   public BZCThemeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCThemeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCTheme";
     this.Columns = BZCThemeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCTheme values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCTheme set ID=?,SiteID=?,ForumID=?,Subject=?,IPAddress=?,Type=?,Status=?,LastPost=?,LastPoster=?,ViewCount=?,ReplyCount=?,OrderFlag=?,VerifyFlag=?,TopTheme=?,Best=?,Bright=?,Applydel=?,LastPostTime=?,OrderTime=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCTheme  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCTheme  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCThemeSet();
   }
 
   public boolean add(BZCThemeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCThemeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCThemeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCThemeSchema get(int index) {
     BZCThemeSchema tSchema = (BZCThemeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCThemeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCThemeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCThemeSet
 * JD-Core Version:    0.5.3
 */