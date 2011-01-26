package com.htsoft.oa.dao.admin;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.OfficeGoods;
import java.util.List;

public abstract interface OfficeGoodsDao extends BaseDao<OfficeGoods>
{
  public abstract List<OfficeGoods> findByWarm();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.OfficeGoodsDao
 * JD-Core Version:    0.5.4
 */