package com.xzq.lib.common;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xzq.lib.base.BaseActivity;

public class CommonActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return getIntent().getIntExtra("layoutId", 0);
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

    }
}
