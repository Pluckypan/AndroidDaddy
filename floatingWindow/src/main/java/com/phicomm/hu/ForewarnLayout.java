package com.phicomm.hu;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * ForewarnLayout
 * Created by Plucky<plucky.pan@ubnt.com> on 17/4/10 2017 下午3:42.
 */
public class ForewarnLayout extends RelativeLayout {
    private float mTouchX;
    private float mTouchY;
    private float x;
    private float y;
    private float mStartX;
    private float mStartY;

    LinearLayout llPanel;

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    public ForewarnLayout(Context context) {
        super(context);

        WindowManager.LayoutParams myparams = new WindowManager.LayoutParams();
        myparams.height = LayoutParams.WRAP_CONTENT;
        myparams.width = LayoutParams.WRAP_CONTENT;
        this.setLayoutParams(myparams);

        LayoutInflater lf = LayoutInflater.from(context);
        mWindowManager = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        View mView = lf.inflate(R.layout.float_layout, null);
        llPanel = (LinearLayout) mView.findViewById(R.id.llPanel);
        this.addView(mView, myparams);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("FxService", "onTouchEvent");
        // statusBarHeight
        Rect frame = new Rect();
        getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        x = event.getRawX();
        y = event.getRawY() - statusBarHeight;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = event.getX();
                mTouchY = event.getY();
                mStartX = x;
                mStartY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:
                updateViewPosition();
                mTouchX = mTouchY = 0;
                if ((x - mStartX) < 2 && (y - mStartY) < 2) {
                    setVisibility();
                }
                break;
        }
        return true;
    }

    private void updateViewPosition() {
        mLayoutParams.x = (int) (x - mTouchX);
        mLayoutParams.y = (int) (y - mTouchY);
        //Log.d("FxService", "moveX:" + mLayoutParams.x + " moveY:" + mLayoutParams.y);
        mWindowManager.updateViewLayout(this, mLayoutParams);
    }

    public WindowManager.LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    private void setVisibility() {
        if (llPanel.getVisibility() == VISIBLE) {
            llPanel.setVisibility(GONE);
        } else {
            llPanel.setVisibility(VISIBLE);
        }
    }
}