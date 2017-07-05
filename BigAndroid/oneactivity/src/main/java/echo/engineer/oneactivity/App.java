package echo.engineer.oneactivity;

import android.app.Application;
import android.content.Intent;

import com.github.moduth.blockcanary.BlockCanary;

import echo.engineer.oneactivity.message.MessengerService;

/**
 * App
 * Created by Plucky<plucky.pan@ubnt.com> on 6/22/17 2017 14:34.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }
}
