 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCSiteSet extends SchemaSet
 {
   public ZCSiteSet()
   {
     this(10, 0);
   }
 
   public ZCSiteSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCSiteSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCSite";
     this.Columns = ZCSiteSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCSite values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCSite set ID=?,Name=?,Alias=?,Info=?,BranchInnerCode=?,URL=?,RootPath=?,IndexTemplate=?,ListTemplate=?,DetailTemplate=?,EditorCss=?,Workflow=?,OrderFlag=?,LogoFileName=?,MessageBoardFlag=?,CommentAuditFlag=?,ChannelCount=?,MagzineCount=?,SpecialCount=?,ImageLibCount=?,VideoLibCount=?,AudioLibCount=?,AttachmentLibCount=?,ArticleCount=?,HitCount=?,ConfigXML=?,AutoIndexFlag=?,AutoStatFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Prop5=?,Prop6=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCSite  where ID=?";
     this.DeleteSQL = "delete from ZCSite  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCSiteSet();
   }
 
   public boolean add(ZCSiteSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCSiteSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCSiteSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCSiteSchema get(int index) {
     ZCSiteSchema tSchema = (ZCSiteSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCSiteSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCSiteSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCSiteSet
 * JD-Core Version:    0.5.3
 */