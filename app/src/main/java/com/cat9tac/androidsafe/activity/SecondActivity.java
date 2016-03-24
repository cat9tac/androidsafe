package com.cat9tac.androidsafe.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.cat9tac.androidsafe.R;
import com.cat9tac.androidsafe.fragment.AutoGuardFragment;
import com.cat9tac.androidsafe.fragment.AutoGuardTestFragment;
import com.cat9tac.androidsafe.fragment.EncryptDataFragment;
import com.cat9tac.androidsafe.fragment.RemoteControlFragment;

public class SecondActivity extends AppCompatActivity {
    private  static final String FRAGMENT_ID = "FRAGMENT_ID";
    private static final  int AUTOGUARD_FRAGMENT_ID = 1;
    private static final  int REMOTECONTROL_FRAGMENT_ID = 2;
    private static final int ENCRYPTDATA_FRAGMENT_ID = 3;
    private int fragmentId;
    private Bundle bundle;
    private Fragment fragment;
    private String toolbarTitle;
    private FragmentManager fm;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bundle = getIntent().getExtras();
        fragmentId = bundle.getInt(FRAGMENT_ID);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBarTitle();
        toolbar.setTitle(toolbarTitle);
        toolbar.setTitleTextColor(getResources().getColor(R.color.backgroundBottom));
        setSupportActionBar(toolbar);
        fm = getFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container);
        chooseFragment();


    }
    public void setToolBarTitle(){
        switch (fragmentId) {
            case AUTOGUARD_FRAGMENT_ID:
                toolbarTitle="自动防盗";
                break;
            case REMOTECONTROL_FRAGMENT_ID:
                toolbarTitle="远程找回手机";
                break;
            case ENCRYPTDATA_FRAGMENT_ID:
                toolbarTitle="数据加密";
                break;
        }


    }

    private void chooseFragment() {
        if (fragment == null) {

            switch (fragmentId) {
                case 1:
                    fragment = AutoGuardFragment.newInstance(null,null);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    break;
                case 2:
                    fragment = new RemoteControlFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    break;
                case 3:
                    fragment = new EncryptDataFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    break;
            }

        }
    }
}
