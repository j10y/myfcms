package com.htsoft.oa.model.hrm;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.Department;

public class Job extends BaseModel {
	public static short DELFLAG_NOT = 0;
	public static short DELFLAG_HAD = 1;
	protected Long jobId;
	protected String jobName;
	protected String memo;
	protected Short delFlag;
	protected Department department;
	protected Set empProfiles = new HashSet();

	public Job() {
	}

	public Job(Long in_jobId) {
		setJobId(in_jobId);
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department in_department) {
		this.department = in_department;
	}

	public Set getEmpProfiles() {
		return this.empProfiles;
	}

	public void setEmpProfiles(Set in_empProfiles) {
		this.empProfiles = in_empProfiles;
	}

	public Long getJobId() {
		return this.jobId;
	}

	public void setJobId(Long aValue) {
		this.jobId = aValue;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String aValue) {
		this.jobName = aValue;
	}

	public Long getDepId() {
		return (getDepartment() == null) ? null : getDepartment().getDepId();
	}

	public void setDepId(Long aValue) {
		if (aValue == null) {
			this.department = null;
		} else if (this.department == null) {
			this.department = new Department(aValue);
			this.department.setVersion(new Integer(0));
		} else {
			this.department.setDepId(aValue);
		}
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String aValue) {
		this.memo = aValue;
	}

	public Short getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Job)) {
			return false;
		}
		Job rhs = (Job) object;
		return new EqualsBuilder().append(this.jobId, rhs.jobId).append(this.jobName, rhs.jobName)
				.append(this.memo, rhs.memo).append(this.delFlag, rhs.delFlag).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.jobId).append(this.jobName)
				.append(this.memo).append(this.delFlag).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("jobId", this.jobId)
				.append("jobName", this.jobName).append("memo", this.memo).append("delFlag",
						this.delFlag).toString();
	}
}


 
 
 
 