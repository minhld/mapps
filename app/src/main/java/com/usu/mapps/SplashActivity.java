package com.usu.mapps;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.usu.mapps.business.XPubUI;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    AsyncTask<Void,Integer,Void> startUpTask=
            new AsyncTask<Void,Integer,Void>(){
                @Override
                protected void onPreExecute(){
//				try{
//					NSoftUtils.getFacebookHashKey(SplashActivity.this);
//				}catch(Exception e){
//
//				}
                }

                @Override
                protected Void doInBackground(Void... params){
                    try{
                        // try to get category list from XML file or get it
                        // from server if file doesn't exist
                        XPubUI.retrieveCategoryList(SplashActivity.this);

                        // get local book list
                        NSoftEngine.getBookListFromXml();

                        publishProgress(Constant.sync.SYNC_COMPLETED);
                    }catch(Exception e){
                        publishProgress(Constant.sync.SYNC_FAILED);
                    }

                    return null;
                }

                @Override
                protected void onProgressUpdate(Integer... values){
                    // to do
                    XPubUI.startUpParams(SplashActivity.this);

                    // update language before entering
                    updateLanguage();

                    // depend on login status, the splash will turn to login screen
                    // or main screen (if login)
                    Intent intent = null;
                    intent = new Intent(SplashActivity.this,
                            NSoftEngine.loginInfo.getLoginStatus() == LoginInfo.LOGIN_OK ?
                                    StoreActivity_v7.class : LoginActivity.class);
                    startActivity(intent);

                    // close this activity
                    finish();
                }
            };
		startUpTask.execute();
}
