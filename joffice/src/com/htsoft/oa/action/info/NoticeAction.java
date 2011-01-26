 package com.htsoft.oa.action.info;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.info.Notice;
 import com.htsoft.oa.service.info.NoticeService;
 import flexjson.DateTransformer;
 import flexjson.JSONSerializer;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class NoticeAction extends BaseAction
 {
 
   @Resource
   private NoticeService noticeService;
   private Notice notice;
   private List<Notice> list;
   private Long noticeId;
 
   public List<Notice> getList()
   {
     return this.list;
   }
 
   public void setList(List<Notice> list) {
     this.list = list;
   }
 
   public Long getNoticeId()
   {
     return this.noticeId;
   }
 
   public void setNoticeId(Long noticeId) {
     this.noticeId = noticeId;
   }
 
   public Notice getNotice() {
     return this.notice;
   }
 
   public void setNotice(Notice notice) {
     this.notice = notice;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.noticeService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new Gson();
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
         this.noticeService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     Notice notice = (Notice)this.noticeService.get(this.noticeId);
 
     JSONSerializer serializer = new JSONSerializer();
     serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), new String[] { "effectiveDate" });
     serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), new String[] { "expirationDate" });
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(serializer.exclude(new String[] { "class" }).serialize(notice));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.noticeService.save(this.notice);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String search()
   {
     PagingBean pb = getInitPagingBean();
     String searchContent = getRequest().getParameter("searchContent");
     List list = this.noticeService.findBySearch(searchContent, pb);
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(",result:");
 
     Gson gson = new Gson();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String display()
   {
     PagingBean pb = new PagingBean(0, 8);
     List list = this.noticeService.findBySearch(null, pb);
     getRequest().setAttribute("noticeList", list);
     return "display";
   }
 
   public String scroller()
   {
     PagingBean pb = new PagingBean(0, 8);
     List list = this.noticeService.findBySearch(null, pb);
     getRequest().setAttribute("noticeList", list);
     return "scroller";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.info.NoticeAction
 * JD-Core Version:    0.5.4
 */