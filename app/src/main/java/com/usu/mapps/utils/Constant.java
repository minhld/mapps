package com.usu.mapps.utils;

import java.text.SimpleDateFormat;

public class Constant {
	public static final String EMPTY = "";
	public static final String PATH_DOT = ".";
	public static final String PATH_SPLITTER = "/";
	public static final String SPACE = " ";
	public static final String UNDERSCORE = "_";
	public static final String DASH = " - ";
	public static final String SEMICOLON = ";";
	public static final String COLON = ":";
	public static final String ETC = "...";
	public static final String LINEBREAK = "\n";
	public static final String AXE = "@";
	public static final String INTERPUNCT = " · ";
	public static final boolean DEFAULT_BOOL  =  false;
	
	public static final String EXT_BMP = "bmp";
	public static final String EXT_JPG = "jpg";
	public static final String EXT_PNG = "png";
	public static final String EXT_EPUB = "epub";
	
	public static final int INVALID_INDEX = -1;
	public static final int ZERO = 0;
	public static final float FZERO = 0f;
	public static final int DEF_INT = 1;
	public static final String DEFAULT_DATE = "1970-01-01";
	public static final String CHR_REPLACE = "%s%";
	public static final String STATUS_OK = "ok";
	public static final String STATUS_EXCEPTION = "exception";
	public static final String CHARSET_UTF8 = "UTF-8";
	
	public static String[] ACCEPTED_EXT_LIST = { "epub" };
	public static SimpleDateFormat simpleDateFormat =
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	
	public static class sync{
		public static final String SYNC_HTTP = "http";
		public static final int SYNC_HISTORY_BOOKS = 1;
		public static final int SYNC_ONLINE_BOOKS = 0;
		
		public static final int SYNC_FAILED = 1;
		public static final int SYNC_COMPLETED = 0;
		
		public static final int SYNC_DUPLICATED = 100;
		
		public static final int SYNC_TIMEOUT = 15000;
		public static final int SYNC_CODE_OK = 200;
		public static final int SYNC_CODE_OPEN_GALLERY = 101;
		
		public static final String REPLACE_TAG = "/REPLACE_TAG/";
		public static final String SERVER_ADDRESS_TAG = "/SERVER_ADDRESS_TAG/";
		public static final String USERID_TAG = "/USER_ID_TAG/";
		public static final String LOGGED_USERID_TAG = "/LOGGED_USER_ID_TAG/";
		public static final String BOOKID_TAG = "/BOOK_ID_TAG/";
		public static final String STATUSID_TAG = "/STATUS_ID_TAG/";
		public static final String CATEGORY_ID_TAG = "/CATEGORY_ID_TAG/";
		public static final String OFFSET_TAG = "/OFFSET_TAG/";
		public static final String LENGTH_TAG = "/LENGTH_TAG/";
		public static final String BOOKSTANDID_TAG = "/BOOKSTAND_ID_TAG/";
		public static final String CUSTCATID_TAG = "/CUSTCAT_ID_TAG/";
		public static final String PARTNERID_TAG = "/PARTNER_ID_TAG/";
		public static final String TYPE = "/TYPE/";
		
		public static final String PARAM_USER_ID = "user_id";
		public static final String PARAM_BOOK_ID = "book_id";
		public static final String PARAM_OBJECT_ID = "object_id";
		public static final String PARAM_TYPE = "type";
		public static final String PARAM_COMMENT = "comment";
		public static final String PARAM_BOOKSTAND_ID = "folder_id";
		public static final String PARAM_CUSTCAT_ID = "custcat_id";
		public static final String PARAM_UPDATE_TYPE = "type";
		public static final String PARAM_UPDATE_VALUE = "value";
		public static final String BOOK_UPDATE_STATUS = "status";
		public static final String PARAM_BOOKSTAND_NAME = "folder_name";
		public static final String PARAM_BOOKSTAND_COVER = "new_bookstand_img";
		public static final String PARAM_BOOKSTAND_USER_ID = "folder_user_id";
		public static final String PARAM_LIKED = "liked";
		public static final String PARAM_URL = "url";
		
		public static final int SYNC_DEFAULT_OFFSET = 0;
		public static final int SYNC_DEFAULT_LENGTH = 30;
		public static final int SYNC_DEFAULT_LENGTH_WITH_CREATE = SYNC_DEFAULT_LENGTH-1;
		
		public static final int STORE_DEFAULT_OFFSET = 0;
		public static final int STORE_DEFAULT_LENGTH = 30;
		
		public static final int STORE_COMMENT_LENGTH = 5;
		
		public static final String RESPONSE_STATUS = "status";
		public static final String RESPONSE_STATUS_OK = "ok";
		public static final String RESPONSE_STATUS_FAILED = "failed";
		public static final String RESPONSE_MESSAGE = "message";
		public static final String RESPONSE_EXCEPTION = "exception";

