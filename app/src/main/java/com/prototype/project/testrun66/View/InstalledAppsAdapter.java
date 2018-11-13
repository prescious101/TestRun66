package com.prototype.project.testrun66.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prototype.project.testrun66.Model.AppsManager;
import com.prototype.project.testrun66.Model.MyApp;
import com.prototype.project.testrun66.Model.PackageData;
import com.prototype.project.testrun66.R;

import java.util.ArrayList;
import java.util.List;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.ViewHolder>{

    private static final String TAG = "AddedtoArrayList";
    private static final String KEY_TEXT = "TEXT";
    private Context mContext;
    private List<PackageData> mDataSet;
    private ArrayList<PackageData> mDataSet2 = new ArrayList<>();
    private AppsManager appsManager = new AppsManager();
    SharedPreferences prefs;

    public InstalledAppsAdapter(Context context, List<PackageData> list) {
        mContext = context;
        mDataSet =  list;
    }

    @Override
    public InstalledAppsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.main_applist, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String packageName = mDataSet.get(position).getPackageName();// Get the current package name
        Drawable icon = mDataSet.get(position).getAppIcon();// Get the current app icon
        String label = mDataSet.get(position).getAppName();// Get the current app label
        holder.mTextViewLabel.setText(label); // Set the current app label
        holder.mTextViewPackage.setText(packageName); // Set the current app package name
        holder.mImageViewIcon.setImageDrawable(icon); // Set the current app icon
        holder.mAppSelect.setChecked(mDataSet.get(position).isSelected());

        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataSet.get(position).setSelected(!mDataSet.get(position).isSelected());
                InstalledAppsAdapter.this.notifyDataSetChanged();
                appsManager.setPreferences(MyApp.getAppContext(), KEY_TEXT, packageName);
                appsManager.addAppToBlock(packageName);
//                Toast.makeText(mContext.getApplicationContext(), "package name: " + appsManager.getPreferences(MyApp.getAppContext(), KEY_TEXT) +
//                        " save to shared pref", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() { return mDataSet.size(); }// Count the installed apps

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewLabel,mTextViewPackage;
        public ImageView mImageViewIcon;
        public CheckBox mAppSelect;
        public RelativeLayout mItem;

        public ViewHolder(View v) {
            super(v);
            // Get the widgets reference from custom layout
            mTextViewLabel = v.findViewById(R.id.Apk_Name);
            mTextViewPackage = v.findViewById(R.id.Apk_Package_Name);
            mImageViewIcon = v.findViewById(R.id.packageImage);
            mAppSelect = v.findViewById(R.id.appSelect);
            mItem = v.findViewById(R.id.item);

            if(mAppSelect.isChecked()) { mAppSelect.setChecked(true);
            }
            else { mAppSelect.setChecked(false);
            }this.setIsRecyclable(false);
        }
    }
}
