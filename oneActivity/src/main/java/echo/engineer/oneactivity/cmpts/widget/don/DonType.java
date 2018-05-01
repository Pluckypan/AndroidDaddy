package echo.engineer.oneactivity.cmpts.widget.don;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static echo.engineer.oneactivity.cmpts.widget.don.Don.TYPE_DIALOG;
import static echo.engineer.oneactivity.cmpts.widget.don.Don.TYPE_PROGRESS_FILL;
import static echo.engineer.oneactivity.cmpts.widget.don.Don.TYPE_PROGRESS_INTERMINATE;
import static echo.engineer.oneactivity.cmpts.widget.don.Don.TYPE_PROGRESS_STROKE;
import static echo.engineer.oneactivity.cmpts.widget.don.Don.TYPE_TOAST;
import static echo.engineer.oneactivity.cmpts.widget.don.Don.TYPE_TOAST_WITH_IMAGE;

/**
 * DonType.java.java
 * Info: DonType.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/1 - 21:47
 * More about me: http://www.1991th.com
 */

@IntDef({TYPE_TOAST, TYPE_TOAST_WITH_IMAGE, TYPE_PROGRESS_INTERMINATE, TYPE_PROGRESS_STROKE, TYPE_PROGRESS_FILL, TYPE_DIALOG})
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface DonType {
}
