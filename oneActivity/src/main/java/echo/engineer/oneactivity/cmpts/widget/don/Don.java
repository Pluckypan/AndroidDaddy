package echo.engineer.oneactivity.cmpts.widget.don;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;

import echo.engineer.oneactivity.R;

/**
 * Don
 * Created by Plucky<plucky@echo.engineer> on 2018/4/21 下午4:39.
 * more about me: http://www.1991th.com
 */

public class Don {

    private ViewGroup mDecorView;
    private FrameLayout mRootView;
    private FrameLayout mContainerView;

    private WeakReference<Activity> mContext;

    private boolean mCanceledOnTouchOutside;
    private float mOpacity;

    private Don() {

    }

    Don(Builder builder) {
        mContext = new WeakReference<>(builder.activity);
        mCanceledOnTouchOutside = builder.canceledOnTouchOutside;
        mOpacity = builder.opacity;
        Activity activity = mContext.get();
        if (activity != null) {
            mDecorView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
            mRootView = (FrameLayout) LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_don, mDecorView, false);
            mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            setBackgroundOpacity(mOpacity);
            mRootView.setOnClickListener(v -> {
                if (mCanceledOnTouchOutside) {
                    dismiss();
                }
            });
        }
    }

    private void attachRootView() {
        mDecorView.addView(mRootView);
    }

    private void detachRootView() {
        mDecorView.removeView(mRootView);
    }

    private void setBackgroundOpacity(float opacity) {
        Drawable drawable = mRootView.getBackground();
        if (drawable != null) {
            drawable.setAlpha((int) (opacity * 255));
        }
    }

    public boolean isShowing() {
        return mRootView != null && mRootView.getParent() != null;
    }

    public void show() {
        if (mDecorView == null) return;
        if (mRootView == null) return;
        attachRootView();
    }

    public void dismiss() {
        if (mDecorView == null) return;
        if (mRootView == null) return;
        detachRootView();
    }


    public static class Builder {

        private Activity activity;
        private boolean canceledOnTouchOutside = true;
        private float opacity = 0.5f;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder setOpacity(float opacity) {
            this.opacity = opacity;
            return this;
        }

        public Don build() {
            return new Don(this);
        }
    }
}
