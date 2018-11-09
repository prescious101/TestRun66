package com.prototype.project.testrun66;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class RestartServiceBroadcastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Service Stops", "Trying too restart Service");
        Toast.makeText(context, "Restarting JobService", Toast.LENGTH_SHORT).show();
        context.startService(new Intent(context, JobSchedulerService.class));
    }
}
