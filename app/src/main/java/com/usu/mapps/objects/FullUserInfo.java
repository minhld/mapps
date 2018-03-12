package com.usu.mapps.objects;

public class FullUserInfo {
	private User userInfo;
	private UserStatistic userStatistic;
	
	public FullUserInfo(){
		this.userInfo = new User();
		this.userStatistic = new UserStatistic();
	}
	
	public User getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	
	public UserStatistic getUserStatistic() {
		return userStatistic;
	}
	public void setUserStatistic(UserStatistic userStatistic) {
		this.userStatistic = userStatistic;
	}

}
