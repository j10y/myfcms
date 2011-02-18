package com.htsoft.oa.model.archive;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.FileAttach;

public class ArchTemplate extends BaseModel {
	protected Long templateId;
	protected String tempName;
	protected String tempPath;
	protected FileAttach fileAttach;
	protected ArchivesType archivesType;

	public ArchTemplate() {
	}

	public ArchTemplate(Long in_templateId) {
		setTemplateId(in_templateId);
	}

	public FileAttach getFileAttach() {
		return this.fileAttach;
	}

	public void setFileAttach(FileAttach in_fileAttach) {
		this.fileAttach = in_fileAttach;
	}

	public ArchivesType getArchivesType() {
		return this.archivesType;
	}

	public void setArchivesType(ArchivesType in_archivesType) {
		this.archivesType = in_archivesType;
	}

	public Long getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Long aValue) {
		this.templateId = aValue;
	}

	public Long getTypeId() {
		return (getArchivesType() == null) ? null : getArchivesType().getTypeId();
	}

	public void setTypeId(Long aValue) {
		if (aValue == null) {
			this.archivesType = null;
		} else if (this.archivesType == null) {
			this.archivesType = new ArchivesType(aValue);
			this.archivesType.setVersion(new Integer(0));
		} else {
			this.archivesType.setTypeId(aValue);
		}
	}

	public String getTempName() {
		return this.tempName;
	}

	public void setTempName(String aValue) {
		this.tempName = aValue;
	}

	public String getTempPath() {
		return this.tempPath;
	}

	public void setTempPath(String aValue) {
		this.tempPath = aValue;
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

	public boolean equals(Object object) {
		if (!(object instanceof ArchTemplate)) {
			return false;
		}
		ArchTemplate rhs = (ArchTemplate) object;
		return new EqualsBuilder().append(this.templateId, rhs.templateId).append(this.tempName,
				rhs.tempName).append(this.tempPath, rhs.tempPath).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.templateId).append(
				this.tempName).append(this.tempPath).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("templateId", this.templateId).append("tempName",
				this.tempName).append("tempPath", this.tempPath).toString();
	}
}


 
 
 
 
 