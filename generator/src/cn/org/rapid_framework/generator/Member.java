package cn.org.rapid_framework.generator;


import java.util.Date;

public class Member 
//extends Revisable 
{

	// ��˾���
	private String companyName;

	// ��������
//	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
//	@JoinColumn(name = "category_id")
//	private Dict category;

	// jϵ��ʽ
	private String contacts;

	// ���ʱ��
	private Date joinTime;

	// ����ʱ��
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
	 * ���� category
	 */
//	public Dict getCategory() {
//		return category;
//	}
//
//	/**
//	 * ���� category
//	 */
//	public void setCategory(Dict category) {
//		this.category = category;
//	}

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
