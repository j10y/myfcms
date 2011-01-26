 package com.htsoft.oa.action.archive;
 
 import com.google.gson.Gson;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.archive.ArchivesDep;
 import com.htsoft.oa.service.archive.ArchivesDepService;
 import flexjson.JSONSerializer;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class ArchivesDepAction extends BaseAction
 {
 
   @Resource
   private ArchivesDepService archivesDepService;
   private ArchivesDep archivesDep;
   private Long archDepId;
 
   public Long getArchDepId()
   {
     return this.archDepId;
   }
 
   public void setArchDepId(Long archDepId) {
     this.archDepId = archDepId;
   }
 
   public ArchivesDep getArchivesDep() {
     return this.archivesDep;
   }
 
   public void setArchivesDep(ArchivesDep archivesDep) {
     this.archivesDep = archivesDep;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_signUserID_L_EQ", ContextUtil.getCurrentUserId().toString());
     List list = this.archivesDepService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "archives.createtime" });
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
         this.archivesDepService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ArchivesDep archivesDep = (ArchivesDep)this.archivesDepService.get(this.archDepId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(archivesDep));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.archivesDepService.save(this.archivesDep);
     setJsonString("{success:true}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.archive.ArchivesDepAction
 * JD-Core Version:    0.5.4
 */