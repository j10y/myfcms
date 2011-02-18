package com.htsoft.oa.dao.communicate.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.communicate.PhoneBookDao;
import com.htsoft.oa.model.communicate.PhoneBook;

public class PhoneBookDaoImpl extends BaseDaoImpl<PhoneBook> implements PhoneBookDao {
	public PhoneBookDaoImpl() {
		super(PhoneBook.class);
	}

	public List<PhoneBook> sharedPhoneBooks(String fullname, String ownerName, PagingBean pb) {
		StringBuffer hql = new StringBuffer(
				"select pb from PhoneBook pb,PhoneGroup pg where pb.phoneGroup=pg and (pg.isShared=1 or pb.isShared=1)");
		List list = new ArrayList();
		if (StringUtils.isNotEmpty(fullname)) {
			hql.append(" and pb.fullname like ?");
			list.add("%" + fullname + "%");
		}
		if (StringUtils.isNotEmpty(ownerName)) {
			hql.append(" and pb.appUser.fullname like ?");
			list.add("%" + ownerName + "%");
		}
		return findByHql(hql.toString(), list.toArray(), pb);
	}
}


 
 
 
 
 