package echo.engineer.oneactivity.cmpts.widget.don;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static echo.engineer.oneactivity.cmpts.widget.don.DonProgressView.TYPE_FILL;
import static echo.engineer.oneactivity.cmpts.widget.don.DonProgressView.TYPE_STROKE;

/**
 * DonProgressType.java.java
 * Info: DonProgressType.java
 * Created by Plucky<plucky@echo.engineer> on 2018/4/30 - 22:17
 * More about me: http://www.1991th.com
 */

@IntDef({TYPE_STROKE, TYPE_FILL})
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface DonProgressType {
}
