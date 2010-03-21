 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDDistrictSet extends SchemaSet
 {
   public BZDDistrictSet()
   {
     this(10, 0);
   }
 
   public BZDDistrictSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDDistrictSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDDistrict";
     this.Columns = BZDDistrictSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDDistrict values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDDistrict set Code=?,Name=?,CodeOrder=?,TreeLevel=?,Type=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where Code=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDDistrict  where Code=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDDistrict  where Code=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDDistrictSet();
   }
 
   public boolean add(BZDDistrictSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDDistrictSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDDistrictSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDDistrictSchema get(int index) {
     BZDDistrictSchema tSchema = (BZDDistrictSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDDistrictSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDDistrictSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDDistrictSet
 * JD-Core Version:    0.5.3
 */