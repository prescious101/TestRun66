package com.prototype.project.testrun66.Model;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import com.prototype.project.testrun66.Service.JobSchedulerService;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

public class BlockApp {
    private static final String TAG = "BlockApp";

    public void startJob(){
        ComponentName componentName = new ComponentName(MyApp.getAppContext(),JobSchedulerService.class);
        JobInfo info = new JobInfo.Builder(1,componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .build();

        JobScheduler scheduler = (JobScheduler) MyApp.getAppContext().getSystemService(JOB_SCHEDULER_SERVICE);
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

    public  boolean isJobSchedulerRunning(final Context context) {
        final JobScheduler jobScheduler = (JobScheduler) context.getSystemService( Context.JOB_SCHEDULER_SERVICE );
        return jobScheduler.getAllPendingJobs().size() > 0;
    }



}
