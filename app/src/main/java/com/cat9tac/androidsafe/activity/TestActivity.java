package com.cat9tac.androidsafe.activity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cat9tac.androidsafe.R;
import com.cat9tac.androidsafe.receiver.MyDeviceAdminReceiver;

public class TestActivity extends AppCompatActivity {
    static DevicePolicyManager mDPM;
    ComponentName componentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, MyDeviceAdminReceiver.class);
    }
    public void onClick(View V){
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME,componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "We want to watch logins on this device.");
        startActivityForResult(intent, 1);
        boolean b = mDPM.isAdminActive(componentName);
        Log.i("DDD", "已激活:" + b);
    }
}
