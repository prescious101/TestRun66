package com.prototype.project.testrun66.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prototype.project.testrun66.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppsManager {


    private static final String SHARED_PREF = "SharedPreference";
    private static final String KEY_TEXT_ARRAYLIST = "ARRAYLIST ";
    private Context mContext;
    private ArrayList<PackageData> myApps;

    public AppsManager() { }

    public AppsManager(Context c) {
        mContext = c;
        myApps = new ArrayList<PackageData>();
    }
    public ArrayList<PackageData> getApps() {
        loadApps();
        return myApps;
    }

    private void loadApps() {
        List<ApplicationInfo> packages = mContext.getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if ((packageInfo.flags & (ApplicationInfo.FLAG_UPDATED_SYSTEM_APP | ApplicationInfo.FLAG_SYSTEM)) > 0) {
                Log.d("System App", "App is System define");
            }
            else{
                PackageData newApp = new PackageData();
                newApp.setAppName(getApplicationLabelByPackageName(packageInfo.packageName));
                newApp.setPackageName(packageInfo.packageName);
                newApp.setAppIcon(getAppIconByPackageName(packageInfo.packageName));
                myApps.add(newApp);
            }
        }

        Collections.sort(myApps, new Comparator<PackageData>() {
            @Override
            public int compare(PackageData s1, PackageData s2) {
                return s1.getAppName().compareToIgnoreCase(s2.getAppName());
            }
        });
    }

    // Custom method to get application icon by package name
    private Drawable getAppIconByPackageName(String packageName) {
        Drawable icon;
        try {
            icon = mContext.getPackageManager().getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // Get a default icon
            icon = ContextCompat.getDrawable(mContext, R.drawable.ic_launcher_background);
        }
        return icon;
    }

    // Custom method to get application label by package name
    private String getApplicationLabelByPackageName(String packageName) {
        PackageManager packageManager = mContext.getPackageManager();
        ApplicationInfo applicationInfo;
        String label = "Unknown";
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            if (applicationInfo != null) {
                label = (String) packageManager.getApplicationLabel(applicationInfo);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return label;
    }

    public void addAppToBlock(String blockApp){
        ArrayList<String> blackList = new ArrayList<String>();
        blackList.add(blockApp);
        saveArrayList(blackList,KEY_TEXT_ARRAYLIST);
    }

    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public String getPreferences(Context context, String key) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = appSharedPrefs.getString(key, "");
        if (TextUtils.isEmpty(json)) {
            return null;
        }return json;
    }

    public void setPreferences(Context context, String key, String value) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

}
