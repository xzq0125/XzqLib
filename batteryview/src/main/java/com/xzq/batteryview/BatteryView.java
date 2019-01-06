package com.xzq.batteryview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 电池显示控件
 *
 * @author xzq
 */
public class BatteryView extends View {

    private static final float DEFAULT_WIDTH = 35;//dp 默认控件宽度
    private static final float DEFAULT_HEIGHT = 20;//dp 默认控件高度
    private static final float DEFAULT_RADIUS = 0.5f;//dp 默认圆角半径
    private static final float DEFAULT_LID_WIDTH = 4;//dp 默认盖子宽度
    private static final float DEFAULT_LID_HEIGHT = 12;//dp 默认盖子高度
    private static final float DEFAULT_LID_MARGIN_LEFT = 3;//dp 默认盖子距离电池的margin
    private static final float DEFAULT_POWER_MARGIN = 1;//dp 默认电量边距
    private static final int DEFAULT_BODY_COLOR = 0xffffffff;//默认电池外观颜色
    private static final int DEFAULT_LID_COLOR = 0xffffffff;//默认电池盖子颜色
    private static final int DEFAULT_POWER_COLOR = 0xff167ff6;//默认电量颜色

    private Paint mBodyPaint = new Paint();//电池外部画笔
    private Paint mLidPaint = new Paint();//电池盖子画笔
    private Paint mPowerPaint = new Paint();//电池电量画笔
    private final RectF mBodyRect = new RectF();
    private final RectF mLidRect = new RectF();
    private final RectF mPowerRect = new RectF();
    private int mBodyRx, mBodyRy;
    private int mPowerRx, mPowerRy;
    private int mLidRx, mLidRy;
    private int lidWidth;//电池盖子宽度
    private int lidHeight;//电池盖子高度
    private int lidMarginLeft;//盖子距离电池的margin
    private int powerMargin;//电量边距
    private int totalCapacity;//电池总容量
    private int powerPercent;//电量百分比
    private int max;//电池进度最大值
    private int progress;//电池当前进度值

    public BatteryView(Context context) {
        super(context);
        init(null);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {

        mBodyRx = mBodyRy = mPowerRx = mPowerRy = mLidRx = mLidRy = dp2px(getContext(), DEFAULT_RADIUS);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BatteryView);

        lidWidth = dp2px(getContext(), DEFAULT_LID_WIDTH);
        lidHeight = dp2px(getContext(), DEFAULT_LID_HEIGHT);
        lidMarginLeft = dp2px(getContext(), DEFAULT_LID_MARGIN_LEFT);
        lidWidth = a.getDimensionPixelSize(R.styleable.BatteryView_bv_lid_width, lidWidth);
        lidHeight = a.getDimensionPixelSize(R.styleable.BatteryView_bv_lid_height, lidHeight);
        lidMarginLeft = a.getDimensionPixelSize(R.styleable.BatteryView_bv_lid_margin_left, lidMarginLeft);

        mBodyRx = a.getDimensionPixelSize(R.styleable.BatteryView_bv_body_rx, mBodyRx);
        mBodyRy = a.getDimensionPixelSize(R.styleable.BatteryView_bv_body_ry, mBodyRy);
        mPowerRx = a.getDimensionPixelSize(R.styleable.BatteryView_bv_power_rx, mPowerRx);
        mPowerRy = a.getDimensionPixelSize(R.styleable.BatteryView_bv_power_ry, mPowerRy);
        mLidRx = a.getDimensionPixelSize(R.styleable.BatteryView_bv_lid_rx, mLidRx);
        mLidRy = a.getDimensionPixelSize(R.styleable.BatteryView_bv_lid_ry, mLidRy);

        powerMargin = dp2px(getContext(), DEFAULT_POWER_MARGIN);
        powerMargin = a.getDimensionPixelSize(R.styleable.BatteryView_bv_power_margin, powerMargin);

        int colorBody = a.getColor(R.styleable.BatteryView_bv_body_color, DEFAULT_BODY_COLOR);
        int colorLid = a.getColor(R.styleable.BatteryView_bv_lid_color, DEFAULT_LID_COLOR);
        int colorPower = a.getColor(R.styleable.BatteryView_bv_power_color, DEFAULT_POWER_COLOR);
        mBodyPaint.setColor(colorBody);
        mBodyPaint.setAntiAlias(true);
        mLidPaint.setColor(colorLid);
        mLidPaint.setAntiAlias(true);
        mPowerPaint.setColor(colorPower);
        mPowerPaint.setAntiAlias(true);

        max = a.getInt(R.styleable.BatteryView_bv_power_max, 0);
        progress = a.getInt(R.styleable.BatteryView_bv_power_progress, 0);
        if (max > 0 && progress >= 0) {
            setProgress(progress);
        } else {
            powerPercent = a.getInt(R.styleable.BatteryView_bv_power_percent, 0);
            setPower(powerPercent);
        }

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
            default:
                width = dp2px(getContext(), DEFAULT_WIDTH);
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
            default:
                height = dp2px(getContext(), DEFAULT_HEIGHT);
                break;
        }

