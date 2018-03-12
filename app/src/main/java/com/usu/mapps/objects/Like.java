package com.usu.mapps.objects;

import java.io.Serializable;

public class Like implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String userId;
	private String userName;
	private String userPortrait;
	private String bookId;
	private BookItem bookItem;
	private int likeType;
	private String likeTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPortrait() {
		return userPortrait;
	}
	public void setUserPortrait(String userPortrait) {
		this.userPortrait = userPortrait;
	}
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	public BookItem getBookItem() {
		return bookItem;
	}
	public void setBookItem(BookItem bookItem) {
		this.bookItem=bookItem;
	}
	
	public int getLikeType() {
		return likeType;
	}
	public void setLikeType(int likeType) {
		this.likeType = likeType;
	}
	
	public String getLikeTime() {
		return likeTime;
	}
	public void setLikeTime(String likeTime) {
		this.likeTime = likeTime;
	}

}
