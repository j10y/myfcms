 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCSiteSet extends SchemaSet
 {
   public BZCSiteSet()
   {
     this(10, 0);
   }
 
   public BZCSiteSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCSiteSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCSite";
     this.Columns = BZCSiteSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCSite values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCSite set ID=?,Name=?,Alias=?,Info=?,BranchInnerCode=?,URL=?,RootPath=?,IndexTemplate=?,ListTemplate=?,DetailTemplate=?,EditorCss=?,Workflow=?,OrderFlag=?,LogoFileName=?,MessageBoardFlag=?,CommentAuditFlag=?,ChannelCount=?,MagzineCount=?,SpecialCount=?,ImageLibCount=?,VideoLibCount=?,AudioLibCount=?,AttachmentLibCount=?,ArticleCount=?,HitCount=?,ConfigXML=?,AutoIndexFlag=?,AutoStatFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Prop5=?,Prop6=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCSite  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCSite  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCSiteSet();
   }
 
   public boolean add(BZCSiteSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCSiteSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCSiteSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCSiteSchema get(int index) {
     BZCSiteSchema tSchema = (BZCSiteSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCSiteSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCSiteSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCSiteSet
 * JD-Core Version:    0.5.3
 */