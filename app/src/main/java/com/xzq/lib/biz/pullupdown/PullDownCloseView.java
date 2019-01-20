package com.xzq.lib.biz.pullupdown;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;

import com.xzq.lib.utils.LogUtils;

/**
 * 下拉关闭控件
 * Created by Tse on 2019/1/19.
 */

public class PullDownCloseView extends FrameLayout {

    private Scroller mScroller;
    private TextView mHeaderView;
    private Point point = new Point();
    private View mTarget;
    private boolean isChildShow = true;

    public PullDownCloseView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PullDownCloseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullDownCloseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context, new AccelerateDecelerateInterpolator());
        mHeaderView = new TextView(context);
        mHeaderView.setText("下拉关闭");
        mHeaderView.setGravity(Gravity.CENTER);
        addView(mHeaderView);
    }

    private void ensureTarget() {
        if (mTarget == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (child instanceof RecyclerView) {
                    mTarget = child;
                    break;
                }
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mHeaderView.layout(l, -mHeaderView.getMeasuredHeight(), r, 0);
        ensureTarget();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int headHeightSpec = MeasureSpec.makeMeasureSpec(200, MeasureSpec.EXACTLY);
        mHeaderView.measure(widthMeasureSpec, headHeightSpec);
        ensureTarget();
    }

    private void reset() {
        mHeaderView.setText("下拉关闭");
        mScroller.startScroll(0, getScrollY(), 0, -getScrollY());
        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                point.y = (int) ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                return dealMove(ev);

            case MotionEvent.ACTION_UP:

                int scrollY = getScrollY();
                if (scrollY <= -mHeaderView.getMeasuredHeight()) {
                    mScroller.startScroll(0, scrollY, 0, -mHeaderView.getMeasuredHeight() - scrollY);
                    close();
                } else {
                    mScroller.startScroll(0, scrollY, 0, -scrollY);
                }

                invalidate();

                operatType = TYPE_NONE;
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    private void close() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(PullDownCloseView.this, View.TRANSLATION_Y,
                0, PullDownCloseView.this.getHeight());
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                reset();
            }
        });
        animator.setDuration(200);
        animator.start();
        isChildShow = false;
    }

    public boolean show() {
        if (isChildShow) {
            return true;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y,
                this.getHeight(), 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(300);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isChildShow = true;
            }
        });
        animator.start();
        return false;
    }

    private boolean dealMove(MotionEvent ev) {
        // 获取本次用户的操作是下拉还是上推
        getOperatType(ev);
        switch (operatType) {
            case TYPE_NONE:// 没有判断出操作模式，则不动
                break;

            case TYPE_PULL:// 下拉操作
                if (canClose()) {
                    mHeaderView.setText("松手关闭");
                } else {
                    mHeaderView.setText("下拉关闭");
                }
                boolean canChildScrollUp = canChildScrollUp();
                if (!canChildScrollUp) {
                    moveCanvas(ev);
                    return false;
                } else {
                    return super.dispatchTouchEvent(ev);
                }

            case TYPE_PUSH:// 上推操作
                int getScrollY = getScrollY();
                if (canClose()) {
                    mHeaderView.setText("松手关闭");
                } else {
                    mHeaderView.setText("下拉关闭");
                }
                LogUtils.debug("XZQ", "getScrollY = " + getScrollY);
                if (getScrollY != 0) {// 如果头部被拉出来了，则让头部滚动到0
                    moveCanvas(ev);
                    return false;
                } else {
                    return super.dispatchTouchEvent(ev);
                }

        }

        return super.dispatchTouchEvent(ev);

    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int cy = mScroller.getCurrY();
            scrollTo(0, cy);
            invalidate();
        }
    }

    private boolean canClose() {
        return getScrollY() <= -mHeaderView.getMeasuredHeight();
    }

    public boolean canChildScrollUp() {
        if (mTarget instanceof ListView) {
            return ListViewCompat.canScrollList((ListView) mTarget, -1);
        }
        return mTarget != null && mTarget.canScrollVertically(-1);
    }

    private static final int TYPE_PULL = 0;
    private static final int TYPE_PUSH = 1;
    private static final int TYPE_NONE = -1;
    // 用来判断用户操作类型的距离
    private static final int DISTANCE = 10;
    private int operatType = TYPE_NONE;

    private void getOperatType(MotionEvent ev) {

        if (operatType != TYPE_NONE) {// 如果本次操作已经判断出了模式，不用再判断了
            return;
        }

        int y = (int) ev.getY();
        // 计算用户按下手指之后的总体偏移量
        int dis_y = y - point.y;
        if (Math.abs(dis_y) >= DISTANCE) {// 说明判断出了用户的操作类型
            if (dis_y > 0) {// 下拉
                operatType = TYPE_PULL;
            } else {
                operatType = TYPE_PUSH;
            }

            // 以计算出操作模式的那一点作为事件处理的起始点
            point.y = y;
        }

    }

    private void moveCanvas(MotionEvent ev) {
        int ty = (int) ev.getY();
        // 计算偏移量
        int dy = ty - point.y;

        int dst_y = getScrollY() - dy;

        // System.out.println("本次想要滚到: " + dst_y);
        if (dst_y > 0) {
            dy = getScrollY();
        }

        scrollBy(0, -dy);

        point.y = ty;
    }

}
