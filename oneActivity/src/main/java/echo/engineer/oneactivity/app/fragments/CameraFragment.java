package echo.engineer.oneactivity.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mmin18.widget.RealtimeBlurView;

import echo.engineer.oneactivity.R;
import engineer.echo.oneactivity.core.MasterFragment;

/**
 * CameraFragment
 * Created by Plucky<plucky@echo.engineer> on 7/13/17 2017 15:48.
 */

public class CameraFragment extends MasterFragment {

    private RealtimeBlurView blurView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        blurView = (RealtimeBlurView) view.findViewById(R.id.blurView);
        blurView.setBlurRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()));
        blurView.invalidate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }
}
