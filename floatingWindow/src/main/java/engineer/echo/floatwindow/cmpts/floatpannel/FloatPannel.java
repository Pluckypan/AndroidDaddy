package engineer.echo.floatwindow.cmpts.floatpannel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * SecondActivity
 * Created by Plucky<plucky@echo.engineer> on 2018/1/9 - 10:22
 * more about me: http://www.1991th.com
 */

public class FloatPannel {

    private Context mContext;
    private FloatWindowService mService;

    private FloatPannel() {

    }

    private static FloatPannel mPannel;

    private static FloatPannel getPannel() {
        if (mPannel == null) {
            mPannel = new FloatPannel();
        }
        return mPannel;
    }

    private void bindService() {
        if (mContext != null) {
            Intent intent = new Intent(mContext, FloatWindowService.class);
            mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    private void unBindService() {
        if (mContext != null) {
            mContext.unbindService(mServiceConnection);
        }
    }

    private void print(String msg) {
        if (mService != null) {
            mService.print(msg);
        }
    }

    public static void Log(String msg) {
        getPannel().print(msg);
    }

    private void install(Context context) {
        mContext = context.getApplicationContext();
        bindService();
    }

    private void unInstall() {
        unBindService();
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((FloatWindowService.FloatWindowServiceBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    public static void register(Context context) {
        getPannel().install(context);
    }

    public static void unRegister() {
        getPannel().unInstall();
    }

}
