package echo.engineer.oneactivity;

import android.app.Application;
import android.hardware.SensorManager;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import echo.engineer.oneactivity.cmpts.dagger.AppScope;
import echo.engineer.oneactivity.cmpts.sensor.GyroscopeSensorWrapper;

/**
 * App
 * Created by Plucky<plucky.pan@ubnt.com> on 6/22/17 2017 14:34.
 */

public class App extends Application {

    private static App instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        instance = App.this;
        Stetho.initializeWithDefaults(this);
        Config.makeSureDirExits();
        handleUncaughtException();
    }

    private void handleUncaughtException() {
        Thread.UncaughtExceptionHandler mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                Log.d("App", "uncaughtException: ", throwable);
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(new FileWriter(Config.LOG_PATH + "app_error.txt", true));
                    writer.append(new Date(System.currentTimeMillis()).toString()).append("\t" + BuildConfig.VERSION_NAME).append("\n\n");
                    throwable.printStackTrace(writer);
                    writer.append("-------\n\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null) {
                        writer.close();
                    }
                }
                mUncaughtExceptionHandler.uncaughtException(thread, throwable);
            }
        });
    }

    public static App getApp() {
        return instance;
    }

    public static AppComponent getComponent() {
        return getApp().getAppComponent();
    }

    private AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerApp_AppComponent.builder().appModule(new AppModule()).build();
        }
        return appComponent;
    }


    @Module
    public class AppModule {
        @AppScope
        @Provides
        public SensorManager provideSensorManager() {
            return (SensorManager) App.this.getSystemService(SENSOR_SERVICE);
        }

        @AppScope
        @Provides
        public GyroscopeSensorWrapper provideGyroscopeSensorWrapper(SensorManager sensorManager) {
            return new GyroscopeSensorWrapper(sensorManager);
        }
    }

    @AppScope
    @Component(modules = {AppModule.class})
    public interface AppComponent {

        SensorManager getSensorManager();

        GyroscopeSensorWrapper getGyroscopeSensorWrapper();
    }
}
