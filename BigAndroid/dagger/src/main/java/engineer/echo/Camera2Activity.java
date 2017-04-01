package engineer.echo;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.transition.AutoTransition;
import android.support.transition.ChangeBounds;
import android.support.transition.Fade;
import android.support.transition.Scene;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionValues;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Camera2Activity extends AppCompatActivity {

    @Bind(R.id.frameCircle)
    RoundCoverView frameCircle;
    @Bind(R.id.tvRender)
    View tvRender;
    @Bind(R.id.vCircle)
    View vCircle;
    @Bind(R.id.frameRoot)
    FrameLayout frameRoot;

    private int circleSize, circleRadius, screenW, startTop, oldTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        circleSize = getResources().getDimensionPixelSize(R.dimen.circle_height);
        screenW = getResources().getDisplayMetrics().widthPixels;

        Position pos = getIntent().getExtras().getParcelable("POS");

        int oldpos[] = new int[]{0, 0};
        frameRoot.getLocationInWindow(oldpos);
        oldTop = oldpos[1];

        if (pos != null) {
            startTop = pos.top;
            Log.d("Plucky", "CameraActivity1 --- top:" + startTop);
            frameRoot.setTranslationY(-120.f);
        }

        setLayoutParams(screenW, circleSize, tvRender);
        setLayoutParams(screenW, circleSize, frameCircle);
        setLayoutParams(circleSize, circleSize, vCircle);

        circleRadius = circleSize / 2;
        frameCircle.setRadius(circleRadius);
        vCircle.setAlpha(0);

        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                if (beforeExit) {
                    //TransitionManager.beginDelayedTransition(frameCircle);
                    frameCircle.setRadius(circleRadius);
                }
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
                Log.d("Plucky", "CameraActivity1 --- onSharedElementStart");

            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
                if (!beforeExit) {
                    ValueAnimator animator = ValueAnimator.ofInt(circleRadius, screenW);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int cur = (int) animation.getAnimatedValue();
                            frameCircle.setRadius(cur);
                            //vCircle.setAlpha(cur/screenW);
                        }
                    });
                    animator.setDuration(1200L);
                    animator.start();
                    beforeExit = true;
                }
                Log.d("Plucky", "CameraActivity1 --- onSharedElementEnd");
            }
        });
    }

    private boolean beforeExit;

    private void setLayoutParams(int w, int h, View view) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(w, h);
        params.gravity = Gravity.CENTER;
        view.setLayoutParams(params);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
