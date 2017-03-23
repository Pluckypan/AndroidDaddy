package engineer.echo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

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

        repeat();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DeviceStatusManager.unSubscribe(subscription);
    }

    private void repeat() {
        Observable.range(1, 5).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                return Observable.timer(6, TimeUnit.SECONDS);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("repeat", "------------->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("repeat", "------------->onError:" + e);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("repeat", "------------->onNext:" + integer);
            }
        });
    }
}
