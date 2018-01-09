package echo.engineer.oneactivity.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import static echo.engineer.oneactivity.widget.EView.getTouchAction;

/**
 * EViewGroup
 * Created by Plucky<plucky@echo.engineer> on 2017/7/24 上午10:41.
 */

public class EViewGroup extends LinearLayout {
    private final static String TAG = "EViewGroup";

    public EViewGroup(Context context) {
        super(context);
        Log.d(TAG, "EViewGroup(Context context)");
    }

    public EViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "EViewGroup(Context context, AttributeSet attrs)");
    }

    public EViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "EViewGroup(Context context, AttributeSet attrs, int defStyleAttr)");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d(TAG, "onLayout changed=" + changed + " l=" + l + " t=" + t + " r=" + r + " b=" + b);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d(TAG, "dispatchDraw(Canvas canvas)");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow()");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow()");
    }

    @Override
    protected void detachAllViewsFromParent() {
        super.detachAllViewsFromParent();
        Log.d(TAG, "detachAllViewsFromParent()");
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d(TAG, "onFocusChanged gainFocus=" + gainFocus + " direction=" + direction);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.d(TAG, "onVisibilityChanged visibility=" + visibility);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged w=" + w + " h=" + h + " oldw=" + oldw + " oldh=" + oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw(Canvas canvas)");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate()");
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        Log.d(TAG, "onDrawForeground(Canvas canvas) ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure widthMeasureSpec=" + widthMeasureSpec + " heightMeasureSpec=" + heightMeasureSpec);
    }

    //Touch


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent action=" + getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);//super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent action=" + getTouchAction(ev.getAction()));
        return super.onInterceptTouchEvent(ev);//super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent action=" + getTouchAction(event.getAction()));
        return super.onTouchEvent(event);//super.onTouchEvent(event);
    }
}
