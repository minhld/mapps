package com.usu.mapps.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * this class contains the settings of all screens in the app.
 * 
 * 
 * @author minhld
 *
 */
public class NSoftSettings {
	final static String TAG="NSoftSettings";
	
	public static final int STYLE_GRID_VIEW=0;
	public static final int STYLE_LIST_VIEW=1;
	
	public static final int SORT_BY_TIME=1;
	public static final int SORT_BY_TITLE=2;
	public static final int SORT_BY_AUTHOR=3;
	
	public static final boolean DEFAULT_SYNTHETIC_SPREAD=false;
	public static final float DEFAULT_FONT_SIZE=1f;
	public static final int DEFAULT_COLUMN_GAP=0;
	public static final boolean DEFAULT_HARDWARE_ACCELERATOR=true;
	
	private static Context context;
	public static void setContext(Context context){
		NSoftSettings.context=context;
	}
	
	// ------ ------ ------ ------ ------ ------
	// my library list style
	// ------ ------ ------ ------ ------ ------

	public static int getMyLibListStyle() {
		return getSharedPref().getInt(
					Constant.settings.MYLIB_LIST_STYLE,
					STYLE_GRID_VIEW);
	}
	
	public static void setMyLibListStyle(int myListStyle) {
		SharedPreferences.Editor editor=startEditing();
		editor.putInt(Constant.settings.MYLIB_LIST_STYLE,myListStyle);
		editor.commit();
	}
	
	public static int getMyLibSortType() {
		return getSharedPref().getInt(
					Constant.settings.MYLIB_SORT_TYPE,
					SORT_BY_TIME);
	}
	
	public static void setMyLibSortType(int sortType) {
		SharedPreferences.Editor editor=startEditing();
		editor.putInt(Constant.settings.MYLIB_SORT_TYPE,sortType);
		editor.commit();
	}

	// ------ ------ ------ ------ ------ ------
	// language
	// ------ ------ ------ ------ ------ ------
	
	public static int getLanguage() {
		return getSharedPref().getInt(
					Constant.settings.LANGUAGE,
					Constant.settings.LANG_ENGLISH);
	}
	
	public static String getLanguageCode() {
		int langCode=getSharedPref().getInt(
					Constant.settings.LANGUAGE,
					Constant.settings.LANG_ENGLISH);
		if (langCode==Constant.settings.LANG_ENGLISH){
			return Constant.settings.LANG_CODE_ENGLISH;
		}else if (langCode==Constant.settings.LANG_KOREAN){
			return Constant.settings.LANG_CODE_KOREAN;
		}else if (langCode==Constant.settings.LANG_JAPANESE){
			return Constant.settings.LANG_CODE_JAPANESE;
		}else if (langCode==Constant.settings.LANG_VIETNAMESE){
			return Constant.settings.LANG_CODE_VIETNAMESE;
		}else if (langCode==Constant.settings.LANG_CHINESE){
			return Constant.settings.LANG_CODE_CHINESE;
		}
		return Constant.settings.LANG_CODE_ENGLISH;
	}
	
	public static void setLanguage(int lang) {
		SharedPreferences.Editor editor=startEditing();
		editor.putInt(Constant.settings.LANGUAGE,lang);
		editor.commit();
	}
	
	// ------ ------ ------ ------ ------ ------
	// viewer
	// ------ ------ ------ ------ ------ ------
	public static boolean getSyntheticSpread() {
		return getSharedPref().getBoolean(
					Constant.settings.SYNTHETIC_SPREAD,
					DEFAULT_SYNTHETIC_SPREAD);
	}
	
	public static void setSyntheticSpread(boolean syntheticSpread) {
		SharedPreferences.Editor editor=startEditing();
		editor.putBoolean(Constant.settings.SYNTHETIC_SPREAD,syntheticSpread);
		editor.commit();
	}
	
	// ------ ------ ------ ------ ------ ------ 
	// font-size
	// ------ ------ ------ ------ ------ ------
	
	public static float getFontSize() {
		return getSharedPref().getFloat(
					Constant.settings.FONT_SIZE,
					DEFAULT_FONT_SIZE);
	}
	
	public static void setFontSize(float fontSize) {
		SharedPreferences.Editor editor=startEditing();
		editor.putFloat(Constant.settings.FONT_SIZE,fontSize);
		editor.commit();
	}
	
