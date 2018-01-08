package engineer.echo.floatwindow;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    FloatWindowService mService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, FloatWindowService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

        findViewById(R.id.app_second_tv).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mService != null)
            mService.setVisibility(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_second_tv:
                SecondActivity.gotoActivity(this);
                break;
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory: level=" + level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            if (mService != null)
                mService.setVisibility(false);
        }
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
}