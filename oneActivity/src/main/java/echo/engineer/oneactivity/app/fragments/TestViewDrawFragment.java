package echo.engineer.oneactivity.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.cmpts.thread.RatThread;
import engineer.echo.oneactivity.core.MasterFragment;

/**
 * TestViewDrawFragment
 * Created by Plucky<plucky@echo.engineer> on 2017/7/23 下午11:52.
 */

public class TestViewDrawFragment extends MasterFragment implements View.OnClickListener {

    private static final String TAG = "EViewFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewdraw, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnStart).setOnClickListener(this);
        view.findViewById(R.id.btnStop).setOnClickListener(this);
        view.findViewById(R.id.btnOOM1).setOnClickListener(this);
        view.findViewById(R.id.btnOOM2).setOnClickListener(this);
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

    RatThread ratThread;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                ratThread = new RatThread();
                Thread thread = new Thread(ratThread, "线程");
                thread.start();
                break;
            case R.id.btnStop:
                if (ratThread != null) {
                    ratThread.stop();
                }
                break;
            case R.id.btnOOM1:
                doOOM();
                break;
            case R.id.btnOOM2:
                Integer i;
                Long l = 1L;
                break;
        }
    }

    private class OOMObj {
        private String[] strs = new String[100];
    }

    private void doOOM() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            OOMObj oom = new OOMObj();
            ooms.put(String.valueOf(i), oom);
            oom = null;
        }
    }

    private HashMap<String, OOMObj> ooms = new HashMap<>();
}
