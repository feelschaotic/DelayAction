package com.feelschaotic.delayaction.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.feelschaotic.delayaction.R;
import com.feelschaotic.delayaction.cache.UserConfigCache;
import com.feelschaotic.lib.call.SingleCall;


public class PermissionsActivity extends Activity {

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, PermissionsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                Toast.makeText(PermissionsActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
                UserConfigCache.setPermissions(PermissionsActivity.this, true);
                //这里继续
                SingleCall.getInstance().doCall();
                finish();
            }
        });
    }
}
