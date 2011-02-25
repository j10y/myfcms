/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�May 12, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.fileAttach.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xiacc
 * 
 * ������������
 */
@Entity
@Table(name = "fileAttach")
public class FileAttach {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * ԭ�ļ���
	 */
	private String originalName;

	/**
	 * �ļ���
	 */
	private String fileName;

	/**
	 * �ļ�·��
	 */
	private String filePath;

	/**
	 * ����ʱ��
	 */
	private Date createtime;

	/**
	 * ��չ��
	 */
	private String ext;

	/**
	 * �ϴ���
	 */
	private String creator;

	/**
	 * ���� id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * ���� id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * ���� originalName
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * ���� originalName
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	/**
	 * ���� fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * ���� fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * ���� filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * ���� filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * ���� createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * ���� createtime
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * ���� ext
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * ���� ext
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * ���� creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * ���� creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

}
