 package com.htsoft.oa.service.admin.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.oa.dao.admin.OfficeGoodsDao;
 import com.htsoft.oa.dao.system.AppUserDao;
 import com.htsoft.oa.model.admin.OfficeGoods;
 import com.htsoft.oa.model.info.ShortMessage;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.admin.OfficeGoodsService;
 import com.htsoft.oa.service.info.ShortMessageService;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 
 public class OfficeGoodsServiceImpl extends BaseServiceImpl<OfficeGoods>
   implements OfficeGoodsService
 {
   private static Log logger = LogFactory.getLog(OfficeGoodsServiceImpl.class);
   private OfficeGoodsDao dao;
 
   @Resource
   private AppUserDao appUserDao;
 
   @Resource
   private ShortMessageService shortMessageService;
 
   public OfficeGoodsServiceImpl(OfficeGoodsDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public void sendWarmMessage()
   {
     List list = this.dao.findByWarm();
     if (list.size() > 0) {
       StringBuffer sb = new StringBuffer("办公用品：");
       for (OfficeGoods goods : list) {
         if (goods.getIsWarning().shortValue() == 1)
           sb.append(goods.getGoodsName() + "已经低于警报库存量" + goods.getWarnCounts() + "了.");
         else {
           sb.append(goods.getGoodsName() + "已经没有库存了.");
         }
       }
       sb.append("请尽快购买相应的用品");
       Map map = AppUtil.getSysConfig();
       String username = (String)map.get("goodsStockUser");
       if (StringUtils.isNotEmpty(username)) {
         AppUser user = this.appUserDao.findByUserName(username);
         if (user != null) {
           this.shortMessageService.save(AppUser.SYSTEM_USER, user.getUserId().toString(), sb.toString(), ShortMessage.MSG_TYPE_SYS);
           logger.info("messages had sent to the manager!" + user.getUsername());
         } else {
           logger.info("can not find the user in the system.");
         }
       } else {
         logger.info("can not find the name in the map.");
       }
       logger.info(sb.toString());
     } else {
       logger.info("没有产品要补仓.");
     }
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.OfficeGoodsServiceImpl
 * JD-Core Version:    0.5.4
 */