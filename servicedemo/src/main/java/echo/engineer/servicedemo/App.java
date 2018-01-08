package echo.engineer.servicedemo;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**
 * App
 * Created by Plucky<plucky.pan@ubnt.com> on 7/5/17 2017 14:09.
 */

public class App extends Application {

    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, MessageService.class));
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory level=" + level + " TRIM_MEMORY_UI_HIDDEN=" + TRIM_MEMORY_UI_HIDDEN);

    }
}
