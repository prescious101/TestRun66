package com.prototype.project.testrun66;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class LockActivity extends Activity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private Runnable mJobStarted = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, 1000);
            finish();
        }
    };
}
