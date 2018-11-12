package com.prototype.project.testrun66.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prototype.project.testrun66.Model.AppsManager;
import com.prototype.project.testrun66.Model.BlockApp;
import com.prototype.project.testrun66.Model.MyApp;
import com.prototype.project.testrun66.Model.PackageData;
import com.prototype.project.testrun66.R;

import java.util.ArrayList;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.ViewHolder>{

    private static final String KEY_TEXT = "TEXT";
    private Context mContext;
    private ArrayList<PackageData> mDataSet;
    private AppsManager appsManager = new AppsManager();
    private PackageData packageData = new PackageData();
    private BlockApp blockApp = new BlockApp();


    public InstalledAppsAdapter(Context context, ArrayList<PackageData> list) {
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
        holder.mBlock.setOnClickListener();

        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataSet.get(position).setSelected(!mDataSet.get(position).isSelected());
                InstalledAppsAdapter.this.notifyDataSetChanged();

                appsManager.setPreferences(MyApp.getAppContext(),KEY_TEXT,packageName);
                packageData.addAppToBlock(appsManager.getPreferences(MyApp.getAppContext(),KEY_TEXT));

                Toast.makeText(mContext.getApplicationContext(),"package name: "+ appsManager.getPreferences(MyApp.getAppContext(),KEY_TEXT) +
                        " save to shared pref", Toast.LENGTH_SHORT).show();
            }
        });

        holder.mBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockApp.startJob();
            }
        });
    }


    @Override
    public int getItemCount() {
        // Count the installed apps
        return mDataSet.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewLabel;
        public TextView mTextViewPackage;
        public ImageView mImageViewIcon;
        public CheckBox mAppSelect;
        public RelativeLayout mItem;
        public Button mBlock;

        public ViewHolder(View v) {
            super(v);

            // Get the widgets reference from custom layout
            mTextViewLabel = (TextView) v.findViewById(R.id.Apk_Name);
            mTextViewPackage = (TextView) v.findViewById(R.id.Apk_Package_Name);
            mImageViewIcon = (ImageView) v.findViewById(R.id.packageImage);
            mAppSelect = (CheckBox) v.findViewById(R.id.appSelect);
            mItem = (RelativeLayout) v.findViewById(R.id.item);
            mBlock = (Button) v.findViewById(R.id.btnBlock);


            if(mAppSelect.isChecked()) {
              mAppSelect.setChecked(true);
            }
            else {
               mAppSelect.setChecked(false);
            }this.setIsRecyclable(false);
        }
    }
}
