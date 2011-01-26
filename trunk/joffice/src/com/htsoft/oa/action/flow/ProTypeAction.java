 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.oa.model.flow.ProType;
 import com.htsoft.oa.service.flow.ProTypeService;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class ProTypeAction extends BaseAction
 {
 
   @Resource
   private ProTypeService proTypeService;
   private ProType proType;
   private Long typeId;
 
   public Long getTypeId()
   {
     return this.typeId;
   }
 
   public void setTypeId(Long typeId) {
     this.typeId = typeId;
   }
 
   public ProType getProType() {
     return this.proType;
   }
 
   public void setProType(ProType proType) {
     this.proType = proType;
   }
 
   public String list()
   {
     List processTypeList = this.proTypeService.getAll();
     StringBuffer sb = new StringBuffer("[");
     for (ProType proType : processTypeList) {
       sb.append("{id:'").append(proType.getTypeId()).append("',text:'").append(proType.getTypeName()).append("',leaf:true},");
     }
     if (!processTypeList.isEmpty()) {
       sb.deleteCharAt(sb.length() - 1);
     }
     sb.append("]");
     this.jsonString = sb.toString();
 
     return "success";
   }
 
   public String root() {
     List processTypeList = this.proTypeService.getAll();
     StringBuffer sb = new StringBuffer("[{id:'0',text:'流程分类',leaf:false,expanded:true,children:[");
     for (ProType proType : processTypeList) {
       sb.append("{id:'").append(proType.getTypeId()).append("',text:'").append(proType.getTypeName()).append("',leaf:true},");
     }
     if (!processTypeList.isEmpty()) {
       sb.deleteCharAt(sb.length() - 1);
     }
     sb.append("]}]");
     this.jsonString = sb.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.proTypeService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String remove()
   {
     this.proTypeService.remove(this.typeId);
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     ProType proType = (ProType)this.proTypeService.get(this.typeId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(proType));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.proTypeService.save(this.proType);
     setJsonString("{success:true}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.flow.ProTypeAction
 * JD-Core Version:    0.5.4
 */