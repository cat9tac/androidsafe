package com.cat9tac.androidsafe.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cat9tac.androidsafe.R;
import com.cat9tac.androidsafe.util.ActivateDeviceAdmin;

public class ActivateActvity extends AppCompatActivity {
    private static final String IS_ACTIVATE = "IS_ACTIVATE";
    private Button btnActivateAutoGurad;
    //must be static else you can't open the activate activity

    private ActivateDeviceAdmin activateDeviceAdmin;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean isActivate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);
        init();
    }
    private void init() {
        sharedPreferences = getSharedPreferences("state.activate", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        activateDeviceAdmin = ActivateDeviceAdmin.getInstance(getApplicationContext());
        btnActivateAutoGurad = (Button) findViewById(R.id.btn_activate_auto_guard);
        btnActivateAutoGurad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = activateDeviceAdmin.getActivateIntent();
                startActivityForResult(intent, 1);
                boolean x = activateDeviceAdmin.isActivate;

                Log.i("DDD", "is activate" + x);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //-1 is activate
        //0 is cancel activate
        if(resultCode==-1){
            editor.putBoolean(IS_ACTIVATE, true).commit();
            finish();
            Log.i("DDD","onActivityResultis commit");
            //getActivity().finish();
        }
        if(resultCode==0){
            boolean b=editor.putBoolean(IS_ACTIVATE, false).commit();

        }

    }
}
