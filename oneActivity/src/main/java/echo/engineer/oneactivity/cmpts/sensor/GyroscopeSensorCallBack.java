package echo.engineer.oneactivity.cmpts.sensor;

/**
 * GyroscopeSensorCallBack
 * Created by Plucky<plucky@echo.engineer> on 7/7/17 2017 17:51.
 */

public interface GyroscopeSensorCallBack {
    void onXDegreesChanged(float x);

    void onYDegreesChanged(float y);

    void onZDegreesChanged(float z);
}
