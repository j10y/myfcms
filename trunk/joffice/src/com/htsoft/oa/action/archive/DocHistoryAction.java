 package com.htsoft.oa.action.archive;
 
 import com.google.gson.Gson;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.archive.DocHistory;
 import com.htsoft.oa.service.archive.DocHistoryService;
 import flexjson.JSONSerializer;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class DocHistoryAction extends BaseAction
 {
 
   @Resource
   private DocHistoryService docHistoryService;
   private DocHistory docHistory;
   private Long historyId;
 
   public Long getHistoryId()
   {
     return this.historyId;
   }
 
   public void setHistoryId(Long historyId) {
     this.historyId = historyId;
   }
 
   public DocHistory getDocHistory() {
     return this.docHistory;
   }
 
   public void setDocHistory(DocHistory docHistory) {
     this.docHistory = docHistory;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.docHistoryService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "updatetime" });
     buff.append(json.serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.docHistoryService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     DocHistory docHistory = (DocHistory)this.docHistoryService.get(this.historyId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(docHistory));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.docHistoryService.save(this.docHistory);
     setJsonString("{success:true}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.archive.DocHistoryAction
 * JD-Core Version:    0.5.4
 */