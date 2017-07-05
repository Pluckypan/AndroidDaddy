package echo.engineer.oneactivity;

import android.app.Application;
import android.hardware.SensorManager;

import com.github.moduth.blockcanary.BlockCanary;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import echo.engineer.oneactivity.cmpts.dagger.AppScope;

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


    @Module
    public class AppModule {
        @AppScope
        @Provides
        public SensorManager provideSensorManager() {
            return (SensorManager) App.this.getSystemService(SENSOR_SERVICE);
        }
    }

    @AppScope
    @Component(modules = {AppModule.class})
    public interface AppComponent {
        SensorManager getSensorManager();
    }
}
