package engineer.echo;

import android.animation.ObjectAnimator;
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
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Camera2Activity extends AppCompatActivity {

    @Bind(R.id.llRoot)
    LinearLayout llRoot;
    @Bind(R.id.frameCircle)
    RoundCoverView frameCircle;
    @Bind(R.id.tvRender)
    View tvRender;
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

        setLayoutParams(screenW, circleSize, tvRender);
        setLayoutParams(screenW, circleSize, frameCircle);

        final TranslatePosition position = getIntent().getExtras().getParcelable("POS");

//        ObjectAnimator translationUp = ObjectAnimator.ofFloat(frameRoot, "Y",
//                frameRoot.getY(),position.startCenterY);
//        translationUp.setInterpolator(new DecelerateInterpolator());
//        translationUp.setDuration(400);
//        translationUp.start();

        llRoot.postDelayed(new Runnable() {
            @Override
            public void run() {
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
            }
        },400);

        frameRoot.setTranslationY(frameRoot.getY()-position.startCenterY);

        circleRadius = circleSize / 2;
        frameCircle.setRadius(circleRadius);

//        llRoot.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ObjectAnimator translationUp = ObjectAnimator.ofFloat(frameRoot, "Y",
//                        position.startCenterY, frameRoot.getY());
//                translationUp.setInterpolator(new DecelerateInterpolator());
//                translationUp.setDuration(400);
//                translationUp.start();
//            }
//        }, 10);


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
