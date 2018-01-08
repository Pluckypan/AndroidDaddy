package echo.engineer.oneactivity.fragments;

import android.graphics.YuvImage;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flurgle.camerakit.CameraListener;
import com.flurgle.camerakit.CameraView;
import com.fragmentmaster.app.MasterFragment;
import com.github.mmin18.widget.RealtimeBlurView;

import java.io.File;

import echo.engineer.oneactivity.R;

/**
 * CameraFragment
 * Created by Plucky<plucky.pan@ubnt.com> on 7/13/17 2017 15:48.
 */

public class CameraFragment extends MasterFragment {

    private CameraView cameraView;
    private RealtimeBlurView blurView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cameraView = (CameraView) view.findViewById(R.id.cameraView);
        blurView = (RealtimeBlurView) view.findViewById(R.id.blurView);
        cameraView.setCameraListener(new CameraListener() {
            @Override
            public void onCameraOpened() {
                super.onCameraOpened();
            }

            @Override
            public void onCameraClosed() {
                super.onCameraClosed();
            }

            @Override
            public void onPictureTaken(byte[] jpeg) {
                super.onPictureTaken(jpeg);
            }

            @Override
            public void onPictureTaken(YuvImage yuv) {
                super.onPictureTaken(yuv);
            }

            @Override
            public void onVideoTaken(File video) {
                super.onVideoTaken(video);
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
    public void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraView.stop();
    }
}
