package echo.engineer.oneactivity.cmpts.thread;

import android.util.Log;

/**
 * MyThread
 * Created by Plucky<plucky@echo.engineer> on 2017/7/26 下午6:52.
 */

public class RatThread implements Runnable {
    private static final String TAG = "MyThread";
    private boolean flag = true;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int i = 0;
        while (this.flag) {
            Log.d(TAG, Thread.currentThread().getName() + "线程运行，i = " + (i++));
        }
    }

    public void stop() {
        this.flag = false;
    }
}