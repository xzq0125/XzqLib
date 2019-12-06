package com.xzq.lib.biz.girdrv;

import android.view.View;
import android.widget.TextView;

import com.xzq.baseadapter.BaseRecyclerViewHolder;
import com.xzq.lib.R;

/**
 * GirdViewHolder
 * Created by Tse on 2019/5/17.
 */
public class GirdViewHolder extends BaseRecyclerViewHolder<ItemDto> {

    private final TextView tvTitle;

    public GirdViewHolder(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_title);
    }

    @Override
    public void setData(ItemDto data) {
        if (tvTitle != null) {
            tvTitle.setText(data.title + "  position = " + position);
        }
    }
}
