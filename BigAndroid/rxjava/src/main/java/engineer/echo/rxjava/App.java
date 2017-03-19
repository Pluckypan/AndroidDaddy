package engineer.echo.rxjava;

import android.app.Application;
import android.content.Context;

import engineer.echo.rxjava.cmpts.AppComponent;
import engineer.echo.rxjava.cmpts.AppModule;
import engineer.echo.rxjava.cmpts.DaggerAppComponent;

/**
 * App
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/19 下午10:11.
 */

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

    public static AppComponent getComponent(Context context) {
        return getApp(context).component;
    }

    public static DeviceStatus getStatus(Context context) {
        return getComponent(context).getStatus();
    }

    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }
}
