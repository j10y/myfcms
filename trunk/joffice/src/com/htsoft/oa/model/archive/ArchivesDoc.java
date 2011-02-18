package com.htsoft.oa.model.archive;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;

public class ArchivesDoc extends BaseModel {
	public static short STATUS_MODIFY = 0;
	public static short STATUS_MODIFY_END = 1;
	public static int ORI_VERSION = 1;

	@Expose
	protected Long docId;

	@Expose
	protected String creator;

	@Expose
	protected Long creatorId;

	@Expose
	protected Long menderId;

	@Expose
	protected String mender;

	@Expose
	protected String docName;

	@Expose
	protected Short docStatus;

	@Expose
	protected Integer curVersion;

	@Expose
	protected String docPath;

	@Expose
	protected Date updatetime;

	@Expose
	protected Date createtime;

	@Expose
	protected FileAttach fileAttach;
	protected Archives archives;
	protected Set docHistorys = new HashSet();

	public ArchivesDoc() {
	}

	public void initUsers(AppUser curUser) {
		setCreator(curUser.getFullname());
		setCreatorId(curUser.getUserId());

		setMender(curUser.getFullname());
		setMenderId(curUser.getUserId());
	}

	public ArchivesDoc(Long in_docId) {
		setDocId(in_docId);
	}

	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives in_archives) {
		this.archives = in_archives;
	}

	public FileAttach getFileAttach() {
		return this.fileAttach;
	}

	public void setFileAttach(FileAttach in_fileAttach) {
		this.fileAttach = in_fileAttach;
	}

	public Set getDocHistorys() {
		return this.docHistorys;
	}

	public void setDocHistorys(Set in_docHistorys) {
		this.docHistorys = in_docHistorys;
	}

	public Long getDocId() {
		return this.docId;
	}

	public void setDocId(Long aValue) {
		this.docId = aValue;
	}

	public Long getFileId() {
		return (getFileAttach() == null) ? null : getFileAttach().getFileId();
	}

	public void setFileId(Long aValue) {
		if (aValue == null) {
			this.fileAttach = null;
		} else if (this.fileAttach == null) {
			this.fileAttach = new FileAttach(aValue);
			this.fileAttach.setVersion(new Integer(0));
		} else {
			this.fileAttach.setFileId(aValue);
		}
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

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String aValue) {
		this.creator = aValue;
	}

	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long aValue) {
		this.creatorId = aValue;
	}

	public Long getMenderId() {
		return this.menderId;
	}

	public void setMenderId(Long aValue) {
		this.menderId = aValue;
	}

	public String getMender() {
		return this.mender;
	}

	public void setMender(String aValue) {
		this.mender = aValue;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String aValue) {
		this.docName = aValue;
	}

	public Short getDocStatus() {
		return this.docStatus;
	}

	public void setDocStatus(Short aValue) {
		this.docStatus = aValue;
	}

	public Integer getCurVersion() {
		return this.curVersion;
	}

	public void setCurVersion(Integer aValue) {
		this.curVersion = aValue;
	}

	public String getDocPath() {
		return this.docPath;
	}

	public void setDocPath(String aValue) {
		this.docPath = aValue;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date aValue) {
		this.updatetime = aValue;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date aValue) {
		this.createtime = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof ArchivesDoc)) {
			return false;
		}
		ArchivesDoc rhs = (ArchivesDoc) object;
		return new EqualsBuilder().append(this.docId, rhs.docId).append(this.creator, rhs.creator)
				.append(this.creatorId, rhs.creatorId).append(this.menderId, rhs.menderId).append(
						this.mender, rhs.mender).append(this.docName, rhs.docName).append(
						this.docStatus, rhs.docStatus).append(this.curVersion, rhs.curVersion)
				.append(this.docPath, rhs.docPath).append(this.updatetime, rhs.updatetime).append(
						this.createtime, rhs.createtime).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.docId).append(this.creator)
				.append(this.creatorId).append(this.menderId).append(this.mender).append(
						this.docName).append(this.docStatus).append(this.curVersion).append(
						this.docPath).append(this.updatetime).append(this.createtime).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("docId", this.docId)
				.append("creator", this.creator).append("creatorId", this.creatorId).append(
						"menderId", this.menderId).append("mender", this.mender).append("docName",
						this.docName).append("docStatus", this.docStatus).append("curVersion",
						this.curVersion).append("docPath", this.docPath).append("updatetime",
						this.updatetime).append("createtime", this.createtime).toString();
	}
}


 
 
 
 
 