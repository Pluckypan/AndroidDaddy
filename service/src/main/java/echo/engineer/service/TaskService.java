package echo.engineer.service;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class TaskService extends IntentService {

    private static final String TAG = "TaskService";

    public TaskService() {
        this("TaskService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TaskService(String name) {
        super(name);
        Log.d(TAG, "TaskService name=" + name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String tag = intent != null ? intent.getStringExtra(TAG) : "null";
        Log.d(TAG, "onHandleIntent thread=" + Thread.currentThread().getName() + " tag=" + tag);
        for (int i = 0; i < 10; i++) {
            Log.d(TAG, "onHandleIntent i=" + i + " thread=" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String tag = intent != null ? intent.getStringExtra(TAG) : "null";
        Log.d(TAG, "onBind tag=" + tag);
        return new TaskServiceBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        String tag = intent != null ? intent.getStringExtra(TAG) : "null";
        Log.d(TAG, "onUnbind tag=" + tag);
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        String tag = intent != null ? intent.getStringExtra(TAG) : "null";
        Log.d(TAG, "onRebind tag=" + tag);
        super.onRebind(intent);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        String tag = intent != null ? intent.getStringExtra(TAG) : "null";
        Log.d(TAG, "onStart tag=" + tag + " startId=" + startId);
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        String tag = intent != null ? intent.getStringExtra(TAG) : "null";
        Log.d(TAG, "onStartCommand flags=" + flags + " startId=" + startId + " tag=" + tag);
        return super.onStartCommand(intent, flags, startId);
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, TaskService.class);
        intent.putExtra(TAG, "start");
        context.startService(intent);
    }


    public static void stop(Context context) {
        Intent intent = new Intent(context, TaskService.class);
        intent.putExtra(TAG, "stop");
        context.stopService(intent);
    }

    private class TaskServiceBinder extends Binder {
        public TaskService getService() {
            return TaskService.this;
        }
    }

    private static IBinder sBinder;
    private static final ServiceConnection sConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected name=" + name.getClassName());
            sBinder = service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected name=" + name.getClassName());
            sBinder = null;
        }
    };

    @Nullable
    public static TaskService getTaskService() {
        if (sBinder == null) return null;
        if (sBinder instanceof TaskServiceBinder) {
            return ((TaskServiceBinder) sBinder).getService();
        }
        return null;
    }

    public static void bind(Context context) {
        Intent intent = new Intent(context, TaskService.class);
        intent.putExtra(TAG, "bind");
        context.bindService(intent, sConnection, BIND_AUTO_CREATE);
    }

    public static boolean unbind(Context context) {
        try {
            context.unbindService(sConnection);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
