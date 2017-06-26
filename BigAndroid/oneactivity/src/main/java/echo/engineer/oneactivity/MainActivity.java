package echo.engineer.oneactivity;

import android.os.Bundle;
import android.util.Log;

import com.fragmentmaster.app.FragmentMaster;
import com.fragmentmaster.app.IMasterFragment;
import com.fragmentmaster.app.MasterActionBarActivity;
import com.fragmentmaster.app.Request;
import com.google.gson.Gson;
import com.ubnt.fr.library.ipc.SimpleIPC;
import com.ubnt.fr.library.ipc.message.SIMessageSender;

import echo.engineer.oneactivity.fragments.HomeFragment;
import echo.engineer.oneactivity.ipc.MyMessageHandler;
import echo.engineer.oneactivity.ipc.MyMessageService;

public class MainActivity extends MasterActionBarActivity {

    public static final String TAG = "MainActivity";

    private FragmentMaster.FragmentLifecycleCallbacks mLifecycleCallbacks =
            new FragmentMaster.SimpleFragmentLifecycleCallbacks() {
                @Override
                public void onFragmentResumed(IMasterFragment fragment) {
                    if (BuildConfig.DEBUG)
                        Log.d(TAG, "[onResume]     " + fragment.toString());
                }

                @Override
                public void onFragmentActivated(IMasterFragment fragment) {
                    if (BuildConfig.DEBUG)
                        Log.d(TAG, "[onActivated]  " + fragment.toString());
                }

                @Override
                public void onFragmentDeactivated(IMasterFragment fragment) {
                    if (BuildConfig.DEBUG)
                        Log.d(TAG, "[onDeactivated]" + fragment.toString());
                }

                @Override
                public void onFragmentPaused(IMasterFragment fragment) {
                    if (BuildConfig.DEBUG)
                        Log.d(TAG, "[onPaused]     " + fragment.toString());
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentMaster fragmentMaster = getFragmentMaster();
        fragmentMaster.registerFragmentLifecycleCallbacks(mLifecycleCallbacks);
        fragmentMaster.install(R.id.container, new Request(HomeFragment.class), true);
        initIpc();
    }

    private void initIpc() {
        SimpleIPC ipc = new SimpleIPC(this);
        ipc.register("echo.engineer.oneactivity.NEW_MESSAGE", new MyMessageHandler());
    }

    private void createService() {
        final MyMessageService service =
                new SIMessageSender.Builder(this, new Gson())
                        .action("com.ubnt.ipc.test.action.NEW_MESSAGE")
                        .target("YOUR TARGET's PACKAGE_NAME")
                        .build().createService(MyMessageService.class);
    }
}
