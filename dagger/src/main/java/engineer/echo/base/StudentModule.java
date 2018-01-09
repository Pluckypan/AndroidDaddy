package engineer.echo.base;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * StudentModule(manage object instance)
 * Created by Plucky<plucky@echo.engineer> on 2017/3/18 下午10:53.
 */

@Module
public class StudentModule {

    @Provides
    public String provideName() {
        return "Plucky";
    }

    @Provides
    public int provideAge() {
        return 26;
    }

    @Provides
    @Singleton
    public Student provideStudent(String name, int age) {
        return new Student(name, age);
    }
}
