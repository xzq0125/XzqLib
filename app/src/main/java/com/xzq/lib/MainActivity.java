package com.xzq.lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(android.R.id.list);
        adapter = new SimpleAdapter(this, new ItemList().init(),
                android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
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
