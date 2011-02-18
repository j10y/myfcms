package com.htsoft.oa.model.admin;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class Car extends BaseModel {
	public static short PASS_APPLY = 1;
	public static short NOTPASS_APPLY = 0;

	@Expose
	protected Long carId;

	@Expose
	protected String carNo;

	@Expose
	protected String carType;

	@Expose
	protected String engineNo;

	@Expose
	protected Date buyInsureTime;

	@Expose
	protected Date auditTime;

	@Expose
	protected String notes;

	@Expose
	protected String factoryModel;

	@Expose
	protected String driver;

	@Expose
	protected Date buyDate;

	@Expose
	protected Short status;

	@Expose
	protected String cartImage;
	protected Set<CarApply> carApplys = new HashSet();
	protected Set<CartRepair> cartRepairs = new HashSet();

	public Set<CarApply> getCarApplys() {
		return this.carApplys;
	}

	public void setCarApplys(Set<CarApply> carApplys) {
		this.carApplys = carApplys;
	}

	public Set<CartRepair> getCartRepairs() {
		return this.cartRepairs;
	}

	public void setCartRepairs(Set<CartRepair> cartRepairs) {
		this.cartRepairs = cartRepairs;
	}

	public Car() {
	}

	public Car(Long in_carId) {
		setCarId(in_carId);
	}

	public Long getCarId() {
		return this.carId;
	}

	public void setCarId(Long aValue) {
		this.carId = aValue;
	}

	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String aValue) {
		this.carNo = aValue;
	}

	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String aValue) {
		this.carType = aValue;
	}

	public String getEngineNo() {
		return this.engineNo;
	}

	public void setEngineNo(String aValue) {
		this.engineNo = aValue;
	}

	public Date getBuyInsureTime() {
		return this.buyInsureTime;
	}

	public void setBuyInsureTime(Date aValue) {
		this.buyInsureTime = aValue;
	}

	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date aValue) {
		this.auditTime = aValue;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String aValue) {
		this.notes = aValue;
	}

	public String getFactoryModel() {
		return this.factoryModel;
	}

	public void setFactoryModel(String aValue) {
		this.factoryModel = aValue;
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String aValue) {
		this.driver = aValue;
	}

	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date aValue) {
		this.buyDate = aValue;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short aValue) {
		this.status = aValue;
	}

	public String getCartImage() {
		return this.cartImage;
	}

	public void setCartImage(String aValue) {
		this.cartImage = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Car)) {
			return false;
		}
		Car rhs = (Car) object;
		return new EqualsBuilder().append(this.carId, rhs.carId).append(this.carNo, rhs.carNo)
				.append(this.carType, rhs.carType).append(this.engineNo, rhs.engineNo).append(
						this.buyInsureTime, rhs.buyInsureTime)
				.append(this.auditTime, rhs.auditTime).append(this.notes, rhs.notes).append(
						this.factoryModel, rhs.factoryModel).append(this.driver, rhs.driver)
				.append(this.buyDate, rhs.buyDate).append(this.status, rhs.status).append(
						this.cartImage, rhs.cartImage).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.carId).append(this.carNo)
				.append(this.carType).append(this.engineNo).append(this.buyInsureTime).append(
						this.auditTime).append(this.notes).append(this.factoryModel).append(
						this.driver).append(this.buyDate).append(this.status)
				.append(this.cartImage).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("carId", this.carId).append("carNo", this.carNo)
				.append("carType", this.carType).append("engineNo", this.engineNo).append(
						"buyInsureTime", this.buyInsureTime).append("auditTime", this.auditTime)
				.append("notes", this.notes).append("factoryModel", this.factoryModel).append(
						"driver", this.driver).append("buyDate", this.buyDate).append("status",
						this.status).append("cartImage", this.cartImage).toString();
	}
}


 
 
 
 