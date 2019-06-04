package com.xzq.lib.biz.girdrv;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.xzq.baseadapter.BaseRecyclerAdapter;
import com.xzq.lib.R;

import java.util.List;

/**
 * GirdAdapter
 * Created by Tse on 2019/5/17.
 */
public class GirdAdapter extends BaseRecyclerAdapter<ItemDto, GirdViewHolder> {

    @NonNull
    @Override
    public GirdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @Nullable View itemView, int viewType) {
        return new GirdViewHolder(itemView);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        switch (viewType) {
            default:
            case 0:
                return R.layout.item_title;
            case 1:
                return R.layout.item_gird;
        }
    }

    @Override
    public void onConvert(@NonNull GirdViewHolder holder, ItemDto data, int position, @NonNull List<Object> payload) {
        holder.setData(data);
    }

    @Override
    public int getItemViewType(int position) {
        ItemDto d = getDataAt(position);
        return d.isTitle ? 0 : 1;
    }
}
