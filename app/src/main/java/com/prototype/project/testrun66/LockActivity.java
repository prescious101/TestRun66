package com.prototype.project.testrun66;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;

import com.prototype.project.testrun66.Model.MyApp;

public class LockActivity extends Activity {
    private static final String KEY_TEXT = "TEXT";
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String retrieveData = getIntent().getStringExtra(KEY_TEXT);
        ActivityManager activityManager = (ActivityManager)getApplicationContext().getSystemService(MyApp.getAppContext().ACTIVITY_SERVICE);
        activityManager.killBackgroundProcesses(retrieveData);
        mJobStarted.run();
    }
    private Runnable mJobStarted = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, 5000);

            finish();
            System.exit(0);
        }
    };
}
