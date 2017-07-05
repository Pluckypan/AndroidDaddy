package echo.engineer.oneactivity.cmpts;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import javax.inject.Inject;

import static android.content.Context.SENSOR_SERVICE;

/**
 * GyroscopeSensorWrapper
 * Created by Plucky<plucky.pan@ubnt.com> on 7/5/17 2017 21:28.
 */

public class GyroscopeSensorWrapper implements SensorEventListener {

    @Inject
    private SensorManager sensorManager;
    private Sensor sensor;

    public GyroscopeSensorWrapper(Context context) {
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    public void start() {
        sensorManager.registerListener(this, sensor, 400);
    }

    public void stop() {
        sensorManager.unregisterListener(this, sensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
