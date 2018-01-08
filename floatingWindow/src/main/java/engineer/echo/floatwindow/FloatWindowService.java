package engineer.echo.floatwindow;

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

public class FloatWindowService extends Service {
    private static final String TAG = "FloatWindowService";

    private FloatWindowLayout mLayout;
    private WindowManager mWindowManager;
    public static final int DEFAULT_POS = 200;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
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
        return new FloatWindowServiceBinder();
    }

    public void setVisibility(boolean flag) {
        if (mLayout != null) {
            mLayout.setVisibility(flag ? View.VISIBLE : View.GONE);
        }
    }

    private void createFloatView() {
        try {
            mLayout = new FloatWindowLayout(getApplicationContext());
            mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
            LayoutParams wmParams = mLayout.getLayoutParams();
            /**
             * 参考文章 {@link url=https://www.jianshu.com/p/167fd5f47d5c}
             * type=TYPE_PHONE 电话窗口。它用于电话交互（特别是呼入）。它置于所有应用程序之上，状态栏之下。 需要权限 android.permission.SYSTEM_ALERT_WINDOW
             * type=TYPE_TOAST 信息窗口。用于显示toast。 无需权限
             */
            wmParams.type = LayoutParams.TYPE_TOAST;
            wmParams.format = PixelFormat.RGBA_8888;
            wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
            wmParams.gravity = Gravity.LEFT | Gravity.TOP;
            wmParams.x = DEFAULT_POS;
            wmParams.y = DEFAULT_POS;
            wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            mWindowManager.addView(mLayout, wmParams);
            mLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                    .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        } catch (Exception e) {
            mLayout = null;
        }

    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
        super.onDestroy();
        if (mLayout != null && mWindowManager != null) {
            mWindowManager.removeView(mLayout);
        }
    }

    class FloatWindowServiceBinder extends Binder {
        public FloatWindowService getService() {
            return FloatWindowService.this;
        }
    }
}