		public static final int IS_FRIEND = 1;
		public static final int NOT_FRIEND = 0;
		public static final int LIKE_TYPE_BOOK = 1;
		
		public static final int BOOK_STATUS_UNPUBLISHED = 3;
		public static final int BOOK_STATUS_PUBLISHED = 6;
		
		public static final int TYPE_BOOK = 1;
		public static final int TYPE_BOOKSTAND = 2;
	}
	
	public static class mylib{
		public static final int IMPORT_COMPLETED = 0;
		public static final int IMPORT_FAILED = 1;
		public static final int GRID_LIST_COLUMN_COUNT = 1;
		
		public static final String EPUB_PATH = "epubPath";
		public static final String EPUB_BOOK = "epubBook";
	}
	
	public static class utils{
		public static final String BASE_FOLDER = ".nsoftpub";
		public static final String BASE_BOOKFOLDER = "books";
		public static final String BASE_THUMBFOLDER = "thumbs";
		public static final String BASE_HISTORYFOLDER = "history";
		public static final String MYLIB_HISTORY_FILE = "mylib_history.xml";
		public static final String CATEGORY_FILE = "category.xml";
	}
	
	public static class image{
		public static final int WIDTH = 120;
		public static final int HEIGHT = 160;
		public static final int QUALITY = 95;
	}
	
	public static class login{
		public static String LOGIN_TITLE = "Login";

		public static String LOGIN_INFO = "LOGIN_INFO";
		public static String LOGIN_STATUS = "login_status";
		public static final String LOGIN_PARENT_CLASS = "parent_class";
		public static final String LOGIN_MEMORY_OBJECT = "memory_object";
		public static final String LOGIN_CLEAN_HISTORY = "clean_history";
		
		public static String USERNAME_TAG = "/USERNAME_TAG/";
		public static String PASSWORD_TAG = "/PASSWORD_TAG/";
		public static String LOGIN_RESULT = "login_result";
		public static String LOGIN_STATUS_OK = "OK";
		public static String LOGIN_STATUS_FAILED = "FAILED";
		
		public static String PROVIDER_XPUB = "xpub";
		public static String PROVIDER_FACEBOOK = "facebook";
		public static String PROVIDER_TWITTER = "twitter";
		public static String PROVIDER_LINKEDIN = "linkedin";
		public static String PROVIDER_GOOGLE = "google";
		public static String PROVIDER_YAHOO = "yahoo";
		
		public static int PROVIDER_XPUB_ID = 1;
		public static int PROVIDER_FACEBOOK_ID = 2;
		public static int PROVIDER_TWITTER_ID = 3;
		public static int PROVIDER_LINKEDIN_ID = 4;
		public static int PROVIDER_GOOGLE_ID = 5;
		public static int PROVIDER_YAHOO_ID = 6;
		
		public static String FACEBOOK_IMAGE_URL = "https://graph.facebook.com/"+CHR_REPLACE+"/picture?type = normal";
		public static String FACEBOOK_LARGE_IMAGE_URL = "https://graph.facebook.com/"+CHR_REPLACE+"/picture?type = large";
		
		public static int USER_ACCOUNT_MIN_LENGTH = 6;
		public static int USER_PASSWORD_MIN_LENGTH = 3;
	}
	
	public static class book{
		public static final int BOOKTYPE_REFLOW_EPUB = 1;
		public static final int BOOKTYPE_FIXED_EPUB = 2;
		public static final int BOOKTYPE_NSOFT = 3;
		public static final int BOOKTYPE_PDF = 4;
		public static final int BOOKTYPE_DOCX = 5;
		public static final int BOOKTYPE_HTML = 6;
	}
	
	public static class user{
		public static final String USER_ID = "user_id";
		public static final String USER_NAME = "user_name";
		public static final String USER_ACCOUNT = "account";
		public static final String USER_PROVIDER_ID = "provider_id";
		public static final String USER_PROVIDER_NAME = "provider_name";
		public static final String USER_SERVICE_ID = "service_id";
		public static final String USER_GROUP_ID = "group_id";
		public static final String USER_PORTRAIT = "user_portrait";
		public static final String USER_PORTRAIT_SMALL = "user_portrait_small";
		public static final String USER_EMAIL = "user_email";
		public static final String USER_DOB = "user_dob";
		public static final String USER_ADDRESS = "user_address";
		public static final String USER_JOINT_DATE = "joint_date";
		public static final String USER_DESCRIPTION = "user_description";
		public static final String USER_ACTIVATED = "activated";
		public static final String USER_PASSWORD = "password";
		public static final String USER_LOGGED_USER_ID = "logged_user_id";
		
		
		public static final String TAG_USER_ID = "USER";
		public static final String TAG_FOLLOW_TYPE = "FOLLOW_TYPE";
		
