package com.cat9tac.androidsafe.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.cat9tac.androidsafe.util.SharePreferenceEditor;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by cat9tac on 2016/3/14.
 */
public class MyDeviceAdminReceiver extends DeviceAdminReceiver {
    private static int failed_count=0;
    private static  final String FAILED_COUNT="FAILED_COUNT";
    //private SharePreferenceEditor sharePreferenceEditor;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;


    @Override
    public void onPasswordFailed(Context context, Intent intent) {
        super.onPasswordFailed(context, intent);
        //int c=sharePreferenceEditor.getSharedPreferences().getInt(FAILED_COUNT,2);
        sharedPreferences=context.getSharedPreferences("state.activate",Context.MODE_PRIVATE);
        int c=sharedPreferences.getInt(FAILED_COUNT,2);
        failed_count++;
        if(failed_count>=c){
            Log.i("DDD","onPasswordFailed"+failed_count+"time");
        }



    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        super.onPasswordSucceeded(context, intent);
        Log.i("DDD","onPasswordSucceeded");
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        Log.i("DDD","Myadmin eable");
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        Log.i("DDD","Myadmin disable");
    }
}
