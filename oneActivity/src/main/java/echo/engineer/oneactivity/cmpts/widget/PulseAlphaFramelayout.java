package echo.engineer.oneactivity.cmpts.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * PulseAlphaFramelayout
 * Created on 07/06/2017.
 */

public class PulseAlphaFramelayout extends FrameLayout implements ValueAnimator.AnimatorUpdateListener {
    private static final long DEFAULT_PULSE_INTERVAL = 1000L;

    private ValueAnimator mAnimator;
    private long mPulseInterval = DEFAULT_PULSE_INTERVAL;

    public PulseAlphaFramelayout(@NonNull Context context) {
        super(context);
    }

    public PulseAlphaFramelayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PulseAlphaFramelayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (getVisibility() == VISIBLE && !isPulsing()) {
            startPulse();
        }
    }

    private boolean isPulsing() {
        return mAnimator != null && mAnimator.isRunning();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (isPulsing()) {
            stopPulse();
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (visibility == VISIBLE) {
            if (!isPulsing()) {
                startPulse();
            }
        } else {
            if (isPulsing()) {
                stopPulse();
            }
        }
    }

    private void startPulse() {
        if (mAnimator == null) {
            mAnimator = ValueAnimator.ofFloat(0f, 1f);
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mAnimator.removeAllUpdateListeners();
                    super.onAnimationEnd(animation);
                }
            });

            mAnimator.setRepeatCount(ValueAnimator.INFINITE);

            mAnimator.setInterpolator(new TimeInterpolator() {
                @Override
                public float getInterpolation(float v) {
                    return (float) (Math.sin(2f * Math.PI * v - Math.PI / 2f) + 1) / 2f;
                }
            });
        }

        if (mAnimator.isRunning()) mAnimator.end();

        mAnimator.setDuration(mPulseInterval);

        mAnimator.addUpdateListener(this);

        mAnimator.start();
    }

    private void stopPulse() {
        if (mAnimator != null && mAnimator.isRunning()) mAnimator.end();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float value = (float) valueAnimator.getAnimatedValue();

        setAlpha(value);
    }
}
