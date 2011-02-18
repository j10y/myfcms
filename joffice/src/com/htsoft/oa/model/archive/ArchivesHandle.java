package com.htsoft.oa.model.archive;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class ArchivesHandle extends BaseModel {

	@Expose
	protected Long handleId;

	@Expose
	protected String handleOpinion;

	@Expose
	protected Long userId;

	@Expose
	protected String userFullname;

	@Expose
	protected Date createtime;

	@Expose
	protected Date fillTime;

	@Expose
	protected Short isPass;
	protected Archives archives;

	public ArchivesHandle() {
	}

	public ArchivesHandle(Long in_handleId) {
		setHandleId(in_handleId);
	}

	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives in_archives) {
		this.archives = in_archives;
	}

	public Long getHandleId() {
		return this.handleId;
	}

	public void setHandleId(Long aValue) {
		this.handleId = aValue;
	}

	public Long getArchivesId() {
		return (getArchives() == null) ? null : getArchives().getArchivesId();
	}

	public void setArchivesId(Long aValue) {
		if (aValue == null) {
			this.archives = null;
		} else if (this.archives == null) {
			this.archives = new Archives(aValue);
			this.archives.setVersion(new Integer(0));
		} else {
			this.archives.setArchivesId(aValue);
		}
	}

	public String getHandleOpinion() {
		return this.handleOpinion;
	}

	public void setHandleOpinion(String aValue) {
		this.handleOpinion = aValue;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long aValue) {
		this.userId = aValue;
	}

	public String getUserFullname() {
		return this.userFullname;
	}

	public void setUserFullname(String aValue) {
		this.userFullname = aValue;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date aValue) {
		this.createtime = aValue;
	}

	public Date getFillTime() {
		return this.fillTime;
	}

	public void setFillTime(Date aValue) {
		this.fillTime = aValue;
	}

	public Short getIsPass() {
		return this.isPass;
	}

	public void setIsPass(Short aValue) {
		this.isPass = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof ArchivesHandle)) {
			return false;
		}
		ArchivesHandle rhs = (ArchivesHandle) object;
		return new EqualsBuilder().append(this.handleId, rhs.handleId).append(this.handleOpinion,
				rhs.handleOpinion).append(this.userId, rhs.userId).append(this.userFullname,
				rhs.userFullname).append(this.createtime, rhs.createtime).append(this.fillTime,
				rhs.fillTime).append(this.isPass, rhs.isPass).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.handleId).append(
				this.handleOpinion).append(this.userId).append(this.userFullname).append(
				this.createtime).append(this.fillTime).append(this.isPass).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("handleId", this.handleId).append("handleOpinion",
				this.handleOpinion).append("userId", this.userId).append("userFullname",
				this.userFullname).append("createtime", this.createtime).append("fillTime",
				this.fillTime).append("isPass", this.isPass).toString();
	}
}


 
 
 
 
 