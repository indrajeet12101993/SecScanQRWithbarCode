package com.qrcode.generator.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.qrcode.generator.R;

/**
 * Created by Thore Dankworth
 * Last Update: 15.08.2017
 * Last Update by Thore Dankworth
 *
 * This class is all about the About Dialog
 */

public class AboutDialog extends Dialog {

    private static final String TAG = AboutDialog.class.getName();

    private Context mContext = null;

    public AboutDialog(Context context) {
        super(context);

        mContext = context;

    }

    /**
     * Standard Android on create method that gets called when the activity
     * initialized.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.about);
        loadTheme();

        TextView tv = (TextView) findViewById(R.id.info_version);
        String packageName = getContext().getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo = getContext().getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            String appInfo = "OR code generator";
            String versionInfo = "Version " +
                    packageInfo.versionName + " (Build " +
                    Integer.toString(packageInfo.versionCode) + ")";
            tv.setText(appInfo + "\n" + versionInfo);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Call to getPackageInfo() failed! => ", e);
        }


    }

    /**
     * Depending on the saved settings. The day or night mode will be loaded
     */
    private void loadTheme(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        String history_setting = prefs.getString("pref_day_night_mode", "");
        if(history_setting.equals("1")){
            mContext.setTheme(R.style.darktheme);
        } else {
        }
    }

}