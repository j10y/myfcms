 package com.htsoft.oa.action.admin;
 
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.admin.OfficeGoods;
 import com.htsoft.oa.service.admin.OfficeGoodsService;
 import flexjson.JSONSerializer;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class OfficeGoodsAction extends BaseAction
 {
 
   @Resource
   private OfficeGoodsService officeGoodsService;
   private OfficeGoods officeGoods;
   private Long goodsId;
 
   public Long getGoodsId()
   {
     return this.goodsId;
   }
 
   public void setGoodsId(Long goodsId) {
     this.goodsId = goodsId;
   }
 
   public OfficeGoods getOfficeGoods() {
     return this.officeGoods;
   }
 
   public void setOfficeGoods(OfficeGoods officeGoods) {
     this.officeGoods = officeGoods;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.officeGoodsService.getAll(filter);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[0]);
     buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.officeGoodsService.remove(new Long(id));
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     OfficeGoods officeGoods = (OfficeGoods)this.officeGoodsService.get(this.goodsId);
     StringBuffer sb = new StringBuffer("{success:true,data:");
     JSONSerializer serializer = new JSONSerializer();
     sb.append(serializer.exclude(new String[] { "class" }).serialize(officeGoods));
     sb.append("}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String save()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-SSSS");
     if (this.officeGoods.getGoodsId() == null) {
       this.officeGoods.setGoodsNo(sdf.format(new Date()));
       this.officeGoods.setStockCounts(Integer.valueOf(0));
     }
     this.officeGoodsService.save(this.officeGoods);
     setJsonString("{success:true}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.admin.OfficeGoodsAction
 * JD-Core Version:    0.5.4
 */