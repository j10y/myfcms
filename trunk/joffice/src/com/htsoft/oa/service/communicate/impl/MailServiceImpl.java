package com.htsoft.oa.service.communicate.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.communicate.MailDao;
import com.htsoft.oa.model.communicate.Mail;
import com.htsoft.oa.service.communicate.MailService;

public class MailServiceImpl extends BaseServiceImpl<Mail> implements MailService {
	private MailDao dao;

	public MailServiceImpl(MailDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 