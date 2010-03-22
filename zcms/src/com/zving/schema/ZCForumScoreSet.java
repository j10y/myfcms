 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCForumScoreSet extends SchemaSet
 {
   public ZCForumScoreSet()
   {
     this(10, 0);
   }
 
   public ZCForumScoreSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCForumScoreSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCForumScore";
     this.Columns = ZCForumScoreSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCForumScore values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCForumScore set ID=?,SiteID=?,InitScore=?,PublishTheme=?,DeleteTheme=?,PublishPost=?,DeletePost=?,Best=?,CancelBest=?,Bright=?,CancelBright=?,TopTheme=?,CancelTop=?,UpTheme=?,DownTheme=?,Upload=?,Download=?,Search=?,Vote=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCForumScore  where ID=?";
     this.DeleteSQL = "delete from ZCForumScore  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCForumScoreSet();
   }
 
   public boolean add(ZCForumScoreSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCForumScoreSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCForumScoreSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCForumScoreSchema get(int index) {
     ZCForumScoreSchema tSchema = (ZCForumScoreSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCForumScoreSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCForumScoreSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCForumScoreSet
 * JD-Core Version:    0.5.3
 */