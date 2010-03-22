 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCForumGroupSet extends SchemaSet
 {
   public ZCForumGroupSet()
   {
     this(10, 0);
   }
 
   public ZCForumGroupSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCForumGroupSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCForumGroup";
     this.Columns = ZCForumGroupSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCForumGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCForumGroup set ID=?,SiteID=?,RadminID=?,Name=?,SystemName=?,Type=?,Color=?,Image=?,LowerScore=?,UpperScore=?,AllowTheme=?,AllowReply=?,AllowSearch=?,AllowUserInfo=?,AllowPanel=?,AllowNickName=?,AllowVisit=?,AllowHeadImage=?,AllowFace=?,AllowAutograph=?,Verify=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,OrderFlag=? where ID=?";
     this.FillAllSQL = "select * from ZCForumGroup  where ID=?";
     this.DeleteSQL = "delete from ZCForumGroup  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCForumGroupSet();
   }
 
   public boolean add(ZCForumGroupSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCForumGroupSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCForumGroupSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCForumGroupSchema get(int index) {
     ZCForumGroupSchema tSchema = (ZCForumGroupSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCForumGroupSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCForumGroupSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCForumGroupSet
 * JD-Core Version:    0.5.3
 */