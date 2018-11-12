package com.prototype.project.testrun66.Model;


import android.graphics.drawable.Drawable;
import java.util.ArrayList;

public class PackageData  {

    private String appName;
    private String packageName;
    private Drawable appIcon;
    private boolean isSelected;


    public void addAppToBlock(String blockApp){
        ArrayList<String> blacklisted = new ArrayList<String>();
        blacklisted.add(blockApp);
    }

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
