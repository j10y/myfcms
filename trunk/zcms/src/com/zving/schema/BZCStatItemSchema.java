 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCStatItemSchema extends Schema
 {
   private Long SiteID;
   private String Period;
   private String Type;
   private String SubType;
   private String Item;
   private Long Count1;
   private Long Count2;
   private Long Count3;
   private Long Count4;
   private Long Count5;
   private Long Count6;
   private Long Count7;
   private Long Count8;
   private Long Count9;
   private Long Count10;
   private Long Count11;
   private Long Count12;
   private Long Count13;
   private Long Count14;
   private Long Count15;
   private Long Count16;
   private Long Count17;
   private Long Count18;
   private Long Count19;
   private Long Count20;
   private Long Count21;
   private Long Count22;
   private Long Count23;
   private Long Count24;
   private Long Count25;
   private Long Count26;
   private Long Count27;
   private Long Count28;
   private Long Count29;
   private Long Count30;
   private Long Count31;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("SiteID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("Period", 1, 1, 10, 0, true, true), 
     new SchemaColumn("Type", 1, 2, 50, 0, true, true), 
     new SchemaColumn("SubType", 1, 3, 50, 0, true, true), 
     new SchemaColumn("Item", 1, 4, 150, 0, true, true), 
     new SchemaColumn("Count1", 7, 5, 0, 0, false, false), 
     new SchemaColumn("Count2", 7, 6, 0, 0, false, false), 
     new SchemaColumn("Count3", 7, 7, 0, 0, false, false), 
     new SchemaColumn("Count4", 7, 8, 0, 0, false, false), 
     new SchemaColumn("Count5", 7, 9, 0, 0, false, false), 
     new SchemaColumn("Count6", 7, 10, 0, 0, false, false), 
     new SchemaColumn("Count7", 7, 11, 0, 0, false, false), 
     new SchemaColumn("Count8", 7, 12, 0, 0, false, false), 
     new SchemaColumn("Count9", 7, 13, 0, 0, false, false), 
     new SchemaColumn("Count10", 7, 14, 0, 0, false, false), 
     new SchemaColumn("Count11", 7, 15, 0, 0, false, false), 
     new SchemaColumn("Count12", 7, 16, 0, 0, false, false), 
     new SchemaColumn("Count13", 7, 17, 0, 0, false, false), 
     new SchemaColumn("Count14", 7, 18, 0, 0, false, false), 
     new SchemaColumn("Count15", 7, 19, 0, 0, false, false), 
     new SchemaColumn("Count16", 7, 20, 0, 0, false, false), 
     new SchemaColumn("Count17", 7, 21, 0, 0, false, false), 
     new SchemaColumn("Count18", 7, 22, 0, 0, false, false), 
     new SchemaColumn("Count19", 7, 23, 0, 0, false, false), 
     new SchemaColumn("Count20", 7, 24, 0, 0, false, false), 
     new SchemaColumn("Count21", 7, 25, 0, 0, false, false), 
     new SchemaColumn("Count22", 7, 26, 0, 0, false, false), 
     new SchemaColumn("Count23", 7, 27, 0, 0, false, false), 
     new SchemaColumn("Count24", 7, 28, 0, 0, false, false), 
     new SchemaColumn("Count25", 7, 29, 0, 0, false, false), 
     new SchemaColumn("Count26", 7, 30, 0, 0, false, false), 
     new SchemaColumn("Count27", 7, 31, 0, 0, false, false), 
     new SchemaColumn("Count28", 7, 32, 0, 0, false, false), 
     new SchemaColumn("Count29", 7, 33, 0, 0, false, false), 
     new SchemaColumn("Count30", 7, 34, 0, 0, false, false), 
     new SchemaColumn("Count31", 7, 35, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 36, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 37, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 38, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 39, 50, 0, false, false) };
   public static final String _TableCode = "BZCStatItem";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCStatItem values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCStatItem set SiteID=?,Period=?,Type=?,SubType=?,Item=?,Count1=?,Count2=?,Count3=?,Count4=?,Count5=?,Count6=?,Count7=?,Count8=?,Count9=?,Count10=?,Count11=?,Count12=?,Count13=?,Count14=?,Count15=?,Count16=?,Count17=?,Count18=?,Count19=?,Count20=?,Count21=?,Count22=?,Count23=?,Count24=?,Count25=?,Count26=?,Count27=?,Count28=?,Count29=?,Count30=?,Count31=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where SiteID=? and Period=? and Type=? and SubType=? and Item=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCStatItem  where SiteID=? and Period=? and Type=? and SubType=? and Item=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCStatItem  where SiteID=? and Period=? and Type=? and SubType=? and Item=? and BackupNo=?";
 
   public BZCStatItemSchema()
   {
     this.TableCode = "BZCStatItem";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCStatItem values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCStatItem set SiteID=?,Period=?,Type=?,SubType=?,Item=?,Count1=?,Count2=?,Count3=?,Count4=?,Count5=?,Count6=?,Count7=?,Count8=?,Count9=?,Count10=?,Count11=?,Count12=?,Count13=?,Count14=?,Count15=?,Count16=?,Count17=?,Count18=?,Count19=?,Count20=?,Count21=?,Count22=?,Count23=?,Count24=?,Count25=?,Count26=?,Count27=?,Count28=?,Count29=?,Count30=?,Count31=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where SiteID=? and Period=? and Type=? and SubType=? and Item=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCStatItem  where SiteID=? and Period=? and Type=? and SubType=? and Item=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCStatItem  where SiteID=? and Period=? and Type=? and SubType=? and Item=? and BackupNo=?";
     this.HasSetFlag = new boolean[40];
   }
 
   protected Schema newInstance() {
     return new BZCStatItemSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCStatItemSet();
   }
 
   public BZCStatItemSet query() {
     return query(null, -1, -1);
   }
 
   public BZCStatItemSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCStatItemSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCStatItemSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCStatItemSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 1) { this.Period = ((String)v); return; }
     if (i == 2) { this.Type = ((String)v); return; }
     if (i == 3) { this.SubType = ((String)v); return; }
     if (i == 4) { this.Item = ((String)v); return; }
     if (i == 5) { if (v == null) this.Count1 = null; else this.Count1 = new Long(v.toString()); return; }
     if (i == 6) { if (v == null) this.Count2 = null; else this.Count2 = new Long(v.toString()); return; }
     if (i == 7) { if (v == null) this.Count3 = null; else this.Count3 = new Long(v.toString()); return; }
     if (i == 8) { if (v == null) this.Count4 = null; else this.Count4 = new Long(v.toString()); return; }
     if (i == 9) { if (v == null) this.Count5 = null; else this.Count5 = new Long(v.toString()); return; }
     if (i == 10) { if (v == null) this.Count6 = null; else this.Count6 = new Long(v.toString()); return; }
     if (i == 11) { if (v == null) this.Count7 = null; else this.Count7 = new Long(v.toString()); return; }
     if (i == 12) { if (v == null) this.Count8 = null; else this.Count8 = new Long(v.toString()); return; }
     if (i == 13) { if (v == null) this.Count9 = null; else this.Count9 = new Long(v.toString()); return; }
     if (i == 14) { if (v == null) this.Count10 = null; else this.Count10 = new Long(v.toString()); return; }
     if (i == 15) { if (v == null) this.Count11 = null; else this.Count11 = new Long(v.toString()); return; }
     if (i == 16) { if (v == null) this.Count12 = null; else this.Count12 = new Long(v.toString()); return; }
     if (i == 17) { if (v == null) this.Count13 = null; else this.Count13 = new Long(v.toString()); return; }
     if (i == 18) { if (v == null) this.Count14 = null; else this.Count14 = new Long(v.toString()); return; }
     if (i == 19) { if (v == null) this.Count15 = null; else this.Count15 = new Long(v.toString()); return; }
     if (i == 20) { if (v == null) this.Count16 = null; else this.Count16 = new Long(v.toString()); return; }
     if (i == 21) { if (v == null) this.Count17 = null; else this.Count17 = new Long(v.toString()); return; }
     if (i == 22) { if (v == null) this.Count18 = null; else this.Count18 = new Long(v.toString()); return; }
     if (i == 23) { if (v == null) this.Count19 = null; else this.Count19 = new Long(v.toString()); return; }
     if (i == 24) { if (v == null) this.Count20 = null; else this.Count20 = new Long(v.toString()); return; }
     if (i == 25) { if (v == null) this.Count21 = null; else this.Count21 = new Long(v.toString()); return; }
     if (i == 26) { if (v == null) this.Count22 = null; else this.Count22 = new Long(v.toString()); return; }
     if (i == 27) { if (v == null) this.Count23 = null; else this.Count23 = new Long(v.toString()); return; }
     if (i == 28) { if (v == null) this.Count24 = null; else this.Count24 = new Long(v.toString()); return; }
     if (i == 29) { if (v == null) this.Count25 = null; else this.Count25 = new Long(v.toString()); return; }
     if (i == 30) { if (v == null) this.Count26 = null; else this.Count26 = new Long(v.toString()); return; }
     if (i == 31) { if (v == null) this.Count27 = null; else this.Count27 = new Long(v.toString()); return; }
     if (i == 32) { if (v == null) this.Count28 = null; else this.Count28 = new Long(v.toString()); return; }
     if (i == 33) { if (v == null) this.Count29 = null; else this.Count29 = new Long(v.toString()); return; }
     if (i == 34) { if (v == null) this.Count30 = null; else this.Count30 = new Long(v.toString()); return; }
     if (i == 35) { if (v == null) this.Count31 = null; else this.Count31 = new Long(v.toString()); return; }
     if (i == 36) { this.BackupNo = ((String)v); return; }
     if (i == 37) { this.BackupOperator = ((String)v); return; }
     if (i == 38) { this.BackupTime = ((Date)v); return; }
     if (i != 39) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.SiteID;
     if (i == 1) return this.Period;
     if (i == 2) return this.Type;
     if (i == 3) return this.SubType;
     if (i == 4) return this.Item;
     if (i == 5) return this.Count1;
     if (i == 6) return this.Count2;
     if (i == 7) return this.Count3;
     if (i == 8) return this.Count4;
     if (i == 9) return this.Count5;
     if (i == 10) return this.Count6;
     if (i == 11) return this.Count7;
     if (i == 12) return this.Count8;
     if (i == 13) return this.Count9;
     if (i == 14) return this.Count10;
     if (i == 15) return this.Count11;
     if (i == 16) return this.Count12;
     if (i == 17) return this.Count13;
     if (i == 18) return this.Count14;
     if (i == 19) return this.Count15;
     if (i == 20) return this.Count16;
     if (i == 21) return this.Count17;
     if (i == 22) return this.Count18;
     if (i == 23) return this.Count19;
     if (i == 24) return this.Count20;
     if (i == 25) return this.Count21;
     if (i == 26) return this.Count22;
     if (i == 27) return this.Count23;
     if (i == 28) return this.Count24;
     if (i == 29) return this.Count25;
     if (i == 30) return this.Count26;
     if (i == 31) return this.Count27;
     if (i == 32) return this.Count28;
     if (i == 33) return this.Count29;
     if (i == 34) return this.Count30;
     if (i == 35) return this.Count31;
     if (i == 36) return this.BackupNo;
     if (i == 37) return this.BackupOperator;
     if (i == 38) return this.BackupTime;
     if (i == 39) return this.BackupMemo;
     return null;
   }
 
   public long getSiteID()
   {
     if (this.SiteID == null) return 0L;
     return this.SiteID.longValue();
   }
 
   public void setSiteID(long siteID)
   {
     this.SiteID = new Long(siteID);
   }
 
   public void setSiteID(String siteID)
   {
     if (siteID == null) {
       this.SiteID = null;
       return;
     }
     this.SiteID = new Long(siteID);
   }
 
   public String getPeriod()
   {
     return this.Period;
   }
 
   public void setPeriod(String period)
   {
     this.Period = period;
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 
   public String getSubType()
   {
     return this.SubType;
   }
 
   public void setSubType(String subType)
   {
     this.SubType = subType;
   }
 
   public String getItem()
   {
     return this.Item;
   }
 
   public void setItem(String item)
   {
     this.Item = item;
   }
 
   public long getCount1()
   {
     if (this.Count1 == null) return 0L;
     return this.Count1.longValue();
   }
 
   public void setCount1(long count1)
   {
     this.Count1 = new Long(count1);
   }
 
   public void setCount1(String count1)
   {
     if (count1 == null) {
       this.Count1 = null;
       return;
     }
     this.Count1 = new Long(count1);
   }
 
   public long getCount2()
   {
     if (this.Count2 == null) return 0L;
     return this.Count2.longValue();
   }
 
   public void setCount2(long count2)
   {
     this.Count2 = new Long(count2);
   }
 
   public void setCount2(String count2)
   {
     if (count2 == null) {
       this.Count2 = null;
       return;
     }
     this.Count2 = new Long(count2);
   }
 
   public long getCount3()
   {
     if (this.Count3 == null) return 0L;
     return this.Count3.longValue();
   }
 
   public void setCount3(long count3)
   {
     this.Count3 = new Long(count3);
   }
 
   public void setCount3(String count3)
   {
     if (count3 == null) {
       this.Count3 = null;
       return;
     }
     this.Count3 = new Long(count3);
   }
 
   public long getCount4()
   {
     if (this.Count4 == null) return 0L;
     return this.Count4.longValue();
   }
 
   public void setCount4(long count4)
   {
     this.Count4 = new Long(count4);
   }
 
   public void setCount4(String count4)
   {
     if (count4 == null) {
       this.Count4 = null;
       return;
     }
     this.Count4 = new Long(count4);
   }
 
   public long getCount5()
   {
     if (this.Count5 == null) return 0L;
     return this.Count5.longValue();
   }
 
   public void setCount5(long count5)
   {
     this.Count5 = new Long(count5);
   }
 
   public void setCount5(String count5)
   {
     if (count5 == null) {
       this.Count5 = null;
       return;
     }
     this.Count5 = new Long(count5);
   }
 
   public long getCount6()
   {
     if (this.Count6 == null) return 0L;
     return this.Count6.longValue();
   }
 
   public void setCount6(long count6)
   {
     this.Count6 = new Long(count6);
   }
 
   public void setCount6(String count6)
   {
     if (count6 == null) {
       this.Count6 = null;
       return;
     }
     this.Count6 = new Long(count6);
   }
 
   public long getCount7()
   {
     if (this.Count7 == null) return 0L;
     return this.Count7.longValue();
   }
 
   public void setCount7(long count7)
   {
     this.Count7 = new Long(count7);
   }
 
   public void setCount7(String count7)
   {
     if (count7 == null) {
       this.Count7 = null;
       return;
     }
     this.Count7 = new Long(count7);
   }
 
   public long getCount8()
   {
     if (this.Count8 == null) return 0L;
     return this.Count8.longValue();
   }
 
   public void setCount8(long count8)
   {
     this.Count8 = new Long(count8);
   }
 
   public void setCount8(String count8)
   {
     if (count8 == null) {
       this.Count8 = null;
       return;
     }
     this.Count8 = new Long(count8);
   }
 
   public long getCount9()
   {
     if (this.Count9 == null) return 0L;
     return this.Count9.longValue();
   }
 
   public void setCount9(long count9)
   {
     this.Count9 = new Long(count9);
   }
 
   public void setCount9(String count9)
   {
     if (count9 == null) {
       this.Count9 = null;
       return;
     }
     this.Count9 = new Long(count9);
   }
 
   public long getCount10()
   {
     if (this.Count10 == null) return 0L;
     return this.Count10.longValue();
   }
 
   public void setCount10(long count10)
   {
     this.Count10 = new Long(count10);
   }
 
   public void setCount10(String count10)
   {
     if (count10 == null) {
       this.Count10 = null;
       return;
     }
     this.Count10 = new Long(count10);
   }
 
   public long getCount11()
   {
     if (this.Count11 == null) return 0L;
     return this.Count11.longValue();
   }
 
   public void setCount11(long count11)
   {
     this.Count11 = new Long(count11);
   }
 
   public void setCount11(String count11)
   {
     if (count11 == null) {
       this.Count11 = null;
       return;
     }
     this.Count11 = new Long(count11);
   }
 
   public long getCount12()
   {
     if (this.Count12 == null) return 0L;
     return this.Count12.longValue();
   }
 
   public void setCount12(long count12)
   {
     this.Count12 = new Long(count12);
   }
 
   public void setCount12(String count12)
   {
     if (count12 == null) {
       this.Count12 = null;
       return;
     }
     this.Count12 = new Long(count12);
   }
 
   public long getCount13()
   {
     if (this.Count13 == null) return 0L;
     return this.Count13.longValue();
   }
 
   public void setCount13(long count13)
   {
     this.Count13 = new Long(count13);
   }
 
   public void setCount13(String count13)
   {
     if (count13 == null) {
       this.Count13 = null;
       return;
     }
     this.Count13 = new Long(count13);
   }
 
   public long getCount14()
   {
     if (this.Count14 == null) return 0L;
     return this.Count14.longValue();
   }
 
   public void setCount14(long count14)
   {
     this.Count14 = new Long(count14);
   }
 
   public void setCount14(String count14)
   {
     if (count14 == null) {
       this.Count14 = null;
       return;
     }
     this.Count14 = new Long(count14);
   }
 
   public long getCount15()
   {
     if (this.Count15 == null) return 0L;
     return this.Count15.longValue();
   }
 
   public void setCount15(long count15)
   {
     this.Count15 = new Long(count15);
   }
 
   public void setCount15(String count15)
   {
     if (count15 == null) {
       this.Count15 = null;
       return;
     }
     this.Count15 = new Long(count15);
   }
 
   public long getCount16()
   {
     if (this.Count16 == null) return 0L;
     return this.Count16.longValue();
   }
 
   public void setCount16(long count16)
   {
     this.Count16 = new Long(count16);
   }
 
   public void setCount16(String count16)
   {
     if (count16 == null) {
       this.Count16 = null;
       return;
     }
     this.Count16 = new Long(count16);
   }
 
   public long getCount17()
   {
     if (this.Count17 == null) return 0L;
     return this.Count17.longValue();
   }
 
   public void setCount17(long count17)
   {
     this.Count17 = new Long(count17);
   }
 
   public void setCount17(String count17)
   {
     if (count17 == null) {
       this.Count17 = null;
       return;
     }
     this.Count17 = new Long(count17);
   }
 
   public long getCount18()
   {
     if (this.Count18 == null) return 0L;
     return this.Count18.longValue();
   }
 
   public void setCount18(long count18)
   {
     this.Count18 = new Long(count18);
   }
 
   public void setCount18(String count18)
   {
     if (count18 == null) {
       this.Count18 = null;
       return;
     }
     this.Count18 = new Long(count18);
   }
 
   public long getCount19()
   {
     if (this.Count19 == null) return 0L;
     return this.Count19.longValue();
   }
 
   public void setCount19(long count19)
   {
     this.Count19 = new Long(count19);
   }
 
   public void setCount19(String count19)
   {
     if (count19 == null) {
       this.Count19 = null;
       return;
     }
     this.Count19 = new Long(count19);
   }
 
   public long getCount20()
   {
     if (this.Count20 == null) return 0L;
     return this.Count20.longValue();
   }
 
   public void setCount20(long count20)
   {
     this.Count20 = new Long(count20);
   }
 
   public void setCount20(String count20)
   {
     if (count20 == null) {
       this.Count20 = null;
       return;
     }
     this.Count20 = new Long(count20);
   }
 
   public long getCount21()
   {
     if (this.Count21 == null) return 0L;
     return this.Count21.longValue();
   }
 
   public void setCount21(long count21)
   {
     this.Count21 = new Long(count21);
   }
 
   public void setCount21(String count21)
   {
     if (count21 == null) {
       this.Count21 = null;
       return;
     }
     this.Count21 = new Long(count21);
   }
 
   public long getCount22()
   {
     if (this.Count22 == null) return 0L;
     return this.Count22.longValue();
   }
 
   public void setCount22(long count22)
   {
     this.Count22 = new Long(count22);
   }
 
   public void setCount22(String count22)
   {
     if (count22 == null) {
       this.Count22 = null;
       return;
     }
     this.Count22 = new Long(count22);
   }
 
   public long getCount23()
   {
     if (this.Count23 == null) return 0L;
     return this.Count23.longValue();
   }
 
   public void setCount23(long count23)
   {
     this.Count23 = new Long(count23);
   }
 
   public void setCount23(String count23)
   {
     if (count23 == null) {
       this.Count23 = null;
       return;
     }
     this.Count23 = new Long(count23);
   }
 
   public long getCount24()
   {
     if (this.Count24 == null) return 0L;
     return this.Count24.longValue();
   }
 
   public void setCount24(long count24)
   {
     this.Count24 = new Long(count24);
   }
 
   public void setCount24(String count24)
   {
     if (count24 == null) {
       this.Count24 = null;
       return;
     }
     this.Count24 = new Long(count24);
   }
 
   public long getCount25()
   {
     if (this.Count25 == null) return 0L;
     return this.Count25.longValue();
   }
 
   public void setCount25(long count25)
   {
     this.Count25 = new Long(count25);
   }
 
   public void setCount25(String count25)
   {
     if (count25 == null) {
       this.Count25 = null;
       return;
     }
     this.Count25 = new Long(count25);
   }
 
   public long getCount26()
   {
     if (this.Count26 == null) return 0L;
     return this.Count26.longValue();
   }
 
   public void setCount26(long count26)
   {
     this.Count26 = new Long(count26);
   }
 
   public void setCount26(String count26)
   {
     if (count26 == null) {
       this.Count26 = null;
       return;
     }
     this.Count26 = new Long(count26);
   }
 
   public long getCount27()
   {
     if (this.Count27 == null) return 0L;
     return this.Count27.longValue();
   }
 
   public void setCount27(long count27)
   {
     this.Count27 = new Long(count27);
   }
 
   public void setCount27(String count27)
   {
     if (count27 == null) {
       this.Count27 = null;
       return;
     }
     this.Count27 = new Long(count27);
   }
 
   public long getCount28()
   {
     if (this.Count28 == null) return 0L;
     return this.Count28.longValue();
   }
 
   public void setCount28(long count28)
   {
     this.Count28 = new Long(count28);
   }
 
   public void setCount28(String count28)
   {
     if (count28 == null) {
       this.Count28 = null;
       return;
     }
     this.Count28 = new Long(count28);
   }
 
   public long getCount29()
   {
     if (this.Count29 == null) return 0L;
     return this.Count29.longValue();
   }
 
   public void setCount29(long count29)
   {
     this.Count29 = new Long(count29);
   }
 
   public void setCount29(String count29)
   {
     if (count29 == null) {
       this.Count29 = null;
       return;
     }
     this.Count29 = new Long(count29);
   }
 
   public long getCount30()
   {
     if (this.Count30 == null) return 0L;
     return this.Count30.longValue();
   }
 
   public void setCount30(long count30)
   {
     this.Count30 = new Long(count30);
   }
 
   public void setCount30(String count30)
   {
     if (count30 == null) {
       this.Count30 = null;
       return;
     }
     this.Count30 = new Long(count30);
   }
 
   public long getCount31()
   {
     if (this.Count31 == null) return 0L;
     return this.Count31.longValue();
   }
 
   public void setCount31(long count31)
   {
     this.Count31 = new Long(count31);
   }
 
   public void setCount31(String count31)
   {
     if (count31 == null) {
       this.Count31 = null;
       return;
     }
     this.Count31 = new Long(count31);
   }
 
   public String getBackupNo()
   {
     return this.BackupNo;
   }
 
   public void setBackupNo(String backupNo)
   {
     this.BackupNo = backupNo;
   }
 
   public String getBackupOperator()
   {
     return this.BackupOperator;
   }
 
   public void setBackupOperator(String backupOperator)
   {
     this.BackupOperator = backupOperator;
   }
 
   public Date getBackupTime()
   {
     return this.BackupTime;
   }
 
   public void setBackupTime(Date backupTime)
   {
     this.BackupTime = backupTime;
   }
 
   public String getBackupMemo()
   {
     return this.BackupMemo;
   }
 
   public void setBackupMemo(String backupMemo)
   {
     this.BackupMemo = backupMemo;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCStatItemSchema
 * JD-Core Version:    0.5.3
 */