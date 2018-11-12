package com.prototype.project.testrun66;

import android.app.usage.UsageStatsManager;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.prototype.project.testrun66.Model.AppsManager;
import com.prototype.project.testrun66.Model.BlockApp;
import com.prototype.project.testrun66.Model.MyApp;
import com.prototype.project.testrun66.Model.PackageData;
import com.prototype.project.testrun66.View.InstalledAppsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static  final  String TAG = "MainActivity";
    private PackageData packageData = new PackageData();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<PackageData> installedApps;
    private AppsManager appManager;
    private BlockApp blockApp = new BlockApp();
//    private Button mBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializations();

        if (Build.VERSION.SDK_INT >= 21) {
            Context context = getApplicationContext();
            UsageStatsManager mUsageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            List stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 4000 * 10, time);

            if (stats == null || stats.isEmpty()) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
        Toast.makeText(this, String.valueOf(blockApp.isJobSchedulerRunning(MyApp.getAppContext())), Toast.LENGTH_SHORT).show();
    }

    public void initializations(){
        installedApps = new ArrayList<PackageData>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        appManager = new AppsManager(this);
        installedApps = appManager.getApps();
        mAdapter=new InstalledAppsAdapter(getApplicationContext(),installedApps);
        mRecyclerView.setAdapter(mAdapter);
//        mBlock = (Button) findViewById(R.id.btnBlock);
    }

    public void blockApp(View v) {
        blockApp.startJob();
    }

    @Override
    protected void onStart() { super.onStart(); Log.d(TAG, "onStart: App Started"); }

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
