package engineer.echo.scope;

import android.util.Log;

/**
 * DeviceStatus
 * Created by Plucky<plucky@echo.engineer> on 2017/3/19 下午6:03.
 */

public class DeviceStatus {
    public boolean is_connected;
    public String ssid;
    public int level;

    public DeviceStatus() {
        Log.d("DeviceStatus", toString());
    }
}
