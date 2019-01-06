package com.xzq.lib.biz.picker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.xzq.lib.R;
import com.xzq.lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class PickerActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picker;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        WheelView wheelView = findViewById(R.id.wheelview);

        wheelView.setCyclic(false);
        wheelView.setDividerType(WheelView.DividerType.WRAP);
        wheelView.setIsOptions(true);
       // wheelView.setTotalScrollY(500);
       // wheelView.setLabel("lab");
        wheelView.setLineSpacingMultiplier(2f);
       // wheelView.setTextColorCenter(0xFFffd015);
       // wheelView.setTextColorOut(0xFFffd015);
        wheelView.setGravity(Gravity.CENTER);
        wheelView.setTextSize(24f);
        wheelView.setTextXOffset(0);
        wheelView.setDividerColor(Color.TRANSPARENT);

        final List<String> mOptionsItems = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            mOptionsItems.add("item " + i);
        }

        wheelView.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return mOptionsItems.size();
            }

            @Override
            public Object getItem(int index) {
                return mOptionsItems.get(index);
            }

            @Override
            public int indexOf(Object o) {
                return mOptionsItems.indexOf(o);
            }
        });
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                //Toast.makeText(PickerActivity.this, "" + mOptionsItems.get(index), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected String getGithubUrl() {
        return null;
    }
}
