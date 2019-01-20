package com.xzq.lib.biz.pullupdown;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xzq.lib.R;
import com.xzq.lib.base.BaseActivity;

public class ViewGroupDragActivity extends BaseActivity {

    private PullDownCloseView vg2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_group_drag;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());
        vg2 = findViewById(R.id.vg2);
    }

    @Override
    public void onBackPressed() {
        if (vg2.show()) {
            super.onBackPressed();
        }
    }

    @Override
    protected String getGithubUrl() {
        return null;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            if (holder.itemView instanceof TextView) {
                ((TextView) holder.itemView).setText(position + "");
            }
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
