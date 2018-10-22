package com.xzq.lib.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.xzq.lib.common.ItemList;
import com.xzq.lib.R;
import com.xzq.lib.base.BaseActivity;

import java.util.Map;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private SimpleAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        ListView listView = findViewById(android.R.id.list);
        adapter = new SimpleAdapter(this, new ItemList().init(),
                android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected String getGithubUrl() {
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object data = adapter.getItem(position);
        if (data instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) data;
            Object item = map.get("item");
            if (item instanceof ItemList.Item) {
                ((ItemList.Item) item).startActivity(this);
            }
        }
    }
}
