package engineer.echo.scope;

import dagger.Component;

/**
 * FRAppComponent
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/19 下午6:08.
 */
@ScoApp
@Component(modules = {FRAppModule.class})
public interface FRAppComponent {
    DeviceStatus getDeviceStatus();
}
