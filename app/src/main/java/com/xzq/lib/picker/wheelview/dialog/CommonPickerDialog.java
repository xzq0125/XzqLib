package com.xzq.lib.picker.wheelview.dialog;

import android.content.Context;

import java.util.List;

/**
 * 通用条件选择器
 * Created by sean on 2016/1/6.
 */
public class CommonPickerDialog extends CenterCommonPickerDialog {

    private final OnContentSelectedListener listener;

    public CommonPickerDialog(Context context, String title, OnContentSelectedListener listener) {
        super(context);
        this.listener = listener;
        setTitle(title);
    }

    public void setData(List<?> dataList) {
        setPicker(dataList, null, null, true);
    }

    @Override
    protected void onSelected(int option1, int option2, int option3) {
        super.onSelected(option1, option2, option3);
        dismiss();
        if (listener != null) {
            listener.onContentSelected(option1);
        }
    }

    public interface OnContentSelectedListener {
        void onContentSelected(int pos);
    }
}