	public static String getViewSettingsJSON(){
		JSONObject json = new JSONObject();
		try{
			json.put("isSyntheticSpread", getSyntheticSpread());
			json.put("fontSize", getFontSize()*100);
			json.put("columnGap", DEFAULT_COLUMN_GAP);
			
		}catch(JSONException e){
			Log.v(TAG,e.getClass()+": "+e.getMessage());
		}
		return json.toString();
	}
	
	// ------ ------ ------ ------ ------ ------
	// hardware accelerator
	// ------ ------ ------ ------ ------ ------
	public static boolean getUseHardwareAccelerator(){
		return getSharedPref().getBoolean(
				Constant.settings.HARDWARE_ACCELERATOR,
				DEFAULT_HARDWARE_ACCELERATOR);
	}
	
	public static void setUseHardwareAccelerator(
						boolean useHardwareAccelerator){
		SharedPreferences.Editor editor=startEditing();
		editor.putBoolean(Constant.settings.HARDWARE_ACCELERATOR,
						useHardwareAccelerator);
		editor.commit();
	}
	
	// ------ ------ ------ ------ ------ ------
	// login parent class name
	// ------ ------ ------ ------ ------ ------
	public static String getLoginParentClassName() {
		return getSharedPref().getString(
					Constant.settings.LOGIN_PARENT_CLASSNAME,
					Constant.EMPTY);
	}
	
	public static void setLoginParentScreen(String parentClassName) {
		SharedPreferences.Editor editor=startEditing();
		editor.putString(Constant.settings.LOGIN_PARENT_CLASSNAME,
						parentClassName);
		editor.commit();
	}
	
	public static boolean getRememberLogin() {
		return getSharedPref().getBoolean(
					Constant.settings.LOGIN_REMEMBER_ACCOUNT,
					Constant.DEFAULT_BOOL);
	}
	
	public static void setRememberLogin(boolean rememberLogin) {
		SharedPreferences.Editor editor=startEditing();
		editor.putBoolean(Constant.settings.LOGIN_REMEMBER_ACCOUNT,
						rememberLogin);
		editor.commit();
	}
	
	public static Set<String> getUserLogin() {
		Set<String> userInfo = new HashSet<String>();
		userInfo.add(Constant.EMPTY);
		userInfo.add(Constant.EMPTY);
		
		return getSharedPref().getStringSet(Constant.settings.LOGIN_USER, userInfo);
	}
	
	public static void setUserLogin(String username, String password) {
		Set<String> userInfo = new HashSet<String>();
		userInfo.add(username);
		userInfo.add(password);
		
		SharedPreferences.Editor editor=startEditing();
		editor.putStringSet(Constant.settings.LOGIN_USER, userInfo);
		editor.commit();
	}
	
	
//	// ------ ------ ------ ------ ------ ------
//	// login - clean history
//	// ------ ------ ------ ------ ------ ------
//	
//	public static boolean getCleanHistory() {
//		return getSharedPref().getBoolean(
//					Constant.settings.LOGIN_CLEAN_HISTORY,
//					true);
//	}
//	
//	public static void setCleanHistory(boolean isCleanHistory) {
//		SharedPreferences.Editor editor=startEditing();
//		editor.putBoolean(Constant.settings.LOGIN_CLEAN_HISTORY,
//				isCleanHistory);
//		editor.commit();
//	}
	
	// ------ ------ ------ ------ ------ ------
	// support functions
	// ------ ------ ------ ------ ------ ------
	
	/**
	 * create a share preferences object for retrieving data
	 * 
	 * @return
	 */
	private static SharedPreferences getSharedPref(){
		return NSoftSettings.context.getSharedPreferences(
				Constant.settings.APP_SETTINGS,
				Activity.MODE_PRIVATE);
	}
	/**
	 * creating a new editor for share preferences.
	 * 
	 * @param context
	 * @return
	 */
	private static SharedPreferences.Editor startEditing(){
		SharedPreferences preferences=NSoftSettings.context.
				getSharedPreferences(
						Constant.settings.APP_SETTINGS,
						Activity.MODE_PRIVATE);
		return preferences.edit();
	}
}
