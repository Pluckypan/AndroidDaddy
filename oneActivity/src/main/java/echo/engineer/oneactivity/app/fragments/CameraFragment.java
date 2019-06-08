package echo.engineer.oneactivity.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import echo.engineer.oneactivity.R;
import engineer.echo.oneactivity.core.MasterFragment;

/**
 * CameraFragment
 * Created by Plucky<plucky@echo.engineer> on 7/13/17 2017 15:48.
 */

public class CameraFragment extends MasterFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }
}
