package engineer.echo.rxjava.cmpts;

import dagger.Module;
import dagger.Provides;
import engineer.echo.rxjava.DeviceStatus;

/**
 * AppModule
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/19 下午10:35.
 */
@Module
public class AppModule {

    @AppScope
    @Provides
    public DeviceStatus provideDeviceStatus() {
        return new DeviceStatus();
    }
}
