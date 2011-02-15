package com.htsoft.oa.model.info;

import java.util.Set;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class NewsType extends BaseModel {

	@Expose
	private Long typeId;

	@Expose
	private String typeName;

	@Expose
	private Short sn;
	private Set<News> news;

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Short getSn() {
		return this.sn;
	}

	public void setSn(Short sn) {
		this.sn = sn;
	}

	public Set<News> getNews() {
		return this.news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}
}


 
 
 
 