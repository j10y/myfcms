package com.htsoft.oa.model.archive;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.FileAttach;

public class DocHistory extends BaseModel {
	protected Long historyId;
	protected String docName;
	protected String path;
	protected Integer version;
	protected Date updatetime;
	protected String mender;
	protected FileAttach fileAttach;
	protected ArchivesDoc archivesDoc;

	public DocHistory() {
	}

	public DocHistory(Long in_historyId) {
		setHistoryId(in_historyId);
	}

	public FileAttach getFileAttach() {
		return this.fileAttach;
	}

	public void setFileAttach(FileAttach in_fileAttach) {
		this.fileAttach = in_fileAttach;
	}

	public ArchivesDoc getArchivesDoc() {
		return this.archivesDoc;
	}

	public void setArchivesDoc(ArchivesDoc in_archivesDoc) {
		this.archivesDoc = in_archivesDoc;
	}

	public Long getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(Long aValue) {
		this.historyId = aValue;
	}

	public Long getDocId() {
		return (getArchivesDoc() == null) ? null : getArchivesDoc().getDocId();
	}

	public void setDocId(Long aValue) {
		if (aValue == null) {
			this.archivesDoc = null;
		} else if (this.archivesDoc == null) {
			this.archivesDoc = new ArchivesDoc(aValue);
			this.archivesDoc.setVersion(new Integer(0));
		} else {
			this.archivesDoc.setDocId(aValue);
		}
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

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String aValue) {
		this.docName = aValue;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String aValue) {
		this.path = aValue;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer aValue) {
		this.version = aValue;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date aValue) {
		this.updatetime = aValue;
	}

	public String getMender() {
		return this.mender;
	}

	public void setMender(String aValue) {
		this.mender = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof DocHistory)) {
			return false;
		}
		DocHistory rhs = (DocHistory) object;
		return new EqualsBuilder().append(this.historyId, rhs.historyId).append(this.docName,
				rhs.docName).append(this.path, rhs.path).append(this.version, rhs.version).append(
				this.updatetime, rhs.updatetime).append(this.mender, rhs.mender).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.historyId).append(
				this.docName).append(this.path).append(this.version).append(this.updatetime)
				.append(this.mender).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("historyId", this.historyId).append("docName",
				this.docName).append("path", this.path).append("version", this.version).append(
				"updatetime", this.updatetime).append("mender", this.mender).toString();
	}
}


 
 
 
 