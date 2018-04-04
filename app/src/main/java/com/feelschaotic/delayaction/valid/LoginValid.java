package com.feelschaotic.delayaction.valid;

import android.app.Activity;

import com.feelschaotic.delayaction.utils.ActivityUtil;
import com.feelschaotic.delayaction.cache.UserConfigCache;
import com.feelschaotic.lib.Valid;


public class LoginValid implements Valid {
    private Activity mActivity;

    public LoginValid(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public boolean preCheck() {
        return UserConfigCache.isLogin(mActivity);
    }

    @Override
    public void doValid() {
        ActivityUtil.jumpToLogin(mActivity);
    }
}
