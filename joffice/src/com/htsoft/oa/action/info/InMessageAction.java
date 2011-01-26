 package com.htsoft.oa.action.info;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.info.InMessage;
 import com.htsoft.oa.model.info.ShortMessage;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.info.InMessageService;
 import com.htsoft.oa.service.info.ShortMessageService;
 import java.lang.reflect.Type;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class InMessageAction extends BaseAction
 {
   static short HAVE_DELETE = 1;
   private InMessage inMessage;
   private ShortMessage shortMessage;
   private Date from;
   private Date to;
 
   @Resource
   private InMessageService inMessageService;
 
   @Resource
   private ShortMessageService shortMessageService;
 
   public InMessage getInMessage()
   {
     return this.inMessage;
   }
 
   public void setInMessage(InMessage inMessage) {
     this.inMessage = inMessage;
   }
   public ShortMessage getShortMessage() {
     return this.shortMessage;
   }
 
   public void setShortMessage(ShortMessage shortMessage) {
     this.shortMessage = shortMessage;
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
 
   public String list()
   {
     PagingBean pb = getInitPagingBean();
     AppUser appUser = ContextUtil.getCurrentUser();
 
     List list = this.inMessageService.searchInMessage(appUser.getUserId(), this.inMessage, this.shortMessage, this.from, this.to, pb);
 
     List inList = new ArrayList();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':" + pb.getTotalItems() + ",result:");
     for (int i = 0; i < list.size(); ++i) {
       InMessage inMessage = (InMessage)((Object[])list.get(i))[0];
       inList.add(inMessage);
     }
     Gson gson = new Gson();
     Type type = new TypeToken() {  }
     .getType();
     buff.append(gson.toJson(inList, type));
     buff.append("}");
     setJsonString(buff.toString());
     return "success";
   }
 
   public String know()
   {
     String strReceiveId = getRequest().getParameter("receiveId");
     Long receiveId = null;
     if (StringUtils.isNotEmpty(strReceiveId)) {
       receiveId = Long.valueOf(Long.parseLong(strReceiveId));
     }
     InMessage in = (InMessage)this.inMessageService.get(receiveId);
     in.setReadFlag(Short.valueOf(1));
     this.inMessageService.save(in);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String multiRemove()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.inMessage = ((InMessage)this.inMessageService.get(Long.valueOf(Long.parseLong(id))));
         this.inMessage.setDelFlag(Short.valueOf(HAVE_DELETE));
         this.inMessageService.save(this.inMessage);
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String reply()
   {
     String strReplyId = getRequest().getParameter("receiveId");
     if (StringUtils.isNotEmpty(strReplyId)) {
       Long replyId = Long.valueOf(Long.parseLong(strReplyId));
       this.inMessage = ((InMessage)this.inMessageService.get(replyId));
       StringBuffer buff = new StringBuffer("{success:true,data:[");
       buff.append("{'messageId':" + this.inMessage.getShortMessage().getMessageId() + ",'senderId':'" + this.inMessage.getShortMessage().getSenderId() + "','sender':'" + this.inMessage.getShortMessage().getSender() + "'}").append("]}");
       setJsonString(buff.toString());
     } else {
       setJsonString("{success:false}");
     }
     return "success";
   }
 
   public String read()
   {
     Long userId = ContextUtil.getCurrentUser().getUserId();
     boolean flag = false;
     if (userId != null) {
       this.inMessage = this.inMessageService.findByRead(userId);
       if (this.inMessage == null) {
         flag = true;
         this.inMessage = this.inMessageService.findLatest(userId);
       }
       if (this.inMessage != null) {
         this.inMessage.setReadFlag(InMessage.FLAG_READ);
         this.inMessageService.save(this.inMessage);
         this.shortMessage = this.inMessage.getShortMessage();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String date = sdf.format(this.shortMessage.getSendTime());
         StringBuffer buff = new StringBuffer("{success:true,message:");
         buff.append("{'receiveId':" + this.inMessage.getReceiveId() + ",'messageId':" + this.shortMessage.getMessageId() + ",'senderId':" + this.shortMessage.getSenderId() + ",'sender':'" + this.shortMessage.getSender() + "','content':'" + this.shortMessage.getContent().replace("\n", " ") + "','sendTime':'" + date + "','msgType':" + this.shortMessage.getMsgType());
         if (!flag) {
           InMessage in = this.inMessageService.findByRead(userId);
           if (in != null)
             buff.append(",haveNext:true");
           else
             buff.append(",haveNext:false");
         }
         else {
           buff.append(",haveNext:false");
         }
         buff.append("}}");
         setJsonString(buff.toString());
       } else {
         setJsonString("{success:false}");
       }
     } else {
       setJsonString("{success:true}");
     }
     return "success";
   }
 
   public String count() {
     Integer in = this.inMessageService.findByReadFlag(ContextUtil.getCurrentUser().getUserId());
     setJsonString("{success:true,count:'" + in + "'}");
     return "success";
   }
 
   public String detail()
   {
     String strReceiveId = getRequest().getParameter("receiveId");
     if (StringUtils.isNotEmpty(strReceiveId)) {
       Long receiveId = new Long(strReceiveId);
       this.inMessage = ((InMessage)this.inMessageService.get(receiveId));
       this.inMessage.setReadFlag(Short.valueOf(1));
       this.inMessageService.save(this.inMessage);
     }
     return "detail";
   }
 
   public String display()
   {
     PagingBean pb = new PagingBean(0, 8);
     AppUser appUser = ContextUtil.getCurrentUser();
     List list = this.shortMessageService.searchShortMessage(appUser.getUserId(), null, null, null, pb);
     List inList = new ArrayList();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':" + pb.getTotalItems() + ",result:");
     for (int i = 0; i < list.size(); ++i) {
       InMessage inMessage = (InMessage)((Object[])list.get(i))[0];
       inList.add(inMessage);
     }
 
     getRequest().setAttribute("messageList", inList);
     return "display";
   }
 
   public String multiRead() {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.inMessage = ((InMessage)this.inMessageService.get(Long.valueOf(Long.parseLong(id))));
         this.inMessage.setReadFlag(InMessage.FLAG_READ);
         this.inMessageService.save(this.inMessage);
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.info.InMessageAction
 * JD-Core Version:    0.5.4
 */