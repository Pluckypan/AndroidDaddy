package echo.engineer.oneactivity.cmpts.thread;

import android.util.Log;

/**
 * ThreadInterrupt
 * Created by Plucky<plucky@echo.engineer> on 2017/7/26 下午6:32.
 */

public class ThreadInterrupt extends Thread {

    private static final String TAG = "ThreadInterrupt";

    private volatile boolean exit;

    @Override
    public void run() {
        super.run();
        while (!exit) {
            for (int i = 0; i < 1000000; i++) {
                Log.d(TAG, "i=" + i);
            }
        }
    }

    public void terminate() {
        exit = true;
    }
}
