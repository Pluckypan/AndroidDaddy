package engineer.echo.inject;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * BaseVar
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/26 下午4:51.
 */

public class ChannelManager {
    public static final String CHANNEL_BLUETOOTH = "by_bluetooth";
    public static final String CHANNEL_TCP = "by_tcp";

    public ChannelManager() {

    }

    private MustardChannel getAnno(Class t, String func, Class... params) {
        try {
            Method method = t.getMethod(func, params);
            MustardChannel annotation = method.getAnnotation(MustardChannel.class);
            System.out.println(annotation.value().length);
            return annotation;
        } catch (Exception e) {
            return null;
        }

    }

    private String getMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[4];
        return e.getMethodName();
    }

    public void getBridge() {
        String func = getMethodName();
        MustardChannel c = getAnno(this.getClass(), func);
        if (c != null) {
            String[] arr = c.value();
            if (arr.length > 0) {
                Log.d("Plucky", "current method:" + func + " anno:" + Arrays.toString(arr));
            }
        }

    }
}
