 package com.htsoft.oa.action.info;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.StringUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.info.News;
 import com.htsoft.oa.model.info.NewsType;
 import com.htsoft.oa.service.info.NewsService;
 import com.htsoft.oa.service.info.NewsTypeService;
 import flexjson.JSONSerializer;
 import java.lang.reflect.Type;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class NewsAction extends BaseAction
 {
 
   @Resource
   private NewsService newsService;
 
   @Resource
   private NewsTypeService newsTypeService;
   private News news;
   private List<News> list;
   private Long newsId;
   private Long typeId;
   private NewsType newsType;
 
   public List<News> getList()
   {
     return this.list;
   }
 
   public void setList(List<News> list) {
     this.list = list;
   }
 
   public Long getTypeId()
   {
     return this.typeId;
   }
 
   public void setTypeId(Long typeId) {
     this.typeId = typeId;
   }
 
   public Long getNewsId() {
     return this.newsId;
   }
 
   public void setNewsId(Long newsId) {
     this.newsId = newsId;
   }
 
   public News getNews() {
     return this.news;
   }
 
   public void setNews(News news) {
     this.news = news;
   }
 
   public NewsType getNewsType()
   {
     return this.newsType;
   }
 
   public void setNewsType(NewsType newsType) {
     this.newsType = newsType;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.newsService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.newsService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     News news = (News)this.newsService.get(this.newsId);
 
     JSONSerializer serializer = new JSONSerializer();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(serializer.exclude(new String[] { "class", "newsComments" }).serialize(news));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     String isDeskNews = getRequest().getParameter("isDeskImage");
     if (StringUtils.isNotEmpty(isDeskNews))
       this.news.setIsDeskImage(News.ISDESKNEWS);
     else {
       this.news.setIsDeskImage(News.NOTDESKNEWS);
     }
     News old = new News();
     Boolean isNew = Boolean.valueOf(false);
     if (this.news.getNewsId() == null) {
       isNew = Boolean.valueOf(true);
     }
     if (this.news.getTypeId() != null) {
       setNewsType((NewsType)this.newsTypeService.get(this.news.getTypeId()));
       this.news.setNewsType(this.newsType);
     }
     if (isNew.booleanValue()) {
       this.news.setCreatetime(new Date());
       this.news.setUpdateTime(new Date());
       this.news.setReplyCounts(Integer.valueOf(0));
       this.news.setViewCounts(Integer.valueOf(0));
       this.newsService.save(this.news);
     } else {
       old = (News)this.newsService.get(this.news.getNewsId());
       this.news.setUpdateTime(new Date());
       this.news.setCreatetime(old.getCreatetime());
       this.news.setViewCounts(Integer.valueOf(old.getViewCounts().intValue() + 1));
       this.news.setReplyCounts(old.getReplyCounts());
       this.newsService.merge(this.news);
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   public String category()
   {
     List list = null;
     PagingBean pb = getInitPagingBean();
     if ((this.typeId != null) && (this.typeId.longValue() > 0L))
       list = this.newsService.findByTypeId(this.typeId, pb);
     else {
       list = this.newsService.getAll(pb);
     }
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':" + pb.getTotalItems() + ",result:");
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
     setJsonString(buff.toString());
     return "success";
   }
 
   public String icon()
   {
     setNews((News)this.newsService.get(getNewsId()));
     this.news.setSubjectIcon("");
     this.newsService.save(this.news);
     return "success";
   }
 
   public String search()
   {
     PagingBean pb = getInitPagingBean();
     String searchContent = getRequest().getParameter("searchContent");
     List list = this.newsService.findBySearch(searchContent, pb);
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(",result:");
 
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String display()
   {
     PagingBean pb = new PagingBean(0, 8);
 
     List list = this.newsService.findBySearch(null, pb);
     getRequest().setAttribute("newsList", list);
     return "display";
   }
 
   public String image()
   {
     PagingBean pb = new PagingBean(0, 8);
 
     List list = this.newsService.findImageNews(pb);
     List newList = new ArrayList();
     for (News news : list) {
       String content = StringUtil.html2Text(news.getContent());
       if (content.length() > 250) {
         content = content.substring(0, 250);
       }
       news.setContent(content);
       newList.add(news);
     }
     getRequest().setAttribute("imageNewsList", newList);
     return "image";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.info.NewsAction
 * JD-Core Version:    0.5.4
 */