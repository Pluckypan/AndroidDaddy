package engineer.echo;

import com.iflytek.voicecloud.client.LfasrClient;
import com.iflytek.voicecloud.model.LfasrType;
import com.iflytek.voicecloud.model.Message;

public class XFYunTina {

    static final String APP_ID = "58cc7863";
    static final String APP_KEY = "75d323a9a94cc4f5efe48c655d147c98";

    public static void main(String[] args) {
        System.out.println("Hello - Tina");
        String _path = "/Users/plucky/plucky/github/bigAndroid/BigAndroid/xfyuntina/plucky.mp3";
        String _taskid="0480a16d1098433783e52fb364522d89";
        try {
            //upload(_path);
            result(_taskid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void upload(String path) throws Exception {
        LfasrType type = LfasrType.LFASR_STANDARD_RECORDED_AUDIO;
        LfasrClient client = LfasrClient.InitClient(APP_ID, APP_KEY, type);
        Message message = client.lfasr_upload(path);
        System.out.println(message);
    }

    public static void result(String taskid) throws Exception {
        LfasrType type = LfasrType.LFASR_STANDARD_RECORDED_AUDIO;
        LfasrClient client = LfasrClient.InitClient(APP_ID, APP_KEY, type);
        Message message = client.lfasr_get_result(taskid);
        System.out.println(message);
    }
}
