package com.cat9tac.androidsafe.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cat9tac.androidsafe.R;
import com.cat9tac.androidsafe.fragment.AutoGuardFragment;
import com.cat9tac.androidsafe.fragment.AutoGuardTestFragment;
import com.cat9tac.androidsafe.fragment.EncryptDataFragment;
import com.cat9tac.androidsafe.fragment.RemoteControlFragment;

public class SecondActivity extends AppCompatActivity {
    private int fragmentId;
    private Bundle bundle;
    private Fragment fragment;
    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bundle = getIntent().getExtras();
        fragmentId = bundle.getInt("FRAGMENT_ID");
        fm = getFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container);
        chooseFragment();


    }

    private void chooseFragment() {
        if (fragment == null) {

            switch (fragmentId) {
                case 0:
                    fragment = new AutoGuardFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    break;
                case 1:
                    fragment = new RemoteControlFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    break;
                case 2:
                    fragment = new EncryptDataFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    break;
            }

        }
    }
}
