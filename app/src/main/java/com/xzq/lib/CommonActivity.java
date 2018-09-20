package com.xzq.lib;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class CommonActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return getIntent().getIntExtra("layoutId", 0);
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

    }
}
