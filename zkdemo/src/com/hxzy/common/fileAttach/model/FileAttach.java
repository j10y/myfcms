/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：May 12, 2010</p>
 * <p>更新：</p>
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
 * 描述：附件类
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
	 * 原文件名
	 */
	private String originalName;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 文件路径
	 */
	private String filePath;

	/**
	 * 创建时间
	 */
	private Date createtime;

	/**
	 * 扩展名
	 */
	private String ext;

	/**
	 * 上传人
	 */
	private String creator;

	/**
	 * 返回 id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 originalName
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * 设置 originalName
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	/**
	 * 返回 fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置 fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 返回 filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * 设置 filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 返回 createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * 设置 createtime
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 返回 ext
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * 设置 ext
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * 返回 creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * 设置 creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

}
