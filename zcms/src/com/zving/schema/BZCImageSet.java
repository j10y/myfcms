 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCImageSet extends SchemaSet
 {
   public BZCImageSet()
   {
     this(10, 0);
   }
 
   public BZCImageSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCImageSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCImage";
     this.Columns = BZCImageSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCImage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCImage set ID=?,Name=?,OldName=?,Tag=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,Width=?,Height=?,Count=?,Info=?,IsOriginal=?,FileSize=?,System=?,LinkURL=?,LinkText=?,ProduceTime=?,Author=?,PublishDate=?,Integral=?,OrderFlag=?,HitCount=?,StickTime=?,SourceURL=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCImage  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCImage  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCImageSet();
   }
 
   public boolean add(BZCImageSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCImageSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCImageSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCImageSchema get(int index) {
     BZCImageSchema tSchema = (BZCImageSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCImageSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCImageSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCImageSet
 * JD-Core Version:    0.5.3
 */