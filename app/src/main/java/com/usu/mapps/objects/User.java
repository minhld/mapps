package com.usu.mapps.objects;

import com.usu.mapps.utils.Constant;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId = Constant.EMPTY;
	private String userName = Constant.EMPTY;
	private String account = Constant.EMPTY;
	private String password = Constant.EMPTY;
	private int providerId = Constant.INVALID_INDEX;
	private String providerName = Constant.EMPTY;
	private String identifierId = Constant.EMPTY;
	private String serviceId = Constant.EMPTY;
	private String service = Constant.EMPTY;
	private int groupId = Constant.INVALID_INDEX;
	private String userPortrait = Constant.EMPTY;
	private String userPortraitSmall = Constant.EMPTY;
	private String userEmail = Constant.EMPTY;
	private String dateOfBirth = Constant.EMPTY;
	private String userAddress = Constant.EMPTY;
	private String jointDate = Constant.EMPTY;
	private String description = Constant.EMPTY;
	private int activated = Constant.INVALID_INDEX;
	private int isFriend = Constant.ZERO;
	
	public User(){}
	
	public User(String account, String password){
		this.account = account;
		this.password = password;
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
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getIdentifierId() {
		return identifierId;
	}
	public void setIdentifierId(String identifierId) {
		this.identifierId = identifierId;
	}

	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}

	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public String getUserPortrait() {
		return userPortrait;
	}
	public void setUserPortrait(String userPortrait) {
		this.userPortrait = userPortrait;
	}
	
	public String getUserPortraitSmall() {
		return userPortraitSmall;
	}
	public void setUserPortraitSmall(String userPortraitSmall) {
		this.userPortraitSmall = userPortraitSmall;
	}

	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public String getJointDate() {
		return jointDate;
	}
	public void setJointDate(String jointDate) {
		this.jointDate = jointDate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getActivated() {
		return activated;
	}
	public void setActivated(int activated) {
		this.activated = activated;
	}

	public int getIsFriend() {
		return isFriend;
	}
	public void setIsFriend(int isFriend) {
		this.isFriend = isFriend;
	}
}
