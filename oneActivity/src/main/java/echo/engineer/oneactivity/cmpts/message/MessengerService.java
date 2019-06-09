package echo.engineer.oneactivity.cmpts.message;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * MessengerService
 * Created by Plucky<plucky@echo.engineer> on 7/5/17 2017 14:15.
 */

public class MessengerService extends Service {

    private static final String TAG = "MessengerService";


    public MessengerService() {
        Log.d(TAG, "MessengerService()");
    }

    private Messenger messenger = new Messenger(new MessengerHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        return new MessengerBinder();
    }

    public class MessengerBinder extends Binder {
        public MessengerService getService() {
            return MessengerService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        buildConnection();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand startId=" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        releaseConnection();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    private void buildConnection() {
        Intent intent = new Intent();
        intent.setClassName("echo.engineer.service", "echo.engineer.service.MessageService");
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private void releaseConnection() {
        try {
            unbindService(mConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Messenger mService;

    public void sendMessage(String msg) {
        if (mService == null) return;
        Message message = Message.obtain(null, 0);
        Bundle bundle = new Bundle();
        bundle.putString("msg", msg);
        message.replyTo = messenger;
        message.setData(bundle);
        try {
            mService.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected() componentName=" + name.toString());
            mService = new Messenger(service);
            sendMessage("when connected to server");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected()");
        }
    };
}
