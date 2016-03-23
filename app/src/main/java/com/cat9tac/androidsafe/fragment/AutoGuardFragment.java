package com.cat9tac.androidsafe.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.cat9tac.androidsafe.R;
import com.cat9tac.androidsafe.activity.ActivateActvity;
import com.cat9tac.androidsafe.util.ActivateDeviceAdmin;
import com.cat9tac.androidsafe.util.SharePreferenceEditor;
import com.cat9tac.androidsafe.util.DialogUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AutoGuardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AutoGuardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutoGuardFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    //the ll widget id
    private static final int LOCATE_ID = 2;
    private static final int TAKE_PHOTO_ID = 3;
    private static final int BACKUP_CONTACTS_ID = 4;
    private static final int TTRACK_ID = 5;
    private static final int DELETE_DATA_ID = 6;
    private static final String IS_LOCATE = "IS_LOCATE";
    private static final String IS_TAKE_PHOTO = "IS_TAKE_PHOTO";
    private static final String IS_DELETE_DATA = "IS_DELETE_DATA";
    private static final String IS_BACKUP_CONTACTS = "IS_BACKUP_CONTACTS";
    private static final String IS_TTRACK = "IS_TTRACK";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String KEY_IS_ACTIVATE = "is_activate";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    //
    private LinearLayout ll_autoguard, ll_unlock_fail_monitor, ll_locate, ll_takephoto, ll_backup_contacts, ll_deletedata;
    private Switch switch_open_autoguard, switch_locate, switch_takephoto, switch_backup_contacts, switch_ttrack, switch_deletedata;
    private ActivateDeviceAdmin activateDeviceAdmin;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean isActivate;
    private String emailAddress;
    private SharePreferenceEditor sharePreferenceEditor;

    public AutoGuardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AutoGuardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AutoGuardFragment newInstance(String param1, String param2) {
        AutoGuardFragment fragment = new AutoGuardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        sharePreferenceEditor = new SharePreferenceEditor(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activateDeviceAdmin = new ActivateDeviceAdmin(getActivity());
        View v = inflater.inflate(R.layout.fragment_auto_guard, container, false);

        init(v);
        return v;
    }

    public void init(View v) {

        //switch_locate,switch_takephoto,switch_backup_contacts,switch_ttrack,switch_deletedata;

        sharedPreferences = getActivity().getSharedPreferences("state.activate", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ll_autoguard = (LinearLayout) v.findViewById(R.id.ll_startautoguard);
        ll_unlock_fail_monitor = (LinearLayout) v.findViewById(R.id.ll_unlock_fail_monitor);
        ll_locate = (LinearLayout) v.findViewById(R.id.ll_locate);
        ll_takephoto = (LinearLayout) v.findViewById(R.id.ll_takephoto);
        ll_backup_contacts = (LinearLayout) v.findViewById(R.id.ll_backup_contacts);
        ll_deletedata = (LinearLayout) v.findViewById(R.id.ll_deletedata);
        switch_open_autoguard = (Switch) v.findViewById(R.id.switch_open_autoguard);
        switch_locate = (Switch) v.findViewById(R.id.switch_locate);
        switch_takephoto = (Switch) v.findViewById(R.id.switch_takephoto);
        switch_backup_contacts = (Switch) v.findViewById(R.id.switch_backup_contacts);
        switch_ttrack = (Switch) v.findViewById(R.id.switch_ttrack);
        switch_deletedata = (Switch) v.findViewById(R.id.switch_deletedata);
        ll_autoguard.setOnClickListener(this);
        ll_unlock_fail_monitor.setOnClickListener(this);
        ll_locate.setOnClickListener(this);
        ll_takephoto.setOnClickListener(this);
        ll_backup_contacts.setOnClickListener(this);
        ll_deletedata.setOnClickListener(this);
        switch_open_autoguard.setOnCheckedChangeListener(this);
        switch_locate.setOnCheckedChangeListener(this);
        switch_takephoto.setOnCheckedChangeListener(this);
        switch_backup_contacts.setOnCheckedChangeListener(this);
        switch_deletedata.setOnCheckedChangeListener(this);
    }

    public void setWedgetUnenable() {
        ll_autoguard.setOnClickListener(this);
        ll_unlock_fail_monitor.setOnClickListener(this);
        ll_locate.setOnClickListener(this);
        ll_takephoto.setOnClickListener(this);
        ll_deletedata.setOnClickListener(this);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //LinearLayout onClick
    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        i = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.ll_startautoguard:
                if (sharePreferenceEditor.getSharedPreferences().getBoolean(KEY_IS_ACTIVATE, isActivate)) {
                    dialog();
                    break;
                }
                i.setClass(getActivity(), ActivateActvity.class);
                startActivity(i);
                break;
            case R.id.ll_unlock_fail_monitor:
                DialogUtil.getNumberDialog(getActivity());
                break;
            case R.id.ll_locate:
                onCheckedChanged(switch_locate, !sharePreferenceEditor.getSharedPreferences().getBoolean(IS_LOCATE, false));
                break;
            case R.id.ll_takephoto:
                onCheckedChanged(switch_takephoto, !sharePreferenceEditor.getSharedPreferences().getBoolean(IS_TAKE_PHOTO, false));
                break;
            case R.id.ll_backup_contacts:
                onCheckedChanged(switch_backup_contacts, !sharePreferenceEditor.getSharedPreferences().getBoolean(IS_BACKUP_CONTACTS, false));
                break;
            case R.id.ll_ttrack:
                onCheckedChanged(switch_ttrack, !sharePreferenceEditor.getSharedPreferences().getBoolean(IS_TTRACK, false));
                break;
            case R.id.ll_deletedata:
                onCheckedChanged(switch_deletedata, !sharePreferenceEditor.getSharedPreferences().getBoolean(IS_DELETE_DATA, false));
                break;
        }

    }

    // switch  checked Changed
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.switch_open_autoguard:
                    switchDialog(1, buttonView);
                    break;
                case R.id.switch_locate:
                    if (!sharePreferenceEditor.getSharedPreferences().getBoolean(IS_LOCATE, false))
                        switchDialog(LOCATE_ID, buttonView);
                    break;
                case R.id.switch_takephoto:
                    if (!sharePreferenceEditor.getSharedPreferences().getBoolean(IS_TAKE_PHOTO, false))
                        switchDialog(TAKE_PHOTO_ID, buttonView);
                    break;
                case R.id.switch_backup_contacts:
                    if (!sharePreferenceEditor.getSharedPreferences().getBoolean(IS_BACKUP_CONTACTS, false))
                        switchDialog(BACKUP_CONTACTS_ID, buttonView);
                    break;
                case R.id.switch_ttrack:
                    if (!sharePreferenceEditor.getSharedPreferences().getBoolean(IS_TTRACK, false))
                        switchDialog(TTRACK_ID, buttonView);
                    break;
                case R.id.switch_deletedata:
                    if (!sharePreferenceEditor.getSharedPreferences().getBoolean(IS_DELETE_DATA, false))
                        switchDialog(DELETE_DATA_ID, buttonView);
                    break;
            }
        } else {
            switch (buttonView.getId()) {
                case R.id.switch_open_autoguard:
                    buttonView.setChecked(false);
                    break;
                case R.id.switch_locate:
                    sharePreferenceEditor.getEditor().putBoolean(IS_LOCATE, false).commit();
                    buttonView.setChecked(false);
                    break;
                case R.id.switch_takephoto:
                    sharePreferenceEditor.getEditor().putBoolean(IS_TAKE_PHOTO, false).commit();
                    buttonView.setChecked(false);
                    break;
                case R.id.switch_backup_contacts:
                    sharePreferenceEditor.getEditor().putBoolean(IS_TAKE_PHOTO, false).commit();
                    buttonView.setChecked(false);
                    break;
                case R.id.switch_ttrack:
                    sharePreferenceEditor.getEditor().putBoolean(IS_TTRACK, false).commit();
                    buttonView.setChecked(false);
                    break;
                case R.id.switch_deletedata:
                    sharePreferenceEditor.getEditor().putBoolean(IS_DELETE_DATA, false).commit();
                    buttonView.setChecked(false);
                    break;
            }

        }
    }
// genetate a alertdialog
    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("关闭自动防盗后，屏幕锁依旧生效，但窃贼解锁失败后，安安将不再提供自动防盗保护。");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activateDeviceAdmin.cancelActivate();
                editor.putBoolean(KEY_IS_ACTIVATE, false).commit();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }
// according switchbutton id choose switchdialog
    private  void switchDialog(int i, CompoundButton buttonView) {
        switch (i) {
            case LOCATE_ID:
                DialogUtil.getNumberDialog(getActivity());
                break;
            case TAKE_PHOTO_ID:
                DialogUtil.getEmailDialog(getActivity(), "输入邮箱，发送照片", IS_TAKE_PHOTO, buttonView);
                break;
            case BACKUP_CONTACTS_ID:
                //TODO
                break;
            case TTRACK_ID:
                //TODO
                break;
            case DELETE_DATA_ID:
                //TODO
                break;

        }

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
