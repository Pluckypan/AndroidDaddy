package engineer.echo.floatwindow;

import android.app.Application;
import android.util.Log;

/**
 * SecondActivity
 * Created by Plucky<plucky@echo.engineer> on 2018/1/8 - 16:47
 * more about me: http://www.1991th.com
 */

public class APP extends Application {
    private static final String TAG = "APP";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory: level=" + level);
    }
}
