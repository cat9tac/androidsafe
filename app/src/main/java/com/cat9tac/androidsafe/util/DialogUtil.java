package com.cat9tac.androidsafe.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cat9tac.androidsafe.R;

/**
 * Created by v_ypfu on 2016/3/22.
 */
public class DialogUtil {
    private static String emailAddress;
    private static SharePreferenceEditor sharePreferenceEditor;

    public void getDialog(Context context, String s) {

    }
    Handler hander=new Handler(){

    };

    //
    // create a dialog to get the email user input
    public static void getEmailDialog(final Context context, final String content, final String vname, final CompoundButton buttonView) {
        final EditText et_info = new EditText(context);
        sharePreferenceEditor = new SharePreferenceEditor(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(content);
        builder.setView(et_info);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                emailAddress = et_info.getText().toString();
                sharePreferenceEditor.getEditor().putString("email", emailAddress).commit();
                sharePreferenceEditor.getEditor().putBoolean(vname, true).commit();
                buttonView.setChecked(true);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                sharePreferenceEditor.getEditor().putString("email", null).commit();
                sharePreferenceEditor.getEditor().putBoolean(vname, false).commit();
                buttonView.setChecked(false);
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

    public static void getNumberDialog(final Context context, final TextView view) {
        View v = View.inflate(context, R.layout.dialog_choose_number, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final Dialog dialog=builder.create();
        builder.setView(v);
        sharePreferenceEditor = new SharePreferenceEditor(context);
        Integer number;
        final TextView tv_number;
        ImageButton ib_plus, ib_sub;
        tv_number = (TextView) v.findViewById(R.id.tv_number);
        tv_number.setText(""+sharePreferenceEditor.getSharedPreferences().getInt("FAIL_TIME", 2));
        ib_plus = (ImageButton) v.findViewById(R.id.ib_plus);
        ib_sub = (ImageButton) v.findViewById(R.id.ib_sub);
        number = Integer.parseInt(tv_number.getText().toString());
        ib_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = sharePreferenceEditor.getSharedPreferences().getInt("FAIL_TIME", 2);
                i += 1;
                tv_number.setText("" + i);
                sharePreferenceEditor.getEditor().putInt("FAIL_TIME", i).commit();

            }
        });
        ib_sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int i = sharePreferenceEditor.getSharedPreferences().getInt("FAIL_TIME", 2);
                if (i > 0)
                    i -= 1;
                tv_number.setText("" + i);
                sharePreferenceEditor.getEditor().putInt("FAIL_TIME", i).commit();
                ;

            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                view.setText(""+sharePreferenceEditor.getSharedPreferences().getInt("FAIL_TIME", 2)+"次");
        }});
        builder.create().show();

    }

}
