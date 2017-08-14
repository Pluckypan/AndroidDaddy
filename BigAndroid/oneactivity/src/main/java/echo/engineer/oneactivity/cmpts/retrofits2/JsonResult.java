package echo.engineer.oneactivity.cmpts.retrofits2;

import org.immutables.value.Value;

import javax.annotation.Nullable;

/**
 * JsonResult.java
 * Useage: JsonResult
 * Created by Plucky<py@meitu.com> on 2017/8/14 - 15:15
 */
public class JsonResult<T> {

    public int status;

    public String desc;

    public T data;
}
