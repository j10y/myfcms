package com.htsoft.oa.dao.info;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.News;
import java.util.List;

public abstract interface NewsDao extends BaseDao<News>
{
  public abstract List<News> findByTypeId(Long paramLong, PagingBean paramPagingBean);

  public abstract List<News> findBySearch(String paramString, PagingBean paramPagingBean);

  public abstract List<News> findImageNews(PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.NewsDao
 * JD-Core Version:    0.5.4
 */