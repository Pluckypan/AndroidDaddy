package engineer.echo.rxjava.cmpts;

import dagger.Component;
import engineer.echo.rxjava.DeviceStatus;

/**
 * AppComponent
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午10:37.
 */
@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {
    public DeviceStatus getStatus();
}
