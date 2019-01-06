package com.xzq.lib.picker.wheelview.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * 营业时间/配送时间 选取对话框Fragment
 * Created by sean on 2016/3/22.
 */
public class BusinessTimeDialogFragment extends DialogFragment {

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_LEFTHOUR = "left_hour";
    public static final String EXTRA_LEFTMINUTE = "left_minute";
    public static final String EXTRA_RIGHTHOUR = "right_hour";
    public static final String EXTRA_RIGHTMINUTE = "right_minute";
    public static final String EXTRA_SELECTINDEX = "selectindex";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BusinessTimeDialog businessTimeDialog = new BusinessTimeDialog(getActivity(), getArguments().getString(EXTRA_TITLE));

        ArrayList<String> leftHours = getArguments().getStringArrayList(EXTRA_LEFTHOUR);
        List<List> leftMinutes = (List<List>) getArguments().getSerializable(EXTRA_LEFTMINUTE);

        ArrayList<String> rightHours = getArguments().getStringArrayList(EXTRA_RIGHTHOUR);
        List<List> rightMinutes = (List<List>) getArguments().getSerializable(EXTRA_RIGHTMINUTE);

        int[] selectedIndex = getArguments().getIntArray(EXTRA_SELECTINDEX);

        businessTimeDialog.setData(leftHours, leftMinutes, rightHours, rightMinutes, selectedIndex);

        if (getActivity() instanceof BusinessTimeDialog.OnBusinessTimeSelectedListener) {
            businessTimeDialog.setOnBusinessTimeSelectedListener((BusinessTimeDialog.OnBusinessTimeSelectedListener) getActivity());
        }
        return businessTimeDialog;
    }
}
