package com.xzq.lib;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ItemList
 * Created by sean on 2016/11/2.
 */
public class ItemList {

    private List<Map<String, Object>> items;
    private final List<Item> itemList = new ArrayList<>();

    public List<Map<String, Object>> init() {
        if (items == null)
            items = new ArrayList<>();

        initItemCount();
        Collections.reverse(itemList);
        for (Item item : itemList) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", item.title);
            map.put("item", item);
            items.add(map);
        }

        return items;
    }

    public static class Item {
        public String title;
        public Class<?> cls;
        public int layoutId;

        public Item(String title, Class<?> cls) {
            this.title = title;
            this.cls = cls;
        }

        public Item(String title, @LayoutRes int layoutId) {
            this.title = title;
            this.layoutId = layoutId;
            this.cls = CommonActivity.class;
        }

        public void startActivity(Context context) {
            context.startActivity(new Intent(context, cls)
                    .putExtra("title", title)
                    .putExtra("layoutId", layoutId));
        }
    }

    private void initItemCount() {
        itemList.add(new Item("DividerLinearLayout", R.layout.activity_dividerlinearlayout));
    }

}