package com.xzq.lib.picker.wheelview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.xzq.lib.R;
import com.xzq.lib.picker.wheelview.wheelpicker.OptionsPicker;

import java.util.List;

/**
 * 通用条件选择器
 * Created by sean on 2016/1/6.
 */
public class CenterCommonPickerDialog extends Dialog implements View.OnClickListener {

    private OptionsPicker mPicker;
    private TextView tvTitle;
    private Button btnCancel;
    private Button btnDetermin;

    public CenterCommonPickerDialog(final Context context) {
        super(context, R.style.TransparentDialogStyle);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dlg_center_common_picker);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        mPicker = (OptionsPicker) findViewById(R.id.dccp_op_options);
        tvTitle = (TextView) findViewById(R.id.dccp_tv_title);
        btnCancel = (Button) findViewById(R.id.dccp_btn_cancel);
        btnDetermin = (Button) findViewById(R.id.dccp_btn_determine);
        btnCancel.setOnClickListener(this);
        btnDetermin.setOnClickListener(this);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void setTitle(CharSequence title) {
        tvTitle.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        tvTitle.setText(titleId);
    }

    public void setButtonText(CharSequence cancelText, CharSequence determineText) {
        btnCancel.setText(cancelText);
        btnDetermin.setText(determineText);
    }

    public void setButtonText(int cancelText, int determineText) {
        btnCancel.setText(cancelText);
        btnDetermin.setText(determineText);
    }

    protected void setPicker(List optionsOne, List<List> optionsTwo, List<List<List>> optionsThree,
                             boolean linkage) {
        mPicker.setPicker(optionsOne, optionsTwo, optionsThree, linkage);
    }

    protected void onSelected(int option1, int option2, int option3) {

    }

    public void setCurrentItems(int pos) {
        mPicker.setCurrentItems(pos, 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dccp_btn_cancel:
                dismiss();
                break;
            case R.id.dccp_btn_determine:
                int[] current = mPicker.getCurrentItems();
                onSelected(current[0], current[1], current[2]);
                break;
        }

    }
}
