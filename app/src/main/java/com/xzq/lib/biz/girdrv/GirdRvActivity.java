package com.xzq.lib.biz.girdrv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xzq.lib.R;
import com.xzq.lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class GirdRvActivity extends BaseActivity {

    private GirdAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gird_rv;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        adapter = new GirdAdapter();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setAdapter(adapter);
        adapter.setData(getData());
    }

    @Override
    protected String getGithubUrl() {
        return null;
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        final int spanCount = 6;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                boolean isTitle = adapter.getDataAt(position).isTitle;
                return isTitle ? spanCount : position < 10 ? 6 : position < 30 ? 2 : 3;
            }
        });
        return gridLayoutManager;
    }

    private List<ItemDto> getData() {
        List<ItemDto> list = new ArrayList<>();
        for (int i = 0; i < 105; i++) {
            ItemDto dto = new ItemDto();
            dto.isTitle = i % 9 == 0;
            list.add(dto);
        }
        return list;
    }
}
