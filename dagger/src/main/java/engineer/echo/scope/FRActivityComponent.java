package engineer.echo.scope;

import dagger.Component;
import engineer.echo.ScopeActivity;

/**
 * FRActivityComponent
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/19 下午7:54.
 */
@ScoActivity
@Component(modules = FRActivityModule.class)
public interface FRActivityComponent {
    void inject(ScopeActivity activity);
}
