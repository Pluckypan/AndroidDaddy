package engineer.echo.dependence;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * QualifierForName
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午5:38.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface QualifierForName {
}
