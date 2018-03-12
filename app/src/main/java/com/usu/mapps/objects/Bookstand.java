package com.usu.mapps.objects;

import com.usu.mapps.utils.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bookstand implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id = Constant.EMPTY;
	private String name = Constant.EMPTY;
	private String userId = Constant.EMPTY;
	private String userName = Constant.EMPTY;
	private String image = Constant.EMPTY;
	private List<String> bookCovers = new ArrayList<String>();
	// private Like[] likes;
	private int bookCount = Constant.ZERO;
	private String createTime = Constant.EMPTY;
	private String keywords = Constant.EMPTY;
	private int status = Constant.INVALID_INDEX;
	private String statusName = Constant.EMPTY;
	private int viewType = Constant.INVALID_INDEX;
	
	private int commentCount = Constant.ZERO;
	private int likeCount = Constant.ZERO;
	private int viewCount = Constant.ZERO;
	
	public String getId() {
		return id;
	}
	public void setId(String Id) {
		this.id = Id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	/*
	public BookItem[] getBooks() {
		return books;
	}
	public void setBooks(BookItem[] books) {
		this.books = books;
	}
	
	public Like[] getLikes() {
		return likes;
	}
	public void setLikes(Like[] likes) {
		this.likes = likes;
	}
	*/
	
	public List<String> getBookCovers() {
		return bookCovers;
	}
	public void setBookCovers(List<String> bookCovers) {
		this.bookCovers = bookCovers;
	}
	public void addBookCover(String cover){
		this.bookCovers.add(cover);
	}
	
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public int getViewType() {
		return viewType;
	}
	public void setViewType(int viewType) {
		this.viewType = viewType;
	}
	
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
}
