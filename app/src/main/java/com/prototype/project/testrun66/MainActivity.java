package com.prototype.project.testrun66;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    private static  final  String TAG = "MainActivity";
    private PackageData packageData = new PackageData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            Context context = getApplicationContext();
            UsageStatsManager mUsageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            List stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 4000 * 10, time);

            if (stats == null || stats.isEmpty()) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                context.startActivity(intent);
            }
        }startJob();

        TextView packageStatus = findViewById(R.id.tvPackageStatus);
        packageStatus.setText(packageData.getPackageName());
    }


    public void startJob(){
        ComponentName componentName = new ComponentName(this,JobSchedulerService.class);
        JobInfo info = new JobInfo.Builder(1,componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int  resultcode = 0;
        if (scheduler != null) {
            resultcode = scheduler.schedule(info);
        }

        if (resultcode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "Job Success... Job scheduled");
        }else {
            Log.d(TAG, "Job Failed... Job not scheduled ");
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: App Started");
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
