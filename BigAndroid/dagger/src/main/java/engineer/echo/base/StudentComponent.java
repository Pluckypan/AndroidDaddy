package engineer.echo.base;

import javax.inject.Singleton;

import dagger.Component;
import engineer.echo.ScrollingActivity;
/**
 * StudentComponent
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/18 下午11:00.
 */
@Singleton
@Component(modules = {StudentModule.class})
public interface StudentComponent {
    void inject(ScrollingActivity activity);
    String getName();
}
