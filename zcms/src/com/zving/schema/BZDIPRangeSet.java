 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDIPRangeSet extends SchemaSet
 {
   public BZDIPRangeSet()
   {
     this(10, 0);
   }
 
   public BZDIPRangeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDIPRangeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDIPRange";
     this.Columns = BZDIPRangeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDIPRange values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDIPRange set DistrictCode=?,IPRanges=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where DistrictCode=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDIPRange  where DistrictCode=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDIPRange  where DistrictCode=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDIPRangeSet();
   }
 
   public boolean add(BZDIPRangeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDIPRangeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDIPRangeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDIPRangeSchema get(int index) {
     BZDIPRangeSchema tSchema = (BZDIPRangeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDIPRangeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDIPRangeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDIPRangeSet
 * JD-Core Version:    0.5.3
 */