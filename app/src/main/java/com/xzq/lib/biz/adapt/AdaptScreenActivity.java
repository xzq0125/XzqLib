package com.xzq.lib.biz.adapt;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.xzq.lib.R;
import com.xzq.lib.base.BaseActivity;
import com.xzq.lib.utils.LogUtils;
import com.xzq.lib.utils.SizeUtils;

public class AdaptScreenActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adapt_screen;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        //px = density * dp;
        int widthPixels = SizeUtils.widthPixels(this);
        int heightPixels = SizeUtils.heightPixels(this);
        LogUtils.debugXzq("widthPixels = " + widthPixels);
        LogUtils.debugXzq("heightPixels = " + heightPixels);
        float density = getResources().getDisplayMetrics().density;
        int widthDp = (int) (widthPixels / density);
        LogUtils.debugXzq("density = " + density);
        LogUtils.debugXzq("widthDp = " + widthDp);
    }

    @Override
    protected String getGithubUrl() {
        return null;
    }

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080);
    }
}
