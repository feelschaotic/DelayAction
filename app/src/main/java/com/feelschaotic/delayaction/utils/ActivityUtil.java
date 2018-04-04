package com.feelschaotic.delayaction.utils;

import android.app.Activity;

import com.feelschaotic.delayaction.activity.LoginActivity;
import com.feelschaotic.delayaction.activity.PermissionsActivity;


public class ActivityUtil {
    public static void jumpToLogin(Activity mActivity) {
        LoginActivity.start(mActivity);
    }

    public static void jumpToPermissions(Activity mActivity) {
        PermissionsActivity.start(mActivity);
    }
}
