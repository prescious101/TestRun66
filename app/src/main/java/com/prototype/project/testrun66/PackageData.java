package com.prototype.project.testrun66;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class PackageData  {

    private static final String SHARED_PREF = "SharedPreference";
    private static final String KEY_TEXT = "TEXT";
    private SharedPreferences sharedPreferences;
    private String savePackageName;

    private String appName;
    private String packageName;
    private Drawable appIcon;
    private boolean isSelected;

//    public String getSavePackageName() {
//        String returnData = sharedPreferences.getString(TEXT,"default value");
//        return returnData;
//    }

    public static String getPreferences(Context context, String key) {

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);

        String json = appSharedPrefs.getString(key, "");
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return json;
    }

    public static void setPreferences(Context context, String key, String value) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }


//    public void setSavePackageName(String savePackageName) {
//        this.savePackageName = savePackageName;
//        sharedPreferences = MyApp.getAppContext().getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(TEXT,this.savePackageName);
//        editor.apply();
//        Log.d("check", "setSavePackageName: "+savePackageName);
//    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public PackageData() {
    }

    public PackageData(String packageName) {
        this.packageName = packageName;
    }

}
