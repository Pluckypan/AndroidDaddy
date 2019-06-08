package echo.engineer.oneactivity.app;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import echo.engineer.oneactivity.App;
import echo.engineer.oneactivity.BuildConfig;
import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.app.fragments.HomeFragment;
import echo.engineer.oneactivity.cmpts.message.MessengerService;
import engineer.echo.oneactivity.core.FragmentMaster;
import engineer.echo.oneactivity.core.IMasterFragment;
import engineer.echo.oneactivity.core.MasterCompatActivity;
import engineer.echo.oneactivity.core.Request;

public class MainActivity extends MasterCompatActivity {

    public static final String TAG = "MainActivity";
    private boolean mUseImmersiveMode;

    private FragmentMaster.FragmentLifecycleCallbacks mLifecycleCallbacks =
            new FragmentMaster.SimpleFragmentLifecycleCallbacks() {
                @Override
                public void onFragmentResumed(IMasterFragment fragment) {
                    if (BuildConfig.DEBUG)
                        Log.d(TAG, "[onResume]     " + fragment.toString());
                }

                @Override
                public void onFragmentActivated(IMasterFragment fragment) {
                    if (BuildConfig.DEBUG)
                        Log.d(TAG, "[onActivated]  " + fragment.toString());
                }

                @Override
                public void onFragmentDeactivated(IMasterFragment fragment) {
                    if (BuildConfig.DEBUG)
                        Log.d(TAG, "[onDeactivated]" + fragment.toString());
                }

                @Override
                public void onFragmentPaused(IMasterFragment fragment) {
                    if (BuildConfig.DEBUG)
                        Log.d(TAG, "[onPaused]     " + fragment.toString());
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUseImmersiveMode = App.useImmersiveMode(getWindow(), false);
        setContentView(R.layout.activity_main);
        FragmentMaster fragmentMaster = getFragmentMaster();
        fragmentMaster.registerFragmentLifecycleCallbacks(mLifecycleCallbacks);
        fragmentMaster.install(R.id.container, new Request(HomeFragment.class), true);
        bindMessengerService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindMessengerService();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) return;
        if (mUseImmersiveMode) {
            App.useImmersiveMode(getWindow(), false);
        }
    }

    private void bindMessengerService() {
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private void unbindMessengerService() {
        try {
            unbindService(serviceConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        if (iBinder instanceof MessengerService.MessengerBinder) {
            MessengerService messengerService = ((MessengerService.MessengerBinder) iBinder).getService();
            messengerService.sendMessage(msg);
        }
    }

    private IBinder iBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinder = service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iBinder = null;
        }
    };
}
