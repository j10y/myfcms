 package com.htsoft.oa.action.system;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.system.FileAttachService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class FileAttachAction extends BaseAction
 {
 
   @Resource
   private FileAttachService fileAttachService;
   private FileAttach fileAttach;
   private Long fileId;
   private String filePath;
 
   public Long getFileId()
   {
     return this.fileId;
   }
 
   public void setFileId(Long fileId) {
     this.fileId = fileId;
   }
 
   public FileAttach getFileAttach() {
     return this.fileAttach;
   }
 
   public void setFileAttach(FileAttach fileAttach) {
     this.fileAttach = fileAttach;
   }
 
   public String getFilePath() {
     return this.filePath;
   }
 
   public void setFilePath(String filePath) {
     this.filePath = filePath;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.fileAttachService.getAll(filter);
 
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
         this.fileAttachService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     FileAttach fileAttach = (FileAttach)this.fileAttachService.get(this.fileId);
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(fileAttach));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.fileAttachService.save(this.fileAttach);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String delete()
   {
     this.fileAttachService.removeByPath(this.filePath);
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.FileAttachAction
 * JD-Core Version:    0.5.4
 */