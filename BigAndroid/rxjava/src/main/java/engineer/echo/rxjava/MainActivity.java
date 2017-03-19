package engineer.echo.rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import engineer.echo.rxjava.cmpts.AppComponent;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends RxAppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    private boolean complete;

    DeviceStatus status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.tvResult);
        findViewById(R.id.btnBehaviorSubject).setOnClickListener(this);
        status = App.getStatus(this);
        final String[] arr = new String[]{"A", "B", "C", "D", "E", "F", "G"};

        Observer observer = new Observer<Long>() {
            @Override
            public void onCompleted() {
                tvResult.setText("All complete " + complete);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                if (aLong < arr.length) {
                    String ssid = arr[aLong.intValue()];
                    tvResult.setText("Next=" + ssid);
                    status.ssid = ssid;
                    DeviceStatusManager.publish(status);
                } else {
                    complete = true;
                    onCompleted();
                }
            }
        };

        //.compose(this.bindUntilEvent(ActivityEvent.PAUSE))
        Observable.interval(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, BehaviorSubjectActivity.class);
        startActivity(intent);
    }
}
