 package com.htsoft.oa.action.info;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.info.NewsType;
 import com.htsoft.oa.service.info.NewsTypeService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class NewsTypeAction extends BaseAction
 {
   private NewsType newsType;
 
   @Resource
   private NewsTypeService newsTypeService;
 
   public void setNewsType(NewsType newsType)
   {
     this.newsType = newsType;
   }
 
   public NewsType getNewsType() {
     return this.newsType;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addSorted("sn", "asc");
     List list = this.newsTypeService.getAll(filter);
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':'" + 
       filter.getPagingBean().getTotalItems() + "',result:");
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
     setJsonString(buff.toString());
     return "success";
   }
 
   public String tree()
   {
     List list = this.newsTypeService.getAllBySn();
     StringBuffer sb = new StringBuffer("[");
     String opt = getRequest().getParameter("opt");
     if ((opt == null) || (!opt.equals("treeSelector"))) {
       sb.append("{id:'0',text:'全部新闻',leaf:true,iconCls:'menu-news'},");
     }
     for (NewsType newsType : list) {
       sb.append("{id:'").append(newsType.getTypeId()).append("',text:'").append(newsType.getTypeName()).append("',leaf:true,iconCls:'menu-news'},");
     }
     if (!list.isEmpty()) {
       sb.deleteCharAt(sb.length() - 1);
     }
     sb.append("]");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String add()
   {
     Short top = this.newsTypeService.getTop();
     if (top == null) top = Short.valueOf(0);
 
     if (this.newsType.getTypeId() == null) {
       this.newsType.setSn(Short.valueOf((short)(top.shortValue() + 1)));
     }
     this.newsTypeService.save(this.newsType);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String remove()
   {
     Long typeId = Long.valueOf(getRequest().getParameter("typeId"));
     setNewsType((NewsType)this.newsTypeService.get(typeId));
     if (this.newsType != null) {
       Short sn = this.newsType.getSn();
       this.newsTypeService.remove(typeId);
 
       Short top = this.newsTypeService.getTop();
       if ((top != null) && (top.shortValue() > sn.shortValue())) {
         for (int i = 0; i < top.shortValue() - sn.shortValue(); ++i) {
           setNewsType(this.newsTypeService.findBySn(Short.valueOf((short)(sn.shortValue() + i + 1))));
           this.newsType.setSn(Short.valueOf((short)(sn.shortValue() + i)));
           this.newsTypeService.save(this.newsType);
         }
       }
     }
     return "success";
   }
 
   public String detail()
   {
     Long typeId = Long.valueOf(getRequest().getParameter("typeId"));
     setNewsType((NewsType)this.newsTypeService.get(typeId));
     Gson gson = new Gson();
     StringBuffer sb = new StringBuffer("{success:true,data:[");
     sb.append(gson.toJson(this.newsType));
     sb.append("]}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String search()
   {
     PagingBean pb = getInitPagingBean();
     List list = this.newsTypeService.findBySearch(this.newsType, pb);
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':'" + pb.getTotalItems() + "',result:");
     Gson gson = new Gson();
     buff.append(gson.toJson(list, type));
     buff.append("}");
     setJsonString(buff.toString());
     return "success";
   }
 
   public String sort()
   {
     Integer opt = Integer.valueOf(Integer.parseInt(getRequest().getParameter("opt")));
     Long typeId = Long.valueOf(getRequest().getParameter("typeId"));
     setNewsType((NewsType)this.newsTypeService.get(typeId));
     Short top = this.newsTypeService.getTop();
     Short sn = this.newsType.getSn();
     NewsType change = new NewsType();
     if ((opt.intValue() == 1) && 
       (sn.shortValue() != 1)) {
       for (int i = 0; i < sn.shortValue() - 1; ++i) {
         change = this.newsTypeService.findBySn(Short.valueOf((short)(sn.shortValue() - i - 1)));
         change.setSn(Short.valueOf((short)(sn.shortValue() - i)));
         this.newsTypeService.save(change);
       }
       this.newsType.setSn(Short.valueOf(1));
       this.newsTypeService.save(this.newsType);
     }
 
     if ((opt.intValue() == 2) && 
       (sn.shortValue() != 1)) {
       change = this.newsTypeService.findBySn(Short.valueOf((short)(sn.shortValue() - 1)));
       change.setSn(sn);
       this.newsTypeService.save(change);
       this.newsType.setSn(Short.valueOf((short)(sn.shortValue() - 1)));
       this.newsTypeService.save(this.newsType);
     }
 
     if ((opt.intValue() == 3) && 
       (sn.shortValue() < top.shortValue())) {
       change = this.newsTypeService.findBySn(Short.valueOf((short)(sn.shortValue() + 1)));
       change.setSn(sn);
       this.newsTypeService.save(change);
       this.newsType.setSn(Short.valueOf((short)(sn.shortValue() + 1)));
       this.newsTypeService.save(this.newsType);
     }
 
     if ((opt.intValue() == 4) && 
       (sn.shortValue() < top.shortValue())) {
       for (int i = 0; i < top.shortValue() - sn.shortValue(); ++i) {
         change = this.newsTypeService.findBySn(Short.valueOf((short)(sn.shortValue() + i + 1)));
         change.setSn(Short.valueOf((short)(sn.shortValue() + i)));
         this.newsTypeService.save(change);
       }
       this.newsType.setSn(top);
       this.newsTypeService.save(this.newsType);
     }
 
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.info.NewsTypeAction
 * JD-Core Version:    0.5.4
 */