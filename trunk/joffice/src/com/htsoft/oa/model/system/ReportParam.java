package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class ReportParam extends BaseModel {
	protected Long paramId;
	protected String paramName;
	protected String paramKey;
	protected String defaultVal;
	protected String paramType;
	protected Integer sn;
	protected ReportTemplate reportTemplate;

	public ReportParam() {
	}

	public ReportParam(Long in_paramId) {
		setParamId(in_paramId);
	}

	public ReportTemplate getReportTemplate() {
		return this.reportTemplate;
	}

	public void setReportTemplate(ReportTemplate in_reportTemplate) {
		this.reportTemplate = in_reportTemplate;
	}

	public Long getParamId() {
		return this.paramId;
	}

	public void setParamId(Long aValue) {
		this.paramId = aValue;
	}

	public Long getReportId() {
		return (getReportTemplate() == null) ? null : getReportTemplate().getReportId();
	}

	public void setReportId(Long aValue) {
		if (aValue == null) {
			this.reportTemplate = null;
		} else if (this.reportTemplate == null) {
			this.reportTemplate = new ReportTemplate(aValue);
			this.reportTemplate.setVersion(new Integer(0));
		} else {
			this.reportTemplate.setReportId(aValue);
		}
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String aValue) {
		this.paramName = aValue;
	}

	public String getParamKey() {
		return this.paramKey;
	}

	public void setParamKey(String aValue) {
		this.paramKey = aValue;
	}

	public String getDefaultVal() {
		return this.defaultVal;
	}

	public void setDefaultVal(String aValue) {
		this.defaultVal = aValue;
	}

	public String getParamType() {
		return this.paramType;
	}

	public void setParamType(String aValue) {
		this.paramType = aValue;
	}

	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer aValue) {
		this.sn = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof ReportParam)) {
			return false;
		}
		ReportParam rhs = (ReportParam) object;
		return new EqualsBuilder().append(this.paramId, rhs.paramId).append(this.paramName,
				rhs.paramName).append(this.paramKey, rhs.paramKey).append(this.defaultVal,
				rhs.defaultVal).append(this.paramType, rhs.paramType).append(this.sn, rhs.sn)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.paramId).append(
				this.paramName).append(this.paramKey).append(this.defaultVal)
				.append(this.paramType).append(this.sn).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("paramId", this.paramId).append("paramName",
				this.paramName).append("paramKey", this.paramKey).append("defaultVal",
				this.defaultVal).append("paramType", this.paramType).append("sn", this.sn)
				.toString();
	}
}


 
 
 
 