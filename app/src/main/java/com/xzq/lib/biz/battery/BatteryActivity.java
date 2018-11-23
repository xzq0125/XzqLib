package com.xzq.lib.biz.battery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xzq.battery.BatteryView;
import com.xzq.lib.R;
import com.xzq.lib.base.BaseActivity;

public class BatteryActivity extends BaseActivity implements Runnable {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_battery;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        batteryView = findViewById(R.id.batteryView);
    }

    @Override
    protected String getGithubUrl() {
        return "https://github.com/xzq0125/XzqLib/tree/master/batteryview";
    }

    BatteryView batteryView;
    int count = 0;

    public void refresh(View view) {
        batteryView.post(this);
    }

    public void stop(View view) {
        batteryView.removeCallbacks(this);
    }

    @Override
    public void run() {
        count++;
        if (count > 100) {
            count = 0;
        }
        batteryView.setPower(count);
        batteryView.postDelayed(this, 50);
    }

    @Override
    protected void onDestroy() {
        stop(null);
        super.onDestroy();
    }

}
