 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCLinkGroupSet extends SchemaSet
 {
   public BZCLinkGroupSet()
   {
     this(10, 0);
   }
 
   public BZCLinkGroupSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCLinkGroupSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCLinkGroup";
     this.Columns = BZCLinkGroupSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCLinkGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCLinkGroup set ID=?,Name=?,OrderFlag=?,SiteID=?,Type=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCLinkGroup  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCLinkGroup  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCLinkGroupSet();
   }
 
   public boolean add(BZCLinkGroupSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCLinkGroupSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCLinkGroupSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCLinkGroupSchema get(int index) {
     BZCLinkGroupSchema tSchema = (BZCLinkGroupSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCLinkGroupSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCLinkGroupSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCLinkGroupSet
 * JD-Core Version:    0.5.3
 */