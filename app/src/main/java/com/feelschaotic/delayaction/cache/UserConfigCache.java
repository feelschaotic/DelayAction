package com.feelschaotic.delayaction.cache;

import android.content.Context;
import android.content.SharedPreferences;


public class UserConfigCache {
    private static final String PREFERENCE_FILE = "user_config_cache";

    private static SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
    }

    private static final String IS_LOGIN = "is_login";
    private static final String IS_PERMISSIONS = "is_permissions";

    public static boolean isLogin(Context context) {
        return getPreference(context).getBoolean(IS_LOGIN, false);
    }

    public static void setLogin(Context context, boolean logged) {
        getPreference(context).edit().putBoolean(IS_LOGIN, logged).apply();
    }


    public static boolean isPermissions(Context context) {
        return getPreference(context).getBoolean(IS_PERMISSIONS, false);
    }

    public static void setPermissions(Context context, boolean permissions) {
        getPreference(context).edit().putBoolean(IS_PERMISSIONS, permissions).apply();
    }

}
