package com.usu.mapps.objects;

import com.usu.mapps.utils.Constant;

public class UserStatistic {
	private final static int STANDARD_DISK_SIZE=1024;
	
	public static enum StatisticType{
		BookCount,
		BookCountMinus,
		FolderCount,
		FolderCountMinus,
		LikeCount,
		ViewCount,
		CommentCount,
		FollowerCount,
		FollowerCountMinus,
		FollowingCount,
		FollowingCountMinus,
		PublishCount,
		DownloadCount,
		GroupCount,
		GroupCountMinus,
		DiskTotalSize,
		DiskUsedSize
	}
	
	private String userId;
	private int bookCount;
	private int folderCount;
	private int likeCount;
	private int viewCount;
	private int commentCount;
	private int followerCount;
	private int followingCount;
	private int publishCount;
	private int downloadCount;
	private int groupCount;
	private int diskTotalSize;
	private float diskUsedSize;
	
	public UserStatistic(){
		this.userId = Constant.EMPTY;
		this.bookCount = 0;
		this.folderCount = 0;
		this.likeCount = 0;
		this.viewCount = 0;
		this.commentCount = 0;
		this.followerCount = 0;
		this.followingCount = 0;
		this.publishCount = 0;
		this.downloadCount = 0;
		this.groupCount = 0;
		this.diskTotalSize = STANDARD_DISK_SIZE;
		this.diskUsedSize = 0;
	}
	
	public UserStatistic(String userId){
		this.userId = userId;
		this.bookCount = 0;
		this.folderCount = 0;
		this.likeCount = 0;
		this.viewCount = 0;
		this.commentCount = 0;
		this.followerCount = 0;
		this.followingCount = 0;
		this.publishCount = 0;
		this.downloadCount = 0;
		this.groupCount = 0;
		this.diskTotalSize = STANDARD_DISK_SIZE;
		this.diskUsedSize = 0;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	
	public int getFolderCount() {
		return folderCount;
	}
	public void setFolderCount(int folderCount) {
		this.folderCount = folderCount;
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
	
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	public int getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}
	
	public int getFollowingCount() {
		return followingCount;
	}
	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}

	public int getPublishCount() {
		return publishCount;
	}
	public void setPublishCount(int publishCount) {
		this.publishCount = publishCount;
	}

	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public int getGroupCount() {
		return groupCount;
	}
	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}
	
	public int getDiskTotalSize() {
		return diskTotalSize;
	}
	public void setDiskTotalSize(int diskTotalSize) {
		this.diskTotalSize = diskTotalSize;
	}
	
	public float getDiskUsedSize() {
		return diskUsedSize;
	}
	public void setDiskUsedSize(float diskUsedSize) {
		this.diskUsedSize = diskUsedSize;
	}
	
}
