package com.htsoft.oa.model.system;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class SystemLog extends BaseModel {
	protected Long logId;
	protected String username;
	protected Long userId;
	protected Date createtime;
	protected String exeOperation;

	public SystemLog() {
	}

	public SystemLog(Long in_logId) {
		setLogId(in_logId);
	}

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long aValue) {
		this.logId = aValue;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String aValue) {
		this.username = aValue;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long aValue) {
		this.userId = aValue;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date aValue) {
		this.createtime = aValue;
	}

	public String getExeOperation() {
		return this.exeOperation;
	}

	public void setExeOperation(String aValue) {
		this.exeOperation = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof SystemLog)) {
			return false;
		}
		SystemLog rhs = (SystemLog) object;
		return new EqualsBuilder().append(this.logId, rhs.logId)
				.append(this.username, rhs.username).append(this.userId, rhs.userId).append(
						this.createtime, rhs.createtime)
				.append(this.exeOperation, rhs.exeOperation).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.logId).append(this.username)
				.append(this.userId).append(this.createtime).append(this.exeOperation).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("logId", this.logId).append("username",
				this.username).append("userId", this.userId).append("createtime", this.createtime)
				.append("exeOperation", this.exeOperation).toString();
	}
}


 
 
 
 