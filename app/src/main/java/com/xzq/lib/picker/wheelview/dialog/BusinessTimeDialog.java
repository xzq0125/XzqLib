package com.xzq.lib.picker.wheelview.dialog;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sean on 2016/3/22.
 */
public class BusinessTimeDialog extends CommonDequePickerDialog {

    private OnBusinessTimeSelectedListener listener;

    public BusinessTimeDialog(Context context, String title) {
        super(context);
        setTitle(title);
    }

    public void setData(ArrayList<String> leftHous, List<List> leftMinutes,
                        ArrayList<String> rightHours, List<List> rightMinutes, int[] selectedIndex) {

        setLeftPicker(leftHous, leftMinutes, true);
        setRightPicker(rightHours, rightMinutes, true);
        if (selectedIndex != null && selectedIndex.length == 4) {
            setSelectedItems(selectedIndex[0], selectedIndex[1], selectedIndex[2], selectedIndex[3]);
        }
    }

    @Override
    protected void onSelected(int option1, int option2, int option3, int option4) {
        super.onSelected(option1, option2, option3, option4);
        dismiss();
        if (listener != null) {
            listener.onBusinessTimeSelected(option1, option2, option3, option4);
        }
    }

    public void setOnBusinessTimeSelectedListener(OnBusinessTimeSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnBusinessTimeSelectedListener {
        void onBusinessTimeSelected(int option1, int option2, int option3, int option4);
    }
}
