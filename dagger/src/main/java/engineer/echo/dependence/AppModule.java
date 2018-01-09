package engineer.echo.dependence;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * AppModule
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午4:09.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
    @Provides
    public String getName(){
        return "Dagger - dependence";
    }
}
