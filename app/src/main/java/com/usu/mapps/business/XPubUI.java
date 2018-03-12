package com.usu.mapps.business;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nsoft.campus500.R;
import com.nsoft.nsoftview.data.NSoftReceiver;
import com.nsoft.nsoftview.objects.BookItem;
import com.nsoft.nsoftview.objects.Bookstand;
import com.nsoft.nsoftview.objects.Category;
import com.nsoft.nsoftview.objects.Comment;
import com.nsoft.nsoftview.objects.Like;
import com.nsoft.nsoftview.objects.LoginInfo;
import com.nsoft.nsoftview.objects.User;
import com.nsoft.nsoftview.ui.ExtraLinkWebViewActivity;
import com.nsoft.nsoftview.ui.FollowListViewActivity;
import com.nsoft.nsoftview.ui.LoginActivity;
import com.nsoft.nsoftview.ui.ViewActivity;
import com.nsoft.nsoftview.ui.supports.CommentListAdapter;
import com.nsoft.nsoftview.ui.supports.MyLibAdapter;
import com.nsoft.nsoftview.ui.supports.RecentListAdapter;
import com.nsoft.nsoftview.ui.v7.BookInfoActivity_v7;
import com.nsoft.nsoftview.ui.v7.BookstandActivity_v7;
import com.nsoft.nsoftview.ui.v7.BookstandViewActivity_v7;
import com.nsoft.nsoftview.ui.v7.CategoryStoreActivity_v7;
import com.nsoft.nsoftview.ui.v7.MyLibraryActivity_v7;
import com.nsoft.nsoftview.ui.v7.OtherUserProfileActivity_v7;
import com.nsoft.nsoftview.ui.v7.SettingsActivity_v7;
import com.nsoft.nsoftview.ui.v7.StoreActivity_v7;
import com.nsoft.nsoftview.ui.v7.UserListsActivity_v7;
import com.nsoft.nsoftview.utils.Constant;
import com.nsoft.nsoftview.utils.FileExplorerUtils;
import com.nsoft.nsoftview.utils.FileExplorerUtils.FileSelectedListener;
import com.nsoft.nsoftview.utils.LoadImageThread;
import com.nsoft.nsoftview.utils.NSoftEngine;
import com.nsoft.nsoftview.utils.NSoftSettings;
import com.nsoft.nsoftview.utils.NSoftUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * this class handles UI deeper parts which supports for activities
 * and fragments from the main UI.
 * 
 * @author minhld
 *
 */
public class XPubUI {
	
	private static MyLibAdapter myLibAdapter;
	private static RecentListAdapter recentBooksAdapter;
	private static List<Category> menuCatHeaderList;
	private static Map<Integer,List<Category>> menuCatChildList;
	private static List<String> deleteList;
	
	public static boolean updateFlag=false;
	public static boolean deleteStateFlag=false;
	
	/**
	 * this function is called at the beginning of the program to
	 * wake up all parameters and push the application to the ready state
	 * 
	 * @param ctx
	 */
	public static void startUpParams(Context ctx){
		// check the base folder
		NSoftUtils.checkBaseFolder();
		
		// calculate the device metrics
		NSoftUtils.getDeviceMetrics(ctx);
		
		// calculate the metrics of grid of store activities
		// including books and bookstands
		NSoftUtils.retrieveStoreMetrics(ctx);
		
		// register settings context
		NSoftSettings.setContext(ctx);
		
		// get login information and save backup to the global 
		// variable in NSoft Engine
		NSoftEngine.getLoginInfo(ctx);
	}

	public static MyLibAdapter getMyLibAdapter(){
		return myLibAdapter;
	}
	public static void setMyLibAdapter(MyLibAdapter myLibAdapter){
		XPubUI.myLibAdapter = myLibAdapter;
	}
		
	public static RecentListAdapter getRecentBooksAdapter(){
		return XPubUI.recentBooksAdapter;
	}
	public static void setRecentBooksAdapter(
					RecentListAdapter recentBooksAdapter){
		XPubUI.recentBooksAdapter = recentBooksAdapter;
	}
	
	public static List<Category> getMenuCatHeaderList(){
		return menuCatHeaderList;
	}
	public static void setMenuCatHeaderList(
					List<Category> menuCatHeaderList){
		XPubUI.menuCatHeaderList = menuCatHeaderList;
	}

	public static Map<Integer,List<Category>> getMenuCatChildList(){
		return menuCatChildList;
	}
	public static void setMenuCatChildList(
					Map<Integer,List<Category>> menuCatChildList){
		XPubUI.menuCatChildList = menuCatChildList;
	}

