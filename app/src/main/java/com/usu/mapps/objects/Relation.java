package com.usu.mapps.objects;

import com.usu.mapps.utils.Constant;

public class Relation {
	private String relationId;
	private String userId;
	private String userName;
	private String userImage;
	private int userBookCount;
	private int userBookstandCount;
	private int userFollowerCount;
	private int userFollowingCount;
	private String relationType;
	private String relationTime;
	private int isFriend;
	private String[] supportImages;
		
	public Relation(){
		this.relationId = Constant.EMPTY;
		this.userId = Constant.EMPTY;
		this.setUserName(Constant.EMPTY);
		this.setUserImage(Constant.EMPTY);
		this.setUserBookCount(Constant.ZERO);
		this.setUserBookstandCount(Constant.ZERO);
		this.setUserFollowerCount(Constant.ZERO);
		this.setUserFollowingCount(Constant.ZERO);
		this.setRelationType(Constant.EMPTY);
		this.setRelationTime(Constant.EMPTY);
		this.isFriend = Constant.ZERO;
		this.setSupportImages(new String[0]);
	}
	
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
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

	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public int getUserBookCount() {
		return userBookCount;
	}
	public void setUserBookCount(int userBookCount) {
		this.userBookCount = userBookCount;
	}

	public int getUserBookstandCount() {
		return userBookstandCount;
	}
	public void setUserBookstandCount(int userBookstandCount) {
		this.userBookstandCount = userBookstandCount;
	}

	public int getUserFollowerCount() {
		return userFollowerCount;
	}
	public void setUserFollowerCount(int userFollowerCount) {
		this.userFollowerCount = userFollowerCount;
	}

	public int getUserFollowingCount() {
		return userFollowingCount;
	}
	public void setUserFollowingCount(int userFollowingCount) {
		this.userFollowingCount = userFollowingCount;
	}

	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public String getRelationTime() {
		return relationTime;
	}
	public void setRelationTime(String relationTime) {
		this.relationTime = relationTime;
	}
	
	public int getIsFriend() {
		return isFriend;
	}
	public void setIsFriend(int isFriend) {
		this.isFriend = isFriend;
	}
	
	public String[] getSupportImages() {
		return supportImages;
	}
	public void setSupportImages(String[] supportImages) {
		this.supportImages = supportImages;
	}
	
}
