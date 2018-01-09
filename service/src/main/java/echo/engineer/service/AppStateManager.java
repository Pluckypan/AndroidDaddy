package echo.engineer.service;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.BehaviorSubject;

/**
 * AppStateManager
 * Created by Plucky<plucky@echo.engineer> on 7/5/17 2017 16:42.
 */

public class AppStateManager {
    public static BehaviorSubject<String> messageState = BehaviorSubject.create();

    public static void publishMsgState(String msg) {
        messageState.onNext(msg);
    }

    public static Subscription subscribe(AppStateAction action) {
        return messageState.onBackpressureDrop().observeOn(AndroidSchedulers.mainThread()).subscribe(action::onAppReceiveMessage, action::onAppStatesError);
    }

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    public static class SimpleAppStateAction implements AppStateAction {

        @Override
        public void onAppReceiveMessage(String msg) {

        }

        @Override
        public void onAppStatesError(Throwable throwable) {

        }
    }

    public interface AppStateAction {
        void onAppReceiveMessage(String msg);

        void onAppStatesError(Throwable throwable);
    }
}
