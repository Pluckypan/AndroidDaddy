package engineer.echo.keeper;

import android.app.Application;
import android.util.Log;

/**
 * APP
 * Created by Plucky<plucky@echo.engineer> on 2018/1/14 下午12:17.
 * more about me: http://www.1991th.com
 */

public class App extends Application {
    private static Application mApp;
    public static final boolean DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Application getApp() {
        return mApp;
    }

    public static void LOG(String TAG, String msg) {
        if (DEBUG) {
            Log.d(TAG, "LOG: " + msg);
        }
    }
}
