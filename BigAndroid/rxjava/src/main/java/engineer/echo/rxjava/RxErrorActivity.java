package engineer.echo.rxjava;

import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class RxErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_error);

        final RetryWithDelay handleRetry = new RetryWithDelay(10, 3000);


        checkBluetooth(true).retryWhen(handleRetry).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Log.d("RetryWithDelay", "RxErrorActivity onCompleted.");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("RetryWithDelay", "RxErrorActivity error:" + e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                if (handleRetry.hasRetried()) {
                    handleRetry.resetCount();
                }
                Log.d("RetryWithDelay", "RxErrorActivity onNext:" + o);
            }
        });
    }

    public static boolean isBluetoothOpened() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        return adapter != null && adapter.isEnabled();
    }

    private Observable<Long> checkBluetooth(final boolean bluetooth) {
        return Observable.interval(1, TimeUnit.SECONDS).flatMap(new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                if (isBluetoothOpened()) {
                    return Observable.just(aLong);
                } else {
                    Log.d("RetryWithDelay", "will trigger retry by bluetooth:" + bluetooth);
                    return Observable.error(new Exception("Bluetooth is Closed"));
                }
            }
        });
    }

}


