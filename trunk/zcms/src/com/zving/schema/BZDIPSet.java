 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDIPSet extends SchemaSet
 {
   public BZDIPSet()
   {
     this(10, 0);
   }
 
   public BZDIPSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDIPSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDIP";
     this.Columns = BZDIPSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDIP values(?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDIP set IP1=?,IP2=?,IP3=?,IP4=?,Address=?,Memo=?,DistrictCode=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where IP1=? and IP2=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDIP  where IP1=? and IP2=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDIP  where IP1=? and IP2=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDIPSet();
   }
 
   public boolean add(BZDIPSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDIPSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDIPSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDIPSchema get(int index) {
     BZDIPSchema tSchema = (BZDIPSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDIPSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDIPSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDIPSet
 * JD-Core Version:    0.5.3
 */