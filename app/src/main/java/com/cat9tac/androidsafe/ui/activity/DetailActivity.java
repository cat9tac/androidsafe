package com.cat9tac.androidsafe.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cat9tac.androidsafe.R;
import com.cat9tac.androidsafe.ui.fragment.AutoGuardFragment;
import com.cat9tac.androidsafe.ui.fragment.EncryptDataFragment;
import com.cat9tac.androidsafe.ui.fragment.GetLocationFragment;
import com.cat9tac.androidsafe.ui.fragment.RemoteControlFragment;

public class DetailActivity extends AppCompatActivity {
    private int fragmentId;
    private Bundle bundle;
    private Fragment fragment;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bundle = getIntent().getExtras();
        fragmentId = bundle.getInt("FRAGMENT_ID");
        fm = getFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container_detail);
        chooseFragment();
    }

    private void chooseFragment() {
        if (fragment == null) {
            switch (fragmentId) {
                case 12:
                    fragment = new GetLocationFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;

            }

        }
    }
}
