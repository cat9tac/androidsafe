package com.cat9tac.androidsafe.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_ypfu on 2016/3/23.
 */
public class UIEnableUtil {

    public static void disableView(List<View> vList) {
        for (View view : vList) {
            view.setEnabled(false);
        }

    }

    public static void enableView(List<View> vList) {
        for (View view : vList) {
            view.setEnabled(true);
        }

    }

    public static List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<View>();
        if (view instanceof ViewGroup&&!(view instanceof RelativeLayout)) {
            // filter the View undefined and wedget in RelativeLayout
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchildren = vp.getChildAt(i);
                if (viewchildren.getId() != -1&&!(viewchildren instanceof RelativeLayout)) {
                    allchildren.add(viewchildren);
                }
                    allchildren.addAll(getAllChildViews(viewchildren));

            }
        }

        return allchildren;

    }
}