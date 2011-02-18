package com.htsoft.oa.model.admin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class FixedAssets extends BaseModel {
	protected Long assetsId;
	protected String assetsNo;
	protected String assetsName;
	protected String model;
	protected String manufacturer;
	protected Date manuDate;
	protected Date buyDate;
	protected String beDep;
	protected String custodian;
	protected String notes;
	protected BigDecimal remainValRate;
	protected Date startDepre;
	protected BigDecimal intendTerm;
	protected BigDecimal intendWorkGross;
	protected String workGrossUnit;
	protected BigDecimal assetValue;
	protected BigDecimal assetCurValue;
	protected BigDecimal depreRate;
	protected BigDecimal defPerWorkGross;
	protected DepreType depreType;
	protected AssetsType assetsType;
	protected Set depreRecords = new HashSet();

	public FixedAssets() {
	}

	public FixedAssets(Long in_assetsId) {
		setAssetsId(in_assetsId);
	}

	public BigDecimal getDepreRate() {
		return this.depreRate;
	}

	public void setDepreRate(BigDecimal aValue) {
		this.depreRate = aValue;
	}

	public BigDecimal getDefPerWorkGross() {
		return this.defPerWorkGross;
	}

	public void setDefPerWorkGross(BigDecimal defPerWorkGross) {
		this.defPerWorkGross = defPerWorkGross;
	}

	public DepreType getDepreType() {
		return this.depreType;
	}

	public void setDepreType(DepreType in_depreType) {
		this.depreType = in_depreType;
	}

	public AssetsType getAssetsType() {
		return this.assetsType;
	}

	public void setAssetsType(AssetsType in_assetsType) {
		this.assetsType = in_assetsType;
	}

	public Set getDepreRecords() {
		return this.depreRecords;
	}

	public void setDepreRecords(Set in_depreRecords) {
		this.depreRecords = in_depreRecords;
	}

	public Long getAssetsId() {
		return this.assetsId;
	}

	public void setAssetsId(Long aValue) {
		this.assetsId = aValue;
	}

	public String getAssetsNo() {
		return this.assetsNo;
	}

	public void setAssetsNo(String aValue) {
		this.assetsNo = aValue;
	}

	public String getAssetsName() {
		return this.assetsName;
	}

	public void setAssetsName(String aValue) {
		this.assetsName = aValue;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String aValue) {
		this.model = aValue;
	}

	public Long getAssetsTypeId() {
		return (getAssetsType() == null) ? null : getAssetsType().getAssetsTypeId();
	}

	public void setAssetsTypeId(Long aValue) {
		if (aValue == null) {
			this.assetsType = null;
		} else if (this.assetsType == null) {
			this.assetsType = new AssetsType(aValue);
			this.assetsType.setVersion(new Integer(0));
		} else {
			this.assetsType.setAssetsTypeId(aValue);
		}
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String aValue) {
		this.manufacturer = aValue;
	}

	public Date getManuDate() {
		return this.manuDate;
	}

	public void setManuDate(Date aValue) {
		this.manuDate = aValue;
	}

	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date aValue) {
		this.buyDate = aValue;
	}

	public String getBeDep() {
		return this.beDep;
	}

	public void setBeDep(String aValue) {
		this.beDep = aValue;
	}

	public String getCustodian() {
		return this.custodian;
	}

	public void setCustodian(String aValue) {
		this.custodian = aValue;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String aValue) {
		this.notes = aValue;
	}

	public BigDecimal getRemainValRate() {
		return this.remainValRate;
	}

	public void setRemainValRate(BigDecimal aValue) {
		this.remainValRate = aValue;
	}

	public Long getDepreTypeId() {
		return (getDepreType() == null) ? null : getDepreType().getDepreTypeId();
	}

	public void setDepreTypeId(Long aValue) {
		if (aValue == null) {
			this.depreType = null;
		} else if (this.depreType == null) {
			this.depreType = new DepreType(aValue);
			this.depreType.setVersion(new Integer(0));
		} else {
			this.depreType.setDepreTypeId(aValue);
		}
	}

	public Date getStartDepre() {
		return this.startDepre;
	}

	public void setStartDepre(Date aValue) {
		this.startDepre = aValue;
	}

	public BigDecimal getIntendTerm() {
		return this.intendTerm;
	}

	public void setIntendTerm(BigDecimal aValue) {
		this.intendTerm = aValue;
	}

	public BigDecimal getIntendWorkGross() {
		return this.intendWorkGross;
	}

	public void setIntendWorkGross(BigDecimal aValue) {
		this.intendWorkGross = aValue;
	}

	public String getWorkGrossUnit() {
		return this.workGrossUnit;
	}

	public void setWorkGrossUnit(String aValue) {
		this.workGrossUnit = aValue;
	}

	public BigDecimal getAssetValue() {
		return this.assetValue;
	}

	public void setAssetValue(BigDecimal aValue) {
		this.assetValue = aValue;
	}

	public BigDecimal getAssetCurValue() {
		return this.assetCurValue;
	}

	public void setAssetCurValue(BigDecimal aValue) {
		this.assetCurValue = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof FixedAssets)) {
			return false;
		}
		FixedAssets rhs = (FixedAssets) object;
		return new EqualsBuilder().append(this.assetsId, rhs.assetsId).append(this.assetsNo,
				rhs.assetsNo).append(this.assetsName, rhs.assetsName).append(this.model, rhs.model)
				.append(this.manufacturer, rhs.manufacturer).append(this.manuDate, rhs.manuDate)
				.append(this.buyDate, rhs.buyDate).append(this.beDep, rhs.beDep).append(
						this.custodian, rhs.custodian).append(this.notes, rhs.notes).append(
						this.depreRate, rhs.depreRate)
				.append(this.remainValRate, rhs.remainValRate).append(this.startDepre,
						rhs.startDepre).append(this.intendTerm, rhs.intendTerm).append(
						this.intendWorkGross, rhs.intendWorkGross).append(this.workGrossUnit,
						rhs.workGrossUnit).append(this.assetValue, rhs.assetValue).append(
						this.assetCurValue, rhs.assetCurValue).append(this.defPerWorkGross,
						rhs.defPerWorkGross).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.assetsId).append(
				this.assetsNo).append(this.assetsName).append(this.model).append(this.manufacturer)
				.append(this.manuDate).append(this.buyDate).append(this.beDep).append(
						this.custodian).append(this.notes).append(this.depreRate).append(
						this.remainValRate).append(this.startDepre).append(this.intendTerm).append(
						this.intendWorkGross).append(this.workGrossUnit).append(this.assetValue)
				.append(this.defPerWorkGross).append(this.assetCurValue).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("assetsId", this.assetsId).append("assetsNo",
				this.assetsNo).append("assetsName", this.assetsName).append("model", this.model)
				.append("manufacturer", this.manufacturer).append("manuDate", this.manuDate)
				.append("buyDate", this.buyDate).append("beDep", this.beDep).append("custodian",
						this.custodian).append("notes", this.notes).append("remainValRate",
						this.remainValRate).append("startDepre", this.startDepre).append(
						"intendTerm", this.intendTerm).append("intendWorkGross",
						this.intendWorkGross).append("workGrossUnit", this.workGrossUnit).append(
						"assetValue", this.assetValue).append("depreRate", this.depreRate).append(
						"defPerWorkGross", this.defPerWorkGross).append("assetCurValue",
						this.assetCurValue).toString();
	}
}


 
 
 
 