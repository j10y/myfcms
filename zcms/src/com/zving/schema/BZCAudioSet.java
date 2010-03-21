 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCAudioSet extends SchemaSet
 {
   public BZCAudioSet()
   {
     this(10, 0);
   }
 
   public BZCAudioSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCAudioSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCAudio";
     this.Columns = BZCAudioSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCAudio values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAudio set ID=?,Name=?,OldName=?,SiteID=?,Tag=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,IsOriginal=?,Info=?,System=?,FileSize=?,PublishDate=?,Duration=?,ProduceTime=?,Author=?,Integral=?,SourceURL=?,OrderFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAudio  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAudio  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCAudioSet();
   }
 
   public boolean add(BZCAudioSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCAudioSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCAudioSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCAudioSchema get(int index) {
     BZCAudioSchema tSchema = (BZCAudioSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCAudioSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCAudioSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAudioSet
 * JD-Core Version:    0.5.3
 */