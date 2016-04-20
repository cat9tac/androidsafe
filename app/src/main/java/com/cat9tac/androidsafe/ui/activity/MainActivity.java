package com.cat9tac.androidsafe.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.cat9tac.androidsafe.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout ll_autoguard;
    private LinearLayout ll_remote;
    private LinearLayout ll_encrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private  void init(){
        ll_autoguard= (LinearLayout) findViewById(R.id.ll_autoguard);
        ll_remote= (LinearLayout) findViewById(R.id.ll_remote);
        ll_encrypt= (LinearLayout) findViewById(R.id.ll_encrypt);
        ll_autoguard.setOnClickListener(this);
        ll_remote.setOnClickListener(this);
        ll_encrypt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent();
        Bundle bundle=new Bundle();
        i.setClass(MainActivity.this,SecondActivity.class);
        switch (v.getId()){
            case R.id.ll_autoguard:
                bundle.putInt("FRAGMENT_ID",0);
                break;
            case R.id.ll_remote:
                bundle.putInt("FRAGMENT_ID",1);
                break;
            case R.id.ll_encrypt:
                bundle.putInt("FRAGMENT_ID",2);
                break;
        }
        i.putExtras(bundle);
        startActivity(i);

    }
}
