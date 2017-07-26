package echo.engineer.oneactivity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.fragmentmaster.app.MasterFragment;

import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.cmpts.BaseThread;
import echo.engineer.oneactivity.cmpts.StopableThread;

/**
 * TestViewDrawFragment
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/7/23 下午11:52.
 */

public class TestViewDrawFragment extends MasterFragment implements View.OnClickListener {

    private static final String TAG = "EViewFragment";
    private StopableThread stopableThread;
    private BaseThread thread = new BaseThread("TEST", false) {
        @Override
        public void process() {
            for (int i = 0; i < 100000; i++) {
                Log.d("EView", "TEST i=" + i);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_viewdraw, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnStart).setOnClickListener(this);
        view.findViewById(R.id.btnStop).setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent action=" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onTouchEvent action=" + ev.getAction());
        return super.onTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                stopableThread = new StopableThread();
                stopableThread.start();
                break;
            case R.id.btnStop:
                if (stopableThread != null) {
                    stopableThread.exitThread();
                }

                thread.suspend();
                break;
        }
    }
}
