package engineer.echo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import engineer.echo.scope.DaggerFRAppComponent;
import engineer.echo.scope.FRAppComponent;
import engineer.echo.scope.FRAppModule;

/**
 * DaggerApp
 * Created by Plucky<plucky@echo.engineer> on 2017/3/18 下午10:49.
 */

public class DaggerApp extends Application {

    private static FRAppComponent component;

    public static FRAppComponent getComponent(Context context) {
        if (component == null) {
            component = DaggerFRAppComponent.builder().fRAppModule(new FRAppModule(getApp(context))).build();
        }
        return component;
    }

    public static DaggerApp getApp(Context context) {
        return (DaggerApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void gotoActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
}
