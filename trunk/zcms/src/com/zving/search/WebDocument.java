 package com.zving.search;
 
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import java.io.UnsupportedEncodingException;
 import java.util.Date;
 
 public class WebDocument
 {
   private String Url;
   private String RefUrl;
   private String charset;
   private boolean isTextContent;
   private Date lastmodifiedDate;
   private long lastDownloadTime;
   private int level;
   private transient DocumentList List;
   private transient byte[] Content;
   private String ErrorMessage;
   private Mapx form;
   private boolean isPageUrl;
 
   public String getErrorMessage()
   {
     return this.ErrorMessage;
   }
 
   public void setErrorMessage(String errorMessage) {
     this.ErrorMessage = errorMessage;
   }
 
   public byte[] getContent() {
     if ((this.Content == null) && 
       (this.List != null)) {
       this.Content = this.List.getContent(this.Url);
     }
 
     return this.Content;
   }
 
   public String getContentText() {
     try {
       if (this.charset == null) {
         this.charset = "utf-8";
       }
       byte[] bs = getContent();
       if (bs == null) {
         return null;
       }
       return new String(bs, this.charset);
     } catch (UnsupportedEncodingException e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public String getCharset() {
     return this.charset;
   }
 
   public void setCharset(String charset) {
     this.charset = charset;
   }
 
   public Date getLastmodifiedDate() {
     return this.lastmodifiedDate;
   }
 
   public void setLastmodifiedDate(Date lastmodifiedDate) {
     this.lastmodifiedDate = lastmodifiedDate;
   }
 
   public String getRefUrl() {
     return this.RefUrl;
   }
 
   public void setRefUrl(String refUrl) {
     this.RefUrl = refUrl;
   }
 
   public String getUrl() {
     return this.Url;
   }
 
   public void setUrl(String url) {
     this.Url = url;
   }
 
   public int getLevel() {
     return this.level;
   }
 
   public void setLevel(int level) {
     this.level = level;
   }
 
   public void setContent(byte[] content) {
     this.Content = content;
   }
 
   public long getLastDownloadTime() {
     return this.lastDownloadTime;
   }
 
   public void setLastDownloadTime(long lastDownloadTime) {
     this.lastDownloadTime = lastDownloadTime;
   }
 
   public boolean isTextContent() {
     return this.isTextContent;
   }
 
   public void setTextContent(boolean isTextContent) {
     this.isTextContent = isTextContent;
   }
 
   public DocumentList getList() {
     return this.List;
   }
 
   public void setList(DocumentList list) {
     this.List = list;
   }
 
   public Mapx getForm() {
     return this.form;
   }
 
   public void setForm(Mapx form) {
     this.form = form;
   }
 
   public boolean isPageUrl() {
     return this.isPageUrl;
   }
 
   public void setPageUrl(boolean isPageUrl) {
     this.isPageUrl = isPageUrl;
   }
 
   public byte[] toBytes() {
     Mapx map = new Mapx();
     map.put("Url", this.Url);
     map.put("RefUrl", this.RefUrl);
     map.put("charset", this.charset);
     map.put("isTextContent", new Boolean(this.isTextContent));
     map.put("lastmodifiedDate", this.lastmodifiedDate);
     map.put("lastDownloadTime", new Long(this.lastDownloadTime));
     map.put("level", new Integer(this.level));
     map.put("ErrorMessage", this.ErrorMessage);
     map.put("isPageUrl", new Boolean(this.isPageUrl));
     map.put("form", this.form);
     return FileUtil.mapToBytes(map);
   }
 
   public void parseBytes(byte[] bs) {
     Mapx map = FileUtil.bytesToMap(bs);
     this.Url = map.getString("Url");
     this.RefUrl = map.getString("RefUrl");
     this.charset = map.getString("charset");
     this.isTextContent = ((Boolean)map.get("isTextContent")).booleanValue();
     this.lastmodifiedDate = ((Date)map.get("lastmodifiedDate"));
     this.lastDownloadTime = ((Long)map.get("lastDownloadTime")).longValue();
     this.level = ((Integer)map.get("level")).intValue();
     this.ErrorMessage = map.getString("ErrorMessage");
     this.isPageUrl = ((Boolean)map.get("isPageUrl")).booleanValue();
     this.form = ((Mapx)map.get("form"));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.WebDocument
 * JD-Core Version:    0.5.3
 */