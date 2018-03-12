package com.usu.mapps.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;

import com.usu.mapps.R;
import com.usu.mapps.objects.BookItem;
import com.usu.mapps.objects.BookList;
import com.usu.mapps.objects.Category;
import com.usu.mapps.objects.CategoryList;
import com.usu.mapps.objects.LoginInfo;
import com.usu.mapps.objects.User;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class NSoftEngine {
	/**
	 * store the information of current logged user
	 */
	public static LoginInfo loginInfo = new LoginInfo();
	
//	/**
//	 * this is the my library book list. the key is based on the 
//	 * time (in long format) that book item is added to the map 
//	 */
//	private static SortedMap<Long,BookItem> myLibBookList=
//				new TreeMap<Long,BookItem>(new Comparator<Long>() {
//		
//					@Override
//					public int compare(Long lhs, Long rhs) {
//						return (int)(rhs-lhs);
//					}
//					
//				});
//	
//	public static int getMyLibBooksNumber(){
//		return NSoftEngine.myLibBookList.size();
//	}
//	
//	public static void addMyLibBookItem(long addedTime,BookItem bookItem){
//		NSoftEngine.myLibBookList.put(addedTime,bookItem);
//		// also add the book file name into the name list
//		if (!myLibNameList.contains(bookItem.getFolderName())){
//			myLibNameList.add(bookItem.getFolderName());
//		}
//	}
////	public static BookItem getMyLibBookItem(long key){
////		return myLibBookList.get(key);
////	}
//	
//	public static Map<Long,BookItem> getMyLibBookList(){
//		return NSoftEngine.myLibBookList;
//	}
//	
//	public static void deleteMyLibBookItem(long key){
//		BookItem book=NSoftEngine.myLibBookList.get(key);
//		NSoftEngine.myLibBookList.remove(key);
//		// remove the book file name in the name list
//		NSoftEngine.myLibNameList.remove(book.getFolderName());
//	}
//
//	/**
//	 * get the book list in array format, not map
//	 * @return
//	 */
//	public static List<BookItem> getMyLibBookListInArray(){
//		return new ArrayList<BookItem>(NSoftEngine.myLibBookList.values());
//	}
	
	private static Map<String, BookItem> myLibBookList = new HashMap<String,BookItem>();
		
	public static int getMyLibBooksNumber() {
		return NSoftEngine.myLibBookList.size();
	}
	
	public static void addMyLibBookItem(String title, BookItem bookItem) {
		if (!NSoftEngine.myLibBookList.containsKey(title)) {
			NSoftEngine.myLibBookList.put(title,bookItem);
		}
	}
	
	public static Map<String, BookItem> getMyLibBookList() {
		return NSoftEngine.myLibBookList;
	}
	
	public static void deleteMyLibBookItem(String title) {
		if (NSoftEngine.myLibBookList.containsKey(title)) {
			NSoftEngine.myLibBookList.remove(title);
		}
	}
	
	/**
	 * this function returns the book list in single array by sort order
	 * user can specify the order by DATE, NAME and AUTHOR.
	 * 
	 * @param sortType
	 * @return
	 */
	public static List<BookItem> getMyLibBookListInArray(int sortType) {
		List<BookItem> bookList = new ArrayList<BookItem>(NSoftEngine.myLibBookList.values());

		switch(sortType) {
			case NSoftSettings.SORT_BY_TIME: {
				Collections.sort(bookList, new Comparator<BookItem>() {
					@Override
					public int compare(BookItem lhs, BookItem rhs) {
						return rhs.getBookUniqueId() > lhs.getBookUniqueId() ? 1 : -1;
					}
				});
				break;
			}
			case NSoftSettings.SORT_BY_TITLE: {
				Collections.sort(bookList, new Comparator<BookItem>() {
					@Override
					public int compare(BookItem lhs, BookItem rhs) {
						//return rhs.getTitle().compareToIgnoreCase(lhs.getTitle());
						return lhs.getTitle().compareToIgnoreCase(rhs.getTitle());
					}
				});
				break;
			}
			case NSoftSettings.SORT_BY_AUTHOR: {
				Collections.sort(bookList ,new Comparator<BookItem>() {
					@Override
					public int compare(BookItem lhs, BookItem rhs) {
						//return rhs.getAuthorName().compareToIgnoreCase(lhs.getAuthorName());
						return lhs.getAuthorName().compareToIgnoreCase(rhs.getAuthorName());
					}
				});
				break;
			}
		}
		return bookList;
	}
		
	/**
	 * check by name for an EPUB file if it already exists
	 * in the library
	 * 
	 * @param epubName
	 * @return
	 */
	public static boolean checkBookExist(String epubName) {
		return myLibBookList.containsKey(epubName);
	}
	
	/**
	 * get the top most recently read list. 
	 * this list number limit bases on MAX_RECENT_NUMBER
	 * which has default value is 9
	 * 
	 * @return
	 */
	public static List<BookItem> getRecentReadListInArray() {
		List<BookItem> recentList = new ArrayList<BookItem>();

		int recentCount = 0;
		for (BookItem book : myLibBookList.values()) {
			if (book.getVisitedCount() > 0) {
				recentList.add(0, book);
				recentCount++;
			}

			if (recentCount >= Constant.view.MAX_RECENT_NUMBER) {
				break;
			}
		}
		return recentList;
	}
	
//	private static List<String> myLibNameList=new ArrayList<String>();

//	/**
//	 * this function check if the filename is exist or not
//	 * 
//	 * @param fileName
//	 * @return
//	 */
//	public static boolean checkBookExist(String fileName){
//		String fixedName=getFixedName(fileName);
//		return myLibNameList.contains(fixedName);
//	}
//	
//	public static void addMyLibBookName(String fileName){
//		String fixedName=getFixedName(fileName);
//		myLibNameList.add(fixedName);
//	}
//	
//	public static void removeMyLibBookName(String fileName){
//		String fixedName=getFixedName(fileName);
//		myLibNameList.remove(fixedName);
//	}
	
	/**
	 * check if the user which is being displayed is current 
	 * login user or not.
	 * 
	 * @param userId
	 * @return
	 */
	public static boolean isLoggedUser(String userId) {
		return NSoftEngine.loginInfo.getUserInfo() != null &&
			NSoftEngine.loginInfo.getUserInfo().getUserId() != null &&
			NSoftEngine.loginInfo.getUserInfo().getUserId().equals(userId);
	}
	
	/**
	 * get thumb from URL
	 * 
	 * @param bookIndex
	 * @return
	 */
	/*
	public static Bitmap getThumbFromImage(Context ctx,String imagePath){
		//if (onlineBookList.size()==0)
		//	return null;
		
		if (!imagePath.equals(Constant.EMPTY)){
			return NSoftUtils.resizeBitmap(imagePath,Constant.image.WIDTH);
			//return getBitmapFromFile(imagePath);
		}else{
			return BitmapFactory.decodeResource(
					ctx.getResources(),R.drawable.ic_noimage);
		}
	}
	*/
	
	/**
	 * open bitmap directly from local storage
	 * 
	 * @param src
	 * @return
	 */
	/*
	public static Bitmap getBitmapFromFile(String src){
		return BitmapFactory.decodeFile(src);
	}
	*/
	
	/**
	 * this function support getting bitmap from Internet.
	 * this is rarely used since we're not intending download
	 * anything on-the-fly.
	 * 
	 * @param src
	 * @return
	 */
	/*
	public static Bitmap getBitmapFromURL(String src){
		try {
	        URL url=new URL(src);
	        HttpURLConnection connection=
	        		(HttpURLConnection)url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input=connection.getInputStream();
	        Bitmap myBitmap=BitmapFactory.decodeStream(input);
	        return myBitmap;
	    }catch(IOException e){
	    	Log.v("NSoftEngine.getBitmapFromURL()",
	    			e.getClass().toString()+": "+e.getMessage());
	    	return null;
	    }
	}
	*/
	
	/**
	 * this function save a book cover from Internet to local.
	 * size of image will be scaled down for later loading
	 * 
	 * @param src
	 * @param outPath
	 * @return
	 */
	/*
	private static String saveThumbToStorage(String src,String outPath){
		InputStream input=null;
		OutputStream output=null;
		try {
			URL url=new URL(src);
			int lastOfExt=src.lastIndexOf(Constant.PATH_DOT);
			String fileName=src.substring(
						src.lastIndexOf(Constant.PATH_SPLITTER)+1,
						lastOfExt)+Constant.PATH_DOT+Constant.EXT_PNG;
			
			// check and delete the image if it exists
			String outputFilePath=outPath+Constant.PATH_SPLITTER+fileName;
			File fileOutput=new File(outputFilePath);
			if (fileOutput.exists()){
				NSoftUtils.delete(fileOutput);
			}
			
			HttpURLConnection connection=(HttpURLConnection)url.
											openConnection();
			connection.setDoInput(true);
			connection.setConnectTimeout(Constant.sync.SYNC_TIMEOUT);
			connection.connect();

			int retCode=connection.getResponseCode();
			if (retCode==Constant.sync.SYNC_CODE_OK){
				input=connection.getInputStream();
			} else {
				return Constant.EMPTY;
			}

			if (input==null){
				return Constant.EMPTY;
			}

			int cntLength=connection.getContentLength();

			Bitmap bmp=NSoftUtils.createBitmap(input,cntLength);
			output = new FileOutputStream(outputFilePath, false);
			bmp.compress(Bitmap.CompressFormat.PNG, 
						Constant.image.QUALITY,
						output);

			return outputFilePath;
		} catch (IOException e) {
			Log.v("NSoftEngine.saveThumbToStorage()", 
				e.getClass().toString()+": "+e.getMessage());
			return Constant.EMPTY;
		} finally {
			try {
				if (input!=null){
					input.close();
				}
				if (output!=null){
					output.close();
				}
			} catch (IOException e){}
		}
	}
	*/
	
	/**
	 * get the my library book list from XML file
	 * @return
	 * @throws Exception
	 */
	public static boolean getBookListFromXml() throws Exception {
		// clear the previous my library book list
		NSoftEngine.myLibBookList.clear();
//		NSoftEngine.myLibNameList.clear();
		
		// get book list from XML file, and merge with 
		String xmlHistoryFile = NSoftUtils.getMyLibHistoryFile();
		
		// if the history file does not exist yet, then it will be 
		// skipped.
		if (!new File(xmlHistoryFile).exists()) {
			return false;
		}
		
		try{
			BookList bookListContainer = (BookList)NSoftUtils.decodeXml(
						new File(xmlHistoryFile),BookList.class);

			for (BookItem book:bookListContainer.getBookItems()) {
				//NSoftEngine.myLibBookList.put(book.getBookUniqueId(),book);
				NSoftEngine.addMyLibBookItem(book.getFolderName(), book);
			}
	
			return true;
		}catch(Exception e){
			Log.v("getBookListFromXml()", e.getClass().toString()+": "+e.getMessage());
			throw e;
		}
	}
	
	/**
	 * save the my library book list to the XML file
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String saveBookListToXml() throws Exception {
		String xmlFilePath = NSoftUtils.getMyLibHistoryFile();

		try {
			BookList bookListContainer = new BookList();
			bookListContainer.setBookItems(new ArrayList<BookItem>(NSoftEngine.myLibBookList.values()));

			NSoftUtils.encodeXml(bookListContainer,new File(xmlFilePath));

			return xmlFilePath;
		}catch (Exception e){
			Log.v("saveBookListToXml()", e.getClass().toString() + ": " + e.getMessage());
			throw e;
		}
	}
	
	/**
	 * retrieve the category list from XML file
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<Category> getCategoryListFromXml() throws Exception {
		//// clear the previous category list if there is any
		//// there mustn't be any category before the load.
		//NSoftEngine.categoryList.clear();
		
		// get book list from XML file, and merge with 
		String xmlCategoryFile = NSoftUtils.getCategoryFile();
		
		// if the history file does not exist yet, then it will be 
		// skipped.
		if (!new File(xmlCategoryFile).exists()) {
			return new ArrayList<Category>();
		}
		
		try{
			CategoryList categoryListContainer = (CategoryList) NSoftUtils.decodeXml(
							new File(xmlCategoryFile), CategoryList.class);
			//NSoftEngine.categoryList=categoryListContainer.getCategoryItems();

			//return NSoftEngine.categoryList;

			return categoryListContainer.getCategoryItems();
		}catch(Exception e){
			Log.v("getCatListFromXml()", e.getClass().toString() + ": " + e.getMessage());
			throw e;
		}
	}
	
	/**
	 * save category list to XML file
	 * @return
	 * @throws Exception
	 */
	public static String saveCategoryListToXml(List<Category> categoryList) throws Exception {
		String xmlFilePath=NSoftUtils.getCategoryFile();

		try {
			NSoftUtils.encodeXml(new CategoryList(categoryList), new File(xmlFilePath));

			return xmlFilePath;
		}catch (Exception e) {
			Log.v("saveCategoryListToXml()", e.getClass().toString() + ": " + e.getMessage());
			throw e;
		}
	}
		
	/**
	 * get the fixed name of current file name
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFixedName(String filename) {
		String[] subNameParts = filename.toLowerCase().split(Constant.SPACE);
		StringBuffer fixedName=new StringBuffer();

		for (int i = 0; i < subNameParts.length; i++) {
			fixedName.append(subNameParts[i]);

			if (i < subNameParts.length-1)
				fixedName.append(Constant.UNDERSCORE);
		}

		return fixedName.toString();
	}
	
	/**
	 * 
	 * @param context
	 */
	public static LoginInfo getLoginInfo(Context context) {
		LoginInfo loginInfo=new LoginInfo();
		
		SharedPreferences preferences = context.getSharedPreferences(
						Constant.login.LOGIN_INFO, Activity.MODE_PRIVATE);
		User userInfo = new User();
		userInfo.setUserId(preferences.getString(
				Constant.user.USER_ID,Constant.EMPTY));
		userInfo.setUserName(preferences.getString(
				Constant.user.USER_NAME,Constant.EMPTY));
		userInfo.setAccount(preferences.getString(
				Constant.user.USER_ACCOUNT,Constant.EMPTY));
		userInfo.setProviderId(preferences.getInt(
				Constant.user.USER_PROVIDER_ID,Constant.ZERO));
		userInfo.setProviderName(preferences.getString(
				Constant.user.USER_PROVIDER_NAME,Constant.EMPTY));
		userInfo.setServiceId(preferences.getString(
				Constant.user.USER_SERVICE_ID,Constant.EMPTY));
		userInfo.setGroupId(preferences.getInt(
				Constant.user.USER_GROUP_ID,Constant.ZERO));
		userInfo.setUserPortrait(preferences.getString(
				Constant.user.USER_PORTRAIT,Constant.EMPTY));
		userInfo.setUserPortraitSmall(preferences.getString(
				Constant.user.USER_PORTRAIT_SMALL,Constant.EMPTY));
		userInfo.setUserEmail(preferences.getString(
				Constant.user.USER_EMAIL,Constant.EMPTY));
		userInfo.setDateOfBirth(preferences.getString(
				Constant.user.USER_DOB,Constant.EMPTY));
		userInfo.setUserAddress(preferences.getString(
				Constant.user.USER_ADDRESS,Constant.EMPTY));
		userInfo.setJointDate(preferences.getString(
				Constant.user.USER_JOINT_DATE,Constant.EMPTY));
		userInfo.setDescription(preferences.getString(
				Constant.user.USER_DESCRIPTION,Constant.EMPTY));
		userInfo.setActivated(preferences.getInt(
				Constant.user.USER_ACTIVATED,Constant.ZERO));
		userInfo.setPassword(preferences.getString(
				Constant.user.USER_PASSWORD,Constant.EMPTY));
		
		loginInfo.setUserInfo(userInfo);
		
		loginInfo.setLoginStatus(preferences.getInt(
				Constant.login.LOGIN_STATUS,LoginInfo.LOGIN_FAILED));
		
		NSoftEngine.loginInfo = loginInfo;
		return NSoftEngine.loginInfo;
	}
	
	/**
	 * save login information
	 * 
	 * @param context
	 * @param userInfo
	 */
	public static void saveLoginInfo(Context context, User userInfo){
		// save to preferences
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.login.LOGIN_INFO, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(Constant.user.USER_ID,userInfo.getUserId());
		editor.putString(Constant.user.USER_NAME,userInfo.getUserName());
		editor.putString(Constant.user.USER_ACCOUNT,userInfo.getAccount());
		editor.putString(Constant.user.USER_PASSWORD,userInfo.getPassword());
		editor.putInt(Constant.user.USER_PROVIDER_ID,userInfo.getProviderId());
		editor.putString(Constant.user.USER_PROVIDER_NAME,userInfo.getProviderName());
		editor.putString(Constant.user.USER_SERVICE_ID,userInfo.getServiceId());
		editor.putInt(Constant.user.USER_GROUP_ID,userInfo.getGroupId());
		editor.putString(Constant.user.USER_PORTRAIT,userInfo.getUserPortrait());
		editor.putString(Constant.user.USER_PORTRAIT_SMALL,userInfo.getUserPortraitSmall());
		editor.putString(Constant.user.USER_EMAIL,userInfo.getUserEmail());
		editor.putString(Constant.user.USER_DOB,userInfo.getDateOfBirth());
		editor.putString(Constant.user.USER_ADDRESS,userInfo.getUserAddress());
		editor.putString(Constant.user.USER_JOINT_DATE,userInfo.getJointDate());
		editor.putString(Constant.user.USER_DESCRIPTION,userInfo.getDescription());
		editor.putInt(Constant.user.USER_ACTIVATED,userInfo.getActivated());
		editor.putInt(Constant.login.LOGIN_STATUS,LoginInfo.LOGIN_OK);
		editor.commit();
		
		// save to memory
		NSoftEngine.loginInfo = new LoginInfo(userInfo,LoginInfo.LOGIN_OK);
	}

	/**
	 * clear all login information
	 * 
	 * @param context
	 */
	public static void clearLoginInfo(Context context){
		// clear from share preferences
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.login.LOGIN_INFO, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		editor.commit();
		
		// clear from memory
		NSoftEngine.loginInfo = new LoginInfo();
	}

	/**
	 * check the login information whether the user is valid to
	 * use the app or not.
	 * 
	 * @param context
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static boolean checkLogin(Context context, String username, String password) throws Exception {
		// configure the URL
		String server=context.getResources().getString(R.string.server_address);
		String loginUrl=context.getResources().getString(R.string.sync_url_login2);
		
		loginUrl=loginUrl.replace(Constant.sync.SERVER_ADDRESS_TAG,server).
						replace(Constant.login.USERNAME_TAG,username).
						replace(Constant.login.PASSWORD_TAG,password);
		
		String jsonData = IOUtils.toString(new URL(loginUrl));
						//getJSONDataFromHttpsURL(context,loginUrl);
		JSONObject obj = new JSONObject(jsonData);
		boolean isLoginSuccess = obj.has("user_id");
		if (isLoginSuccess) {
			User userInfo = new User();
			userInfo.setUserId(obj.getJSONArray("user_id") != null ?
				obj.getJSONArray("user_id").getString(0) : Constant.EMPTY);
			userInfo.setUserName(obj.getJSONArray("user_name") != null ?
					obj.getJSONArray("user_name").getString(0) : Constant.EMPTY);
			userInfo.setAccount(obj.getJSONArray("account") != null ?
					obj.getJSONArray("account").getString(0) : Constant.EMPTY);
			userInfo.setProviderId(obj.getJSONArray("provider_id") != null ?
					obj.getJSONArray("provider_id").getInt(0) : Constant.ZERO);
			userInfo.setProviderName(obj.getJSONArray("provider_name") != null?
					obj.getJSONArray("provider_name").getString(0) : Constant.EMPTY);
			userInfo.setGroupId(obj.getJSONArray("group_id") != null ?
					obj.getJSONArray("group_id").getInt(0) : Constant.ZERO);
			userInfo.setUserPortrait(obj.getJSONArray("user_portrait") != null?
					obj.getJSONArray("user_portrait").getString(0) : Constant.EMPTY);
			userInfo.setUserPortraitSmall(obj.getJSONArray("user_portrait_small") != null?
					obj.getJSONArray("user_portrait_small").getString(0) : Constant.EMPTY);
			userInfo.setUserEmail(obj.getJSONArray("user_email") != null ?
					obj.getJSONArray("user_email").getString(0) : Constant.EMPTY);
			userInfo.setDateOfBirth(obj.getJSONArray("user_dob") != null ?
					obj.getJSONArray("user_dob").getString(0) : Constant.EMPTY);
			userInfo.setUserAddress(obj.getJSONArray("user_address") != null ?
					obj.getJSONArray("user_address").getString(0) : Constant.EMPTY);
			userInfo.setJointDate(obj.getJSONArray("joint_date") != null ?
					obj.getJSONArray("joint_date").getString(0) : Constant.EMPTY);
			userInfo.setDescription(obj.getJSONArray("user_description") != null ?
					obj.getJSONArray("user_description").getString(0) : Constant.EMPTY);
			userInfo.setActivated(obj.getJSONArray("activated") != null ?
					obj.getJSONArray("activated").getInt(0) : Constant.ZERO);
			userInfo.setPassword(obj.getJSONArray("password") != null ?
					obj.getJSONArray("password").getString(0) : Constant.EMPTY);
			
			// save user information to shared preferences & memory
			saveLoginInfo(context,userInfo);
		}else{
			clearLoginInfo(context);
		}
		
		return isLoginSuccess;
	}
	
	public static void updateLanguage(Context context, int languageId){
		Configuration config = new Configuration();

		switch(languageId){
			case Constant.settings.LANG_ENGLISH: {
				config.locale = new Locale("en");
				break;
			}
			case Constant.settings.LANG_KOREAN: {
				config.locale = new Locale("kr");
				break;
			}
			case Constant.settings.LANG_JAPANESE: {
				config.locale = new Locale("jp");
				break;
			}
			case Constant.settings.LANG_VIETNAMESE: {
				config.locale = new Locale("vn");
				break;
			}
			case Constant.settings.LANG_CHINESE: {
				config.locale = new Locale("cn");
				break;
			}
		}

		Locale.setDefault(config.locale);
		context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
	}
	
	/**
	 * convert provider name to provider Id
	 * 
	 * @param providerName
	 * @return
	 */
	public static int getProviderId(String providerName) {
		if (providerName == Constant.login.PROVIDER_XPUB) {
			return Constant.login.PROVIDER_XPUB_ID;
		}else if (providerName == Constant.login.PROVIDER_FACEBOOK) {
			return Constant.login.PROVIDER_FACEBOOK_ID;
		}else if (providerName == Constant.login.PROVIDER_TWITTER) {
			return Constant.login.PROVIDER_TWITTER_ID;
		}else if (providerName == Constant.login.PROVIDER_LINKEDIN) {
			return Constant.login.PROVIDER_LINKEDIN_ID;
		}else if (providerName == Constant.login.PROVIDER_GOOGLE) {
			return Constant.login.PROVIDER_GOOGLE_ID;
		}else if (providerName == Constant.login.PROVIDER_YAHOO) {
			return Constant.login.PROVIDER_YAHOO_ID;
		}

		return Constant.login.PROVIDER_XPUB_ID;
	}
	
	/**
	 * return the full book view URL
	 * 
	 * @param context
	 * @param bookId
	 * @return
	 */
	public static String getBookViewUrl(Context context, String bookId){
		String server = context.getResources().getString(R.string.server_address);
		// build up URL
		String syncUrl = context.getResources().getString(R.string.sync_url_view_book_url).
					replace(Constant.sync.SERVER_ADDRESS_TAG,server).
					replace(Constant.sync.BOOKID_TAG,bookId);

		return syncUrl;
	}
	
	/**
	 * this function is to get JSON Data from both Http and 
	 * Https protocols by passing through all the certificate 
	 * checking
	 * 
	 * @param context
	 * @param urlStr
	 * @return
	 * @throws Exception
	 */
	/*
	public static String getJSONDataFromHttpsURL(Context context,String urlStr) 
			throws Exception {
		// connect to server
		InputStream is = null;
		try {
			HttpURLConnection httpConn = null;
			URL url = new URL(urlStr);
		    if (url.getProtocol().toLowerCase().equals("https")) {
		        trustAllHosts();
		        HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
		        https.setHostnameVerifier(DO_NOT_VERIFY);
		        httpConn = https;
		    } else {
		    	httpConn = (HttpURLConnection) url.openConnection();
		    }

			httpConn.setConnectTimeout(Constant.sync.SYNC_TIMEOUT);
			httpConn.setDoInput(true);
			httpConn.connect();

			if (httpConn.getResponseCode() == Constant.sync.SYNC_CODE_OK) {
				is = httpConn.getInputStream();
			} else {
				throw new Exception(context.getResources().getString(
						R.string.server_connect_failed));
			}
		} catch (IOException e) {
			throw e;
		}
		if (is == null)
			throw new Exception(context.getResources().getString(
					R.string.server_get_data_failed));

		// read to a string buffer
		String jsonData = Constant.EMPTY;
		try {
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					is, "utf-8"));
			StringBuilder sBuilder = new StringBuilder();

			String line = null;
			while ((line = bReader.readLine()) != null) {
				sBuilder.append(line + "\n");
			}

			is.close();
			jsonData = sBuilder.toString();

			return jsonData;
		} catch (Exception e) {
			throw e;
		} finally {
			if (is != null)
				is.close();
		}

	}
	*/

	/**
	 * to return true on all host
	 */
	/*
	private final static HostnameVerifier DO_NOT_VERIFY=
										new HostnameVerifier(){
		public boolean verify(String hostname,SSLSession session){
			return true;
		}
	};
	*/
	
	/**
	 * this function is to trust all the incoming host on Https protocol
	 */
	/*
	private static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts=new TrustManager[]{
			new X509TrustManager(){
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}
	
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException{}
	
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException{}
			} 
		};

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	/**
	 * this function is to get JSON data from URL over Http protocol
	 * 
	 * @param context
	 * @param url
	 * @return
	 * @throws Exception
	 */
	/*
	private static String getJSONDataFromURL(Context context,String url) 
													throws Exception{
		// connect to server
		InputStream is=null;
		try{
			HttpURLConnection httpConn=(HttpURLConnection)
							new URL(url).openConnection();
			httpConn.setConnectTimeout(Constant.sync.SYNC_TIMEOUT);
			httpConn.setDoInput(true);
			httpConn.connect();
		
			if (httpConn.getResponseCode()==Constant.sync.SYNC_CODE_OK){
				is=httpConn.getInputStream();
			}else{
				throw new Exception(context.getResources().getString(
									R.string.server_connect_failed));
			}
		}catch(IOException e){
			throw e;
		}
		if (is==null)
			throw new Exception(context.getResources().getString(
							R.string.server_get_data_failed));
		
		// read to a string buffer
		String jsonData=Constant.EMPTY;
        try {
            BufferedReader bReader=new BufferedReader(
            				new InputStreamReader(is,"utf-8"));
            StringBuilder sBuilder=new StringBuilder();

            String line=null;
            while ((line=bReader.readLine())!=null){
                sBuilder.append(line+"\n");
            }

            is.close();
            jsonData=sBuilder.toString();
            
            return jsonData;
        }catch (Exception e){
        	throw e;
        }finally{
        	if (is!=null)
        		is.close();
        }
		        
	}
	*/

}
