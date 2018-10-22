package com.xzq.lib.biz.divider;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.xzq.divider.Divider;
import com.xzq.divider.ItemDivider;
import com.xzq.lib.R;
import com.xzq.lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class DividerActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    private RecyclerView recyclerView;
    private Divider.Builder builder;
    private Rect rect = new Rect();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_divider;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        builder = Divider.with(this)
                .colorRes(R.color.red)
                .dpSize(1);
        recyclerView.addItemDecoration(Divider.create(builder));
//        recyclerView.addItemDecoration(DividerHelper.VERTICAL);

        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        myAdapter.setList(integers);

        this.<SeekBar>findViewById(R.id.sb_size).setOnSeekBarChangeListener(this);
        this.<SeekBar>findViewById(R.id.sb_pl).setOnSeekBarChangeListener(this);
        this.<SeekBar>findViewById(R.id.sb_pr).setOnSeekBarChangeListener(this);
        this.<SeekBar>findViewById(R.id.sb_pt).setOnSeekBarChangeListener(this);
        this.<SeekBar>findViewById(R.id.sb_pb).setOnSeekBarChangeListener(this);

        this.<Spinner>findViewById(R.id.sp_divider_indexes).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    builder.orientation(ItemDivider.VERTICAL);
                    recyclerView.setLayoutManager(new LinearLayoutManager(DividerActivity.this));
                } else {
                    builder.orientation(ItemDivider.HORIZONTAL);
                    recyclerView.setLayoutManager(new LinearLayoutManager(DividerActivity.this, LinearLayoutManager.HORIZONTAL, false));
                }
                recyclerView.removeItemDecorationAt(0);
                recyclerView.addItemDecoration(Divider.create(builder));
                recyclerView.invalidateItemDecorations();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.<Spinner>findViewById(R.id.sp_divider_indexes2).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    builder.colorRes(R.color.red);
                } else if (position == 1) {
                    builder.colorRes(R.color.green);
                } else {
                    builder.colorRes(R.color.blue);
                }
                recyclerView.removeItemDecorationAt(0);
                recyclerView.addItemDecoration(Divider.create(builder));
                recyclerView.invalidateItemDecorations();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.<Spinner>findViewById(R.id.sp_divider_indexes3).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                builder.lastItemNoDivider(position != 0);
                recyclerView.removeItemDecorationAt(0);
                recyclerView.addItemDecoration(Divider.create(builder));
                recyclerView.invalidateItemDecorations();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected String getGithubUrl() {
        return "https://github.com/xzq0125/XzqLib/tree/master/divider";
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sb_size:
                builder.dpSize(progress);
                break;
            case R.id.sb_pl:
                rect.left = progress;
                break;
            case R.id.sb_pr:
                rect.right = progress;
                break;
            case R.id.sb_pt:
                rect.top = progress;
                break;
            case R.id.sb_pb:
                rect.bottom = progress;
                break;
        }
        builder.dpPadding(rect);
        recyclerView.removeItemDecorationAt(0);
        recyclerView.addItemDecoration(Divider.create(builder));
        recyclerView.invalidateItemDecorations();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            if (builder.getOrientation() == ItemDivider.VERTICAL) {
                holder.textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            } else {
                holder.textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
            }
            holder.textView.setText("Item " + position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private List<Integer> list = new ArrayList<>();

        public void setList(List<Integer> list) {
            this.list = list;
            notifyDataSetChanged();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setBackgroundResource(R.color.colorAccent);
            textView.setGravity(Gravity.CENTER);
        }
    }
}
