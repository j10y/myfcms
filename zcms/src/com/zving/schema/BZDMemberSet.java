 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDMemberSet extends SchemaSet
 {
   public BZDMemberSet()
   {
     this(10, 0);
   }
 
   public BZDMemberSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDMemberSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDMember";
     this.Columns = BZDMemberSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDMember values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDMember set UserName=?,Password=?,Name=?,Email=?,Gender=?,Type=?,SiteID=?,Logo=?,Status=?,Score=?,Rank=?,MemberLevel=?,PWQuestion=?,PWAnswer=?,LastLoginIP=?,LastLoginTime=?,RegTime=?,RegIP=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Prop5=?,Prop6=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDMember  where UserName=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDMember  where UserName=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDMemberSet();
   }
 
   public boolean add(BZDMemberSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDMemberSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDMemberSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDMemberSchema get(int index) {
     BZDMemberSchema tSchema = (BZDMemberSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDMemberSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDMemberSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDMemberSet
 * JD-Core Version:    0.5.3
 */