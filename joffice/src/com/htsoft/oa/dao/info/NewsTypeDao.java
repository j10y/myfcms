package com.htsoft.oa.dao.info;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.NewsType;
import java.util.List;

public abstract interface NewsTypeDao extends BaseDao<NewsType>
{
  public abstract Short getTop();

  public abstract NewsType findBySn(Short paramShort);

  public abstract List<NewsType> getAllBySn();

  public abstract List<NewsType> getAllBySn(PagingBean paramPagingBean);

  public abstract List<NewsType> findBySearch(NewsType paramNewsType, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.NewsTypeDao
 * JD-Core Version:    0.5.4
 */