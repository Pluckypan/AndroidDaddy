package engineer.echo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import rx.Subscription;
import rx.functions.Action1;

public class BehaviorSubjectActivity extends AppCompatActivity {

    private Subscription subscription;
    TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_subject);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        subscription = DeviceStatusManager.subscribe(new Action1<DeviceStatus>() {
            @Override
            public void call(DeviceStatus status) {
                Log.d("Plucky", status.ssid);
                tvMsg.setText("ssid:" + status.ssid);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DeviceStatusManager.unSubscribe(subscription);
    }
}
