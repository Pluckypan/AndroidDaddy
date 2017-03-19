package engineer.echo.rxjava;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/**
 * DeviceStatusManager
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/19 下午10:02.
 */

public class DeviceStatusManager {

    private static BehaviorSubject<DeviceStatus> deviceSubject = BehaviorSubject.create();

    private DeviceStatusManager() {

    }

    public static void publish(DeviceStatus status) {
        deviceSubject.onNext(status);
    }

    public static Subscription subscribe(Action1<DeviceStatus> action) {
        return deviceSubject.subscribe(action);
    }

    public static void unSubscribe(Subscription subscription) {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }
}
