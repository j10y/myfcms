 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCKeywordSet extends SchemaSet
 {
   public ZCKeywordSet()
   {
     this(10, 0);
   }
 
   public ZCKeywordSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCKeywordSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCKeyword";
     this.Columns = ZCKeywordSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCKeyword values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCKeyword set ID=?,Keyword=?,SiteId=?,KeywordType=?,LinkUrl=?,LinkTarget=?,LinkAlt=?,HintCount=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCKeyword  where ID=?";
     this.DeleteSQL = "delete from ZCKeyword  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCKeywordSet();
   }
 
   public boolean add(ZCKeywordSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCKeywordSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCKeywordSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCKeywordSchema get(int index) {
     ZCKeywordSchema tSchema = (ZCKeywordSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCKeywordSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCKeywordSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCKeywordSet
 * JD-Core Version:    0.5.3
 */