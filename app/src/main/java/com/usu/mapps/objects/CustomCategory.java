package com.usu.mapps.objects;

import java.io.Serializable;

public class CustomCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String bookstandId;
	private String createDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBookstandId() {
		return bookstandId;
	}
	public void setBookstandId(String bookstandId) {
		this.bookstandId = bookstandId;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
