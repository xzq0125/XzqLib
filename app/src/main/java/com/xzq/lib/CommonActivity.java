package com.xzq.lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CommonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getIntent().getIntExtra("layoutId", 0);
        if (layoutId <= 0) {
            finish();
            return;
        }
        setContentView(layoutId);
    }
}
