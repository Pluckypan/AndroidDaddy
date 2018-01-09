package engineer.echo.dependence;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * QualifierForContext
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午5:24.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface QualifierForContext {
}
