package engineer.echo.rxjava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * RetryWithDelay
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/4/9 下午3:38.
 */

public class RetryWithDelay implements
        Func1<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    public void resetCount() {
        retryCount = 0;
    }

    public boolean hasRetried() {
        return retryCount > 0;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> attempts) {
        return attempts
                .flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        if (++retryCount <= maxRetries) {
                            Log.d("RetryWithDelay", "get error, it will try after " + retryDelayMillis
                                    + " millisecond, retry count " + retryCount);
                            return Observable.timer(retryDelayMillis,
                                    TimeUnit.MILLISECONDS);
                        }
                        return Observable.error(throwable);
                    }
                });
    }

    public interface RetryTrigger{
        void onGetError();
    }
}