 package com.htsoft.oa.action.document;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.document.DocFolder;
 import com.htsoft.oa.model.document.Document;
 import com.htsoft.oa.model.system.AppRole;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.Department;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.document.DocFolderService;
 import com.htsoft.oa.service.document.DocPrivilegeService;
 import com.htsoft.oa.service.document.DocumentService;
 import com.htsoft.oa.service.system.FileAttachService;
 import java.lang.reflect.Type;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class DocumentAction extends BaseAction
 {
 
   @Resource
   private DocumentService documentService;
 
   @Resource
   private FileAttachService fileAttachService;
 
   @Resource
   private DocFolderService docFolderService;
 
   @Resource
   private DocPrivilegeService docPrivilegeService;
   private AppUser appUser;
   private Document document;
   private Date from;
   private Date to;
   private Long docId;
 
   public AppUser getAppUser()
   {
     return this.appUser;
   }
 
   public void setAppUser(AppUser appUser) {
     this.appUser = appUser;
   }
 
   public Date getFrom() {
     return this.from;
   }
 
   public void setFrom(Date from) {
     this.from = from;
   }
 
   public Date getTo() {
     return this.to;
   }
 
   public void setTo(Date to) {
     this.to = to;
   }
 
   public Long getDocId()
   {
     return this.docId;
   }
 
   public void setDocId(Long docId) {
     this.docId = docId;
   }
 
   public Document getDocument() {
     return this.document;
   }
 
   public void setDocument(Document document) {
     this.document = document;
   }
 
   public String share()
   {
     HttpServletRequest request = getRequest();
     String userIds = request.getParameter("sharedUserIds");
     String depIds = request.getParameter("sharedDepIds");
     String roleIds = request.getParameter("sharedRoleIds");
     String docId = request.getParameter("docId");
     String userNames = request.getParameter("sharedUserNames");
     String depNames = request.getParameter("sharedDepNames");
     String roleNames = request.getParameter("sharedRoleNames");
     if ((StringUtils.isNotEmpty(userIds)) || (StringUtils.isNotEmpty(depIds)) || (StringUtils.isNotEmpty(roleIds))) {
       Document doc = (Document)this.documentService.get(new Long(docId));
       doc.setSharedUserIds(userIds);
       doc.setSharedRoleIds(roleIds);
       doc.setSharedDepIds(depIds);
       doc.setSharedUserNames(userNames);
       doc.setSharedDepNames(depNames);
       doc.setSharedRoleNames(roleNames);
       doc.setIsShared(Document.SHARED);
       this.documentService.save(doc);
     }
 
     return "success";
   }
 
   public String shareList()
   {
     PagingBean pb = getInitPagingBean();
     AppUser appUser = ContextUtil.getCurrentUser();
     Set appRoles = appUser.getRoles();
     Long depId = null;
     if (!appUser.getUserId().equals(AppUser.SUPER_USER)) {
       Department dep = appUser.getDepartment();
       depId = dep.getDepId();
     }
     Iterator it = appRoles.iterator();
     ArrayList arrayList = new ArrayList();
     while (it.hasNext()) {
       arrayList.add(((AppRole)it.next()).getRoleId());
     }
     List list = this.documentService.findByIsShared(this.document, this.from, this.to, appUser.getUserId(), arrayList, depId, pb);
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(",result:");
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
     setJsonString(buff.toString());
     return "success";
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_docFolder.appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
     String folderId = getRequest().getParameter("folderId");
     String path = null;
     if ((StringUtils.isNotEmpty(folderId)) && (!"0".equals(folderId))) {
       path = ((DocFolder)this.docFolderService.get(new Long(folderId))).getPath();
     }
     if (path != null) {
       filter.addFilter("Q_docFolder.path_S_LK", path + "%");
     }
     List list = this.documentService.getAll(filter);
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
 
   public String publicList()
   {
     PagingBean pb = getInitPagingBean();
     String strFolderId = getRequest().getParameter("folderId");
     String path = null;
     if (StringUtils.isNotEmpty(strFolderId)) {
       Long folderId = new Long(strFolderId);
       if (folderId.longValue() > 0L) {
         path = ((DocFolder)this.docFolderService.get(new Long(strFolderId))).getPath();
       }
     }
     List list = this.documentService.findByPublic(path, this.document, this.from, this.to, ContextUtil.getCurrentUser(), pb);
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
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids)
       {
         this.documentService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     Document document = (Document)this.documentService.get(this.docId);
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:[");
     sb.append(gson.toJson(document));
     sb.append("]}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String save()
   {
     String fileIds = getRequest().getParameter("fileIds");
     String folderId = getRequest().getParameter("folderId");
     this.document.setSharedDepIds(this.document.getSharedDepIds());
     this.document.setSharedRoleIds(this.document.getSharedRoleIds());
     this.document.setSharedUserIds(this.document.getSharedUserIds());
     if (StringUtils.isNotEmpty(fileIds)) {
       this.document.getAttachFiles().clear();
       String[] fIds = fileIds.split(",");
       for (int i = 0; i < fIds.length; ++i) {
         FileAttach fileAttach = (FileAttach)this.fileAttachService.get(new Long(fIds[i]));
         this.document.getAttachFiles().add(fileAttach);
       }
     }
 
     if ((StringUtils.isNotEmpty(folderId)) && (!"0".equals(folderId))) {
       DocFolder folder = (DocFolder)this.docFolderService.get(new Long(folderId));
       this.document.setDocFolder(folder);
     }
     if (this.document.getDocId() == null) {
       AppUser appUser = ContextUtil.getCurrentUser();
       this.document.setAppUser(appUser);
       this.document.setFullname(appUser.getFullname());
       this.document.setCreatetime(new Date());
       this.document.setUpdatetime(new Date());
       this.document.setIsShared(Document.NOT_SHARED);
 
       if (this.document.getAttachFiles().size() > 0)
         this.document.setHaveAttach(Document.HAVE_ATTACH);
       else {
         this.document.setHaveAttach(Document.NOT_HAVE_ATTACH);
       }
       this.documentService.save(this.document);
     }
     else {
       Document doc = (Document)this.documentService.get(this.document.getDocId());
       doc.setUpdatetime(new Date());
       doc.setDocName(this.document.getDocName());
       doc.setContent(this.document.getContent());
       doc.setAttachFiles(this.document.getAttachFiles());
       if (this.document.getAttachFiles().size() > 0)
         doc.setHaveAttach(Document.HAVE_ATTACH);
       else {
         doc.setHaveAttach(Document.NOT_HAVE_ATTACH);
       }
       this.documentService.save(doc);
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   public String detail()
   {
     String strDocId = getRequest().getParameter("docId");
     if (StringUtils.isNotEmpty(strDocId)) {
       Long docId = Long.valueOf(Long.parseLong(strDocId));
       this.document = ((Document)this.documentService.get(docId));
     }
     return "detail";
   }
 
   public String publicDetail() {
     String strDocId = getRequest().getParameter("docId");
     if (StringUtils.isNotEmpty(strDocId)) {
       Long docId = Long.valueOf(Long.parseLong(strDocId));
       this.document = ((Document)this.documentService.get(docId));
     }
     return "publicDetail";
   }
 
   public String right()
   {
     String strDocId = getRequest().getParameter("docId");
     Integer right = Integer.valueOf(0);
     Document document = new Document();
     AppUser appUser = ContextUtil.getCurrentUser();
     if (StringUtils.isNotEmpty(strDocId)) {
       Long docId = new Long(strDocId);
       right = this.docPrivilegeService.getRightsByDocument(appUser, docId);
       document = (Document)this.documentService.get(docId);
     }
     Integer rightD = Integer.valueOf(0);
     Integer rightM = Integer.valueOf(0);
     String strRight = Integer.toBinaryString(right.intValue());
     char[] cc = strRight.toCharArray();
     if ((cc.length == 2) && 
       (cc[0] == '1')) {
       rightM = Integer.valueOf(1);
     }
 
     if (cc.length == 3) {
       if (cc[0] == '1') {
         rightD = Integer.valueOf(1);
       }
       if (cc[1] == '1') {
         rightM = Integer.valueOf(1);
       }
     }
 
     setJsonString("{success:true,rightM:'" + rightM + "',rightD:'" + rightD + "',docName:'" + document.getDocName() + "'}");
     return "success";
   }
 
   public String search() {
     PagingBean pb = getInitPagingBean();
     String content = getRequest().getParameter("content");
     AppUser appUser = ContextUtil.getCurrentUser();
     List list = this.documentService.searchDocument(appUser, content, pb);
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(",result:");
     buff.append(gson.toJson(list, type));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String display()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_docFolder.appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
     List list = this.documentService.getAll(filter);
     getRequest().setAttribute("documentList", list);
     return "display";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.document.DocumentAction
 * JD-Core Version:    0.5.4
 */