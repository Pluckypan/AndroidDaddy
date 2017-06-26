package echo.engineer.oneactivity.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Locale;

import echo.engineer.oneactivity.R;

/**
 * Count down view
 * Created by glorin on 16/4/18.
 */
public class CountDownView extends FrameLayout {
    private static final String TAG = "CAM_CountDownView";
    private static final int SET_TIMER_TEXT = 1;
    private TextView mRemainingSecondsView;
    private Context mContext;
    private int mRemainingSecs = 0;
    private OnCountDownFinishedListener mListener;
    private final Handler mHandler = new MainHandler();
    private Animation mCountDownAnim;

    private boolean mPlaySound;

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mCountDownAnim = AnimationUtils.loadAnimation(context, R.anim.count_down_exit);
    }

    public interface OnCountDownFinishedListener {
        void onCountDownFinished();
    }

    private class MainHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            if (message.what == SET_TIMER_TEXT) {
                remainingSecondsChanged(mRemainingSecs - 1);
            }
        }
    }

    private void remainingSecondsChanged(int newVal) {
        mRemainingSecs = newVal;
        if (newVal == 0) {
            // Countdown has finished
            setVisibility(View.INVISIBLE);
            if (mListener != null)
                mListener.onCountDownFinished();
        } else {
            Locale locale = getResources().getConfiguration().locale;
            String localizedValue = String.format(locale, "%d", newVal);
            mRemainingSecondsView.setText(localizedValue);
            // Fade-out animation
            mCountDownAnim.reset();
            mRemainingSecondsView.clearAnimation();
            mRemainingSecondsView.startAnimation(mCountDownAnim);

            // Play sound effect for the last 3 seconds of the countdown
            if (mPlaySound) {
                if (newVal <= 3) {
                    FRSoundPool.get(getContext()).play(FRSoundPool.SoundType.CountDown);
                }
            }

            // Schedule the next remainingSecondsChanged() call in 1 second
            mHandler.sendEmptyMessageDelayed(SET_TIMER_TEXT, 1000);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRemainingSecondsView = (TextView) findViewById(R.id.remaining_seconds);
    }

    public void setCountDownFinishedListener(OnCountDownFinishedListener listener) {
        mListener = listener;
    }

    public void startCountDown(int sec, boolean playSound) {
        if (sec <= 0) {
            Log.w(TAG, "Invalid input for countdown timer: " + sec + " seconds");
            return;
        }
        mPlaySound = playSound;
        setVisibility(View.VISIBLE);
        remainingSecondsChanged(sec);
    }

    public void cancelCountDown() {
        if (mRemainingSecs > 0) {
            mRemainingSecs = 0;
            mHandler.removeMessages(SET_TIMER_TEXT);
            setVisibility(View.INVISIBLE);
        }
    }

    public void setOrientation(int orientation) {
        // mRemainingSecondsView.setRotation(orientation);
        // TODO
    }


    public boolean isCountingDown() {
        return mRemainingSecs > 0;
    }

}
