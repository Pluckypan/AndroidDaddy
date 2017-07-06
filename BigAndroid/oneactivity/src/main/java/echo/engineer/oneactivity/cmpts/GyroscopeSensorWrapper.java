package echo.engineer.oneactivity.cmpts;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import javax.inject.Inject;

import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;

/**
 * GyroscopeSensorWrapper
 * Created by Plucky<plucky.pan@ubnt.com> on 7/5/17 2017 21:28.
 */

public class GyroscopeSensorWrapper implements SensorEventListener {

    private static final String TAG = "GyroscopeSensorWrapper";

    private Sensor sensor;
    private SensorManager sensorManager;

    @Inject
    public GyroscopeSensorWrapper(SensorManager sensorManager) {
        Log.d(TAG, "GyroscopeSensorWrapper created!");
        this.sensorManager = sensorManager;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    public void start() {
        sensorManager.registerListener(this, sensor, SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        sensorManager.unregisterListener(this, sensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            Log.d(TAG, "onSensorChanged x:" + event.values[0] + " y:" + event.values[1] + " z:" + event.values[2]
                    + " accuracy=" + event.accuracy + " vendor=" + sensor.getVendor() + " version=" + sensor.getVersion());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
