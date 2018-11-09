package com.prototype.project.testrun66;

import android.app.Activity;

public class PackageData  {
    String packageName;
    String prevPackageName;

    public PackageData() {
    }

    public PackageData(String packageName, String prevPackageName) {
        this.packageName = packageName;
        this.prevPackageName = prevPackageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPrevPackageName() {
        return prevPackageName;
    }

    public void setPrevPackageName(String prevPackageName) {
        this.prevPackageName = prevPackageName;
    }
}
