package com.htsoft.oa.model.document;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Document extends BaseModel {
	public static final Short SHARED = 1;
	public static final Short NOT_SHARED = 0;

	public static final Short HAVE_ATTACH = 1;
	public static final Short NOT_HAVE_ATTACH = 0;

	@Expose
	protected Long docId;

	@Expose
	protected String docName;

	@Expose
	protected String content;

	@Expose
	protected Date createtime;

	@Expose
	protected Date updatetime;

	@Expose
	protected Short haveAttach;

	@Expose
	protected String sharedUserIds;

	@Expose
	protected String sharedUserNames;

	@Expose
	protected String sharedDepIds;

	@Expose
	protected String sharedDepNames;

	@Expose
	protected String sharedRoleIds;

	@Expose
	protected String sharedRoleNames;

	@Expose
	protected Short isShared;

	@Expose
	protected String fullname;

	@Expose
	protected DocFolder docFolder;
	protected AppUser appUser;

	@Expose
	protected Set<FileAttach> attachFiles = new HashSet();

	public Document() {
	}

	public Document(Long in_docId) {
		setDocId(in_docId);
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public DocFolder getDocFolder() {
		return this.docFolder;
	}

	public void setDocFolder(DocFolder in_docFolder) {
		this.docFolder = in_docFolder;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser in_appUser) {
		this.appUser = in_appUser;
	}

	public Long getDocId() {
		return this.docId;
	}

	public void setDocId(Long aValue) {
		this.docId = aValue;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String aValue) {
		this.docName = aValue;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String aValue) {
		this.content = aValue;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date aValue) {
		this.createtime = aValue;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date aValue) {
		this.updatetime = aValue;
	}

	public Long getFolderId() {
		return (getDocFolder() == null) ? null : getDocFolder().getFolderId();
	}

	public void setFolderId(Long aValue) {
		if (aValue == null) {
			this.docFolder = null;
		} else if (this.docFolder == null) {
			this.docFolder = new DocFolder(aValue);
			this.docFolder.setVersion(new Integer(0));
		} else {
			this.docFolder.setFolderId(aValue);
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

	public Short getHaveAttach() {
		return this.haveAttach;
	}

	public void setHaveAttach(Short aValue) {
		this.haveAttach = aValue;
	}

	public String getSharedUserIds() {
		return this.sharedUserIds;
	}

	public void setSharedUserIds(String aValue) {
		this.sharedUserIds = aValue;
	}

	public String getSharedDepIds() {
		return this.sharedDepIds;
	}

	public void setSharedDepIds(String aValue) {
		this.sharedDepIds = aValue;
	}

	public String getSharedRoleIds() {
		return this.sharedRoleIds;
	}

	public void setSharedRoleIds(String aValue) {
		this.sharedRoleIds = aValue;
	}

	public Short getIsShared() {
		return this.isShared;
	}

	public void setIsShared(Short aValue) {
		this.isShared = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Document)) {
			return false;
		}
		Document rhs = (Document) object;
		return new EqualsBuilder().append(this.docId, rhs.docId).append(this.docName, rhs.docName)
				.append(this.fullname, rhs.fullname).append(this.content, rhs.content).append(
						this.createtime, rhs.createtime).append(this.updatetime, rhs.updatetime)
				.append(this.haveAttach, rhs.haveAttach).append(this.sharedUserIds,
						rhs.sharedUserIds).append(this.sharedDepIds, rhs.sharedDepIds).append(
						this.sharedRoleIds, rhs.sharedRoleIds).append(this.isShared, rhs.isShared)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.docId).append(this.docName)
				.append(this.content).append(this.createtime).append(this.updatetime).append(
						this.fullname).append(this.haveAttach).append(this.sharedUserIds).append(
						this.sharedDepIds).append(this.sharedRoleIds).append(this.isShared)
				.toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("docId", this.docId)
				.append("docName", this.docName).append("content", this.content).append("fullname",
						this.fullname).append("createtime", this.createtime).append("updatetime",
						this.updatetime).append("haveAttach", this.haveAttach).append(
						"sharedUserIds", this.sharedUserIds).append("sharedDepIds",
						this.sharedDepIds).append("sharedRoleIds", this.sharedRoleIds).append(
						"isShared", this.isShared).toString();
	}

	public String getFirstKeyColumnName() {
		return "docId";
	}

	public Long getId() {
		return this.docId;
	}

	public Set<FileAttach> getAttachFiles() {
		return this.attachFiles;
	}

	public void setAttachFiles(Set<FileAttach> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getSharedUserNames() {
		return this.sharedUserNames;
	}

	public void setSharedUserNames(String sharedUserNames) {
		this.sharedUserNames = sharedUserNames;
	}

	public String getSharedDepNames() {
		return this.sharedDepNames;
	}

	public void setSharedDepNames(String sharedDepNames) {
		this.sharedDepNames = sharedDepNames;
	}

	public String getSharedRoleNames() {
		return this.sharedRoleNames;
	}

	public void setSharedRoleNames(String sharedRoleNames) {
		this.sharedRoleNames = sharedRoleNames;
	}
}

/*
 * Location:
 * E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name: com.htsoft.oa.model.document.Document JD-Core Version: 0.5.4
 */