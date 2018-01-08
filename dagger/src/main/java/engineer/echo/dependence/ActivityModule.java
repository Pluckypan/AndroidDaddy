package engineer.echo.dependence;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

/**
 * ActivityModule
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/19 下午4:16.
 */
@Module
public class ActivityModule {

    @QualifierForContext
    @Provides
    public Person providePersonByContext(Context c) {
        return new Person(c);
    }

    @QualifierForName
    @Provides
    public Person providePersonByName(String name) {
        return new Person(name);
    }
}
