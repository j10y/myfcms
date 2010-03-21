 package com.zving.cms.site;
 
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCBadWordSchema;
 import com.zving.schema.ZCBadWordSet;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.Set;
 
 public class BadWord extends Page
 {
   public static final String TREELEVEL_1 = "1";
   public static final String TREELEVEL_2 = "2";
   public static final String TREELEVEL_3 = "3";
   public static final Mapx TREELEVEL_MAP = new Mapx();
   private static Mapx BadWordMap;
 
   static
   {
     TREELEVEL_MAP.put("1", "一般");
     TREELEVEL_MAP.put("2", "比较敏感");
     TREELEVEL_MAP.put("3", "非常敏感");
 
     BadWordMap = new Mapx();
 
     BadWordMap.put("1", new Mapx());
     BadWordMap.put("2", new Mapx());
     BadWordMap.put("3", new Mapx());
     updateCache();
   }
 
   private static void updateCache() {
     ((Mapx)BadWordMap.get("1")).clear();
     ((Mapx)BadWordMap.get("2")).clear();
     ((Mapx)BadWordMap.get("3")).clear();
     DataTable dt = new QueryBuilder("select TreeLevel,Word,ReplaceWord from ZCBadWord order by ID desc").executeDataTable();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       String TreeLevel = dt.getString(i, "TreeLevel");
       if ("3".compareTo(TreeLevel) <= 0) {
         ((Mapx)BadWordMap.get("2")).put(dt.getString(i, "Word"), dt.getString(i, "ReplaceWord"));
         ((Mapx)BadWordMap.get("3")).put(dt.getString(i, "Word"), dt.getString(i, "ReplaceWord"));
       } else if ("2".compareTo(TreeLevel) <= 0) {
         ((Mapx)BadWordMap.get("2")).put(dt.getString(i, "Word"), dt.getString(i, "ReplaceWord"));
       }
       ((Mapx)BadWordMap.get("1")).put(dt.getString(i, "Word"), dt.getString(i, "ReplaceWord"));
     }
   }
 
   public static String checkBadWord(String content) {
     return checkBadWord("1");
   }
 
   public static String checkBadWord(String content, String priority) {
     Mapx map = (Mapx)BadWordMap.get(priority);
     String badwords = "";
     for (Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
       String word = (String)iter.next();
       if ((StringUtil.isNotEmpty(content)) && (!("null".equals(content))) && (content.indexOf(word) != -1)) {
         badwords = badwords + " " + word;
       }
     }
     return badwords;
   }
 
   public static String filterBadWord(String content) {
     return filterBadWord(content, "1");
   }
 
   public static String filterBadWord(String content, String priority) {
     Mapx map = (Mapx)BadWordMap.get(priority);
     String word = null;
     String replaceWord = null;
     for (Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
       word = null;
       replaceWord = null;
       word = (String)iter.next();
       replaceWord = (String)map.get(word);
       if (StringUtil.isNotEmpty(word)) {
         content = StringUtil.replaceAllIgnoreCase(content, word, replaceWord);
       }
     }
     return content;
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     String word = dga.getParam("Word");
     String wherePart = "";
     if (StringUtil.isNotEmpty(word)) {
       wherePart = wherePart + " where Word like '%" + word.trim() + "%'";
       dga.setTotal(0);
     }
     String sql = "select ID,Word,TreeLevel,ReplaceWord,AddUser,AddTime from ZCBadWord " + wherePart;
     if (StringUtil.isNotEmpty(dga.getSortString()))
       sql = sql + dga.getSortString();
     else {
       sql = sql + "Order by ID desc";
     }
 
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(*) from ZCBadWord" + wherePart));
     }
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     dt.decodeColumn("TreeLevel", TREELEVEL_MAP);
     dga.bindData(dt);
   }
 
   public void dg1Edit() {
     DataTable dt = (DataTable)this.Request.get("DT");
     ZCBadWordSet set = new ZCBadWordSet();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       ZCBadWordSchema badWord = new ZCBadWordSchema();
       badWord.setID(Integer.parseInt(dt.getString(i, "ID")));
       badWord.fill();
       badWord.setValue(dt.getDataRow(i));
       badWord.setModifyTime(new Date());
       badWord.setModifyUser(User.getUserName());
 
       set.add(badWord);
     }
     if (set.update()) {
       updateCache();
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("发生错误!");
     }
   }
 
   public static Mapx init(Mapx params) {
     return null;
   }
 
   public void add() {
     String Word = $V("BadWord").trim();
     if (new QueryBuilder("select count(*) from ZCBadWord where Word = ?", Word).executeInt() == 0) {
       ZCBadWordSchema badWord = new ZCBadWordSchema();
       badWord.setID(NoUtil.getMaxID("BadWordID"));
       badWord.setWord(Word);
       badWord.setTreeLevel(Integer.parseInt($V("Level")));
       badWord.setReplaceWord($V("ReplaceWord"));
       badWord.setAddTime(new Date());
       badWord.setAddUser(User.getUserName());
       if (badWord.insert()) {
         updateCache();
         this.Response.setStatus(1);
         this.Response.setMessage("新增成功!");
       } else {
         this.Response.setStatus(0);
         this.Response.setMessage("发生错误!");
       }
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("已经存在的敏感词!");
     }
   }
 
   public void importWords() {
     String FilePath = $V("FilePath");
     String Words = $V("BadWords");
     String wordsText = "";
     if (StringUtil.isNotEmpty(FilePath)) {
       FilePath = FilePath.replaceAll("//", "/");
       wordsText = FileUtil.readText(FilePath);
     } else {
       wordsText = Words;
     }
     String[] badWords = wordsText.split("\n");
     String temp = "";
     Transaction trans = new Transaction();
     ZCBadWordSchema badword = new ZCBadWordSchema();
     for (int i = 0; i < badWords.length; ++i) {
       if ((!(badWords[i].equals("\r"))) && (!(StringUtil.isEmpty(badWords[i])))) {
         temp = badWords[i];
         temp = temp.trim().replaceAll("\\s+", ",");
         temp = temp.replaceAll("，", ",");
         String[] word = StringUtil.splitEx(temp, ",");
         if ((word.length == 0) || (word.length > 3)) continue; if (StringUtil.isEmpty(word[0])) {
           continue;
         }
         boolean flag = false;
         if (new QueryBuilder("select count(*) from ZCBadWord where Word = ?", word[0]).executeInt() > 0)
           flag = true;
         else {
           flag = false;
         }
         badword = new ZCBadWordSchema();
         if (flag) {
           String WordID = new QueryBuilder("select ID from ZCBadWord where Word = ?", word[0]).executeOneValue().toString();
           badword.setID(WordID);
           badword.fill();
           badword.setWord(word[0]);
           if (word.length == 1) {
             badword.setReplaceWord("");
             badword.setTreeLevel(1L);
           } else if (word.length == 2) {
             badword.setReplaceWord(word[1]);
             badword.setTreeLevel(1L);
           } else if (word.length == 3) {
             badword.setReplaceWord(word[1]);
             if ((StringUtil.isDigit(word[2])) && (Integer.parseInt(word[2]) > 0) && (Integer.parseInt(word[2]) < 4))
               badword.setTreeLevel(word[2]);
             else
               badword.setTreeLevel(1L);
           }
         }
         else {
           badword.setID(NoUtil.getMaxID("BadWordID"));
           badword.setWord(word[0]);
           if (word.length == 1) {
             badword.setReplaceWord("");
             badword.setTreeLevel(1L);
           } else if (word.length == 2) {
             badword.setReplaceWord(word[1]);
             badword.setTreeLevel(1L);
           } else if (word.length == 3) {
             badword.setReplaceWord(word[1]);
             if ((StringUtil.isDigit(word[2])) && (Integer.parseInt(word[2]) > 0) && (Integer.parseInt(word[2]) < 4))
               badword.setTreeLevel(word[2]);
             else {
               badword.setTreeLevel(1L);
             }
           }
         }
         if (flag) {
           badword.setModifyTime(new Date());
           badword.setModifyUser(User.getUserName());
           trans.add(badword, 2);
         } else {
           badword.setAddTime(new Date());
           badword.setAddUser(User.getUserName());
           trans.add(badword, 1);
         }
       }
     }
 
     if (trans.commit())
       this.Response.setLogInfo(1, "导入成功");
     else
       this.Response.setLogInfo(0, "导入失败");
   }
 
   public void del()
   {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     Transaction trans = new Transaction();
     ZCBadWordSchema badWord = new ZCBadWordSchema();
     ZCBadWordSet set = badWord.query(new QueryBuilder("where id in (" + ids + ")"));
     trans.add(set, 5);
 
     if (trans.commit()) {
       updateCache();
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.BadWord
 * JD-Core Version:    0.5.3
 */