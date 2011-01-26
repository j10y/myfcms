package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Diary;
import java.util.List;

public abstract interface DiaryService extends BaseService<Diary>
{
  public abstract List<Diary> getAllBySn(PagingBean paramPagingBean);

  public abstract List<Diary> getSubDiary(String paramString, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.DiaryService
 * JD-Core Version:    0.5.4
 */