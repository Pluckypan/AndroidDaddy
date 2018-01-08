package com.phicomm.hu;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class FxService extends Service {

    private ForewarnLayout mForewarnLayout;
    WindowManager mWindowManager;
    int screenW;

    private static final String TAG = "FxService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
        screenW = getResources().getDisplayMetrics().widthPixels;
        createFloatView();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind()");
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind()");
        return new FxServiceBinder();
    }

    public void setVisibility(boolean flag) {
        mForewarnLayout.setVisibility(flag ? View.VISIBLE : View.GONE);
    }

    private void createFloatView() {
        mForewarnLayout = new ForewarnLayout(getApplicationContext());
        mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        LayoutParams wmParams = mForewarnLayout.getLayoutParams();
        wmParams.type = LayoutParams.TYPE_PHONE;
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        wmParams.x = screenW - 200;
        wmParams.y = 100;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowManager.addView(mForewarnLayout, wmParams);
        mForewarnLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
        super.onDestroy();
        if (mForewarnLayout != null) {
            mWindowManager.removeView(mForewarnLayout);
        }
    }

    class FxServiceBinder extends Binder {
        public FxService getService() {
            return FxService.this;
        }
    }
}
