 package com.zving.framework.controls;
 
 import com.zving.framework.Constant;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.SchemaSet;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class DataListAction
   implements IControlAction
 {
   private DataTable DataSource;
   private String ID;
   private String TagBody;
   private boolean page;
   protected Mapx Params = new Mapx();
   private String method;
   private int total;
   private int pageIndex;
   private int pageSize;
 
   public String getMethod()
   {
     return this.method;
   }
 
   public void setMethod(String method) {
     this.method = method;
   }
 
   public Mapx getParams() {
     return this.Params;
   }
 
   public void setParams(Mapx params) {
     this.Params = params;
   }
 
   public String getParam(String key) {
     return this.Params.getString(key);
   }
 
   public void bindData(DataTable dt) {
     this.DataSource = dt;
     try {
       bindData();
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public void bindData(SchemaSet set) {
     bindData(set.toDataTable());
   }
 
   private void bindData() throws Exception {
     HtmlUtil.replaceWithDataTable(this.DataSource, this.TagBody);
   }
 
   public String getHtml() {
     StringBuffer sb = new StringBuffer();
     sb.append("<!--_ZVING_DATALIST_START_" + this.ID + "-->");
     sb.append("<input type='hidden' id='" + this.ID + "' method='" + this.method + "'>");
     sb.append(HtmlUtil.replaceWithDataTable(this.DataSource, this.TagBody));
     HtmlScript script = new HtmlScript();
     script.setInnerHTML(getScript());
     sb.append(script.getOuterHtml());
     sb.append("<!--_ZVING_DATALIST_END_" + this.ID + "-->");
     return sb.toString();
   }
 
   public String getScript() {
     StringBuffer sb = new StringBuffer();
 
     sb.append("$('" + this.ID + "').TagBody = \"" + StringUtil.htmlEncode(getTagBody().replaceAll("\\s+", " ")) + "\";");
     Object[] ks = this.Params.keyArray();
     Object[] vs = this.Params.valueArray();
     for (int i = 0; i < this.Params.size(); ++i) {
       Object key = ks[i];
       if ((!(key.equals("_ZVING_TAGBODY"))) && (!(key.toString().startsWith("Cookie."))) && (!(key.toString().startsWith("Header.")))) {
         sb.append("DataList.setParam('" + this.ID + "','" + key + "',\"" + vs[i] + "\");");
       }
 
     }
 
     if (this.page) {
       int type = 1;
       if (StringUtil.isNotEmpty(this.Params.getString("PageBarType"))) {
         type = Integer.parseInt(this.Params.getString("PageBarType"));
       }
       String html = PageBarTag.getPageBarHtml(this.ID, type, this.total, this.pageSize, this.pageIndex);
       sb.append("\ntry{$('_PageBar_" + this.ID + "').innerHTML=\"" + StringUtil.javaEncode(html) + "\";}catch(ex){}\n");
     }
 
     sb.append("DataList.setParam('" + this.ID + "','" + "_ZVING_PAGEINDEX" + "'," + this.pageIndex + ");");
     sb.append("DataList.setParam('" + this.ID + "','" + "_ZVING_PAGETOTAL" + "'," + this.total + ");");
     sb.append("DataList.setParam('" + this.ID + "','" + "_ZVING_PAGE" + "'," + this.page + ");");
     sb.append("DataList.setParam('" + this.ID + "','" + "_ZVING_SIZE" + "'," + this.pageSize + ");");
     sb.append("");
     sb.append("DataList.init('" + this.ID + "');");
     String content = sb.toString();
     Matcher matcher = Constant.PatternField.matcher(content);
     sb = new StringBuffer();
     int lastEndIndex = 0;
     while (matcher.find(lastEndIndex)) {
       sb.append(content.substring(lastEndIndex, matcher.start()));
       sb.append("$\\{");
       sb.append(matcher.group(1));
       sb.append("}");
       lastEndIndex = matcher.end();
     }
     sb.append(content.substring(lastEndIndex));
 
     return sb.toString();
   }
 
   public String getTagBody()
   {
     return this.TagBody;
   }
 
   public void setTagBody(String tagBody) {
     this.TagBody = tagBody;
   }
 
   public DataTable getDataSource() {
     return this.DataSource;
   }
 
   public String getID() {
     return this.ID;
   }
 
   public void setID(String id) {
     this.ID = id;
   }
 
   public boolean isPage() {
     return this.page;
   }
 
   public void setPage(boolean page) {
     this.page = page;
   }
 
   public int getTotal() {
     return this.total;
   }
 
   public void setTotal(int total) {
     this.total = total;
   }
 
   public void setTotal(QueryBuilder qb) {
     setTotal(qb.executeInt());
   }
 
   public int getPageIndex() {
     return this.pageIndex;
   }
 
   public void setPageIndex(int pageIndex) {
     this.pageIndex = pageIndex;
   }
 
   public int getPageSize()
   {
     return this.pageSize;
   }
 
   public void setPageSize(int pageSize) {
     this.pageSize = pageSize;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.DataListAction
 * JD-Core Version:    0.5.3
 */