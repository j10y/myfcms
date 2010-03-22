 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCLinkSet extends SchemaSet
 {
   public BZCLinkSet()
   {
     this(10, 0);
   }
 
   public BZCLinkSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCLinkSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCLink";
     this.Columns = BZCLinkSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCLink values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCLink set ID=?,SiteID=?,LinkGroupID=?,Name=?,URL=?,ImagePath=?,OrderFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCLink  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCLink  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCLinkSet();
   }
 
   public boolean add(BZCLinkSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCLinkSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCLinkSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCLinkSchema get(int index) {
     BZCLinkSchema tSchema = (BZCLinkSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCLinkSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCLinkSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCLinkSet
 * JD-Core Version:    0.5.3
 */