package com.htsoft.oa.model.hrm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class StandSalary extends BaseModel {

	@Expose
	protected Long standardId;

	@Expose
	protected String standardNo;

	@Expose
	protected String standardName;

	@Expose
	protected BigDecimal totalMoney;

	@Expose
	protected String framer;

	@Expose
	protected Date setdownTime;

	@Expose
	protected String checkName;

	@Expose
	protected Date checkTime;

	@Expose
	protected String modifyName;

	@Expose
	protected Date modifyTime;

	@Expose
	protected String checkOpinion;

	@Expose
	protected Short status;

	@Expose
	protected String memo;
	protected Set standSalaryItems = new HashSet();

	public StandSalary() {
	}

	public StandSalary(Long in_standardId) {
		setStandardId(in_standardId);
	}

	public Set getStandSalaryItems() {
		return this.standSalaryItems;
	}

	public void setStandSalaryItems(Set in_standSalaryItems) {
		this.standSalaryItems = in_standSalaryItems;
	}

	public Long getStandardId() {
		return this.standardId;
	}

	public void setStandardId(Long aValue) {
		this.standardId = aValue;
	}

	public String getStandardNo() {
		return this.standardNo;
	}

	public void setStandardNo(String aValue) {
		this.standardNo = aValue;
	}

	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String aValue) {
		this.standardName = aValue;
	}

	public BigDecimal getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(BigDecimal aValue) {
		this.totalMoney = aValue;
	}

	public String getFramer() {
		return this.framer;
	}

	public void setFramer(String aValue) {
		this.framer = aValue;
	}

	public Date getSetdownTime() {
		return this.setdownTime;
	}

	public void setSetdownTime(Date aValue) {
		this.setdownTime = aValue;
	}

	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String aValue) {
		this.checkName = aValue;
	}

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date aValue) {
		this.checkTime = aValue;
	}

	public String getModifyName() {
		return this.modifyName;
	}

	public void setModifyName(String aValue) {
		this.modifyName = aValue;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date aValue) {
		this.modifyTime = aValue;
	}

	public String getCheckOpinion() {
		return this.checkOpinion;
	}

	public void setCheckOpinion(String aValue) {
		this.checkOpinion = aValue;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short aValue) {
		this.status = aValue;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String aValue) {
		this.memo = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof StandSalary)) {
			return false;
		}
		StandSalary rhs = (StandSalary) object;
		return new EqualsBuilder().append(this.standardId, rhs.standardId).append(this.standardNo,
				rhs.standardNo).append(this.standardName, rhs.standardName).append(this.totalMoney,
				rhs.totalMoney).append(this.framer, rhs.framer).append(this.setdownTime,
				rhs.setdownTime).append(this.checkName, rhs.checkName).append(this.checkTime,
				rhs.checkTime).append(this.modifyName, rhs.modifyName).append(this.modifyTime,
				rhs.modifyTime).append(this.checkOpinion, rhs.checkOpinion).append(this.status,
				rhs.status).append(this.memo, rhs.memo).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.standardId).append(
				this.standardNo).append(this.standardName).append(this.totalMoney).append(
				this.framer).append(this.setdownTime).append(this.checkName).append(this.checkTime)
				.append(this.modifyName).append(this.modifyTime).append(this.checkOpinion).append(
						this.status).append(this.memo).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("standardId", this.standardId).append("standardNo",
				this.standardNo).append("standardName", this.standardName).append("totalMoney",
				this.totalMoney).append("framer", this.framer).append("setdownTime",
				this.setdownTime).append("checkName", this.checkName).append("checkTime",
				this.checkTime).append("modifyName", this.modifyName).append("modifyTime",
				this.modifyTime).append("checkOpinion", this.checkOpinion).append("status",
				this.status).append("memo", this.memo).toString();
	}
}


 
 
 
 