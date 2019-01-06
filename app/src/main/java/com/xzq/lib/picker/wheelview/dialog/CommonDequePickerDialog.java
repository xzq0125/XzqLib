package com.xzq.lib.picker.wheelview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xzq.lib.R;
import com.xzq.lib.picker.wheelview.wheelpicker.OptionsPicker;

import java.util.List;

/**
 * 双端通用条件选择器
 * Created by sean on 2016/3/22.
 */
public class CommonDequePickerDialog extends Dialog {

    private OptionsPicker mPicker;
    private OptionsPicker mPicker2;
    private TextView tvTitle;

    public CommonDequePickerDialog(final Context context) {
        super(context, R.style.BottomTransparentDialogStyle);
        setContentView(R.layout.dlg_common_deque_picker);
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        mPicker = (OptionsPicker) findViewById(R.id.dcp_op_options);
        mPicker2 = (OptionsPicker) findViewById(R.id.dcp_op_options2);
        tvTitle = (TextView) findViewById(R.id.dcp_tv_title);
        findViewById(R.id.dcp_btn_determine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] current = mPicker.getCurrentItems();
                int[] current2 = mPicker2.getCurrentItems();
                onSelected(current[0], current[1], current2[0], current2[1]);
            }
        });
    }

    @Override
    public void setTitle(CharSequence title) {
        tvTitle.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        tvTitle.setText(titleId);
    }

    protected void setLeftPicker(List optionsOne, List<List> optionsTwo, boolean linkage) {
        mPicker.setPicker(optionsOne, optionsTwo, null, linkage);
    }

    protected void setRightPicker(List optionsOne, List<List> optionsTwo, boolean linkage) {
        mPicker2.setPicker(optionsOne, optionsTwo, null, linkage);
    }

    protected void setSelectedItems(int option1, int option2, int option3, int option4) {
        mPicker.setCurrentItems(option1, option2, 0);
        mPicker2.setCurrentItems(option3, option4, 0);
    }

    protected void onSelected(int option1, int option2, int option3, int option4) {

    }
}
