package cn.org.rapid_framework.generator;

import java.util.Date;

import cn.org.rapid_framework.generator.annotation.Description;

public class Member {

	@Description("javaeye,做最棒的软件开发交流社区") 
	private String companyName;

	/**
	 * 联系方式
	 */
	private String contacts;

	/**
	 * 入会时间
	 */
	private Date joinTime;

	/**
	 * 截止时间
	 */
	private Date endTime;

	/**
	 * ���� companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * ���� companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	/**
	 * ���� contacts
	 */
	public String getContacts() {
		return contacts;
	}

	/**
	 * ���� contacts
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	/**
	 * ���� joinTime
	 */
	public Date getJoinTime() {
		return joinTime;
	}

	/**
	 * ���� joinTime
	 */
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	/**
	 * ���� endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * ���� endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
