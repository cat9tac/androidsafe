package com.cat9tac.androidsafe.util;

import android.util.Log;

/**
 * Created by v_ypfu on 2016/3/30.
 */
public class Loger {
    private static boolean isOpenLoger=true;
    private static String TAG="DDD";


    public static void logd(String message) {
        if (isOpenLoger) {
            Log.d(TAG, message);
        }
    }
}
