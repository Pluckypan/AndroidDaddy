package engineer.echo.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * ScopeApp 全局单例APP
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/19 下午5:54.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ScoApp {
}
