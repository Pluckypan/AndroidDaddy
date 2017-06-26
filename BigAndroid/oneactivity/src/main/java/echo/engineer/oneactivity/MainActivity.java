package echo.engineer.oneactivity;

import android.os.Bundle;
import android.util.Log;

import com.fragmentmaster.app.FragmentMaster;
import com.fragmentmaster.app.IMasterFragment;
import com.fragmentmaster.app.MasterActionBarActivity;
import com.fragmentmaster.app.Request;

import echo.engineer.oneactivity.fragments.HomeFragment;

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
    }
}