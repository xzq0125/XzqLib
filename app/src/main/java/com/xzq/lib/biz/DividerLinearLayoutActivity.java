package com.xzq.lib.biz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.xzq.dividerlinearlayout.DividerLinearLayout;
import com.xzq.lib.BaseActivity;
import com.xzq.lib.R;

public class DividerLinearLayoutActivity extends BaseActivity {

    private DividerLinearLayout mDividerLinearLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_divider_linear_layout;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        mDividerLinearLayout = findViewById(R.id.dividerLinearLayout);
        this.<Spinner>findViewById(R.id.sp_index).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDividerLinearLayout.setNonDividerIndexes(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}







