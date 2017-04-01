package engineer.echo;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.transition.TransitionManager;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.os.TraceCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity1 extends AppCompatActivity {

    @Bind(R.id.frameCircle)
    RoundAngleFrameLayout frameCircle;

    @Bind(R.id.tvRender)
    View tvRender;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private int circleSize, circleRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_camera1);
        ButterKnife.bind(this);

        circleSize = getResources().getDimensionPixelSize(R.dimen.circle_height);
        circleRadius = getResources().getDimensionPixelSize(R.dimen.circle_radius);

        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                if (beforeExit) {
                    //TransitionManager.beginDelayedTransition(frameCircle);
                    frameCircle.setRadius(circleRadius);
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(circleSize, circleSize);
                    tvRender.setLayoutParams(params);
                }
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
                Log.d("Plucky", "CameraActivity1 --- onSharedElementStart");

            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
                if (!beforeExit) {
                    tvRender.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TransitionManager.beginDelayedTransition(frameCircle);
                            frameCircle.setRadius(0);
                            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, circleSize);
                            tvRender.setLayoutParams(params);
                            beforeExit = true;
                        }
                    },400);
                }
                Log.d("Plucky", "CameraActivity1 --- onSharedElementEnd");
            }
        });
    }

    private boolean beforeExit;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Plucky", "CameraActivity1 --- onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Plucky", "CameraActivity1 --- onStop");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("Plucky", "CameraActivity1 --- onBackPressed");
    }

    @Override
    public void finish() {
        super.finish();
        Log.d("Plucky", "CameraActivity1 --- finish");
    }

    @OnClick(value = {R.id.btnChange})
    public void onViewClick(View view) {
        TransitionManager.beginDelayedTransition(frameCircle);
        if (view.isSelected()) {
            frameCircle.setRadius(0);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, circleSize);
            tvRender.setLayoutParams(params);
        } else {
            frameCircle.setRadius(circleRadius);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(circleSize, circleSize);
            tvRender.setLayoutParams(params);
        }
        view.setSelected(!view.isSelected());

    }
}
