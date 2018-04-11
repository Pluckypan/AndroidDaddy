package echo.engineer.oneactivity.cmpts.annotaions;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * OAType.java.java
 * Info: OAType.java
 * Created by Plucky<plucky@echo.engineer> on 2018/4/11 - 11:36
 * More about me: http://www.1991th.com
 */

@Documented
@Retention(RetentionPolicy.SOURCE)
@IntDef({OAType.OA_SMALL, OAType.OA_MIDDLE, OAType.OA_BIG})
public @interface OAType {
    public static final int OA_SMALL = 1001;
    public static final int OA_MIDDLE = 1002;
    public static final int OA_BIG = 1003;
}
