 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCStatItemSet extends SchemaSet
 {
   public BZCStatItemSet()
   {
     this(10, 0);
   }
 
   public BZCStatItemSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCStatItemSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCStatItem";
     this.Columns = BZCStatItemSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCStatItem values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCStatItem set SiteID=?,Period=?,Type=?,SubType=?,Item=?,Count1=?,Count2=?,Count3=?,Count4=?,Count5=?,Count6=?,Count7=?,Count8=?,Count9=?,Count10=?,Count11=?,Count12=?,Count13=?,Count14=?,Count15=?,Count16=?,Count17=?,Count18=?,Count19=?,Count20=?,Count21=?,Count22=?,Count23=?,Count24=?,Count25=?,Count26=?,Count27=?,Count28=?,Count29=?,Count30=?,Count31=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where SiteID=? and Period=? and Type=? and SubType=? and Item=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCStatItem  where SiteID=? and Period=? and Type=? and SubType=? and Item=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCStatItem  where SiteID=? and Period=? and Type=? and SubType=? and Item=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCStatItemSet();
   }
 
   public boolean add(BZCStatItemSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCStatItemSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCStatItemSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCStatItemSchema get(int index) {
     BZCStatItemSchema tSchema = (BZCStatItemSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCStatItemSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCStatItemSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCStatItemSet
 * JD-Core Version:    0.5.3
 */