package cn.org.rapid_framework.generator;

import java.util.Date;

public class Member {

	private String companyName;

	private String contacts;

	private Date joinTime;

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
