package echo.engineer.oneactivity;

import android.app.Application;
import android.hardware.SensorManager;
import com.github.moduth.blockcanary.BlockCanary;
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
