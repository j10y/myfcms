package com.htsoft.oa.dao.info;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.ShortMessage;
import java.util.Date;
import java.util.List;

public abstract interface ShortMessageDao extends BaseDao<ShortMessage>
{
  public abstract List<ShortMessage> findAll(Long paramLong, PagingBean paramPagingBean);

  public abstract List<ShortMessage> findByUser(Long paramLong);

  public abstract List searchShortMessage(Long paramLong, ShortMessage paramShortMessage, Date paramDate1, Date paramDate2, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.ShortMessageDao
 * JD-Core Version:    0.5.4
 */