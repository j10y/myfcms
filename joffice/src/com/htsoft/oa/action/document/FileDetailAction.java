 package com.htsoft.oa.action.document;
 
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.system.FileAttachService;
 import javax.annotation.Resource;
 
 public class FileDetailAction extends BaseAction
 {
 
   @Resource
   private FileAttachService fileAttachService;
   private FileAttach fileAttach;
   private Long fileId;
 
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
 
   public String execute() throws Exception
   {
     this.fileAttach = ((FileAttach)this.fileAttachService.get(this.fileId));
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.document.FileDetailAction
 * JD-Core Version:    0.5.4
 */