	public static List<String> getDeleteList(){
		if (XPubUI.deleteList==null){
			XPubUI.deleteList=new ArrayList<String>();
		}
		return XPubUI.deleteList;
	}
	
	public static void retrieveCategoryList(Context context) throws Exception {
		// try to get category list from memory
		List<Category> categoryList=NSoftEngine.getCategoryListFromXml();
		// category list shouldn't be left null
		if (categoryList!=null && categoryList.size()>0){
			//// save it into memory
			//NSoftEngine.setCategoryList(categoryList);
		}
		
		// if not possible then try to contact server
		if (categoryList==null || categoryList.size()==0){
			categoryList=NSoftReceiver.retrieveCategoryList(context);
			// category list shouldn't be left null
			if (categoryList==null){
				categoryList=new ArrayList<Category>();
			}
			//// save it into memory
			//NSoftEngine.setCategoryList(categoryList);
			// save the backup to file
			NSoftEngine.saveCategoryListToXml(categoryList);
		}
		
		// convert menu category from single layer array into
		// multiple layer array grouping by parent_id 
		rearrangeMenuCategories(categoryList);
	}
	
	/**
	 * this function will convert a single array of category into
	 * a multiple layer array of category by parent_id
	 * 
	 * @return
	 */
	@SuppressLint("UseSparseArrays")
	private static boolean rearrangeMenuCategories(
							List<Category> categoryList){
		//menuCatHeaderList=new ArrayList<Category>();
		Map<Integer,Category> tempoCatHeaderList=
						new TreeMap<Integer, Category>();
		menuCatChildList=new TreeMap<Integer,List<Category>>();
		List<Category> child;
		int parentId;
		for (Category cat:categoryList){
			parentId=cat.getCategory_parent_id();
			if (parentId==Constant.ZERO){
				//menuCatHeaderList.add(cat);
				tempoCatHeaderList.put(cat.getId(),cat);
			}else{
				if (!menuCatChildList.containsKey(parentId)){
					child=new ArrayList<Category>();
					child.add(cat);
					menuCatChildList.put(parentId,child);
				}else{
					child=menuCatChildList.get(parentId);
					child.add(cat);
				}
			}
		}
		menuCatHeaderList=new ArrayList<Category>(
					tempoCatHeaderList.values());
		return true;
	}
	
	/**
	 * open file explorer for importing books into.
	 * 
	 * @param context
	 * @param fileExplorerList
	 * @param fileSelectedListener
	 */
	public static void openFileExplorer(
            Context context, ListView fileExplorerList,
            FileSelectedListener fileSelectedListener){
		FileExplorerUtils.openFileExplorer(context,
					fileExplorerList,fileSelectedListener);
	}
	
	/**
	 * this function to force showing the more menu item
	 * on action bar.<br>
	 * this function is not needed in android 4.4 and higher version.
	 * 
	 * @param window
	 */
	public static void forceShowOverflowMenu(Activity window){
		if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){
			return;
		}
		
