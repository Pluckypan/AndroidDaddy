package echo.engineer.oneactivity.cmpts.thread;

import android.util.Log;

/**
 * StopableThread
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/7/26 上午11:36.
 */

public class StopableThread extends Thread {

    private volatile boolean exit;

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100000; i++) {
            Log.d("EView", "i=" + i);
        }
        Log.d("EView", "StopableThread run()");
    }

    @Override
    public synchronized void start() {
        exit = false;
        super.start();
    }

    public void exitThread() {
        exit = true;
    }
}
