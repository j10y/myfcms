 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCVideoSet extends SchemaSet
 {
   public BZCVideoSet()
   {
     this(10, 0);
   }
 
   public BZCVideoSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCVideoSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCVideo";
     this.Columns = BZCVideoSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCVideo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCVideo set ID=?,Name=?,OldName=?,SiteID=?,Tag=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,IsOriginal=?,Info=?,System=?,FileSize=?,PublishDate=?,ImageName=?,Count=?,Width=?,Height=?,Duration=?,ProduceTime=?,Author=?,Integral=?,OrderFlag=?,HitCount=?,StickTime=?,Status=?,SourceURL=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCVideo  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCVideo  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCVideoSet();
   }
 
   public boolean add(BZCVideoSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCVideoSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCVideoSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCVideoSchema get(int index) {
     BZCVideoSchema tSchema = (BZCVideoSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCVideoSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCVideoSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCVideoSet
 * JD-Core Version:    0.5.3
 */