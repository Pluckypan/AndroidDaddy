package echo.engineer.oneactivity;

import android.app.Application;
import android.hardware.SensorManager;

import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.lang.ref.SoftReference;

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
        instance = App.this;
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        initLogger();
        Stetho.initializeWithDefaults(this);
        Config.makeSureDirExits();
        handleUncaughtException();
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(0)
                .methodOffset(7)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });


        Logger.addLogAdapter(new DiskLogAdapter());
    }

    private void handleUncaughtException() {
        Thread.UncaughtExceptionHandler mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                Logger.d("hello");
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
