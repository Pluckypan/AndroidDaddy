package echo.engineer.oneactivity.cmpts.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import javax.inject.Inject;

import static android.hardware.SensorManager.SENSOR_DELAY_UI;

/**
 * GyroscopeSensorWrapper
 * Created by Plucky<plucky.pan@ubnt.com> on 7/5/17 2017 21:28.
 */

public class GyroscopeSensorWrapper implements SensorEventListener {

    private static final String TAG = "GyroscopeSensorWrapper";

    private Sensor sensor;
    private SensorManager sensorManager;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private float timestamp;
    private static final float THRESHOLD = 0.50f;

    private float dx, dy, dz;

    @Inject
    public GyroscopeSensorWrapper(SensorManager sensorManager) {
        log("GyroscopeSensorWrapper created!");
        this.sensorManager = sensorManager;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    public void start() {
        sensorManager.registerListener(this, sensor, SENSOR_DELAY_UI);
    }

    public void stop() {
        sensorManager.unregisterListener(this, sensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            if (timestamp > 0) {
                final float dT = (event.timestamp - timestamp) * NS2S;
                dx += event.values[0] * dT;
                dy += event.values[1] * dT;
                dz += event.values[2] * dT;

                float anglex = (float) Math.toDegrees(dx);
                float angley = (float) Math.toDegrees(dy);
                float anglez = (float) Math.toDegrees(dz);

                if (Math.abs(lastAngleX - anglex) > THRESHOLD) {
                    log("x degrees changed:" + anglex);
                    if (sensorCallBack != null) {
                        sensorCallBack.onXDegreesChanged(anglex);
                    }
                    lastAngleX = anglex;
                }

                if (Math.abs(lastAngleY - angley) > THRESHOLD) {
                    log("y degrees changed:" + angley);
                    if (sensorCallBack != null) {
                        sensorCallBack.onYDegreesChanged(angley);
                    }
                    lastAngleY = angley;
                }

                if (Math.abs(lastAngleZ - anglez) > THRESHOLD) {
                    log("z degrees changed:" + anglez);
                    if (sensorCallBack != null) {
                        sensorCallBack.onZDegreesChanged(anglez);
                    }
                    lastAngleZ = anglez;
                }

            }
            timestamp = event.timestamp;
        }
    }

    private float lastAngleX, lastAngleY, lastAngleZ;

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private GyroscopeSensorCallBack sensorCallBack;

    public void setSensorCallBack(GyroscopeSensorCallBack sensorCallBack) {
        this.sensorCallBack = sensorCallBack;
    }

    private void log(String msg) {
        Log.d(TAG, msg);
    }
}
