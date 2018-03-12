package com.usu.mapps.objects;

import com.usu.mapps.R;
import com.usu.mapps.utils.Constant;

import java.io.Serializable;

public class Provider implements Serializable {
	public static class ProviderType{
		public static final int XPubMe = 1;
		public static final int Facebook = 2;
		public static final int Twitter = 3;
		public static final int LinkedIn = 4;
		public static final int Google = 5;
		public static final int Yahoo = 6;
	}
	
	public static class ProviderName{
		public static final String XPubMe = "Osobit";
		public static final String Facebook = "Facebook";
		public static final String Twitter = "Twitter";
		public static final String LinkedIn = "LinkedIn";
		public static final String Google = "Google";
		public static final String Yahoo = "Yahoo";
		public static final String Unknown = "Unknown Service";
	}
	
	private static final long serialVersionUID = 1L;
	private int providerType = ProviderType.XPubMe;
	private String providerName = Constant.EMPTY;
	private int providerIconId = Constant.ZERO;
	
	public Provider(int pType) {
		this.setProviderType(pType);
		switch (this.providerType) {
			case ProviderType.XPubMe: {
				providerName = ProviderName.XPubMe;
				setProviderIconId(R.drawable.ic_launcher);
				break;
			}
			case ProviderType.Facebook: {
				providerName = ProviderName.Facebook;
				setProviderIconId(R.drawable.ic_facebook);
				break;
			}
			case ProviderType.Twitter: {
				providerName = ProviderName.Twitter;
				setProviderIconId(R.drawable.ic_twitter);
				break;
			}
			case ProviderType.LinkedIn: {
				providerName = ProviderName.LinkedIn;
				break;
			}
			case ProviderType.Google: {
				providerName = ProviderName.Google;
				break;
			}
			case ProviderType.Yahoo: {
				providerName = ProviderName.Yahoo;
				break;
			}
			default:{
				providerName = ProviderName.Unknown;
				break;
			}
		}
	}
	
	public int getProviderType() {
		return providerType;
	}
	public void setProviderType(int providerType) {
		this.providerType = providerType;
	}

	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public int getProviderIconId() {
		return providerIconId;
	}
	public void setProviderIconId(int providerIconId) {
		this.providerIconId = providerIconId;
	}
	
}