		try{
	       ViewConfiguration config= ViewConfiguration.get(window);
	       Field menuKeyField=ViewConfiguration.class.
	    		   		getDeclaredField("sHasPermanentMenuKey");
	       if(menuKeyField!=null){
	           menuKeyField.setAccessible(true);
	           menuKeyField.setBoolean(config, false);
	       }
	   }catch (Exception e){
	       e.printStackTrace();
	   }
	}
	
	/**
	 * this function is to toggle the My Library from grid to list style
	 * 
	 * @param context
	 * @param gridList
	 * @param listAdapter
	 * @return
	 */
	public static int toggleMyLibListStyle(Context context, GridView gridList,
                                           MyLibAdapter listAdapter){
		int listStyle=NSoftSettings.getMyLibListStyle();
		List<BookItem> emptyList=new ArrayList<BookItem>();
		
		int newListStyle;
		
		if (listStyle==NSoftSettings.STYLE_GRID_VIEW){
			newListStyle=NSoftSettings.STYLE_LIST_VIEW;
			// reset the column number to list style (1 item/row)
			gridList.setNumColumns(Constant.mylib.GRID_LIST_COLUMN_COUNT);
			NSoftSettings.setMyLibListStyle(newListStyle);
			// reload the list adapter
			listAdapter=new MyLibAdapter(context,emptyList,newListStyle);
		}else{
			newListStyle=NSoftSettings.STYLE_GRID_VIEW;
			// reset column number to grid style
			int metrics[]=NSoftUtils.getViewMetrics(context);
			gridList.setNumColumns(metrics[0]);
			NSoftSettings.setMyLibListStyle(newListStyle);
			// reload the list adapter
			listAdapter=new MyLibAdapter(context,emptyList,newListStyle);
		}

		
		gridList.setAdapter(listAdapter);
		// save to common UI class for later use
		XPubUI.setMyLibAdapter(listAdapter);
		
		// start retrieving data for my library list
		int sortType=NSoftSettings.getMyLibSortType();
		new MyLibLoadingEngine(context,sortType).start();
		
		return newListStyle;
	}
	
	/**
	 * this will refresh the My Library with current
	 * sort type
	 * 
	 * @param context
	 */
	public static void reloadMyLibBySortType(Context context){
		// get sort type
		int sortType=NSoftSettings.getMyLibSortType();
		
		// start retrieving data for my library list
		// also set RELOAD flag to reload the list
		new MyLibLoadingEngine(context,sortType,true).start();
	}
	
	/**
	 * this will reload the My Library screen with new
	 * sort type indicated
	 * 
	 * @param context
	 * @param sortType
	 */
	public static void reloadMyLibBySortType(Context context,
											int newSortType){
		// set to settings
		NSoftSettings.setMyLibSortType(newSortType);
		
		// start retrieving data for my library list
		// also set RELOAD flag to reload the list
		new MyLibLoadingEngine(context,newSortType,true).start();
	}
	
	/**
	 * this will clean the activity stack and open the My Library
	 * screen without any further action
	 * 
	 * @param context
	 */
	public static void openMyLibWindow(Context context){
		Intent intent=new Intent(context,MyLibraryActivity_v7.class);
		intent.putExtra(Constant.windows.TAB_INDEX,
						Constant.windows.POS_MYLIB_MYLIBRARY);
		context.startActivity(intent);
		// to clean window stack list
		((Activity)context).finish();
	}
	
	/**
	 * this will clean the activity stack and open the My Library
	 * and then open a book from My Library
	 * 
	 * @param context
	 * @param bookItem
	 */
	public static void openMyLibWindow(Context context, BookItem bookItem){
		Intent intent=new Intent(context,MyLibraryActivity_v7.class);
		NSoftUtils.attachNoStackIntent(intent);
		intent.putExtra(Constant.view.MYLIB_ACTION,
						Constant.view.ACTION_OPEN_BOOK);
		
		intent.putExtra(Constant.view.MYLIB_BOOKITEM,bookItem);
		
		context.startActivity(intent);
		// to clean window stack list
		((Activity)context).finish();
	}
	
	/**
	 * this will open My Library screen and import file into 
	 * My Library
	 * 
	 * @param context
	 * @param path
	 * @param type
	 */
	public static void openMyLibWindow(Context context,
                                       String path, String type){
		Intent intent=new Intent(context,MyLibraryActivity_v7.class);
		
		context.startActivity(intent);
		((Activity)context).finish();
	}
	
	public static void openStoreWindow(Context context){
		Intent intent=new Intent(context,StoreActivity_v7.class);
		context.startActivity(intent);
		// to clean the window stack list
		((Activity)context).finish();
	}
	
	public static void openBookstandWindow(Context context){
		Intent intent=new Intent(context,BookstandActivity_v7.class);
		context.startActivity(intent);
		// to clean the window stack list
		((Activity)context).finish();
	}
	
	/**
	 * this will open book view base on current book path
	 * 
	 * @param context
	 * @param bookPath
	 */
	public static void openBookView(Context context,
									BookItem bookItem){
		Intent intent=new Intent(context,ViewActivity.class);
		intent.putExtra(Constant.mylib.EPUB_BOOK,bookItem);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
	
	/**
	 * open window to open extra link from viewer
	 * 
	 * @param context
	 * @param bookItem
	 */
	public static void openExtraLinkViewer(Context context,
									String outterUrl) {
		Intent intent = new Intent(context,
						ExtraLinkWebViewActivity.class);
		intent.putExtra(Constant.view.WEBVIEW_EXTRA_LINK, outterUrl);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}

	
	/**
	 * open settings activity
	 * 
	 * @param context
	 */
	public static void openSettingsWindow(Context context){
		Intent intent=new Intent(context,SettingsActivity_v7.class);
		context.startActivity(intent);
	}
	
	/**
	 * open a book info windows to display related information
	 * of that book
	 * 
	 * @param context
	 * @param bookItem
	 */
	public static void openBookInfoWindow(Context context,
										BookItem bookItem){
		if (bookItem.getBookConfig().equals(Constant.EMPTY)){
			NSoftUtils.showError(context,R.string.app_name,
							R.string.view_open_failed);
			return;
		}
		Intent intent=new Intent(context,BookInfoActivity_v7.class);
		intent.putExtra(Constant.store.TAG_BOOKITEM,bookItem);
		context.startActivity(intent);
	}
	
	/**
	 * open a book list belong to a specified category
	 * 
	 * @param context
	 * @param category
	 */
	public static void openCategoryWindow(Context context,
									Category category){
		Intent intent=new Intent(context,CategoryStoreActivity_v7.class);
		intent.putExtra(Constant.store.TAG_CATEGORY,category);
		context.startActivity(intent);
	}

	/**
	 * open a bookstand view 
	 * 
	 * @param context
	 * @param bookstand
	 */
	public static void openBookstandWindow(Context context,
								Bookstand bookstand){
		Intent intent=new Intent(context,BookstandViewActivity_v7.class);
		intent.putExtra(Constant.store.TAG_BOOKSTAND,bookstand);
		context.startActivity(intent);
	}
	
	/**
	 * this will open the Login screen and go back to the main form
	 * after login successful or failed without any pledge following data 
	 * 
	 * 
	 * @param context
	 * @param parentClassName
	 */
	public static void openLoginWindow(Activity context,
								String parentClassName){
		Intent intent=new Intent(context,LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(Constant.login.LOGIN_PARENT_CLASS,
						parentClassName);
		context.startActivity(intent);
		context.finish();
	}
	
	/**
	 * this will open the Login screen and go back to the 
	 * main form after login get successful or failed with pledge 
	 * following data
	 * 
	 * @param context
	 * @param parentClassName
	 * @param isCleanHistory
	 * @param memObject
	 * 
	 */
	public static void openLoginWindow(Activity context,
								String parentClassName,
								boolean isCleanHistory,
								Serializable memObject){
		Intent intent=new Intent(context,LoginActivity.class);
		if (isCleanHistory){
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		}
		intent.putExtra(Constant.login.LOGIN_PARENT_CLASS,
						parentClassName);
		intent.putExtra(Constant.login.LOGIN_CLEAN_HISTORY,
						isCleanHistory);
		intent.putExtra(Constant.login.LOGIN_MEMORY_OBJECT,
						memObject);
		context.startActivity(intent);
		//context.finish();
	}
	
	/**
	 * logout from current user. all data will be cleared 
	 * 
	 * @param context
	 */
	public static void logout(Context context){
		
		// clear in share preferences & memory
		NSoftEngine.clearLoginInfo(context);
	}
	
	/**
	 * this function is to show the login notification panel
	 * @param activity
	 */
	public static void showLoginNotifyPanel(View view){
		// show/hide the login panel
		final RelativeLayout notifyPanel=(RelativeLayout)
						view.findViewById(R.id.notifyGeneralPanel);
		notifyPanel.setVisibility(NSoftEngine.loginInfo.getLoginStatus()==
					LoginInfo.LOGIN_OK? View.GONE: View.VISIBLE);

		// add button events
		// close button on panel
		((RelativeLayout)view.findViewById(R.id.closeStorePanel)).
				setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v) {
						notifyPanel.setVisibility(View.GONE);
					}
				});
	}
	
	/**
	 * go to the user profile page for displaying their bookstands
	 * and like list
	 * 
	 * @param context
	 */
	public static void openUserProfileDetailsWindow(
            Context context, int tabPosition){
		Intent intent=new Intent(context,UserListsActivity_v7.class);
		intent.putExtra(Constant.store.TAG_TAB_POSITION,tabPosition);
		context.startActivity(intent);
	} 
	
	/**
	 * this will open the user profile window
	 * 
	 * @param context
	 * @param userId
	 */
	public static void openOtherUserProfileWindow(
            Context context, String userId){
		Intent intent=new Intent(context,
						OtherUserProfileActivity_v7.class);
		intent.putExtra(Constant.user.TAG_USER_ID,userId);
		context.startActivity(intent);
	}
	
	/**
	 * open follower/following window
	 * 
	 * @param context
	 * @param userInfo
	 * @param followType
	 */
	public static void openFollowWindow(Context context,
						User userInfo,int followType){
		Intent intent=new Intent(context,FollowListViewActivity.class);
		intent.putExtra(Constant.user.TAG_USER_ID,userInfo);
		intent.putExtra(Constant.user.TAG_FOLLOW_TYPE,followType);
		context.startActivity(intent);
	}
	
	/**
	 * this function is used in book info screen in order to add
	 * a single comment view to the comment list panel
	 * 
	 * @param context
	 * @param owner
	 * @param comment
	 */
	public static void addCommentItem(final Context context,
                                      LinearLayout owner, final Comment comment){
		RelativeLayout commentLayout=(RelativeLayout) View.inflate(
			context,R.layout.store_comment_item,null);
		owner.addView(commentLayout,0);
		// user image
		ImageView image=(ImageView)commentLayout.findViewById(
							R.id.userCommentImage);
		image.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				XPubUI.openOtherUserProfileWindow(context,
								comment.getUserId());
			}
		});
		int imageSize=(int)(context.getResources().getInteger(
							R.integer.store_author_image_width)*
							NSoftUtils.getDensity());
		new LoadImageThread(context,image,comment.getUserPortrait(),
							imageSize,imageSize,true);
		// user name
		TextView userNameText=(TextView)commentLayout.
					findViewById(R.id.usernameCommentText);
		userNameText.setText(comment.getUserName());
		// post date
		TextView postDateText=(TextView)commentLayout.
						findViewById(R.id.timeCommentText);
		postDateText.setText(comment.getCommentTime());
		// comment
		TextView commentText=(TextView)commentLayout.
						findViewById(R.id.commentText);
		commentText.setText(comment.getComment());
	}

	/**
	 * add a single like to the list view
	 * 
	 * @param context
	 * @param owner
	 * @param like
	 */
	public static void addLikeItem(final Context context,
                                   LinearLayout owner, final Like like){
		RelativeLayout likeLayout=(RelativeLayout) View.inflate(
				context,R.layout.store_like_short_item,null);
		owner.addView(likeLayout);
		if (owner.getLayoutParams().height==0){
			owner.getLayoutParams().height=(int)context.getResources().
					getDimension(R.dimen.bookinf_like_container_height);
		}
		// user image
		ImageView image=(ImageView)likeLayout.findViewById(
							R.id.userLikeImage);
		image.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				XPubUI.openOtherUserProfileWindow(context,like.getUserId());
			}
		});
		int imageSize=(int)(context.getResources().getInteger(
						R.integer.store_author_image_width)*
						NSoftUtils.getDensity());
		new LoadImageThread(context,image,like.getUserPortrait(),
						imageSize,imageSize,true);
	}

	/**
	 * this function is called after user leaves a comment
	 * to add comment to the fragment
	 * 
	 * @param context
	 * @param commentList
	 * @param userId
	 * @param bookId
	 * @param comment
	 */
	public static void submitComment(Context context,
                                     LinearLayout commentList, String userId,
                                     String bookId, String comment){
		// new add comment task
		new SubmitTaskEngine(context,commentList,
				userId,bookId,comment).execute();
	}

	/**
	 * submit a comment to post it on either more list panel 
	 * and comment fragment
	 * 
	 * @param context
	 * @param commentList
	 * @param viewList
	 * @param userId
	 * @param bookId
	 * @param comment
	 * @param postOnSocial
	 * @param items
	 * 
	 */
	public static void submitComment(Context context,
                                     CommentListAdapter commentList,
                                     LinearLayout viewList,
                                     String userId, String bookId,
                                     String comment,
                                     boolean postOnSocial,
                                     Object... items){
		// new add comment task
		new SubmitTaskEngine(context,commentList,viewList,
				userId,bookId,comment,postOnSocial,items).
				execute();
	}

	/**
	 * submit a new like to this book
	 * 
	 * @param context
	 * @param likeList
	 * @param userId
	 * @param bookId
	 * @param bookstandId
	 */
	public static void submitLike(Context context,
					LinearLayout likeList,
					final ImageView likeButtonIcon,
					String userId,
					String bookId,
					boolean isLiked,
					boolean isUpdateList){
		// add new like
		SubmitTaskEngine submitEngine=new SubmitTaskEngine(
								context,likeList,userId,bookId,
								isLiked,isUpdateList);
		submitEngine.setSubmitTaskHandler(
					new SubmitTaskEngine.SubmitTaskHandler(){
			@Override
			public void submitTaskDone(Object... object){
				boolean isLiked=(Boolean)object[0];
				likeButtonIcon.setImageResource(isLiked?
						R.drawable.ic_liked:R.drawable.ic_like_big);
				likeButtonIcon.setTag(isLiked);
			}
		});
		submitEngine.execute();
	}
	
	/**
	 * check whether current user likes this book or not
	 * 
	 * @param context
	 * @param iconImage
	 * @param userId
	 * @param bookId
	 */
	public static void checkLiked(Context context,
                                  final RelativeLayout likeButton,
                                  final ImageView iconImage,
                                  String userId, String bookId){
		SubmitTaskEngine taskEngine=new SubmitTaskEngine(
							context,userId,bookId);
		taskEngine.setSubmitTaskHandler(
				new SubmitTaskEngine.SubmitTaskHandler() {
			@Override
			public void submitTaskDone(Object... object) {
				boolean retVal=(Boolean)object[0];
				//likeButton.setEnabled(!retVal);
				iconImage.setImageResource(retVal?
						R.drawable.ic_liked:R.drawable.ic_like_big);
				iconImage.setTag(retVal);
			}
		});
		// don't use progress icon
		taskEngine.useProgressIcon(false);
		taskEngine.execute();
	}
	
	/**
	 * this function is called when user opens a book to briefly see
	 * its contents
	 * 
	 * @param context
	 * @param userId
	 * @param bookId
	 * @param contentType
	 */
	public static void userVisited(Context context, String userId,
                                   String bookId, int contentType){
		SubmitTaskEngine userVisitProc=new SubmitTaskEngine(context,
									userId,bookId,contentType);
		userVisitProc.setSubmitTaskHandler(
				new SubmitTaskEngine.SubmitTaskHandler(){
			@Override
			public void submitTaskDone(Object... object){
				// to do
			}
		});
		userVisitProc.execute();
	}
	
	/**
	 * confirm to download this book to local
	 * 
	 * @param context
	 * @param bookItem
	 */
	public static void downloadBook(final Context context,
									final BookItem bookItem){
		AlertDialog.Builder alert=new AlertDialog.Builder(context);
		alert.setIcon(R.drawable.ic_alert);
		alert.setTitle(R.string.dialog_confirm)
				.setMessage(R.string.store_confirm_download)
				.setPositiveButton(R.string.dialog_yes,
					new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							new BookDownloader(context,bookItem).
								execute();
						}
					})
				.setNegativeButton(R.string.dialog_no,
					new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							dialog.cancel();
						}
					}).
				create().show();
	}
	
	/**
	 * this function leads to the screen that previously called the
	 * Login screen along with the memory object.
	 * 
	 * @param context
	 * @param parentClassName
	 * @param cleanHistory
	 * @param memoryObject
	 * 
	 * @throws Exception
	 */
	public static void goBackParentFromLogin(Activity context,
                                             String parentClassName, boolean cleanHistory,
                                             Serializable memoryObject) {
		try{
			Intent intent=new Intent(context, Class.forName(parentClassName));
			if (cleanHistory){
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
						(NSoftUtils.isLowVersion()?
								Intent.FLAG_ACTIVITY_NO_HISTORY:
								Intent.FLAG_ACTIVITY_CLEAR_TASK));
			}
			if (memoryObject!=null){
				intent.putExtra(Constant.login.LOGIN_MEMORY_OBJECT,
								memoryObject);
			}
			context.startActivity(intent);
			context.finish();
		}catch(Exception e){
			Log.v("XPubUI.goBackParentScreen", e.getMessage());
		}
	}
	
	/**
	 * open the gallery and select an image from gallery
	 * 
	 * @param context
	 */
	public static void openImageDialog(Context context){
		Intent intent=new Intent(Intent.ACTION_PICK,
						Media.EXTERNAL_CONTENT_URI);
		((Activity)context).startActivityForResult(intent,
					Constant.sync.SYNC_CODE_OPEN_GALLERY);
	}
	
	/**
	 * get category name specified by language
	 * 
	 * @param cat
	 * @param langId
	 * @return
	 */
	public static String getMenuItemTextByLanguage(Category cat, int langId){
		if (langId==Constant.settings.LANG_ENGLISH){
			return cat.getCategory_name();
		}else if (langId==Constant.settings.LANG_KOREAN){
			return cat.getCategory_name_kr();
		}else if (langId==Constant.settings.LANG_JAPANESE){
			return cat.getCategory_name_jp();
		}else if (langId==Constant.settings.LANG_VIETNAMESE){
			return cat.getCategory_name_vn();
		}else if (langId==Constant.settings.LANG_CHINESE){
			return cat.getCategory_name_cn();
		}
		return cat.getCategory_name();
	}
}
