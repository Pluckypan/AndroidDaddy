package engineer.echo.rxjava;

import android.util.Log;

/**
 * DeviceStatus
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午10:03.
 */

public class DeviceStatus {
    public String ssid;
    public boolean tcp_chanel_on;
    public boolean bluetooth_chanel_on;

    public DeviceStatus() {
        Log.d("Plucky", "DeviceStatus Created:" + toString());
    }
}
