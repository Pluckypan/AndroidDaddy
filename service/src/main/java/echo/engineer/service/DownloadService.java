package echo.engineer.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class DownloadService extends Service {

    private static final String TAG = "DownloadService";
    private Timer timer;

    public DownloadService() {
        Log.d(TAG, "DownloadService ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "Timer " + SystemClock.elapsedRealtime() / (1000));
            }
        }, 1000, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    //will be invoked by System when call stardService
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand startId=" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    //will be invoked by System when call bindService
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return new DownloadIBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");
    }


    public class DownloadIBinder extends Binder {
        public DownloadService getService() {
            return DownloadService.this;
        }
    }
}
