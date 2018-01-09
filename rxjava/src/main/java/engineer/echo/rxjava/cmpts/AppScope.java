package engineer.echo.rxjava.cmpts;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * AppScope
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午10:36.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
