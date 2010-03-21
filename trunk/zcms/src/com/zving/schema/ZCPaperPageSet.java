 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCPaperPageSet extends SchemaSet
 {
   public ZCPaperPageSet()
   {
     this(10, 0);
   }
 
   public ZCPaperPageSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCPaperPageSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCPaperPage";
     this.Columns = ZCPaperPageSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCPaperPage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCPaperPage set ID=?,IssueID=?,PageNo=?,Title=?,PaperImage=?,PDFFile=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCPaperPage  where ID=?";
     this.DeleteSQL = "delete from ZCPaperPage  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCPaperPageSet();
   }
 
   public boolean add(ZCPaperPageSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCPaperPageSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCPaperPageSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCPaperPageSchema get(int index) {
     ZCPaperPageSchema tSchema = (ZCPaperPageSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCPaperPageSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCPaperPageSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCPaperPageSet
 * JD-Core Version:    0.5.3
 */