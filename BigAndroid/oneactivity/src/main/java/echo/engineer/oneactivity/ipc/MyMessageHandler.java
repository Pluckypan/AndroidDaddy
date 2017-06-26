package echo.engineer.oneactivity.ipc;

import android.util.Log;

import com.ubnt.fr.library.ipc.request.SICallback;
import com.ubnt.fr.library.ipc.request.SIResponse;

/**
 * MyMessageHandler
 * Created by Plucky<plucky.pan@ubnt.com> on 6/26/17 2017 18:29.
 */

public class MyMessageHandler implements MyMessageService {
    public static final String TAG = "MyMessageHandler";

    @Override
    public void onNoExtraMessage() {
        Log.d(TAG, "onNoExtraMessage: ");
    }

    @Override
    public void onExtraMessage(MessageExtra extra) {
        Log.d(TAG, "onExtraMessage: " + extra);
    }

    @Override
    public void onWillNeverReceivedMessageByTarget() {
        Log.e(TAG, "onWillNeverReceivedMessageByTarget: ");
    }

    @Override
    public void onLongParam(long abc) {
        Log.d(TAG, "onLongParam: " + abc);
    }

    @Override
    public void onSimpleCallBack(long abc, SICallback<Void> callback) {
        callback.onResponse(SIResponse.successVoid());
    }
}
