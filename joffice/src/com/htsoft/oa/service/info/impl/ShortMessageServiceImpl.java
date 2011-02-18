package com.htsoft.oa.service.info.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.Constants;
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.info.InMessageDao;
import com.htsoft.oa.dao.info.ShortMessageDao;
import com.htsoft.oa.dao.system.AppUserDao;
import com.htsoft.oa.model.info.InMessage;
import com.htsoft.oa.model.info.ShortMessage;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.info.ShortMessageService;

public class ShortMessageServiceImpl extends BaseServiceImpl<ShortMessage> implements
		ShortMessageService {
	private ShortMessageDao messageDao;

	@Resource
	private InMessageDao inMessageDao;

	@Resource
	private AppUserDao appUserDao;

	public ShortMessageServiceImpl(ShortMessageDao dao) {
		super(dao);
		this.messageDao = dao;
	}

	public List<ShortMessage> findAll(Long userId, PagingBean pb) {
		return this.messageDao.findAll(userId, pb);
	}

	public List<ShortMessage> findByUser(Long userId) {
		return this.messageDao.findByUser(userId);
	}

	public List searchShortMessage(Long userId, ShortMessage shortMessage, Date from, Date to,
			PagingBean pb) {
		return this.messageDao.searchShortMessage(userId, shortMessage, from, to, pb);
	}

	public ShortMessage save(Long senderId, String receiveIds, String content, Short msgType) {
		ShortMessage shortMessage = new ShortMessage();
		shortMessage.setContent(content);
		shortMessage.setMsgType(msgType);
		AppUser curUser = (AppUser) this.appUserDao.get(senderId);
		shortMessage.setSender(curUser.getFullname());
		shortMessage.setSenderId(curUser.getUserId());
		shortMessage.setSendTime(new Date());

		this.messageDao.save(shortMessage);

		String[] reIds = receiveIds.split("[,]");
		if (reIds != null) {
			for (String userId : reIds) {
				InMessage inMsg = new InMessage();
				inMsg.setDelFlag(Constants.FLAG_UNDELETED);
				inMsg.setReadFlag(InMessage.FLAG_UNREAD);
				inMsg.setShortMessage(shortMessage);
				AppUser receiveUser = (AppUser) this.appUserDao.get(new Long(userId));

				inMsg.setUserId(receiveUser.getUserId());
				inMsg.setUserFullname(receiveUser.getFullname());
				this.inMessageDao.save(inMsg);
			}
		}

		return shortMessage;
	}
}


 
 
 
 
 