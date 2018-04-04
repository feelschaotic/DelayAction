package com.feelschaotic.delayaction.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.feelschaotic.delayaction.R;

public class TargetActivity extends Activity {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, TargetActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
    }


}
