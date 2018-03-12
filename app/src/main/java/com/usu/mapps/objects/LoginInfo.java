package com.usu.mapps.objects;

import com.usu.mapps.utils.Constant;

public class LoginInfo {
	public static int LOGIN_OK=0;
	public static int LOGIN_FAILED=1;
	
	private User userInfo;
	private int loginStatus;
	
	public LoginInfo(){
		this.setUserInfo(null);
		this.loginStatus=LOGIN_FAILED;
	}
	
	public LoginInfo(User userInfo,int loginStatus){
		this.userInfo=userInfo;
		this.loginStatus=loginStatus;
	}
	
	public User getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	/**
	 * this function will confirm whether an userId
	 * is match with the logged user Id.
	 * 
	 * @param userId
	 * @return
	 */
	public boolean isSameUser(String userId){
		return userInfo != null &&
			userInfo.getUserId() != null &&
			userInfo.getUserId().equals(userId);
	}
	
	/**
	 * check if current logged user is from a social 
	 * provider or not
	 * 
	 * @return
	 */
	public boolean isSocialUser(){
		return userInfo != null &&
			userInfo.getProviderId() > Constant.login.PROVIDER_XPUB_ID;
	}
}
