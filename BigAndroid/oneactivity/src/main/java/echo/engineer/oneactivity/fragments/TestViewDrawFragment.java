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

/**
 * TestViewDrawFragment
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/7/23 下午11:52.
 */

public class TestViewDrawFragment extends MasterFragment {

    private static final String TAG = "EViewFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_viewdraw, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
}
