package com.cat9tac.androidsafe.util;

import android.app.admin.DeviceAdminInfo;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.cat9tac.androidsafe.StateRecord;
import com.cat9tac.androidsafe.application.AAApp;
import com.cat9tac.androidsafe.receiver.MyDeviceAdminReceiver;

/**
 * Created by v_ypfu on 2016/3/18.
 */
public class ActivateDeviceAdmin {
    public  static DevicePolicyManager mDPM;
    public  static  ComponentName componentName;
    public static  DeviceAdminInfo deviceAdminInfo;
    public static  Boolean isActivate = false;
    public static  Context context;
    public static ActivateDeviceAdmin activateDeviceAdmin;


    public ActivateDeviceAdmin(Context context) {
        DevicePolicyManager  mDPM = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName componentName = new ComponentName(context, MyDeviceAdminReceiver.class);
        this.mDPM=mDPM;
        this.componentName=componentName;
        this.context=context;
    }
    public static ActivateDeviceAdmin getInstance(Context context){
        if(activateDeviceAdmin==null){
            activateDeviceAdmin= new ActivateDeviceAdmin(context);
        }
        return activateDeviceAdmin;

    }

    public DevicePolicyManager getmDPM() {
        return mDPM;
    }

    public  Intent getActivateIntent() {
        componentName = new ComponentName(context, MyDeviceAdminReceiver.class);
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME,componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Just activate on this device.");
        isActivate = mDPM.isAdminActive(componentName);
        return intent;
    }
    public void cancelActivate(){
        mDPM.removeActiveAdmin(componentName);
        isActivate = mDPM.isAdminActive(componentName);
    }


}
