package echo.engineer.oneactivity.message;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * MessengerHandler
 * Created by Plucky<plucky.pan@ubnt.com> on 7/5/17 2017 14:16.
 */

public class MessengerHandler extends Handler {

    private static final String TAG = "MessengerHandler";

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Log.d(TAG, "Client handleMessage msg=" + msg.getData().getString("reply"));
    }
}
