package echo.engineer.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import rx.Subscription;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Subscription subscription;
    private TextView tvMsg;
    private CheckBox cbBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStartService).setOnClickListener(this);
        findViewById(R.id.btnStopService).setOnClickListener(this);
        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnBindService).setOnClickListener(this);
        tvMsg = findViewById(R.id.tvMsg);
        cbBind = findViewById(R.id.cb_bind_app);


        subscription = AppStateManager.subscribe(new AppStateManager.SimpleAppStateAction() {
            @Override
            public void onAppReceiveMessage(String msg) {
                super.onAppReceiveMessage(msg);
                tvMsg.setText(msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppStateManager.unsubscribe(subscription);
    }

    public void onStartService(View view) {
        Intent intentDownload = new Intent(this, DownloadService.class);
        startService(intentDownload);
    }

    public void onStopService(View view) {
        stopService(new Intent(this, DownloadService.class));
    }

    public void onBindService(View view) {
        Intent intent = new Intent(this, DownloadService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void onUnBindService(View view) {
        if (serviceConnection != null) {
            try {
                unbindService(serviceConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartService:
                onStartService(v);
                break;
            case R.id.btnStopService:
                onStopService(v);
                break;
            case R.id.btnBindService:
                onBindService(v);
                break;
            case R.id.btnUnBindService:
                onUnBindService(v);
                break;
        }
    }

    public void startTask(View view) {
        if (cbBind.isChecked()) {
            TaskService.bind(this);
            Log.d("TaskService", "startTask");
        } else {
            TaskService.start(this);
        }
    }

    public void stopTask(View view) {
        if (cbBind.isChecked()) {
            boolean r = TaskService.unbind(this);
            Log.d("TaskService", "stopTask r=" + r);
        } else {
            TaskService.stop(this);
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("DownloadService", "onServiceConnected name=" + name.getClassName());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("DownloadService", "onServiceDisconnected name=" + name.getClassName());
        }
    };
}
