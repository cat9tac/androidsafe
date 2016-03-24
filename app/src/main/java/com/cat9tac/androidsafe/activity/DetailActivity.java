package com.cat9tac.androidsafe.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.cat9tac.androidsafe.R;
import com.cat9tac.androidsafe.fragment.ActivateAutoGuardFragment;

public class DetailActivity extends AppCompatActivity {
    private  static final String FRAGMENT_ID = "FRAGMENT_ID";
    private static final  int ACTIVATE_ACTIVITY_ID = 11;
    private static final  int REMOTECONTROL_ACTIVITY_ID = 12;
    private static final int LOCATE_ACTIVATE_ID = 13;
    private static final int TAKEPHOTO_FRAGMENT_ID = 14;
    private static final int BACKUP_CONTACTS_FRAGMENT_ID = 15;
    private static final int TTRACK_FRAGMENT_ID = 16;
    private static final int DELETEDATA_FRAGMENT_ID = 17;
    private static final  int CHANGESSIM_INFORM_FRAGMENT_ID = 21;
    private static final  int REMOTELOCATE_FRAGMENT_ID = 22;
    private static final int ALARMBELL_ID = 23;
    private static final int SCREENMESSAGE_FRAGMENT_ID = 24;
    private static final int LOCKDEVICE_FRAGMENT_ID = 25;
    private static final int ENCRYPTDATA_FRAGMENT_ID = 31;



    private int fragmentId;
    private Bundle bundle;
    private Fragment fragment;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;
                case 13:
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;
                case 14:
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;
                case 15:
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;
                case 16:
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;
                case 17:
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;
                case 21:
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;
                case 23:
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;
                case 24:
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;
                case 25:
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;
                case 31:
                    fragment = new Fragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, fragment).commit();
                    break;

            }

        }
    }
}
