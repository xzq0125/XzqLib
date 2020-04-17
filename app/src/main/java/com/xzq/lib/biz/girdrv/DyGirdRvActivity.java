package com.xzq.lib.biz.girdrv;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.xzq.baseadapter.BaseRecyclerAdapter;
import com.xzq.baseadapter.BaseRecyclerViewHolder;
import com.xzq.lib.R;
import com.xzq.lib.base.BaseActivity;
import com.xzq.lib.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class DyGirdRvActivity extends BaseActivity {

    private final MyAdapter adapter = new MyAdapter();
    private GridLayoutManager gridLayoutManager;
    private int maxHeight = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gird_rv2;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setAdapter(adapter);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int rvHeight = recyclerView.getHeight();
                maxHeight = rvHeight;
                LogUtils.debugXzq("rvHeight = " + rvHeight);
                adapter.setData(getFirstData(rvHeight));
            }
        });
    }

    @Override
    protected String getGithubUrl() {
        return null;
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        final int spanCount = 6;
        gridLayoutManager = new GridLayoutManager(this, spanCount);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemCount = adapter.getItemCount();
                if (itemCount == 1) {
                    return spanCount;
                }
                if (itemCount % 2 == 0) {
                    return spanCount / 2;
                } else {
                    return position == 0 ? spanCount : (spanCount / itemCount < 5 ? 2 : 3);
                }
            }
        });
        return gridLayoutManager;
    }

    private List<Item> getFirstData(int rvHeight) {
        List<Item> list = new ArrayList<>();
        list.add(new Item(rvHeight));
        return list;
    }

    public void addItem(View view) {
        if (adapter.getItemCount() == 10) {
            ToastUtils.showShort("最多添加10个");
            return;
        }
        List<Item> items = adapter.getData();
        items.add(new Item());
        calcItemHeight();
        adapter.notifyItemRangeChanged(0, adapter.getItemCount());
    }

    public void removeItem(View view) {
        if (adapter.getItemCount() > 0) {
            int removePos = 0;
            adapter.getData().remove(removePos);
            calcItemHeight();
            adapter.notifyItemRemoved(removePos);
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        }
    }

    private void calcItemHeight() {
        List<Item> items = adapter.getData();
        int total = items.size();
        if (total <= 0) {
            return;
        }
        if (total == 1 || total == 2) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).itemHeight = maxHeight;
            }
        } else if (total == 3) {
            int topHeight = maxHeight * 6 / 10;
            for (int i = 0; i < items.size(); i++) {
                if (i == 0) {
                    items.get(i).itemHeight = topHeight;
                } else {
                    items.get(i).itemHeight = maxHeight - topHeight;
                }
            }
        } else if (total == 4) {
            int topHeight = maxHeight / 2;
            for (int i = 0; i < items.size(); i++) {
                items.get(i).itemHeight = topHeight;
            }
        } else {
            int topHeight = maxHeight / (total / 2);
            for (int i = 0; i < items.size(); i++) {
                items.get(i).itemHeight = topHeight;
            }
        }
    }

    public void getViews(View view) {
        for (int i = 0; i < adapter.getItemCount(); i++) {
            View itemView = gridLayoutManager.findViewByPosition(i);
            LogUtils.debugXzq("itemView[" + i + "] = " + itemView);
        }
    }

    private static class MyViewHolder extends BaseRecyclerViewHolder<Item> {

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(Item data) {
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.height = data.itemHeight;
            itemView.setLayoutParams(lp);
        }
    }

    private static class MyAdapter extends BaseRecyclerAdapter<Item, MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @Nullable View itemView, int viewType) {
            return new MyViewHolder(itemView);
        }

        @Override
        protected int getItemLayoutId(int viewType) {
            return R.layout.item_gird;
        }

        @Override
        public void onConvert(@NonNull MyViewHolder holder, Item data, int position, @NonNull List<Object> payload) {
            holder.setData(data);
        }
    }

    private static class Item {
        int itemHeight = 0;

        public Item() {
        }

        public Item(int itemHeight) {
            this.itemHeight = itemHeight;
        }
    }
}
