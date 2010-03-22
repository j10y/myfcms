 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCArticleSet extends SchemaSet
 {
   public BZCArticleSet()
   {
     this(10, 0);
   }
 
   public BZCArticleSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCArticleSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCArticle";
     this.Columns = BZCArticleSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCArticle values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCArticle set ID=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Title=?,SubTitle=?,ShortTitle=?,TitleStyle=?,ShortTitleStyle=?,Author=?,Type=?,Attribute=?,URL=?,RedirectURL=?,Status=?,Summary=?,Content=?,TopFlag=?,TopDate=?,TemplateFlag=?,Template=?,CommentFlag=?,CopyImageFlag=?,OrderFlag=?,ReferName=?,ReferURL=?,Keyword=?,RelativeArticle=?,RecommendArticle=?,ReferType=?,ReferSourceID=?,HitCount=?,StickTime=?,PublishFlag=?,Priority=?,LockUser=?,PublishDate=?,DownlineDate=?,WorkFlowID=?,IssueID=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCArticle  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCArticle  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCArticleSet();
   }
 
   public boolean add(BZCArticleSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCArticleSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCArticleSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCArticleSchema get(int index) {
     BZCArticleSchema tSchema = (BZCArticleSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCArticleSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCArticleSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCArticleSet
 * JD-Core Version:    0.5.3
 */