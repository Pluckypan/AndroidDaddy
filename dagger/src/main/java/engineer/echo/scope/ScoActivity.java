package engineer.echo.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * ScopeActivity
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午5:57.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ScoActivity {
}
