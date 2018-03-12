package com.usu.mapps.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.nsoft.campus500.R;
import com.nsoft.nsoftview.business.ILoadingEngine;
import com.usu.mapps.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class NSoftUtils {
	private static DisplayMetrics metrics;
	private static int[] viewMetrics;
	private static int[] storeMetrics;
	
	private static String externalPath=Constant.EMPTY;
	private static String fullBasePath=Constant.EMPTY;
	private static String bookPath=Constant.EMPTY;
	private static String thumbPath=Constant.EMPTY;
	private static String historyPath=Constant.EMPTY;
	private static String myLibHistoryFile=Constant.EMPTY;
	private static String categoryFile=Constant.EMPTY;
	
	/**
	 * this function submits a POST request and return a piece 
	 * of JSON data
	 * 
	 * @param postUrl
	 * @param valuePairs
	 * @return
	 * @throws Exception
	 */
	public static String submitPost(String postUrl,
                                    List<NameValuePair> valuePairs) throws Exception {
		String retJsonData=Constant.EMPTY;
		
	    HttpClient httpClient=new DefaultHttpClient();
	    HttpPost httpPost=new HttpPost(postUrl);
	    httpPost.setEntity(new UrlEncodedFormEntity(
	    				valuePairs,Constant.CHARSET_UTF8));
	    HttpResponse response=httpClient.execute(httpPost);
	    BufferedReader reader=new BufferedReader(
	    		new InputStreamReader(response.getEntity().
						getContent(),Constant.CHARSET_UTF8));
	    StringBuilder builder=new StringBuilder();
	    for (String line = null; (line=reader.readLine())!=null;){
	        builder.append(line).append(Constant.LINEBREAK);
	    }
	    retJsonData=builder.toString();
		return retJsonData;
	}
	
	/**
	 * this function submits a POST request to create a folder
	 * and return a piece of JSON data
	 * 
	 * @param postUrl
	 * @param valuePairs
	 * @return
	 * @throws Exception
	 */
	public static String submitBookstandCreate(String postUrl,
                                               String title, File coverFile) throws Exception {
		String retJsonData=Constant.EMPTY;
		HttpClient httpClient=new DefaultHttpClient();
	    HttpPost httpPost=new HttpPost(postUrl);
	    MultipartEntityBuilder entityBuilder= MultipartEntityBuilder.create();
	    entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
	    entityBuilder.addPart(Constant.sync.PARAM_BOOKSTAND_COVER,
	    					new FileBody(coverFile));
	    entityBuilder.addTextBody(Constant.sync.PARAM_BOOKSTAND_NAME,title);
	    
	    // assume user has logged
	    // user can't create bookstand without logging in
	    String userId=NSoftEngine.loginInfo.getUserInfo().getUserId();
	    entityBuilder.addTextBody(Constant.sync.PARAM_BOOKSTAND_USER_ID,userId);
	    httpPost.setEntity(entityBuilder.build());
	    
	    HttpResponse response=httpClient.execute(httpPost);
	    BufferedReader reader=new BufferedReader(
	    		new InputStreamReader(response.getEntity().
						getContent(),Constant.CHARSET_UTF8));
	    StringBuilder builder=new StringBuilder();
	    for (String line = null; (line=reader.readLine())!=null;){
	        builder.append(line).append(Constant.LINEBREAK);
	    }
	    retJsonData=builder.toString();
		return retJsonData;
	}
	
	/**
	 * check if a device is in portrait mode
	 * 
	 * @param context
	 * @return
	 */
	public static boolean inPortrait(Context context){
		return context.getResources().getConfiguration().orientation==
				Configuration.ORIENTATION_PORTRAIT;
	}
	
	/**
	 * get view metrics for (almost) my library activity<br><br>
	 * 0. number of item in horizontal direction<br>
	 * 1. width of item in horizontal direction<br>
	 * 2. height of item in horizontal direction<br>
	 * 4. width of device<br>
	 * 5. height of device<br>
     * 
	 * @return
	 */
	public static int[] getViewMetrics(Context context){
		int[] modViewMetrics=new int[5];
		if (NSoftUtils.inPortrait(context)){
			modViewMetrics[0]=NSoftUtils.viewMetrics[0];
			modViewMetrics[1]=NSoftUtils.viewMetrics[1];
			modViewMetrics[2]=NSoftUtils.viewMetrics[2];
			modViewMetrics[3]=NSoftUtils.viewMetrics[6];
			modViewMetrics[4]=NSoftUtils.viewMetrics[7];
		}else{
			modViewMetrics[0]=NSoftUtils.viewMetrics[3];
			modViewMetrics[1]=NSoftUtils.viewMetrics[4];
			modViewMetrics[2]=NSoftUtils.viewMetrics[5];
			modViewMetrics[3]=NSoftUtils.viewMetrics[7];
			modViewMetrics[4]=NSoftUtils.viewMetrics[6];
		}
		
		return modViewMetrics;
	}
	
	/**
	 * get metrics of store item including book item and 
	 * bookstand item. the metrics are
	 * 0. number of item in portrait orientation
	 * 1. number of item in landscape orientation
	 * 2. size of item in portrait mode
	 * 3. size of item in landscape mode
	 * 4. 
	 * 
	 * @return
	 */
	public static int[] getStoreMetrics(){
		return NSoftUtils.storeMetrics;
	}
	
	/**
	 * check if the android version is lower than 3.0 or not
	 * 
	 * @return
	 */
	public static boolean isLowVersion(){
		return Build.VERSION.SDK_INT< Build.VERSION_CODES.HONEYCOMB;
	}
	
	/**
	 * check if OS is Kitkat or not
	 * 
	 * @return
	 */
	public static boolean isKitkat(){
		return Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT;
	}
	
	/**
	 * check if the network is available on the device
	 * 
	 * @return
	 */
	public static boolean isOnline(Context context){
	    ConnectivityManager cm = (ConnectivityManager)context.
	    			getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    return (netInfo != null && netInfo.isConnectedOrConnecting());
	}
	
	public static void printToast(Context context, int resId){
		Toast newToast= Toast.makeText(context,resId, Toast.LENGTH_LONG);
		newToast.setDuration(1000);
		newToast.show();
	}
	
	/**
	 * 
	 * 
	 * @param context
	 * @param titleId
	 * @param msgId
	 * @param loadEngine
	 */
	public static void showConfirm(Context context, int titleId, int msgId,
                                   final ILoadingEngine loadEngine){
		AlertDialog.Builder alert=new AlertDialog.Builder(context);
		alert.setTitle(titleId).setMessage(msgId).
		setPositiveButton(R.string.dialog_yes,
		new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try{
					loadEngine.execute();
				}catch(Exception e){
					Log.v("NsoftUtil - loading error",
						e.getClass()+": "+e.getMessage());
				}
			}
		}).
		setNegativeButton(R.string.dialog_no,
		new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(
					DialogInterface dialog,
					int which){
				dialog.cancel();
			}
		}).
		create().show();
	}
	
	/**
	 * show error message
	 * 
	 * @param context
	 * @param titleId
	 * @param msgId
	 */
	public static void showError(Context context, int titleId, int msgId){
		AlertDialog.Builder alert=new AlertDialog.Builder(context);
		alert.setIcon(R.drawable.ic_alert);
		alert.setTitle(titleId).setMessage(msgId).
			setPositiveButton(R.string.dialog_ok,
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(
							DialogInterface dialog,
							int which) {
						dialog.cancel();
					}
				}).create().show();
	}

	/**
	 * show dialog that displays information
	 * 
	 * @param parent
	 * @param title
	 * @param msg
	 */
	public static void showInfo(Context ctx, int titleId, String msg){
		AlertDialog.Builder alert=new AlertDialog.Builder(ctx);
		alert.setIcon(R.drawable.ic_alert);
		alert.setTitle(titleId).setMessage(msg).
			setPositiveButton(R.string.dialog_ok,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.cancel();
					}
				}).create().show();
	}
	
	/**
	 * show dialog that displays information
	 * 
	 * @param parent
	 * @param title
	 * @param msg
	 */
	public static void showInfo(Context ctx, int titleId, int msgId){
		AlertDialog.Builder alert=new AlertDialog.Builder(ctx);
		alert.setIcon(R.drawable.ic_alert);
		alert.setTitle(titleId).setMessage(msgId).
			setPositiveButton(R.string.dialog_ok,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.cancel();
					}
				}).create().show();
	}
	
	/**
	 * this function will attach the feature that enhance an Intent
	 * so that activity it is pointing to will have no tail activity stack.
	 * 
	 * @param intent
	 */
	@SuppressLint("InlinedApi")
	public static void attachNoStackIntent(Intent intent){
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
				(NSoftUtils.isLowVersion()?
						Intent.FLAG_ACTIVITY_NO_HISTORY:
						Intent.FLAG_ACTIVITY_CLEAR_TASK));
	}
	
	public static String unzipFile2(String zipFilePath,
                                    String outputFolder) throws Exception {
		byte[] buffer=new byte[1024];
		
		try{
			File folder=new File(outputFolder);
			if (folder.exists()){
				NSoftUtils.delete(folder);
			}
			folder.mkdir();
			
		   	//if(!folder.exists()){
		   	//	folder.mkdir();
		   	//}
		
		   	File zipFile=new File(zipFilePath);
		   	ZipInputStream zis=new ZipInputStream(
		   				new FileInputStream(zipFile));
		   	ZipEntry ze=zis.getNextEntry();
		   	String outputFile=Constant.EMPTY;
		   	while(ze!=null){
		   		// only process file - skip folder
		   		if (ze.isDirectory()){
		   			ze=zis.getNextEntry();
		   			continue;
		   		}
		   		String fileName=ze.getName();
		   		outputFile=outputFolder+ File.separator+fileName;
		   		File newFile=new File(outputFile);
		        new File(newFile.getParent()).mkdirs();
		        FileOutputStream fos=new
                        FileOutputStream(newFile);
		
		        int len;
		        while ((len=zis.read(buffer))>0){
		        	fos.write(buffer,0,len);
		        }
		        fos.close();   
		        ze=zis.getNextEntry();
		   	}
		   	zis.closeEntry();
		   	zis.close();
		   	
		   	String zipFileNameNoExt=zipFile.getName();
		   	int extOffs=zipFileNameNoExt.lastIndexOf(Constant.PATH_DOT);
		   	zipFileNameNoExt=zipFile.getName().substring(0,extOffs);
		   	
		   	return outputFolder;
		}catch(IOException e){
			Log.e("NsoftUtils.unzipFile()",e.getClass()+": "+e.getMessage());
    		throw e;
		}
	}
	
	/**
	 * unzip a file
	 * 
	 * @param c
	 * @param zipFile
	 * @param outputFolder
	 * @throws IOException
	 */
	/*
    public static void unzipFile(String zipFile,String outputFolder) 
    										throws IOException{
    	
    	byte[] buffer=new byte[1024];
        
    	try{
    		File folder=new File(outputFolder);
	       	if(!folder.exists()){
	       		folder.mkdir();
	       	}
    
	       	ZipInputStream zis=new ZipInputStream(
	       					new FileInputStream(zipFile));
	       	ZipEntry ze=zis.getNextEntry();
	       	String outputFile=Constant.EMPTY;
	       	while(ze!=null){
	       		String fileName=ze.getName();
	       		outputFile=outputFolder+File.separator+fileName;
	       		File newFile=new File(outputFile);
	            new File(newFile.getParent()).mkdirs();
	            FileOutputStream fos=new FileOutputStream(newFile);             
	
	            int len;
	            while ((len=zis.read(buffer))>0){
	            	fos.write(buffer,0,len);
	            }
	            fos.close();   
	            
	            ze=zis.getNextEntry();
	       	}
	       	zis.closeEntry();
	       	zis.close();
   
    	}catch(IOException e){
    		Log.e("NsoftUtils.unzipFile()",e.getClass()+": "+e.getMessage());
    		throw e;
    	}
    }
    */
	
	/**
	 * returns the extension of an input file. the <i>filename</i>
	 * can be full name or just the file name
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileExt(String filename){
		int offs=filename.lastIndexOf(".");
		return filename.substring(offs+1).toLowerCase();
	}
	
	/**
     * delete an non-empty folder (recursively)
     * @param file
     * @throws IOException
     */
    public static void delete(File file)
    			throws IOException {
     	if(file.isDirectory()){
     		if(file.list().length==0){
     			file.delete();
    		}else{
    			String files[]=file.list();
    			for (String temp:files){
    				File fileDelete=new File(file,temp);
    				delete(fileDelete);
    			}

    			if(file.list().length==0){
    				file.delete();
    			}
    		}
     	}else{
    		file.delete();
    	}
    }
    
    /**
     * make a vibration in a short time
     * 
     * @param c
     * @param duration
     */
    public static void vibrate(Context c, int duration){
    	Vibrator vibrator = (Vibrator)c.getSystemService(
    					Context.VIBRATOR_SERVICE);
    	vibrator.vibrate(duration);
    }
    
    /**
     * get device metric after calculation
     * 
     * @return
     */
    public static DisplayMetrics getDeviceMetrics(){
    	return NSoftUtils.metrics;
    }
    
    /**
     * get device metrics<br>
     * this function works for all devices 
     * 
     * @param c
     * @return
     */
    public static DisplayMetrics getDeviceMetrics(Context c){
    	if (NSoftUtils.metrics==null){
    		NSoftUtils.metrics=new DisplayMetrics();
	    	if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
	    		((Activity)c).getWindowManager().getDefaultDisplay().
								getRealMetrics(NSoftUtils.metrics);
	    	}else{
	    		NSoftUtils.metrics=new DisplayMetrics();
	    		((Activity)c).getWindowManager().getDefaultDisplay().
								getMetrics(NSoftUtils.metrics);
	    	}
    	}
    	
    	return NSoftUtils.metrics;
    }
    
    /**
     * get device's density in integer
     * 
     * @return
     */
    public static float getDensity(){
    	return 2;//NSoftUtils.metrics.density;
    }
    
    /**
	 * 
	 * @param bmpPath
	 * @return
	 * @throws IOException
	 */
    /*
	public static Bitmap createBitmap(String bmpPath) throws IOException {
		return resizeBitmap(bmpPath,Constant.image.WIDTH);
	}
	*/
	
    /*
	public static Bitmap resizeBitmap(String imagePath,int targetW) {
	    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
	    bmOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(imagePath, bmOptions);
	    int photoW = bmOptions.outWidth;
	    //int photoH = bmOptions.outHeight;

	    int scaleFactor = 1;
	    if (targetW > 0) {
	            scaleFactor = photoW/targetW;        
	    }

	    bmOptions.inJustDecodeBounds = false;
	    bmOptions.inSampleSize = scaleFactor;
	    bmOptions.inPurgeable = true;

	    return BitmapFactory.decodeFile(imagePath, bmOptions);            
	}
	*/
	
    /**
     * create a bitmap from an input stream (probably from
     * Internet) and resize the bitmap to thumb size.
     * @param is
     * @param length
     * @return
     * @throws Exception
     */
	/*
	public static Bitmap createBitmap(InputStream is,
						int length) throws IOException{
		BufferedInputStream bis=null;
		
		try{
			bis=new BufferedInputStream(is);
			if (bis.markSupported()){
				int contentLength=length;
				bis.mark(contentLength);
			}
		
			BitmapFactory.Options o=new BitmapFactory.Options();
			o.inJustDecodeBounds=true;
			BitmapFactory.decodeStream(bis,null,o);
			if (bis.markSupported()){
				bis.reset();
			}
		
			int scale=1;
			while(o.outWidth/scale/2>=Constant.image.WIDTH)
				scale*=2;
		
			BitmapFactory.Options o2=new BitmapFactory.Options();
			o2.inSampleSize=scale;
			o2.inPurgeable=true;
			
			return BitmapFactory.decodeStream(bis,null,o2);
		}catch(IOException ex){
			Log.e("utils.createBitmap()",
				ex.getClass()+": "+ex.getMessage());
			throw ex;
		}finally{
			if (bis!=null){
				bis.close();
			}
		}
	}
	*/
	
	/**
	 * get external SD card path
	 * @return
	 */
	public static String getExternalStoragePath(){
		return externalPath;
	}
	
	/**
	 * get path of application's base folder
	 * @return
	 */
	public static String getFullBasePath(){
		if (fullBasePath.equals(Constant.EMPTY)){
			checkBaseFolder();
		}
		return fullBasePath;
	}
	
	/**
	 * get book sub path in application's base folder
	 * @return
	 */
	public static String getBookPath(){
		if (bookPath.equals(Constant.EMPTY)){
			checkBaseFolder();
		}
		return bookPath;
	}
	
	/**
	 * get thumb sub path in application's base folder
	 * @return
	 */
	public static String getThumbPath(){
		if (thumbPath.equals(Constant.EMPTY)){
			checkBaseFolder();
		}
		return thumbPath;
	}

	public static boolean checkBaseFolder(){
		// check if full base path exists. if it really exists
		// then quit the check and return OK.
		if (!fullBasePath.equals(Constant.EMPTY)){
			return true;
		}
		boolean isFolderCreated=false;
		
		externalPath= Environment.getExternalStorageDirectory().
					getAbsolutePath()+Constant.PATH_SPLITTER;
		fullBasePath=externalPath+Constant.utils.BASE_FOLDER;
		File p=new File(fullBasePath);
		if (!p.exists()){
			p.mkdir();
			isFolderCreated=true;
		}
		bookPath=fullBasePath+Constant.PATH_SPLITTER+
					Constant.utils.BASE_BOOKFOLDER;
		p=new File(bookPath);
		if (!p.exists()){
			p.mkdir();
			isFolderCreated=true;
		}
		historyPath=fullBasePath+Constant.PATH_SPLITTER+
					Constant.utils.BASE_HISTORYFOLDER;
		p=new File(historyPath);
		if (!p.exists()){
			p.mkdir();
			isFolderCreated=true;
		}
		thumbPath=historyPath+Constant.PATH_SPLITTER+
					Constant.utils.BASE_THUMBFOLDER;
		p=new File(thumbPath);
		if (!p.exists()){
			p.mkdir();
			isFolderCreated=true;
		}
		return !isFolderCreated;
	}
	
	/**
	 * this function is to check the my library history file
	 * 
	 * @return
	 */
	public static String getMyLibHistoryFile(){
		if (historyPath.equals(Constant.EMPTY)){
			checkBaseFolder();
		}
		myLibHistoryFile=NSoftUtils.historyPath+Constant.PATH_SPLITTER+
						Constant.utils.MYLIB_HISTORY_FILE;
		return myLibHistoryFile;
	}
	
	/**
	 * check path of category file
	 * @return
	 */
	public static String getCategoryFile(){
		if (historyPath.equals(Constant.EMPTY)){
			checkBaseFolder();
		}
		categoryFile=NSoftUtils.historyPath+Constant.PATH_SPLITTER+
						Constant.utils.CATEGORY_FILE;
		return categoryFile;
	}
	
	public static boolean encodeXml(Object o, File f) throws Exception {
		Persister persister=new Persister();
		persister.write(o,f);
		return true;
	}

	public static Object decodeXml(File f, Class<?> c) throws Exception {
		Persister persister=new Persister();
		return persister.read(c,f);
	}

	/**
	 * return a random color in integer format
	 * 
	 * @return
	 */
	public static int getRandomColor(){
		Random rand=new Random();
		int r=rand.nextInt(255);
		int g=rand.nextInt(255);
		int b=rand.nextInt(255);
		return Color.rgb(r,g,b);
	}

	/**
	 * this function is to get the column number in portrait & 
	 * landscape modes. this also supports to get column width
	 * in both modes.
	 * 
	 * @param context
	 * 
	 * @return
	 */
	public static int[] retrieveStoreMetrics(Context context){
		int[] colNums=new int[5];
		int width=0, height=0;
		/*
		if (NSoftUtils.inPortrait(context)){
			width=NSoftUtils.metrics.widthPixels;
			height=NSoftUtils.metrics.heightPixels;
		}else{
			width=NSoftUtils.metrics.heightPixels;
			height=NSoftUtils.metrics.widthPixels;
		}
		*/
		boolean inPortrait=NSoftUtils.metrics.widthPixels<
								NSoftUtils.metrics.heightPixels;
		width=inPortrait?NSoftUtils.metrics.widthPixels:
							NSoftUtils.metrics.heightPixels;
		height=inPortrait?NSoftUtils.metrics.heightPixels:
							NSoftUtils.metrics.widthPixels;
		float density=NSoftUtils.metrics.density;
		
		// ------ update store metrics ------
		// number of column in portrait (shouldn't be less than 2)
		colNums[0]=(int) Math.floor(((double)width/(double)density)/
					(double)Constant.view.BOOK_STANDARD_WIDTH);
		if (colNums[0]<2) colNums[0]=2; 
		// number of column in landscape
		colNums[1]=(int) Math.floor(((double)height/(double)density)/
					(double)Constant.view.BOOK_STANDARD_WIDTH);
		if (colNums[1]<2) colNums[1]=2; 
		
		// width of item in pixel in portrait
		colNums[2]=(int) Math.floor((double)width/(double)colNums[0]);
		// height of item in pixel in landscape
		colNums[3]=(int) Math.floor((double)height/(double)colNums[1]);
		colNums[4]=(int)(colNums[2]*
				Constant.store.BOOKSTAND_1IMG_RATE);
		NSoftUtils.storeMetrics=colNums;
		
    	// ------ update my-library metrics ------
		int[] size=new int[8];
    	// number of column in portrait
		size[0]=(int) Math.floor(((double)width/(double)density)/
				(double)Constant.view.MYLIB_BOOK_WIDTH);
		// width of item in pixel in portrait
		size[1]=(int) Math.floor((double)width/(double)size[0]);
		// height of item in pixel in portrait
    	size[2]=(int)(size[1]/Constant.view.BOOK_SIZE_RATE);
    	
    	// number of column in landscape
		size[3]=(int) Math.floor(((double)height/(double)density)/
				(double)Constant.view.MYLIB_BOOK_WIDTH)-1;
		// width of item in pixel in landscape
    	size[4]=(int) Math.floor((double)height/(double)size[3]);
		// height of item in pixel in portrait
    	size[5]=(int)(size[4]/Constant.view.BOOK_SIZE_RATE);
    	
    	// width of device
    	size[6]=width;
    	// height of device
    	size[7]=height;

    	NSoftUtils.viewMetrics=size;

		return colNums;
	}
	
	/**
	 * convert a number to semantic string<br>
	 * for example: 5, 10, etc... <br>
	 * and 1,530 -> 1.5K or 1,530,250 -> 1.5M etc... 
	 * 
	 * @param num
	 * @return
	 */
	public static String convertNumber(int num){
		if (num < 1000){
			return Integer.toString(num);
		}else if (num >= 1000 && num < 1000000){
			if (num % 1000 == 0){
				return num/1000+"K";
			}else{
				return Math.round(num*10/1000)/10+"K";
			}
		}else if (num >= 1000000 && num < 1000000000){
			if (num % 1000000 == 0){
				return num/1000000+"M";
			}else{
				return Math.round(num*10/1000000)/10+"M";
			}
		}else {
			if (num % 1000000000 == 0){
				return num/1000000000+"B";
			}else{
				return Math.round(num*10/1000000000)/10+"B";
			}
		}
	}
	
	/**
	 * this function for getting Facebook hash key. the hash key
	 * is to used in login 
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static String getFacebookHashKey(Context context)
										throws Exception {
		try{
			PackageInfo info = context.getPackageManager().getPackageInfo(
					"com.nsoft.pubress", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				String keyHash= Base64.encodeToString(md.digest(), Base64.DEFAULT);
				Log.d("reader key hash:",keyHash);
				return keyHash;
			}
			
			// when there is nothing returned
			return Constant.EMPTY;
		}catch (NameNotFoundException e){
			Log.d("reader key-hash error "+e.getClass(),e.getMessage());
			throw e;
		}catch (NoSuchAlgorithmException e){
			Log.d("reader key-hash error "+e.getClass(),e.getMessage());
			throw e;
		}
	}
	
	/**
	 * get real path from URI, this function will be used to extract
	 * full path from resources from gallery which was returned in
	 * URI format.
	 * 
	 * @param context
	 * @param contentUri
	 * @return
	 */
	public static String getRealPathFromURI(Context context, Uri contentUri){
		Cursor cursor=null;
		try{ 
			String[] proj={ MediaStore.Images.Media.DATA };
			cursor=context.getContentResolver().query(
						contentUri,proj,null,null,null);
			int column_index=cursor.getColumnIndexOrThrow(
						MediaStore.Images.Media.DATA);
		    cursor.moveToFirst();
		    return cursor.getString(column_index);
		}finally{
			if (cursor != null) {
				cursor.close();
			}
		}
	}
	
	/**
	 * this function will restart the app<br>
	 * use in some case that requires restarting like change language
	 * 
	 * @param context
	 * @param c
	 */
	@SuppressWarnings("rawtypes")
	public static void restartApp(Context context, Class c){
		Intent mStartActivity=new Intent(context,c);
		int mPendingIntentId=123456;
		PendingIntent mPendingIntent= PendingIntent.getActivity(
					context,mPendingIntentId,mStartActivity,
					PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager mgr=(AlarmManager)context.
					getSystemService(Context.ALARM_SERVICE);
		mgr.set(AlarmManager.RTC, System.currentTimeMillis()+100,
				mPendingIntent);
		System.exit(0);
	}
}
