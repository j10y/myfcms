package com.htsoft.oa.service.communicate;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.PhoneBook;
import java.util.List;

public abstract interface PhoneBookService extends BaseService<PhoneBook>
{
  public abstract List<PhoneBook> sharedPhoneBooks(String paramString1, String paramString2, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.PhoneBookService
 * JD-Core Version:    0.5.4
 */