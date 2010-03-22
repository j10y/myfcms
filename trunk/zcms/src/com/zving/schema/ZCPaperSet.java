 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCPaperSet extends SchemaSet
 {
   public ZCPaperSet()
   {
     this(10, 0);
   }
 
   public ZCPaperSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCPaperSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCPaper";
     this.Columns = ZCPaperSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCPaper values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCPaper set ID=?,SiteID=?,Name=?,Alias=?,CoverImage=?,Period=?,OpenFlag=?,Total=?,CurrentYear=?,CurrentPeriodNum=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCPaper  where ID=?";
     this.DeleteSQL = "delete from ZCPaper  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCPaperSet();
   }
 
   public boolean add(ZCPaperSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCPaperSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCPaperSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCPaperSchema get(int index) {
     ZCPaperSchema tSchema = (ZCPaperSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCPaperSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCPaperSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCPaperSet
 * JD-Core Version:    0.5.3
 */