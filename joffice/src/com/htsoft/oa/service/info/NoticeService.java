package com.htsoft.oa.service.info;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.Notice;
import java.util.Date;
import java.util.List;

public abstract interface NoticeService extends BaseService<Notice>
{
  public abstract List<Notice> findByNoticeId(Long paramLong, PagingBean paramPagingBean);

  public abstract List<Notice> findBySearch(Notice paramNotice, Date paramDate1, Date paramDate2, PagingBean paramPagingBean);

  public abstract List<Notice> findBySearch(String paramString, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.NoticeService
 * JD-Core Version:    0.5.4
 */