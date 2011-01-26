package com.htsoft.oa.dao.document;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.document.Document;
import com.htsoft.oa.model.system.AppUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract interface DocumentDao extends BaseDao<Document>
{
  public abstract List<Document> findByIsShared(Document paramDocument, Date paramDate1, Date paramDate2, Long paramLong1, ArrayList<Long> paramArrayList, Long paramLong2, PagingBean paramPagingBean);

  public abstract List<Document> findByPublic(String paramString, Document paramDocument, Date paramDate1, Date paramDate2, AppUser paramAppUser, PagingBean paramPagingBean);

  public abstract List<Document> findByPersonal(Long paramLong, Document paramDocument, Date paramDate1, Date paramDate2, String paramString, PagingBean paramPagingBean);

  public abstract List<Document> findByFolder(String paramString);

  public abstract List<Document> searchDocument(AppUser paramAppUser, String paramString, boolean paramBoolean, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.document.DocumentDao
 * JD-Core Version:    0.5.4
 */