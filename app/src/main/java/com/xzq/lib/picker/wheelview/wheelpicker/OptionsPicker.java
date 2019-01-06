package com.xzq.lib.picker.wheelview.wheelpicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xzq.lib.R;
import com.xzq.lib.picker.wheelview.ListWheelAdapter;
import com.xzq.lib.picker.wheelview.WheelView;

import java.util.List;


/**
 * 条件选择器
 * Created by Alex on 2015/11/16.
 */
public class OptionsPicker extends LinearLayout implements WheelView.OnWheelScrollListener {

    private WheelView wvOption1;
    private WheelView wvOption2;
    private WheelView wvOption3;
    private boolean hasTarget = false;
    private String targetText = null;

    public OptionsPicker(Context context) {
        super(context);
        initView(context, null, 0);
    }

    public OptionsPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public OptionsPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public OptionsPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        super.setOrientation(HORIZONTAL);
        removeAllViews();
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        wvOption1 = new WheelView(context, attrs, defStyleAttr);
        wvOption2 = new WheelView(context, attrs, defStyleAttr);
        wvOption3 = new WheelView(context, attrs, defStyleAttr);
        wvOption1.addScrollingListener(this);
        wvOption2.addScrollingListener(this);
        wvOption3.addScrollingListener(this);
        addView(wvOption1, lp);
        addView(wvOption2, lp);
        addView(wvOption3, lp);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.OptionsPicker);
        hasTarget = typedArray.getBoolean(R.styleable.OptionsPicker_hasTarget, false);
        if (hasTarget) {
            targetText = typedArray.getString(R.styleable.OptionsPicker_targetText);
        }
        typedArray.recycle();
    }

    public void setPicker(final List options) {
        setPicker(options, null, null, false);
    }

    public void setPicker(final List optionsOne,
                          final List<List> optionsTwo, boolean linkage) {
        setPicker(optionsOne, optionsTwo, null, linkage);
    }

    public void setPicker(final List optionsOne,
                          final List<List> optionsTwo,
                          final List<List<List>> optionsThree,
                          boolean linkage) {
        // 选项1
        wvOption1.setViewAdapter(new ListWheelAdapter(getContext(), optionsOne));
        wvOption1.setCurrentItem(0);
        // 选项2
        if (optionsTwo != null) {
            wvOption2.setViewAdapter(new ListWheelAdapter(getContext(), optionsTwo.get(0)));
            wvOption2.setCurrentItem(0);
            wvOption2.setVisibility(View.VISIBLE);
            if (linkage) {
                // 联动监听器
                wvOption1.addChangingListener(new WheelView.OnWheelChangedListener() {
                    @Override
                    public void onChanged(WheelView wheel, int oldValue, int newValue) {
                        wvOption2.setViewAdapter(new ListWheelAdapter(getContext(),
                                optionsTwo.get(wvOption1.getCurrentItem())));
                        wvOption2.setCurrentItem(0);
                        if (optionsThree != null) {
                            wvOption3.setViewAdapter(new ListWheelAdapter(getContext(),
                                    optionsThree.get(wvOption1.getCurrentItem()).get(wvOption2.getCurrentItem())));
                            wvOption3.setCurrentItem(0);
                        }
                    }
                });
            }
            addTargertIfNecessary(wvOption2.getContext(), 1);
        } else {
            wvOption2.setVisibility(View.GONE);
        }
        // 选项3
        if (wvOption2.getVisibility() == View.VISIBLE && optionsThree != null && optionsThree.size() > 0) {
            wvOption3.setViewAdapter(new ListWheelAdapter(getContext(), optionsThree.get(0).get(0)));
            wvOption3.setCurrentItem(0);
            wvOption3.setVisibility(View.VISIBLE);
            if (linkage) {
                // 联动监听器
                wvOption2.addChangingListener(new WheelView.OnWheelChangedListener() {
                    @Override
                    public void onChanged(WheelView wheel, int oldValue, int newValue) {
                        wvOption3.setViewAdapter(new ListWheelAdapter(getContext(),
                                optionsThree.get(wvOption1.getCurrentItem()).get(wvOption2.getCurrentItem())));
                        wvOption3.setCurrentItem(0);
                    }
                });
            }
            addTargertIfNecessary(wvOption3.getContext(), 3);
        } else {
            wvOption3.setVisibility(View.GONE);
        }
    }

    public void setPicker(final List optionsOne, final List optionsTwo, final List optionsThree) {
        // 选项1
        wvOption1.setViewAdapter(new ListWheelAdapter(getContext(), optionsOne));
        wvOption1.setCurrentItem(0);
        // 选项2
        if (optionsTwo == null)
            wvOption2.setVisibility(GONE);
        else {
            wvOption2.setViewAdapter(new ListWheelAdapter(getContext(), optionsTwo));
            wvOption2.setCurrentItem(0);
            addTargertIfNecessary(wvOption2.getContext(), 1);
        }
        // 选项3
        if (optionsTwo == null)
            wvOption3.setVisibility(GONE);
        else {
            wvOption3.setViewAdapter(new ListWheelAdapter(getContext(), optionsThree));
            wvOption3.setCurrentItem(0);
            addTargertIfNecessary(wvOption3.getContext(), 3);
        }
    }

    private void addTargertIfNecessary(Context context, int index) {
        if (!hasTarget)
            return;

        TextView textView = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setText(targetText);
        addView(textView, index, layoutParams);
    }

    /**
     * 设置选项的单位
     *
     * @param label1 单位
     * @param label2 单位
     * @param label3 单位
     */
    public void setLabels(String label1, String label2, String label3) {
        if (label1 != null)
            wvOption1.setText(label1);
        if (label2 != null)
            wvOption2.setText(label2);
        if (label3 != null)
            wvOption3.setText(label3);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic 是否可循环
     */
    public void setCyclic(boolean cyclic) {
        wvOption1.setCyclic(cyclic);
        wvOption2.setCyclic(cyclic);
        wvOption3.setCyclic(cyclic);
    }

    /**
     * 返回当前选中的结果对应的位置数组 因为支持三级联动效果，分三个级别索引，0，1，2
     *
     * @return Location
     */
    public int[] getCurrentItems() {
        int[] currentItems = new int[3];
        currentItems[0] = wvOption1.getCurrentItem();
        currentItems[1] = wvOption2.getVisibility() == View.VISIBLE ? wvOption2.getCurrentItem() : -1;
        currentItems[2] = wvOption3.getVisibility() == View.VISIBLE ? wvOption3.getCurrentItem() : -1;
        return currentItems;
    }

    /**
     * 设置选中位置
     *
     * @param option1 选中位置
     * @param option2 选中位置
     * @param option3 选中位置
     */
    public void setCurrentItems(int option1, int option2, int option3) {
        wvOption1.setCurrentItem(option1);
        wvOption2.setCurrentItem(option2);
        wvOption3.setCurrentItem(option3);
    }


    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(HORIZONTAL);
    }

    private OnCurrentItemSelectedListener listener;

    public void setOnCurrentItemSelectedListener(OnCurrentItemSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScrollingStarted(WheelView wheel) {

    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
        if (listener != null) {
            listener.onScrollingFinished(wheel);
        }
    }

    public WheelView getWvOption1() {
        return wvOption1;
    }

    public WheelView getWvOption2() {
        return wvOption2;
    }

    public WheelView getWvOption3() {
        return wvOption3;
    }

    public interface OnCurrentItemSelectedListener {

        void onScrollingFinished(WheelView wheel);
    }
}
