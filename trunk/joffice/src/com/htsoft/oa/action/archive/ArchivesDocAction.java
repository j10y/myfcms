 package com.htsoft.oa.action.archive;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.archive.ArchivesDoc;
 import com.htsoft.oa.model.archive.DocHistory;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.archive.ArchivesDocService;
 import com.htsoft.oa.service.archive.DocHistoryService;
 import com.htsoft.oa.service.system.FileAttachService;
 import flexjson.JSONSerializer;
 import java.lang.reflect.Type;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class ArchivesDocAction extends BaseAction
 {
 
   @Resource
   private ArchivesDocService archivesDocService;
 
   @Resource
   private DocHistoryService docHistoryService;
 
   @Resource
   private FileAttachService fileAttachService;
   private ArchivesDoc archivesDoc;
   private Long docId;
 
   public Long getDocId()
   {
     return this.docId;
   }
 
   public void setDocId(Long docId) {
     this.docId = docId;
   }
 
   public ArchivesDoc getArchivesDoc() {
     return this.archivesDoc;
   }
 
   public void setArchivesDoc(ArchivesDoc archivesDoc) {
     this.archivesDoc = archivesDoc;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     String archivesId = getRequest().getParameter("archivesId");
     if ((archivesId != null) && (!"".equals(archivesId))) {
       filter.addFilter("Q_archives.archivesId_L_EQ", archivesId);
     }
     List list = this.archivesDocService.getAll(filter);
 
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
         this.archivesDocService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ArchivesDoc archivesDoc = (ArchivesDoc)this.archivesDocService.get(this.docId);
 
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(archivesDoc));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     AppUser curUser = ContextUtil.getCurrentUser();
     if (this.archivesDoc.getDocId() == null)
     {
       this.archivesDoc.initUsers(curUser);
       this.archivesDoc.setDocStatus(Short.valueOf(ArchivesDoc.STATUS_MODIFY));
       this.archivesDoc.setUpdatetime(new Date());
       this.archivesDoc.setCreatetime(new Date());
       this.archivesDoc.setCurVersion(Integer.valueOf(ArchivesDoc.ORI_VERSION));
       this.archivesDoc.setFileAttach(this.fileAttachService.getByPath(this.archivesDoc.getDocPath()));
       this.archivesDocService.save(this.archivesDoc);
     }
     else {
       ArchivesDoc oldVersion = (ArchivesDoc)this.archivesDocService.get(this.archivesDoc.getDocId());
       this.archivesDoc.setCreatetime(oldVersion.getCreatetime());
       this.archivesDoc.setArchives(oldVersion.getArchives());
       this.archivesDoc.setCreatorId(oldVersion.getCreatorId());
       this.archivesDoc.setFileAttach(this.fileAttachService.getByPath(this.archivesDoc.getDocPath()));
       this.archivesDoc.setCreator(oldVersion.getCreator());
       this.archivesDoc.setDocStatus(Short.valueOf(ArchivesDoc.STATUS_MODIFY));
       this.archivesDoc.setUpdatetime(new Date());
       this.archivesDoc.setCurVersion(Integer.valueOf(oldVersion.getCurVersion().intValue() + 1));
       this.archivesDoc.setMender(curUser.getFullname());
       this.archivesDoc.setMenderId(curUser.getUserId());
       this.archivesDoc.setDocHistorys(oldVersion.getDocHistorys());
       this.archivesDoc.setFileAttach(this.fileAttachService.getByPath(this.archivesDoc.getDocPath()));
       this.archivesDocService.merge(this.archivesDoc);
     }
 
     DocHistory docHistory = new DocHistory();
     docHistory.setArchivesDoc(this.archivesDoc);
     docHistory.setFileAttach(this.fileAttachService.getByPath(this.archivesDoc.getDocPath()));
     docHistory.setDocName(this.archivesDoc.getDocName());
     docHistory.setPath(this.archivesDoc.getDocPath());
     docHistory.setVersion(this.archivesDoc.getCurVersion());
     docHistory.setUpdatetime(new Date());
     docHistory.setMender(curUser.getFullname());
     this.docHistoryService.save(docHistory);
 
     StringBuffer buff = new StringBuffer("{success:true,data:");
 
     JSONSerializer json = new JSONSerializer();
     buff.append(json.exclude(new String[] { "class", "docHistorys" }).serialize(this.archivesDoc));
 
     buff.append("}");
 
     setJsonString(buff.toString());
     return "success";
   }
 
   public String copy() {
     String historyId = getRequest().getParameter("historyId");
     DocHistory docHistory = (DocHistory)this.docHistoryService.get(new Long(historyId));
     DocHistory newHistory = new DocHistory();
 
     this.archivesDoc = docHistory.getArchivesDoc();
 
     newHistory.setDocName(docHistory.getDocName());
     newHistory.setFileAttach(docHistory.getFileAttach());
 
     newHistory.setMender(ContextUtil.getCurrentUser().getFullname());
     newHistory.setPath(docHistory.getPath());
     newHistory.setUpdatetime(new Date());
     newHistory.setVersion(Integer.valueOf(this.archivesDoc.getCurVersion().intValue() + 1));
     newHistory.setArchivesDoc(this.archivesDoc);
     this.docHistoryService.save(newHistory);
 
     this.archivesDoc.setCurVersion(newHistory.getVersion());
     this.archivesDoc.setDocPath(newHistory.getPath());
     this.archivesDoc.setFileAttach(newHistory.getFileAttach());
 
     this.archivesDocService.save(this.archivesDoc);
 
     StringBuffer buff = new StringBuffer("{success:true,data:");
     JSONSerializer json = new JSONSerializer();
     buff.append(json.exclude(new String[] { "class", "docHistorys" }).serialize(this.archivesDoc));
     buff.append("}");
 
     setJsonString(buff.toString());
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.archive.ArchivesDocAction
 * JD-Core Version:    0.5.4
 */