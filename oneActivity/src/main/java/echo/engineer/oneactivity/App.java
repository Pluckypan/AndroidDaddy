package echo.engineer.oneactivity;

import android.app.Application;
import android.content.Context;
import android.hardware.SensorManager;

import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.io.File;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import echo.engineer.oneactivity.cmpts.annotaions.OAType;
import echo.engineer.oneactivity.cmpts.blockcanary.AppBlockCanaryContext;
import echo.engineer.oneactivity.cmpts.dagger.AppScope;
import echo.engineer.oneactivity.cmpts.retrofits.WeatherApi;
import echo.engineer.oneactivity.cmpts.retrofits.WeatherConfig;
import echo.engineer.oneactivity.cmpts.sensor.GyroscopeSensorWrapper;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * App
 * Created by Plucky<plucky@echo.engineer> on 6/22/17 2017 14:34.
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
        typeToString(OAType.OA_BIG);
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

        @AppScope
        @Provides
        public OkHttpClient provideOkHttpClient(Context context) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            Cache cache = new Cache(new File(context.getCacheDir(), "web"), 100 * 1024 * 1024);//100M
            return new OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(loggingInterceptor)
                    .build();
        }

        @AppScope
        @Provides
        public Gson provideGson() {
            return new GsonBuilder()
                    .create();
        }

        @AppScope
        @Provides
        public Context provideContext() {
            return App.this;
        }

        @AppScope
        @Provides
        public WeatherApi provideWeatherApi(Gson gson, OkHttpClient okHttpClient) {
            return new Retrofit.Builder()
                    .baseUrl(WeatherConfig.BASEURL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .validateEagerly(BuildConfig.DEBUG)
                    .build()
                    .create(WeatherApi.class);
        }
    }

    @AppScope
    @Component(modules = {AppModule.class})
    public interface AppComponent {

        SensorManager getSensorManager();

        GyroscopeSensorWrapper getGyroscopeSensorWrapper();

        WeatherApi getWeatherApi();

        Gson getGson();
    }

    public static String typeToString(@OAType int oaType) {
        return String.valueOf(oaType);
    }
}
