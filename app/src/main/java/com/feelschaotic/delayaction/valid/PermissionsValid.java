package com.feelschaotic.delayaction.valid;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.feelschaotic.delayaction.cache.UserConfigCache;
import com.feelschaotic.delayaction.utils.ActivityUtil;
import com.feelschaotic.lib.Valid;


public class PermissionsValid implements Valid {
    private Activity mActivity;

    public PermissionsValid(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public boolean preCheck() {
        return UserConfigCache.isPermissions(mActivity);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void doValid() {
        ActivityUtil.jumpToPermissions(mActivity);
    }
}
