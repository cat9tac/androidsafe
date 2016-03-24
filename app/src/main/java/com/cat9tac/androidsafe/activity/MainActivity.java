package com.cat9tac.androidsafe.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.cat9tac.androidsafe.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String FRAGMENT_ID = "FRAGMENT_ID";
    private static int AUTOGUARD_FRAGMENT_ID = 1;
    private static int REMOTECONTROL_FRAGMENT_ID = 2;
    private static int ENCRYPTDATA_FRAGMENT_ID = 3;

    private LinearLayout ll_autoguard;
    private LinearLayout ll_remote;
    private LinearLayout ll_encrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("数据保护");
        toolbar.setTitleTextColor(getResources().getColor(R.color.backgroundBottom));
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        ll_autoguard = (LinearLayout) findViewById(R.id.ll_autoguard);
        ll_remote = (LinearLayout) findViewById(R.id.ll_remote);
        ll_encrypt = (LinearLayout) findViewById(R.id.ll_encrypt);
        ll_autoguard.setOnClickListener(this);
        ll_remote.setOnClickListener(this);
        ll_encrypt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        Bundle bundle = new Bundle();
        i.setClass(MainActivity.this, SecondActivity.class);
        switch (v.getId()) {
            case R.id.ll_autoguard:
                bundle.putInt(FRAGMENT_ID, AUTOGUARD_FRAGMENT_ID);
                break;
            case R.id.ll_remote:
                bundle.putInt(FRAGMENT_ID, REMOTECONTROL_FRAGMENT_ID);
                break;
            case R.id.ll_encrypt:
                bundle.putInt(FRAGMENT_ID, ENCRYPTDATA_FRAGMENT_ID);
                break;

        }

        i.putExtras(bundle);
        startActivity(i);

    }
}
