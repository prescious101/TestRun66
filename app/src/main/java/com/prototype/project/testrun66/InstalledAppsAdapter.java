package com.prototype.project.testrun66;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.ViewHolder>{

    private static final String KEY_TEXT = "TEXT";
    private Context mContext;
    private ArrayList<PackageData> mDataSet;
    private PackageData packageData = new PackageData();
   // private List<AppInfo> mDataSet;




    public InstalledAppsAdapter(Context context, ArrayList<PackageData> list) {
        mContext = context;
        mDataSet =  list;
    }

    @Override
    public InstalledAppsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.main_line_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        // Get the current package name
        final String packageName = mDataSet.get(position).getPackageName();

        // Get the current app icon
        Drawable icon = mDataSet.get(position).getAppIcon();

        // Get the current app label
        String label = mDataSet.get(position).getAppName();

        // Set the current app label
        holder.mTextViewLabel.setText(label);

        // Set the current app package name
        holder.mTextViewPackage.setText(packageName);

        // Set the current app icon
        holder.mImageViewIcon.setImageDrawable(icon);

        holder.mAppSelect.setChecked(mDataSet.get(position).isSelected());

        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataSet.get(position).setSelected(!mDataSet.get(position).isSelected());
                InstalledAppsAdapter.this.notifyDataSetChanged();

                    packageData.setPreferences(MyApp.getAppContext(),KEY_TEXT,packageName);

                Toast.makeText(mContext.getApplicationContext(),"package name: "+ packageData.getPreferences(MyApp.getAppContext(),KEY_TEXT) +
                        "save to shared pref", Toast.LENGTH_SHORT).show();
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

        public ViewHolder(View v) {
            super(v);
            // Get the widgets reference from custom layout
            mTextViewLabel = (TextView) v.findViewById(R.id.Apk_Name);
            mTextViewPackage = (TextView) v.findViewById(R.id.Apk_Package_Name);
            mImageViewIcon = (ImageView) v.findViewById(R.id.packageImage);
            mAppSelect = (CheckBox) v.findViewById(R.id.appSelect);
            mItem = (RelativeLayout) v.findViewById(R.id.item);

            if(mAppSelect.isChecked())
            {
              mAppSelect.setChecked(true);
            }
            else {
               mAppSelect.setChecked(false);
            }
            this.setIsRecyclable(false);
        }

    }



}
