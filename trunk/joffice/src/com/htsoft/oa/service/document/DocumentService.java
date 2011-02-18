package com.htsoft.oa.service.document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.document.Document;
import com.htsoft.oa.model.system.AppUser;

public abstract interface DocumentService extends BaseService<Document> {
	public abstract List<Document> findByIsShared(Document paramDocument, Date paramDate1,
			Date paramDate2, Long paramLong1, ArrayList<Long> paramArrayList, Long paramLong2,
			PagingBean paramPagingBean);

	public abstract List<Document> findByPublic(String paramString, Document paramDocument,
			Date paramDate1, Date paramDate2, AppUser paramAppUser, PagingBean paramPagingBean);

	public abstract List<Document> findByPersonal(Long paramLong, Document paramDocument,
			Date paramDate1, Date paramDate2, String paramString, PagingBean paramPagingBean);

	public abstract List<Document> findByFolder(String paramString);

	public abstract List<Document> searchDocument(AppUser paramAppUser, String paramString,
			PagingBean paramPagingBean);
}


 
 
 
 
 