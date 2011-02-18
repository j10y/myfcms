package com.htsoft.oa.model.communicate;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.AppUser;

public class MailBox extends BaseModel {
	protected Long boxId;
	protected Date sendTime;
	protected Short delFlag;
	protected Short readFlag;
	protected String note;
	protected Mail mail;
	protected AppUser appUser;
	protected MailFolder mailFolder;
	protected Short replyFlag;

	public MailBox() {
	}

	public MailBox(Long in_boxId) {
		setBoxId(in_boxId);
	}

	public Mail getMail() {
		return this.mail;
	}

	public void setMail(Mail in_mail) {
		this.mail = in_mail;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser in_appUser) {
		this.appUser = in_appUser;
	}

	public MailFolder getMailFolder() {
		return this.mailFolder;
	}

	public void setMailFolder(MailFolder in_mailFolder) {
		this.mailFolder = in_mailFolder;
	}

	public Long getBoxId() {
		return this.boxId;
	}

	public void setBoxId(Long aValue) {
		this.boxId = aValue;
	}

	public Long getMailId() {
		return (getMail() == null) ? null : getMail().getMailId();
	}

	public void setMailId(Long aValue) {
		if (aValue == null) {
			this.mail = null;
		} else if (this.mail == null) {
			this.mail = new Mail(aValue);
			this.mail.setVersion(new Integer(0));
		} else {
			this.mail.setMailId(aValue);
		}
	}

	public Long getFolderId() {
		return (getMailFolder() == null) ? null : getMailFolder().getFolderId();
	}

	public void setFolderId(Long aValue) {
		if (aValue == null) {
			this.mailFolder = null;
		} else if (this.mailFolder == null) {
			this.mailFolder = new MailFolder(aValue);
			this.mailFolder.setVersion(new Integer(0));
		} else {
			this.mailFolder.setFolderId(aValue);
		}
	}

	public Long getUserId() {
		return (getAppUser() == null) ? null : getAppUser().getUserId();
	}

	public void setUserId(Long aValue) {
		if (aValue == null) {
			this.appUser = null;
		} else if (this.appUser == null) {
			this.appUser = new AppUser(aValue);
			this.appUser.setVersion(new Integer(0));
		} else {
			this.appUser.setUserId(aValue);
		}
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date aValue) {
		this.sendTime = aValue;
	}

	public Short getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Short aValue) {
		this.delFlag = aValue;
	}

	public Short getReadFlag() {
		return this.readFlag;
	}

	public void setReadFlag(Short aValue) {
		this.readFlag = aValue;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String aValue) {
		this.note = aValue;
	}

	public Short getReplyFlag() {
		return this.replyFlag;
	}

	public void setReplyFlag(Short replyFlag) {
		this.replyFlag = replyFlag;
	}

	public boolean equals(Object object) {
		if (!(object instanceof MailBox)) {
			return false;
		}
		MailBox rhs = (MailBox) object;
		return new EqualsBuilder().append(this.boxId, rhs.boxId)
				.append(this.sendTime, rhs.sendTime).append(this.delFlag, rhs.delFlag).append(
						this.readFlag, rhs.readFlag).append(this.note, rhs.note).append(
						this.replyFlag, this.replyFlag).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.boxId).append(this.sendTime)
				.append(this.delFlag).append(this.readFlag).append(this.note)
				.append(this.replyFlag).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("boxId", this.boxId).append("sendTime",
				this.sendTime).append("delFlag", this.delFlag).append("readFlag", this.readFlag)
				.append("note", this.note).append("replyFlag", this.replyFlag).toString();
	}

	public String getFirstKeyColumnName() {
		return "boxId";
	}

	public Long getId() {
		return this.boxId;
	}
}


 
 
 
 
 