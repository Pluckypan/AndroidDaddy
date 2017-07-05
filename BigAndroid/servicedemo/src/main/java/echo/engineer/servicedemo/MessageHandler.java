package echo.engineer.servicedemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * MessageHandler
 * Created by Plucky<plucky.pan@ubnt.com> on 7/5/17 2017 14:02.
 */

public class MessageHandler extends Handler {
    private static final String TAG = "MessageHandler";

    @Override
    public void handleMessage(Message msg) {
        String receiveMsg = msg.getData().getString("msg");
        Log.i(TAG, "Server handleMessage msg=" + receiveMsg);
        AppStateManager.publishMsgState(receiveMsg);
        Messenger messenger = msg.replyTo;
        Message message = Message.obtain(null, 0);
        Bundle bundle = new Bundle();
        bundle.putString("reply", "receive");
        message.setData(bundle);
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
