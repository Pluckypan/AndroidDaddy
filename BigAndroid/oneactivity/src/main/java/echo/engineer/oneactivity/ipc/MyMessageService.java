package echo.engineer.oneactivity.ipc;

import com.ubnt.fr.library.ipc.SIDefinition;
import com.ubnt.fr.library.ipc.message.SIMessage;
import com.ubnt.fr.library.ipc.request.SICallback;

import java.io.Serializable;

/**
 * MyMessageService
 * Created by Plucky<plucky.pan@ubnt.com> on 6/26/17 2017 18:28.
 */

@SIDefinition
public interface MyMessageService {
    @SIMessage(1)
    void onNoExtraMessage();

    class MessageExtra implements Serializable {
        public String value;
        public int size;

        public MessageExtra(String value, int size) {
            this.value = value;
            this.size = size;
        }

        @Override
        public String toString() {
            return value + ", " + size;
        }
    }

    @SIMessage(2)
    void onExtraMessage(MessageExtra extra);

    @SIMessage(4)
    void onWillNeverReceivedMessageByTarget();

    @SIMessage(5)
    void onLongParam(long abc);

    @SIMessage(6)
    void onSimpleCallBack(long abc, SICallback<Void> callback);
}