package com.xzq.lib.biz.record;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.xzq.lib.R;
import com.xzq.lib.utils.LogUtils;

/**
 * RecordButton
 * Created by xzq on 2019/1/13.
 */

public class RecordButton extends android.support.v7.widget.AppCompatImageView {

    private static final String TAG = "RecordButton";

    private RectF progressRectF = new RectF();
    private Paint mProgressPaint = new Paint();

    private int iconWidth;
    private int iconHeight;

    private int mWidth;
    private int mHeight;
    private int mPadding;

    private Paint mRipplePaint = new Paint();
    private float rippleFirstRadius = 0;
    private float rippleSecondRadius = 0;
    private int rippleStep = 2;
    private float rippleCx;
    private float rippleCy;
    private int rippleGap;

    public RecordButton(Context context) {
        this(context, null);
    }

    public RecordButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecordButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RecordButton);
        mPadding = a.getDimensionPixelSize(R.styleable.RecordButton_rb_padding, dp2px(context, 20));
        int progressColor = a.getColor(R.styleable.RecordButton_rb_progress_color, 0xffffd015);
        int progressWidth = a.getDimensionPixelSize(R.styleable.RecordButton_rb_progress_width, dp2px(context, 3));
        int rippleColor = a.getColor(R.styleable.RecordButton_rb_ripple_color, 0xffffd015);
        int rippleWidth = a.getDimensionPixelSize(R.styleable.RecordButton_rb_ripple_width, dp2px(context, 15));
        int recordDuration = a.getInt(R.styleable.RecordButton_rb_record_duration, duration);
        this.min_duration = a.getInt(R.styleable.RecordButton_rb_record_min_duration, min_duration);
        this.rippleGap = a.getInt(R.styleable.RecordButton_rb_ripple_gap, dp2px(context, 7));
        a.recycle();

        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setColor(progressColor);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(progressWidth);

        mRipplePaint.setColor(rippleColor);
        mRipplePaint.setAntiAlias(true);
        mRipplePaint.setStyle(Paint.Style.STROKE);
        mRipplePaint.setStrokeWidth(rippleWidth);

        duration = recordDuration;
        timer = new RecordCountDownTimer(duration, duration / 360);    //录制定时器
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        iconWidth = getMeasuredWidth();
        iconHeight = getMeasuredHeight();

        LogUtils.debug(TAG, "iconWidth = " + iconWidth);
        LogUtils.debug(TAG, "iconHeight = " + iconHeight);

        int padding = mPadding;
        setMeasuredDimension(iconWidth + padding * 2, iconHeight + padding * 2);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        rippleCx = mWidth / 2;
        rippleCy = mHeight / 2;

        LogUtils.debug(TAG, "mWidth = " + mWidth);
        LogUtils.debug(TAG, "mHeight = " + mHeight);

        int left = padding / 2;
        int top = padding / 2;
        progressRectF.set(left, top, mWidth - left, mHeight - top);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(progressRectF, -90, progress, false, mProgressPaint);
        if (isRecording) {
            int maxStep = mPadding;
            rippleFirstRadius += rippleStep;
            if (rippleFirstRadius > maxStep) {
                rippleFirstRadius = 0;
            }
            rippleSecondRadius += rippleStep;
            if (rippleSecondRadius > maxStep) {
                rippleSecondRadius = 0;
            }
            drawRipple(canvas);
        }
        super.onDraw(canvas);
    }

    /**
     * 绘制波纹
     *
     * @param canvas Canvas
     */
    private void drawRipple(Canvas canvas) {
        mRipplePaint.setAlpha(80);
        canvas.drawCircle(rippleCx, rippleCy, mWidth / 4 + rippleFirstRadius / 2, mRipplePaint);
        mRipplePaint.setAlpha(125);
        canvas.drawCircle(rippleCx, rippleCy, mWidth / 4 + rippleSecondRadius / 2 - rippleGap, mRipplePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.debug(TAG, "按下");
                timer.start();
                canRecord = false;
                isRecording = true;
                postDelayed(runnable, min_duration);
                MyAudioRecorder.getRecorder().startRecord();

                return true;
            case MotionEvent.ACTION_UP:
                LogUtils.debug(TAG, "抬起");
                if (!canRecord) {
                    LogUtils.debug(TAG, "录制时间过短，至少录制 " + min_duration + " s");
                    progress = 0;
                }
                isRecording = false;
                rippleFirstRadius = 0;
                rippleSecondRadius = 0;
                invalidate();
                timer.cancel();
                MyAudioRecorder.getRecorder().stopRecord();
                LogUtils.debug(TAG, "录制结束");
                return true;
        }
        return super.onTouchEvent(event);
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            canRecord = true;
        }
    };

    private boolean canRecord;      //是否可以录制
    private float progress;         //录制进度
    private int duration = 15 * 1000;  //录制最大时间长度(毫秒)
    private int min_duration = 1500;   //最短录制时间限制
    private RecordCountDownTimer timer;  //计时器
    private boolean isRecording = false;

    //更新进度条
    private void updateProgress(long millisUntilFinished) {
        progress = 360f - millisUntilFinished / (float) duration * 360f;
        invalidate();
    }

    //录制视频计时器
    private final class RecordCountDownTimer extends CountDownTimer {
        RecordCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            updateProgress(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            isRecording = false;
            cancel();
            updateProgress(0);
            LogUtils.debug(TAG, "录制结束");
            MyAudioRecorder.getRecorder().stopRecord();
        }
    }

    static int dp2px(Context context, float dpValue) {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

}
