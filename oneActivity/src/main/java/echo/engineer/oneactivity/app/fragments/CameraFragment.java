package echo.engineer.oneactivity.app.fragments;

import android.graphics.YuvImage;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camerakit.CameraKitView;
import com.github.mmin18.widget.RealtimeBlurView;
import echo.engineer.oneactivity.R;
import engineer.echo.oneactivity.core.MasterFragment;

/**
 * CameraFragment
 * Created by Plucky<plucky@echo.engineer> on 7/13/17 2017 15:48.
 */

public class CameraFragment extends MasterFragment {

    private CameraKitView cameraKitView;
    private RealtimeBlurView blurView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cameraKitView = (CameraKitView) view.findViewById(R.id.cameraView);
        blurView = (RealtimeBlurView) view.findViewById(R.id.blurView);
        cameraKitView.setCameraListener(new CameraKitView.CameraListener() {
            @Override
            public void onOpened() {

            }

            @Override
            public void onClosed() {

            }
        });
        blurView.setBlurRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()));
        blurView.invalidate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    public void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
