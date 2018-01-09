package echo.engineer.oneactivity.cmpts.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * AppScope
 * Created by Plucky<plucky@echo.engineer> on 7/5/17 2017 21:25.
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {

}
