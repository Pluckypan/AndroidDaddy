package engineer.echo.dependence;

import android.content.Context;

import dagger.Component;

/**
 * AppComponent
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午4:10.
 */

@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();
    String getName();
}
