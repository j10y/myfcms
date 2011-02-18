package com.htsoft.oa.model.communicate;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.AppUser;

public class MailFolder extends BaseModel {
	protected Long folderId;
	protected String folderName;
	protected Long parentId;
	protected Integer depLevel;
	protected String path;
	protected Short isPublic;
	protected Short folderType;
	protected AppUser appUser;

	public MailFolder() {
	}

	public MailFolder(Long in_folderId) {
		setFolderId(in_folderId);
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser in_appUser) {
		this.appUser = in_appUser;
	}

	public Long getFolderId() {
		return this.folderId;
	}

	public void setFolderId(Long aValue) {
		this.folderId = aValue;
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

	public String getFolderName() {
		return this.folderName;
	}

	public void setFolderName(String aValue) {
		this.folderName = aValue;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long aValue) {
		this.parentId = aValue;
	}

	public Integer getDepLevel() {
		return this.depLevel;
	}

	public void setDepLevel(Integer aValue) {
		this.depLevel = aValue;
	}

	public Short getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Short aValue) {
		this.isPublic = aValue;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Short getFolderType() {
		return this.folderType;
	}

	public void setFolderType(Short aValue) {
		this.folderType = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof MailFolder)) {
			return false;
		}
		MailFolder rhs = (MailFolder) object;
		return new EqualsBuilder().append(this.folderId, rhs.folderId).append(this.folderName,
				rhs.folderName).append(this.parentId, rhs.parentId).append(this.depLevel,
				rhs.depLevel).append(this.isPublic, rhs.isPublic).append(this.folderType,
				rhs.folderType).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.folderId).append(
				this.folderName).append(this.parentId).append(this.depLevel).append(this.isPublic)
				.append(this.folderType).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("folderId", this.folderId).append("folderName",
				this.folderName).append("parentId", this.parentId)
				.append("depLevel", this.depLevel).append("isPublic", this.isPublic).append(
						"folderType", this.folderType).toString();
	}

	public String getFirstKeyColumnName() {
		return "folderId";
	}

	public Long getId() {
		return this.folderId;
	}
}


 
 
 
 
 