        setMeasuredDimension(width, height);

        int viewWidth = getMeasuredWidth();
        int viewHeight = getMeasuredHeight();

        //计算bodyRect
        int bodyLeft = 0;
        int bodyTop = 0;
        int bodyRight = viewWidth * (viewWidth - lidWidth - lidMarginLeft) / viewWidth;
        mBodyRect.set(bodyLeft, bodyTop, bodyRight, viewHeight);
        totalCapacity = bodyRight;

        //计算lidRect
        int lidLeft = bodyRight + lidMarginLeft;
        int lidTop = (viewHeight - lidHeight) / 2;
        int lidRight = lidLeft + lidWidth;
        int lidBottom = viewHeight - lidTop;
        mLidRect.set(lidLeft, lidTop, lidRight, lidBottom);

        //计算电量Rect
        int powerLeft = powerMargin;
        int powerTop = powerMargin;
        int powerRight = computePowerPercent();
        int powerBottom = viewHeight - powerMargin;
        mPowerRect.set(powerLeft, powerTop, powerRight, powerBottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw body
        canvas.drawRoundRect(mBodyRect, mBodyRx, mBodyRy, mBodyPaint);
        //draw lid
        canvas.drawRoundRect(mLidRect, mLidRx, mLidRy, mLidPaint);
        //draw power
        mPowerRect.right = computePowerPercent();
        if (mPowerRect.width() > 0) {
            canvas.drawRoundRect(mPowerRect, mPowerRx, mPowerRy, mPowerPaint);
        }
    }

    /**
     * 计算电量
     *
     * @return 电量占比
     */
    private int computePowerPercent() {
        if (powerPercent > 0 && powerPercent <= 100) {
            return totalCapacity * powerPercent / 100 - powerMargin;
        }
        return powerMargin;
    }

    static int dp2px(Context context, float dpValue) {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * 设置电量进度最大值，并且通过{@link #setProgress(int)}方法设置了当前电量进度才能生效，
     * 或者可以使用{@link #setPower(int)}方法来设置电量
     *
     * @param max 进度最大值
     */
    public void setMax(int max) {
        this.max = max;
        setProgress(progress);
    }

    /**
     * 设置当前进度值
     *
     * @param progress 当前进度值
     */
    public void setProgress(int progress) {
        if (max > 0 && progress >= 0) {
            this.progress = progress;
            int percent = progress * 100 / max;
            setPower(percent);
        }
    }

    /**
     * 设置电量百分比数值(0-100),比如50%就设置50
     *
     * @param powerPercent 电量百分比
     */
    public void setPower(int powerPercent) {
        if (powerPercent > 100) {
            powerPercent = 100;
        }
        if (this.powerPercent == powerPercent) {
            return;
        }
        if (powerPercent >= 0) {
            this.powerPercent = powerPercent;
            invalidate();
        }
    }

    /**
     * 设置电池外观颜色
     *
     * @param colorBody color
     */
    public void setBodyColor(@ColorInt int colorBody) {
        mBodyPaint.setColor(colorBody);
        invalidate();
    }

    /**
     * 设置电池盖子颜色
     *
     * @param colorLid color
     */
    public void setLidColor(@ColorInt int colorLid) {
        mLidPaint.setColor(colorLid);
        invalidate();
    }

    /**
     * 设置电量颜色
     *
     * @param colorPower color
     */
    public void setPowerColor(@ColorInt int colorPower) {
        mPowerPaint.setColor(colorPower);
        invalidate();
    }

    public int getPowerPercent() {
        return powerPercent;
    }

    public int getMax() {
        return max;
    }

    public int getProgress() {
        return progress;
    }

}
