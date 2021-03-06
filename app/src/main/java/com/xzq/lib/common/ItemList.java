package com.xzq.lib.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;

import com.xzq.lib.biz.adapt.AdaptScreenActivity;
import com.xzq.lib.biz.anr.AnrActivity;
import com.xzq.lib.biz.battery.BatteryActivity;
import com.xzq.lib.biz.book.BookActivity;
import com.xzq.lib.biz.camera.MainActivity2;
import com.xzq.lib.biz.divider.DividerActivity;
import com.xzq.lib.biz.divider_linearlayout.DividerLinearLayoutActivity;
import com.xzq.lib.biz.drawable_text_view.DrawableTextViewActivity;
import com.xzq.lib.biz.girdrv.DyGirdRvActivity;
import com.xzq.lib.biz.girdrv.GirdRvActivity;
import com.xzq.lib.biz.picker.PickerActivity;
import com.xzq.lib.biz.pullupdown.ViewGroupDragActivity;
import com.xzq.lib.biz.record.RecordActivity;

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
        itemList.add(new Item("可去除分割线的LinearLayout", DividerLinearLayoutActivity.class));
        itemList.add(new Item("DrawableTextView", DrawableTextViewActivity.class));
        itemList.add(new Item("RecyclerView分割线", DividerActivity.class));
        itemList.add(new Item("电池电量显示控件", BatteryActivity.class));
        itemList.add(new Item("PickerView", PickerActivity.class));
        itemList.add(new Item("相机", MainActivity2.class));
        itemList.add(new Item("录音", RecordActivity.class));
        itemList.add(new Item("下拉关闭", ViewGroupDragActivity.class));
        itemList.add(new Item("屏幕适配", AdaptScreenActivity.class));
        itemList.add(new Item("GirdRvActivity", GirdRvActivity.class));
        itemList.add(new Item("DyGirdRvActivity", DyGirdRvActivity.class));
//        itemList.add(new Item("CardViewActivity", CardViewActivity.class));
        itemList.add(new Item("翻书控件", BookActivity.class));
        itemList.add(new Item("ANR定位", AnrActivity.class));
    }

}
