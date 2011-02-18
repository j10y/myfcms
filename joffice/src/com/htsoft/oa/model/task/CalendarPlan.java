package com.htsoft.oa.model.task;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class CalendarPlan extends BaseModel {
	public static final Short STATUS_UNFINISHED = 0;

	public static final Short STATUS_FINISHED = 1;

	public static final Short URGENT_COMMON = 0;

	public static final Short URGENT_IMPORTANT = 1;

	public static final Short URGENT_INST = 2;

	@Expose
	protected Long planId;

	@Expose
	protected Date startTime;

	@Expose
	protected Date endTime;

	@Expose
	protected Short urgent;

	@Expose
	protected String content;

	@Expose
	protected Short status;

	@Expose
	protected String fullname;

	@Expose
	protected Long assignerId;

	@Expose
	protected String assignerName;

	@Expose
	protected String feedback;

	@Expose
	protected Short showStyle;

	@Expose
	protected Short taskType;

	@Expose
	protected Long userId;

	public String getColor() {
		if (STATUS_FINISHED.equals(this.status)) {
			return "#D5E4BD";
		}
		if (URGENT_INST.equals(this.urgent))
			return "#94B98D";
		if (URGENT_IMPORTANT.equals(this.urgent)) {
			return "#FFBDB4";
		}
		return "#7799BF";
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatusName() {
		if (this.status.equals(STATUS_FINISHED)) {
			return "完成";
		}
		return "未完成";
	}

	public String getUrgentName() {
		if (this.urgent.equals(URGENT_COMMON))
			return "一般";
		if (this.urgent.equals(URGENT_IMPORTANT)) {
			return "重要";
		}
		return "紧急";
	}

	public CalendarPlan() {
	}

	public CalendarPlan(Long in_planId) {
		setPlanId(in_planId);
	}

	public Long getPlanId() {
		return this.planId;
	}

	public void setPlanId(Long aValue) {
		this.planId = aValue;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date aValue) {
		this.startTime = aValue;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date aValue) {
		this.endTime = aValue;
	}

	public Short getUrgent() {
		return this.urgent;
	}

	public void setUrgent(Short aValue) {
		this.urgent = aValue;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String aValue) {
		this.content = aValue;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short aValue) {
		this.status = aValue;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String aValue) {
		this.fullname = aValue;
	}

	public Long getAssignerId() {
		return this.assignerId;
	}

	public void setAssignerId(Long aValue) {
		this.assignerId = aValue;
	}

	public String getAssignerName() {
		return this.assignerName;
	}

	public void setAssignerName(String aValue) {
		this.assignerName = aValue;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String aValue) {
		this.feedback = aValue;
	}

	public Short getShowStyle() {
		return this.showStyle;
	}

	public void setShowStyle(Short aValue) {
		this.showStyle = aValue;
	}

	public Short getTaskType() {
		return this.taskType;
	}

	public void setTaskType(Short aValue) {
		this.taskType = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof CalendarPlan)) {
			return false;
		}
		CalendarPlan rhs = (CalendarPlan) object;
		return new EqualsBuilder().append(this.planId, rhs.planId).append(this.startTime,
				rhs.startTime).append(this.endTime, rhs.endTime).append(this.urgent, rhs.urgent)
				.append(this.content, rhs.content).append(this.status, rhs.status).append(
						this.fullname, rhs.fullname).append(this.userId, rhs.userId).append(
						this.assignerId, rhs.assignerId)
				.append(this.assignerName, rhs.assignerName).append(this.feedback, rhs.feedback)
				.append(this.showStyle, rhs.showStyle).append(this.taskType, rhs.taskType)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.planId)
				.append(this.startTime).append(this.endTime).append(this.urgent).append(
						this.content).append(this.status).append(this.fullname).append(this.userId)
				.append(this.assignerId).append(this.assignerName).append(this.feedback).append(
						this.showStyle).append(this.taskType).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("planId", this.planId).append("startTime",
				this.startTime).append("endTime", this.endTime).append("urgent", this.urgent)
				.append("content", this.content).append("status", this.status).append("fullname",
						this.fullname).append("assignerId", this.assignerId).append("userId",
						this.userId).append("assignerName", this.assignerName).append("feedback",
						this.feedback).append("showStyle", this.showStyle).append("taskType",
						this.taskType).toString();
	}
}


 
 
 
 