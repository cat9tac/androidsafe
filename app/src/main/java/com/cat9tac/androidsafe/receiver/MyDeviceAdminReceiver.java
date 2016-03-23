package com.cat9tac.androidsafe.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by cat9tac on 2016/3/14.
 */
public class MyDeviceAdminReceiver extends DeviceAdminReceiver {
    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        Log.i("DDD","Myadmin disable");
    }
}
