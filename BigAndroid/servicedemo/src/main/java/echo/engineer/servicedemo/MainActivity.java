package echo.engineer.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStartService).setOnClickListener(this);
        findViewById(R.id.btnStopService).setOnClickListener(this);
        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnBindService).setOnClickListener(this);
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

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
