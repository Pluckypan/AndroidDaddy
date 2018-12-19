package engineer.echo.hugox;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * DebugLog.java
 * Info: DebugLog.java
 * Created by Plucky(plucky@echo.engineer) on 2018/12/18 - 9:01 PM
 * more about me: http://www.1991th.com
 */

@Target({TYPE, METHOD, CONSTRUCTOR})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DebugLog {
    boolean showSpendTime() default false;
}
