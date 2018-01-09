package engineer.echo.dependence;

import dagger.Component;
import engineer.echo.SecondActivity;

/**
 * ActivityComponent
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午4:17.
 */

@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(SecondActivity activity);
}
