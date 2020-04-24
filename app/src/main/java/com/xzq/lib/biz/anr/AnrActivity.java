package com.xzq.lib.biz.anr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xzq.lib.R;
import com.xzq.lib.base.BaseActivity;
import com.xzq.lib.utils.AppUtils;
import com.xzq.lib.utils.LogUtils;

public class AnrActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_anr;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected String getGithubUrl() {
        return null;
    }

    private void testAnr() {
        LogUtils.debugXzq("start testAnr");
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.debugXzq("end testAnr");
    }

    public void startAnr(View view) {
        testAnr();
    }

    private int num = 0;

    public void increaseNum(View view) {
        TextView tvNum = findViewById(R.id.tv_num);
        tvNum.setText(String.valueOf(++num));
    }

    public void doc(View view) {
        AppUtils.openBrowser(this, "https://blog.csdn.net/ha_cjy/article/details/83026367");
    }
}
