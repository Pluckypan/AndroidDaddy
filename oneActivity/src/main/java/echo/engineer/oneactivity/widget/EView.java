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

/**
 * EView
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/7/23 下午11:27.
 */

public class EView extends View {
    private static final String TAG = "EView";

    public EView(Context context) {
        super(context);
        Log.d(TAG, "EView(Context context)");
    }

    public EView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "EView(Context context, @Nullable AttributeSet attrs)");
    }

    public EView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "EView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw(Canvas canvas) ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure widthMeasureSpec=" + widthMeasureSpec + " heightMeasureSpec=" + heightMeasureSpec);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.d(TAG, "onVisibilityChanged visibility=" + visibility);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d(TAG, "onFocusChanged gainFocus=" + gainFocus + " direction=" + direction);
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
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout changed=" + changed + " left=" + left + " top=" + top + " right" + right + " bottom=" + bottom);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate()");
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        Log.d(TAG, "onDrawForeground(Canvas canvas)");
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d(TAG, "onScrollChanged l=" + l + " t=" + t + " oldl=" + oldl + " oldt=" + oldt);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged w=" + w + " h=" + h + " oldw=" + oldw + " oldh=" + oldh);
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        Log.d(TAG, "onScreenStateChanged screenState=" + screenState);
    }

    //Touch

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent action=" + getTouchAction(event.getAction()));
        return false;//super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent action=" + getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    }

    public static String getTouchAction(int actionId) {
        String actionName = "Unknow:id=" + actionId;
        switch (actionId) {
            case MotionEvent.ACTION_DOWN:
                actionName = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                actionName = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                actionName = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                actionName = "ACTION_CANCEL";
                break;
            case MotionEvent.ACTION_OUTSIDE:
                actionName = "ACTION_OUTSIDE";
                break;
        }
        return actionName;
    }
}
