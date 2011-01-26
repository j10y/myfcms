 package com.htsoft.oa.action.communicate;
 
 import com.google.gson.Gson;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.util.StringUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.communicate.Mail;
 import com.htsoft.oa.model.communicate.MailBox;
 import com.htsoft.oa.model.communicate.MailFolder;
 import com.htsoft.oa.model.info.ShortMessage;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.communicate.MailBoxService;
 import com.htsoft.oa.service.communicate.MailFolderService;
 import com.htsoft.oa.service.communicate.MailService;
 import com.htsoft.oa.service.info.InMessageService;
 import com.htsoft.oa.service.info.ShortMessageService;
 import com.htsoft.oa.service.system.AppUserService;
 import com.htsoft.oa.service.system.FileAttachService;
 import flexjson.JSONSerializer;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 
 public class MailAction extends BaseAction
 {
   static long FOLDER_ID_RECEIVE = 1L;
   static long FOLDER_ID_SEND = 2L;
   static long FOLDER_ID_DRAFT = 3L;
   static long FOLDER_ID_DELETE = 4L;
 
   static short HAVE_DELETE = 1;
   static short NOT_DELETE = 0;
   static short HAVE_READ = 1;
   static short NOT_READ = 0;
   static Short HAVE_REPLY = Short.valueOf(1);
   static short NOT_REPLY = 0;
   static short SYSTEM_MESSAGE = 4;
   static short COMMON = 1;
 
   static short IS_DRAFT = 0;
   static short IS_MAIL = 1;
 
   @Resource
   private MailService mailService;
 
   @Resource
   private FileAttachService fileAttachService;
 
   @Resource
   private AppUserService appUserService;
 
   @Resource
   private MailFolderService mailFolderService;
 
   @Resource
   private MailBoxService mailBoxService;
 
   @Resource
   private ShortMessageService shortMessageService;
 
   @Resource
   private InMessageService inMessageService;
   private Mail mail;
   private Long mailId;
   private AppUser appUser;
   private Long folderId;
   private Long boxId;
   private String sendMessage;
   private Long replyBoxId;
   private String boxIds;
   private Long fileId;
 
   public Long getMailId()
   {
     return this.mailId;
   }
 
   public void setMailId(Long mailId) {
     this.mailId = mailId;
   }
 
   public Mail getMail() {
     return this.mail;
   }
 
   public void setMail(Mail mail) {
     this.mail = mail;
   }
 
   public AppUser getAppUser() {
     return this.appUser;
   }
 
   public void setAppUser(AppUser appUser) {
     this.appUser = appUser;
   }
 
   public Long getFolderId() {
     if (this.folderId == null) {
       return Long.valueOf(1L);
     }
     return this.folderId;
   }
 
   public void setFolderId(Long folderId)
   {
     this.folderId = folderId;
   }
 
   public Long getBoxId() {
     return this.boxId;
   }
 
   public void setBoxId(Long boxId) {
     this.boxId = boxId;
   }
 
   public String getBoxIds() {
     return this.boxIds;
   }
 
   public void setBoxIds(String boxIds) {
     this.boxIds = boxIds;
   }
 
   public String getSendMessage() {
     return this.sendMessage;
   }
 
   public void setSendMessage(String sendMessage) {
     this.sendMessage = sendMessage;
   }
 
   public Long getReplyBoxId() {
     return this.replyBoxId;
   }
 
   public void setReplyBoxId(Long replyBoxId) {
     this.replyBoxId = replyBoxId;
   }
 
   public Long getFileId() {
     return this.fileId;
   }
 
   public void setFileId(Long fileId) {
     this.fileId = fileId;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     if ((this.folderId == null) || (this.folderId.longValue() == FOLDER_ID_RECEIVE)) {
       setFolderId(new Long(FOLDER_ID_RECEIVE));
     }
     filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
     filter.addFilter("Q_mailFolder.folderId_L_EQ", this.folderId.toString());
     if (this.folderId.longValue() != FOLDER_ID_DELETE) {
       filter.addFilter("Q_delFlag_SN_EQ", "0");
     }
     filter.addSorted("sendTime", "desc");
     List list = this.mailBoxService.getAll(filter);
 
     Gson gson = new Gson();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:[");
     for (MailBox mailBoxTemp : list) {
       Mail mailTemp = mailBoxTemp.getMail();
       buff.append("{boxId:'")
         .append(mailBoxTemp.getBoxId())
         .append("',sendTime:'")
         .append(mailBoxTemp.getSendTime())
         .append("',delFlag:'")
         .append(mailBoxTemp.getDelFlag())
         .append("',readFlag:'")
         .append(mailBoxTemp.getReadFlag())
         .append("',replyFlag:'")
         .append(mailBoxTemp.getReplyFlag())
         .append("',mailId:'")
         .append(mailTemp.getMailId())
         .append("',importantFlag:'")
         .append(mailTemp.getImportantFlag())
         .append("',mailStatus:'")
         .append(mailTemp.getMailStatus())
         .append("',fileIds:'")
         .append(mailTemp.getFileIds())
         .append("',subject:'")
         .append(gson.toJson(mailTemp.getSubject()).replace("\"", ""))
         .append("',recipientNames:'")
         .append(mailTemp.getRecipientNames())
         .append("',sender:'")
         .append(mailTemp.getSender())
         .append("',content:'");
       String content = StringUtil.html2Text(mailTemp.getContent());
       content = gson.toJson(content).replace("\"", "");
 
       if (content.length() > 100) {
         content = content.substring(0, 100) + "...";
       }
       buff.append(content);
       buff.append("'},");
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
     MailFolder deleteFolder = (MailFolder)this.mailFolderService.get(Long.valueOf(FOLDER_ID_DELETE));
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       if (getFolderId().longValue() == FOLDER_ID_DELETE) {
         for (String id : ids)
           this.mailBoxService.remove(new Long(id));
       }
       else {
         for (String id : ids) {
           MailBox mailBoxDelete = (MailBox)this.mailBoxService.get(new Long(id));
           mailBoxDelete.setDelFlag(Short.valueOf(HAVE_DELETE));
           mailBoxDelete.setMailFolder(deleteFolder);
           this.mailBoxService.save(mailBoxDelete);
         }
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     String opt = getRequest().getParameter("opt");
     MailBox mailBox = null;
     if ((opt == null) || ("".equals(opt))) {
       mailBox = (MailBox)this.mailBoxService.get(this.boxId);
       getRequest().setAttribute("__haveNextMailFlag", "");
     }
     else {
       String folderId = getRequest().getParameter("folderId");
       if ((folderId == null) || ("".equals(folderId))) {
         folderId = "1";
       }
 
       QueryFilter filter = new QueryFilter(getRequest());
       filter.getPagingBean().setPageSize(1);
       List list = null;
       filter.addFilter("Q_appUser.userId_L_EQ", 
         ContextUtil.getCurrentUserId().toString());
       filter.addFilter("Q_delFlag_SN_EQ", "0");
       filter.addFilter("Q_mailFolder.folderId_L_EQ", folderId);
 
       if (opt.equals("_next"))
       {
         filter.addFilter("Q_boxId_L_GT", this.boxId.toString());
         list = this.mailBoxService.getAll(filter);
         if (filter.getPagingBean().getStart().intValue() + 1 == filter
           .getPagingBean().getTotalItems())
           getRequest().setAttribute("__haveNextMailFlag", "endNext");
       }
       else if (opt.equals("_pre"))
       {
         filter.addFilter("Q_boxId_L_LT", this.boxId.toString());
         filter.addSorted("boxId", "desc");
         list = this.mailBoxService.getAll(filter);
         if (filter.getPagingBean().getStart().intValue() + 1 == filter
           .getPagingBean().getTotalItems()) {
           getRequest().setAttribute("__haveNextMailFlag", "endPre");
         }
       }
       if (list.size() > 0)
         mailBox = (MailBox)list.get(0);
       else {
         mailBox = (MailBox)this.mailBoxService.get(this.boxId);
       }
     }
 
     setMail(mailBox.getMail());
     mailBox.setReadFlag(Short.valueOf(HAVE_READ));
     this.mailBoxService.save(mailBox);
 
     if (this.mail.getMailStatus().shortValue() != 1) {
       JSONSerializer serializer = new JSONSerializer();
 
       StringBuffer sb = new StringBuffer(
         "{success:true,totalCounts:1,data:[");
       sb.append(serializer.exclude(
         new String[] { "class", "mail.appUser", 
         "appUser.department", "mailFolder.appUser" })
         .serialize(this.mail));
       sb.append("]}");
       setJsonString(sb.toString());
       return "success";
     }
     getRequest().setAttribute("mail", this.mail);
     getRequest().setAttribute("boxId", mailBox.getBoxId());
 
     getRequest().setAttribute("mailAttachs", this.mail.getMailAttachs());
     return "detail";
   }
 
   public String save()
   {
     if (this.mail.getMailStatus().shortValue() == IS_MAIL)
     {
       if (StringUtils.isEmpty(this.mail.getRecipientIDs())) {
         setJsonString("{failure:true,msg:'收件人不能为空!'}");
         return "success";
       }
 
       if (StringUtils.isEmpty(this.mail.getSubject())) {
         setJsonString("{failure:true,msg:'邮件主题不能为空!'}");
         return "success";
       }
 
       if (StringUtils.isEmpty(this.mail.getContent())) {
         setJsonString("{failure:true,msg:'邮件内容不能为空!'}");
         return "success";
       }
 
       if ((this.replyBoxId != null) && (!"".equals(this.replyBoxId))) {
         MailBox reply = (MailBox)this.mailBoxService.get(this.replyBoxId);
         reply.setReplyFlag(Short.valueOf(HAVE_READ));
         this.mailBoxService.save(reply);
       }
 
       MailFolder receiveFolder = (MailFolder)this.mailFolderService.get(Long.valueOf(FOLDER_ID_RECEIVE));
       MailFolder sendFolder = (MailFolder)this.mailFolderService.get(Long.valueOf(FOLDER_ID_SEND));
       String[] recipientIDs = this.mail.getRecipientIDs().split(",");
       String[] copyToIDs = this.mail.getCopyToIDs().split(",");
       String id;
       if (this.mail.getMailId() == null) {
         SaveMail();
 
         MailBox mailBox = new MailBox();
         mailBox.setMail(this.mail);
         mailBox.setMailFolder(sendFolder);
         mailBox.setAppUser(ContextUtil.getCurrentUser());
         mailBox.setSendTime(this.mail.getSendTime());
         mailBox.setDelFlag(Short.valueOf(NOT_DELETE));
         mailBox.setReadFlag(Short.valueOf(NOT_READ));
         mailBox.setNote("已发送的邮件");
         mailBox.setReplyFlag(Short.valueOf(NOT_REPLY));
         this.mailBoxService.save(mailBox);
       } else {
         Mail old = (Mail)this.mailService.get(this.mail.getMailId());
         try {
           BeanUtil.copyNotNullProperties(old, this.mail);
           Set mailAttachs = new HashSet();
           old.setSendTime(new Date());
           fileIds = this.mail.getFileIds().split(",");
           for (id : fileIds) {
             if (id.equals("")) continue;
             mailAttachs
               .add((FileAttach)this.fileAttachService.get(new Long(id)));
           }
 
           old.setMailAttachs(mailAttachs);
           setMail(old);
           this.mailService.save(old);
         } catch (Exception ex) {
           this.logger.error(ex.getMessage());
         }
         MailBox drafted = (MailBox)this.mailBoxService.get(this.boxId);
         drafted.setMailFolder(sendFolder);
         drafted.setNote("已发送的邮件");
         this.mailBoxService.save(drafted);
       }
 
       if ((this.sendMessage != null) && (this.sendMessage.equals("on"))) {
         StringBuffer msgContent = new StringBuffer("<font color=\"green\">");
         sdf = new SimpleDateFormat("yyyy-MM-dd");
         msgContent.append(this.mail.getSender())
           .append("</font>")
           .append("在<font color=\"red\">")
           .append(sdf.format(this.mail.getSendTime()))
           .append("</font>")
           .append("给您发了一封邮件，请注意查收。");
         this.shortMessageService.save(AppUser.SYSTEM_USER, this.mail.getRecipientIDs(), msgContent.toString(), ShortMessage.MSG_TYPE_SYS);
       }
 
       String[] fileIds = (id = recipientIDs).length; for (SimpleDateFormat sdf = 0; sdf < fileIds; ++sdf) { String id = id[sdf];
         if (!"".equals(id)) {
           MailBox mailBoxSend = new MailBox();
           mailBoxSend.setMail(this.mail);
           mailBoxSend.setMailFolder(receiveFolder);
           mailBoxSend.setAppUser((AppUser)this.appUserService.get(new Long(id)));
           mailBoxSend.setSendTime(this.mail.getSendTime());
           mailBoxSend.setDelFlag(Short.valueOf(NOT_DELETE));
           mailBoxSend.setReadFlag(Short.valueOf(NOT_READ));
           mailBoxSend.setNote("发送出去的邮件");
           mailBoxSend.setReplyFlag(Short.valueOf(NOT_REPLY));
           this.mailBoxService.save(mailBoxSend);
         }
  }
 
 
       fileIds = (id = copyToIDs).length; for (sdf = 0; sdf < fileIds; ++sdf) { String id = id[sdf];
         if (!"".equals(id)) {
           MailBox mailBoxCopy = new MailBox();
           mailBoxCopy.setMail(this.mail);
           mailBoxCopy.setMailFolder(receiveFolder);
           mailBoxCopy.setAppUser((AppUser)this.appUserService.get(new Long(id)));
           mailBoxCopy.setSendTime(this.mail.getSendTime());
           mailBoxCopy.setDelFlag(Short.valueOf(NOT_DELETE));
           mailBoxCopy.setReadFlag(Short.valueOf(NOT_READ));
           mailBoxCopy.setNote("抄送出去的邮件");
           mailBoxCopy.setReplyFlag(Short.valueOf(NOT_REPLY));
           this.mailBoxService.save(mailBoxCopy);
         } }
 
     }
     else
     {
       if (StringUtils.isEmpty(this.mail.getSubject())) {
         setJsonString("{failure:true,msg:'邮件主题不能为空!'}");
         return "success";
       }
       SaveMail();
 
       MailFolder draftFolder = (MailFolder)this.mailFolderService.get(Long.valueOf(FOLDER_ID_DRAFT));
       MailBox mailBox = new MailBox();
       mailBox.setMail(this.mail);
       mailBox.setMailFolder(draftFolder);
       mailBox.setAppUser(ContextUtil.getCurrentUser());
       mailBox.setSendTime(this.mail.getSendTime());
       mailBox.setDelFlag(Short.valueOf(NOT_DELETE));
       mailBox.setReadFlag(Short.valueOf(NOT_READ));
       mailBox.setNote("存草稿");
       mailBox.setReplyFlag(Short.valueOf(NOT_REPLY));
       this.mailBoxService.save(mailBox);
     }
 
     setJsonString("{success:true}");
     return "success";
   }
 
   public void SaveMail()
   {
     Set mailAttachs = new HashSet();
     setAppUser(ContextUtil.getCurrentUser());
     this.mail.setAppSender(this.appUser);
     this.mail.setSendTime(new Date());
     this.mail.setSender(this.appUser.getFullname());
     String[] fileIds = this.mail.getFileIds().split(",");
     for (String id : fileIds) {
       if (!id.equals("")) {
         mailAttachs.add((FileAttach)this.fileAttachService.get(new Long(id)));
       }
     }
     this.mail.setMailAttachs(mailAttachs);
     this.mailService.save(this.mail);
   }
 
   public String opt()
   {
     setMail((Mail)this.mailService.get(this.mailId));
     String opt = getRequest().getParameter("opt");
     Mail reply = new Mail();
     StringBuffer newSubject = new StringBuffer(
       "<br><br><br><br><br><br><br><hr>");
     newSubject.append("<br>----<strong>" + opt + "邮件</strong>----");
     newSubject.append("<br><strong>发件人</strong>:" + this.mail.getSender());
     newSubject.append("<br><strong>发送时间</strong>:" + this.mail.getSendTime());
     newSubject.append("<br><strong>收件人</strong>:" + 
       this.mail.getRecipientNames());
     String copyToNames = this.mail.getCopyToNames();
     if ((!"".equals(copyToNames)) && (copyToNames != null)) {
       newSubject.append("<br><strong>抄送人</strong>:" + copyToNames);
     }
     newSubject.append("<br><strong>主题</strong>:" + this.mail.getSubject());
     newSubject.append("<br><strong>内容</strong>:<br><br>" + 
       this.mail.getContent());
     reply.setContent(newSubject.toString());
     reply.setSubject(opt + ":" + this.mail.getSubject());
     reply.setImportantFlag(Short.valueOf(COMMON));
     if (opt.equals("回复")) {
       MailBox replyBox = (MailBox)this.mailBoxService.get(this.boxId);
       replyBox.setReplyFlag(HAVE_REPLY);
       this.mailBoxService.save(replyBox);
       reply.setRecipientIDs(this.mail.getAppSender().getUserId());
       reply.setRecipientNames(this.mail.getSender());
     }
     JSONSerializer serializer = new JSONSerializer();
 
     StringBuffer sb = new StringBuffer("{success:true,data:[");
     sb.append(serializer.exclude(new String[] { "class", "appUser" })
       .serialize(reply));
     sb.append("]}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String move()
   {
     MailFolder moveToFolder = (MailFolder)this.mailFolderService.get(new Long(this.folderId.longValue()));
     String[] ids = this.boxIds.split(",");
     StringBuffer msg = new StringBuffer("{");
     boolean moveSuccess = false;
     if ((ids[0] != null) && (!"".equals(ids[0]))) {
       Mail moveTest = ((MailBox)this.mailBoxService.get(new Long(ids[0]))).getMail();
       if (moveTest.getMailStatus().shortValue() == IS_DRAFT)
       {
         if ((this.folderId.longValue() == FOLDER_ID_DRAFT) || (this.folderId.longValue() == FOLDER_ID_DELETE))
         {
           moveSuccess = true;
         }
         else msg.append("msg:'草稿只能移至草稿箱或是垃圾箱(移至垃圾箱相当于删除,请注意!)'"); 
       }
       else if (moveTest.getMailStatus().shortValue() == IS_MAIL)
       {
         if (this.folderId.longValue() != FOLDER_ID_DRAFT)
         {
           moveSuccess = true;
         }
         else msg.append("msg:'正式邮件不能移至草稿箱'");
       }
     }
     if (moveSuccess)
     {
       for (String id : ids) {
         if (!"".equals(id)) {
           MailBox mailBoxMove = (MailBox)this.mailBoxService.get(new Long(id));
           mailBoxMove.setMailFolder(moveToFolder);
           if (this.folderId.longValue() != FOLDER_ID_DELETE)
             mailBoxMove.setDelFlag(Short.valueOf(NOT_DELETE));
           else {
             mailBoxMove.setDelFlag(Short.valueOf(HAVE_DELETE));
           }
           this.mailBoxService.save(mailBoxMove);
         }
       }
       msg.append("success:true}");
       setJsonString(msg.toString());
     }
     else {
       msg.append(",failure:true}");
       setJsonString(msg.toString());
     }
     return "success";
   }
 
   public String attach()
   {
     String fileIds = getRequest().getParameter("fileIds");
     String filenames = getRequest().getParameter("filenames");
     setMail((Mail)this.mailService.get(this.mailId));
     Set mailAttachs = this.mail.getMailAttachs();
     FileAttach remove = (FileAttach)this.fileAttachService.get(this.fileId);
     mailAttachs.remove(remove);
     this.mail.setMailAttachs(mailAttachs);
     this.mail.setFileIds(fileIds);
     this.mail.setFilenames(filenames);
     this.mailService.save(this.mail);
     this.fileAttachService.remove(this.fileId);
     return "success";
   }
 
   public String search()
   {
     PagingBean pb = getInitPagingBean();
     String searchContent = getRequest().getParameter("searchContent");
     List list = this.mailBoxService.findBySearch(searchContent, pb);
     Gson gson = new Gson();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(list.size()).append(",result:[");
     for (MailBox mailBoxTemp : list) {
       Mail mailTemp = mailBoxTemp.getMail();
       buff.append("{boxId:'")
         .append(mailBoxTemp.getBoxId())
         .append("',sendTime:'")
         .append(mailBoxTemp.getSendTime())
         .append("',delFlag:'")
         .append(mailBoxTemp.getDelFlag())
         .append("',readFlag:'")
         .append(mailBoxTemp.getReadFlag())
         .append("',replyFlag:'")
         .append(mailBoxTemp.getReplyFlag())
         .append("',mailId:'")
         .append(mailTemp.getMailId())
         .append("',importantFlag:'")
         .append(mailTemp.getImportantFlag())
         .append("',mailStatus:'").append(mailTemp.getMailStatus())
         .append("',fileIds:'").append(mailTemp.getFileIds())
         .append("',subject:'").append(gson.toJson(mailTemp.getSubject()))
         .append("',recipientNames:'")
         .append(mailTemp.getRecipientNames()).append("',sender:'")
         .append(mailTemp.getSender()).append("',content:'");
       String content = StringUtil.html2Text(mailTemp.getContent());
       content = gson.toJson(content);
       if (content.length() > 100) {
         content = content.substring(0, 100) + "...";
       }
       buff.append(content);
       buff.append("'},");
     }
     if (list.size() > 0) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String display()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
     filter.addFilter("Q_mailFolder.folderId_L_EQ", Long.toString(FOLDER_ID_RECEIVE));
     filter.addFilter("Q_delFlag_SN_EQ", Short.toString(NOT_DELETE));
     filter.addSorted("sendTime", "desc");
     filter.addSorted("readFlag", "desc");
     List list = this.mailBoxService.getAll(filter);
     getRequest().setAttribute("mailBoxList", list);
     return "display";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.communicate.MailAction
 * JD-Core Version:    0.5.4
 */