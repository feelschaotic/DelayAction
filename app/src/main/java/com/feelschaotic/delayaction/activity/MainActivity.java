package com.feelschaotic.delayaction.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.feelschaotic.delayaction.R;
import com.feelschaotic.delayaction.cache.UserConfigCache;
import com.feelschaotic.delayaction.valid.LoginValid;
import com.feelschaotic.delayaction.valid.PermissionsValid;
import com.feelschaotic.lib.Action;
import com.feelschaotic.lib.call.SingleCall;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.follow_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleCall.getInstance()
                        .addAction(new Action() {
                            @SuppressLint("WrongConstant")
                            @Override
                            public void call() {
                                Toast.makeText(MainActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addValid(new LoginValid(MainActivity.this))
                        .doCall();
            }
        });

        findViewById(R.id.open_target_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleCall.getInstance()
                        .addAction(new Action() {
                            @Override
                            public void call() {
                                TargetActivity.startActivity(MainActivity.this);
                            }
                        })
                        .addValid(new LoginValid(MainActivity.this))
                        .addValid(new PermissionsValid(MainActivity.this))
                        .doCall();

            }
        });

        findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserConfigCache.setLogin(MainActivity.this, false);
            }
        });

        findViewById(R.id.logout_valid_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserConfigCache.setPermissions(MainActivity.this, false);
            }
        });
    }
}
