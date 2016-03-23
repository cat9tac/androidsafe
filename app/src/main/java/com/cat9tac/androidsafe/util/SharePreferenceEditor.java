package com.cat9tac.androidsafe.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by v_ypfu on 2016/3/22.
 */
public class SharePreferenceEditor  {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public  SharePreferenceEditor(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("state.activate",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        this.sharedPreferences=sharedPreferences;
        this.editor=editor;

    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }
}