		public static final int FOLLOW_TYPE_FOLLOWER = 1;
		public static final int FOLLOW_TYPE_FOLLOWING = 2;
	}
	
	public static class windows{
		public static int POS_MYLIB_MYLIBRARY = 0;
		public static int POS_MYLIB_MYLIBRARY_IMPORT = 1000;
		public static int POS_MYLIB_RECENTBOOKS = 1;
		public static int POS_CATEGORIES = 2;
		public static int POS_IMPORTFILES = 3;
		public static int POS_MYINFO = 4;
		public static int POS_ABOUT = 5;
		
		public static int MYLIB_TAB_MYLIB = 0;
		public static int MYLIB_TAB_RECENTREAD = 1;
		public static int MYLIB_TAB_UNKNOWN = 999;
		
		public static String MYLIB_RELOAD_LIST = "MYLIB_RELOAD_LIST";
		public static String TAB_INDEX = "TAB_INDEX";
	}
	
	public static class view{
		public static float BOOK_STANDARD_WIDTH = 170;
		public static float MYLIB_BOOK_WIDTH = 120;
		public static float BOOK_SIZE_RATE = 0.75f;
		public static int BOOK_TITLE_HEIGHT = 35;
		public static int BOOK_MARGIN = 3;
		public static int BOOK_IMAGE_MARGIN = 35;
		public static int BOOK_LINE_HEIGHT = 2;
		public static int TOOLBAR_AREA_HEIGHT = 80;
		
		public static int MAX_RECENT_NUMBER = 9;
		
		public static String MYLIB_ACTION = "MYLIB_ACTION";
		public static String ACTION_OPEN_BOOK = "ACTION_OPEN_BOOK";
		public static String MYLIB_BOOKITEM = "BOOKITEM";
		
		public static String ASSET_PREFIX = "android_asset/";
		public static String JAVASCRIPT_MODULE = "jsInterface";
		public static String VIEW_CHAPTER = "chap ";
		public static String VIEW_PAGE = "page ";
		public static String WEBVIEW_EXTRA_LINK = "EXTRA_LINK";
	}
	
	public static class store{
		public static String TAG_BOOKITEM = "BOOKITEM";
		public static String TAG_CATEGORY = "CATEGORY";
		public static String TAG_BOOKSTAND = "BOOKSTAND";
		public static String TAG_USERID = "USER_ID";
		public static String TAG_BOOKID = "BOOK_ID";
		
		public static String TAB_TAG_COMMENTS = "COMMENTS";
		public static String TAB_TAG_LIKES = "LIKES";
		
		public static final String TAG_TAB_POSITION = "TAB_POSITION";
		
		public static float BOOKSTAND_1IMG_RATE = 1.0f;
		public static int MAX_DESC_CHARS = 100;
		
		public static int SLIDEMENU_FONT_SIZE = 18;
	}
	
	public static class _import{
		public static String IMPORT_STATUS = "IMPORT_STATUS";
		public static int IMPORT_OK = 0;
		public static int IMPORT_FAILED = 1;
	}
	
	public static class settings{
		public static final int LANG_ENGLISH = 0;
		public static final int LANG_KOREAN = 1;
		public static final int LANG_JAPANESE = 2;
		public static final int LANG_VIETNAMESE = 3;
		public static final int LANG_CHINESE = 4;
		
		public static final String LANG_CODE_ENGLISH = "en";
		public static final String LANG_CODE_KOREAN = "kr";
		public static final String LANG_CODE_JAPANESE = "jp";
		public static final String LANG_CODE_VIETNAMESE = "vn";
		public static final String LANG_CODE_CHINESE = "cn";
		
		public static final String APP_SETTINGS = "APP_SETTINGS";
		public static final String MYLIB_LIST_STYLE = "MYLIB_LIST_STYLE";
		public static final String MYLIB_SORT_TYPE = "MYLIB_SORT_TYPE";
		public static final String LANGUAGE = "LANGUAGE";
		public static final String SYNTHETIC_SPREAD = "SYNTHETIC_SPREAD";
		public static final String COLUMN_GAP = "COLUMN_GAP";
		public static final String FONT_SIZE = "FONT_SIZE";
		public static final String HARDWARE_ACCELERATOR = "HARDWARE_ACCELERATOR";
		public static final String LOGIN_PARENT_CLASSNAME = "PARENT_CLASSNAME";
		public static final String LOGIN_CLEAN_HISTORY = "CLEAN_HISTORY";
		public static final String LOGIN_REMEMBER_ACCOUNT = "REMEMBER_ACCOUNT";
		public static final String LOGIN_USER  =  "LOGIN_USER";
	}
}
