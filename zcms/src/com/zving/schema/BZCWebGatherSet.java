 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCWebGatherSet extends SchemaSet
 {
   public BZCWebGatherSet()
   {
     this(10, 0);
   }
 
   public BZCWebGatherSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCWebGatherSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCWebGather";
     this.Columns = BZCWebGatherSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCWebGather values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCWebGather set ID=?,SiteID=?,Name=?,Intro=?,Type=?,ConfigXML=?,ProxyFlag=?,ProxyHost=?,ProxyPort=?,ProxyUserName=?,ProxyPassword=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCWebGather  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCWebGather  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCWebGatherSet();
   }
 
   public boolean add(BZCWebGatherSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCWebGatherSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCWebGatherSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCWebGatherSchema get(int index) {
     BZCWebGatherSchema tSchema = (BZCWebGatherSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCWebGatherSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCWebGatherSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCWebGatherSet
 * JD-Core Version:    0.5.3
 */