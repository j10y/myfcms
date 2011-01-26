 package com.htsoft.oa.action.info;
 
 import com.google.gson.Gson;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.info.News;
 import com.htsoft.oa.model.info.NewsComment;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.info.NewsCommentService;
 import com.htsoft.oa.service.info.NewsService;
 import com.htsoft.oa.service.system.AppUserService;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class NewsCommentAction extends BaseAction
 {
 
   @Resource
   private NewsCommentService newsCommentService;
 
   @Resource
   private AppUserService appUserService;
 
   @Resource
   private NewsService newsService;
   private NewsComment newsComment;
   private Long commentId;
 
   public Long getCommentId()
   {
     return this.commentId;
   }
 
   public void setCommentId(Long commentId) {
     this.commentId = commentId;
   }
 
   public NewsComment getNewsComment() {
     return this.newsComment;
   }
 
   public void setNewsComment(NewsComment newsComment) {
     this.newsComment = newsComment;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     String start = getRequest().getParameter("start");
     List list = this.newsCommentService.getAll(filter);
 
     Gson gson = new Gson();
     SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:[");
     for (NewsComment newsComment : list) {
       buff.append("{commentId:'")
         .append(newsComment.getCommentId())
         .append("',subject:").append(gson.toJson(newsComment.getNews().getSubject()))
         .append(",content:")
         .append(gson.toJson(newsComment.getContent()))
         .append(",createtime:'")
         .append(simpleDate.format(newsComment.getCreatetime()))
         .append("',fullname:'")
         .append(newsComment.getFullname())
         .append("',start:'")
         .append(start)
         .append("'},");
     }
     if (list.size() > 0) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         NewsComment delComment = (NewsComment)this.newsCommentService.get(new Long(id));
         News news = delComment.getNews();
         if (news.getReplyCounts().intValue() > 1) {
           news.setReplyCounts(Integer.valueOf(news.getReplyCounts().intValue() - 1));
         }
         this.newsService.save(news);
         this.newsCommentService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     NewsComment newsComment = (NewsComment)this.newsCommentService.get(this.commentId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(newsComment));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     News news = (News)this.newsService.get(this.newsComment.getNewsId());
     news.setReplyCounts(Integer.valueOf(news.getReplyCounts().intValue() + 1));
     this.newsService.save(news);
 
     this.newsComment.setAppUser((AppUser)this.appUserService.get(this.newsComment.getUserId()));
     this.newsComment.setCreatetime(new Date());
     this.newsComment.setNews(news);
     this.newsCommentService.save(this.newsComment);
     setJsonString("{success:true}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.info.NewsCommentAction
 * JD-Core Version:    0.5.4
 */