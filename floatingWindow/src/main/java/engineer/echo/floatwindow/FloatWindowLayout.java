package engineer.echo.floatwindow;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * FloatWindowLayout
 * Created by Plucky<plucky@echo.engineer> on 17/4/10 2017 下午3:42.
 * more about me: http://www.1991th.com
 */
public class FloatWindowLayout extends RelativeLayout {
    private static final String TAG = "FloatWindowLayout";
    private float mTouchX;
    private float mTouchY;
    private float mRawX;
    private float mRawY;
    private float mStartX;
    private float mStartY;

    LinearLayout mPannel;

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    public FloatWindowLayout(Context context) {
        super(context);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.height = LayoutParams.WRAP_CONTENT;
        params.width = LayoutParams.WRAP_CONTENT;
        this.setLayoutParams(params);

        LayoutInflater lf = LayoutInflater.from(context);
        mWindowManager = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        View mView = lf.inflate(R.layout.layout_float_window, null);
        mPannel = (LinearLayout) mView.findViewById(R.id.llPanel);
        this.addView(mView, params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        Rect frame = new Rect();
        getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        mRawX = event.getRawX();
        mRawY = event.getRawY() - statusBarHeight;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = event.getX();
                mTouchY = event.getY();
                mStartX = mRawX;
                mStartY = mRawY;
                break;

            case MotionEvent.ACTION_MOVE:
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:
                updateViewPosition();
                mTouchX = mTouchY = 0;
                if ((mRawX - mStartX) < 2 && (mRawY - mStartY) < 2) {
                    setVisibility();
                }
                break;
        }
        return true;
    }

    private void updateViewPosition() {
        mLayoutParams.x = (int) (mRawX - mTouchX);
        mLayoutParams.y = (int) (mRawY - mTouchY);
        mWindowManager.updateViewLayout(this, mLayoutParams);
    }

    public WindowManager.LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    private void setVisibility() {
        if (mPannel.getVisibility() == VISIBLE) {
            mPannel.setVisibility(GONE);
        } else {
            mPannel.setVisibility(VISIBLE);
        }
    }
}