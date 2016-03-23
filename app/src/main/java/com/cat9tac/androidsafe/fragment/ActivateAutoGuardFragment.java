package com.cat9tac.androidsafe.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cat9tac.androidsafe.R;
import com.cat9tac.androidsafe.util.ActivateDeviceAdmin;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActivateAutoGuardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActivateAutoGuardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivateAutoGuardFragment extends Fragment {
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
    private Button btnActivateAutoGurad;
    //must be static else you can't open the activate activity

    private ActivateDeviceAdmin activateDeviceAdmin;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean isActivate;
    public ActivateAutoGuardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivateAutoGuardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivateAutoGuardFragment newInstance(String param1, String param2) {
        ActivateAutoGuardFragment fragment = new ActivateAutoGuardFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_activate, container, false);
        init(v);


        return v;
    }

    private void init(View v) {
        sharedPreferences = getActivity().getSharedPreferences("state.activate", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        activateDeviceAdmin=new ActivateDeviceAdmin(getActivity());
        btnActivateAutoGurad= (Button) v.findViewById(R.id.btn_activate_auto_guard);
        btnActivateAutoGurad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = activateDeviceAdmin.getActivateIntent();
                startActivityForResult(intent,1);
                boolean x=activateDeviceAdmin.isActivate;

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //-1 is activate
        //0 is cancel activate
        if(resultCode==-1){
            editor.putBoolean(KEY_IS_ACTIVATE, true).commit();
            onDestroyView();
            Log.i("DDD","onActivityResultis commit");
            //getActivity().finish();
        }
        if(resultCode==0){
            boolean b=editor.putBoolean(KEY_IS_ACTIVATE, false).commit();

        }

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
