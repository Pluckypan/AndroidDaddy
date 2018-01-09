package engineer.echo.scope;

import dagger.Module;
import dagger.Provides;
import engineer.echo.DaggerApp;

/**
 * FRAppModule
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午6:01.
 */

@Module
public class FRAppModule {

    private DaggerApp app;

    public FRAppModule(DaggerApp app) {
        this.app = app;
    }

    @ScoApp
    @Provides
    public DeviceStatus provideDeviceStatus() {
        return new DeviceStatus();
    }
}
