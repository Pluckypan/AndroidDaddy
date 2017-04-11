package com.phicomm.hu;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    FxService fxService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent = new Intent(this, FxService.class);
        bindService(intent, frServiceCon, Context.BIND_AUTO_CREATE);
        findViewById(R.id.btnChange).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (fxService != null)
            fxService.setVisibility(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (fxService != null)
        fxService.setVisibility(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(frServiceCon);
    }

    private ServiceConnection frServiceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            fxService = ((FxService.FxServiceBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            fxService = null;
        }
    };
}