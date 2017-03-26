package engineer.echo.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MustardChannel
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/26 下午4:59.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MustardChannel {
    String[] value() default ChannelManager.CHANNEL_BLUETOOTH;
}
