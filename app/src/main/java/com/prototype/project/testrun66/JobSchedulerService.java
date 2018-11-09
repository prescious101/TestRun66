package com.prototype.project.testrun66;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class JobSchedulerService extends JobService {

    private static final String TAG = "JobScheduleService";
    private static final String SHARED_PREF = "SharedPreference";
    private static final String TEXT = "TEXT";

    PackageData packageData = new PackageData();
    private Handler mHandler = new Handler(Looper.getMainLooper());



    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "Job started");
        mJobStarted.run();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "Job Stop before completion ");
        return true;
    }

    public void getTopActivityFromLolipopOnwards() {
        String topPackageName = null;
        PackageManager pm = getPackageManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager mUsageStatsManager = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
            }
            long time = System.currentTimeMillis();
            // We get usage stats for the last 10 seconds
            List<UsageStats> stats = null;
            if (mUsageStatsManager != null) {
                stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 5, time);
            }
            // Sort the stats by the last time used
            if (stats != null) {
                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : stats) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (!mySortedMap.isEmpty()) {
                    topPackageName = mySortedMap.get(mySortedMap.lastKey()).getPackageName();

                    if(topPackageName != null) {
                        topPackageName = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                        packageData.setPackageName(topPackageName);
                        saveData(packageData.getPackageName());
                        Log.e("TopPackage Name", topPackageName);
                        Toast.makeText(this, packageData.getPackageName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }


    public void saveData(String topPackagename){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT,topPackagename);
    }

    private Runnable mJobStarted = new Runnable() {
        @Override
        public void run() {
            String packageName;
            getTopActivityFromLolipopOnwards();
            packageName = packageData.getPackageName();

            if(packageName!=null) {
                Intent serviceIntent = new Intent(JobSchedulerService.this, ForegroundNotificationService.class);
                serviceIntent.putExtra("packageName", packageName);
                ContextCompat.startForegroundService(JobSchedulerService.this, serviceIntent);
                Log.d(TAG, "onStartJob: Notification start");
                mHandler.postDelayed(this, 1000);
            }else{
                mHandler.postDelayed(this,0);
            }
        }
    };





}




// failed test to send open custom app when opening an app
//
//else if (packageName.contentEquals("com.prototype.project.testrun66")){
//        Intent intent = new Intent(JobSchedulerService.this,MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        }