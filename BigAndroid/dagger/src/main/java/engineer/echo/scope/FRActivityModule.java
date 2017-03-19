package engineer.echo.scope;

import dagger.Module;
import dagger.Provides;

/**
 * FRActivityModule
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/19 下午7:52.
 */
@Module
public class FRActivityModule {
    @Provides
    @ScoActivity
    public DeviceStatus provideDeviceStatus() {
        return new DeviceStatus();
    }
}
