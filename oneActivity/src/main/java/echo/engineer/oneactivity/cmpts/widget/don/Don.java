package echo.engineer.oneactivity.cmpts.widget.don;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.cmpts.widget.don.annotation.DonType;
import echo.engineer.oneactivity.cmpts.widget.don.callback.AbsDon;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonDialogImpl;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonEntity;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonLoadingImpl;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonProgressImpl;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonToastImpl;
import echo.engineer.oneactivity.cmpts.widget.don.widget.DonFrameLayout;

/**
 * Don
 * Created by Plucky<plucky@echo.engineer> on 2018/4/21 下午4:39.
 * more about me: http://www.1991th.com
 */

public class Don {
    //TOAST
    public static final int TYPE_TOAST = 0;
    //带图标的TOAST
    public static final int TYPE_TOAST_WITH_IMAGE = 1;
    //菊花
    public static final int TYPE_LOADING = 2;
    //环形进度条
    public static final int TYPE_PROGRESS_STROKE = 3;
    //扇形进度条
    public static final int TYPE_PROGRESS_FILL = 4;
    //对话框
    public static final int TYPE_DIALOG = 5;

    /**
     * 全局Widget
     */
    private ViewGroup mDecorView;
    private FrameLayout mRootView;
    private DonFrameLayout mContainerView;
    /**
     * 全局变量
     */
    private boolean mCanceledOnTouchOutside;
    private float mOpacity;
    private int mType;

    private AbsDon donImp;

    private Don() {

    }

    Don(Builder builder) {

        Activity activity = builder.activity;
        mCanceledOnTouchOutside = builder.canceledOnTouchOutside;
        mOpacity = builder.opacity;
        mType = builder.type;

        DonEntity entity = new DonEntity();
        entity.setTitle(builder.title);
        entity.setCancel(builder.cancel);
        entity.setCancelAction(builder.cancelAction);
        entity.setConfirm(builder.confirm);
        entity.setConfirmAction(builder.confirmAction);
        entity.setMessage(builder.message);
        entity.setIcon(builder.icon);

        mDecorView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        mRootView = (FrameLayout) LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_don, mDecorView, false);
        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRootView.setOnClickListener(v -> {
            if (mCanceledOnTouchOutside) {
                dismiss();
            }
        });
        mContainerView = (DonFrameLayout) mRootView.findViewById(R.id.layout_don_container);

        setBackgroundOpacity(mOpacity);
        switch (mType) {
            case TYPE_TOAST:
            case TYPE_TOAST_WITH_IMAGE:
            default:
                donImp = new DonToastImpl(activity);
                break;
            case TYPE_LOADING:
                donImp = new DonLoadingImpl(activity);
                break;
            case TYPE_PROGRESS_STROKE:
            case TYPE_PROGRESS_FILL:
                donImp = new DonProgressImpl(activity);
                break;
            case TYPE_DIALOG:
                donImp = new DonDialogImpl(activity);
                break;
        }
        mContainerView.addView(donImp.getView());
        donImp.bindData(entity);
    }

    private void attachRootView() {
        mDecorView.addView(mRootView);
    }

    private void detachRootView() {
        mDecorView.removeView(mRootView);
    }


    private void dismissRightNow() {
        if (mDecorView == null) return;
        if (mRootView == null) return;
        detachRootView();
    }

    /**
     * 延时取消
     *
     * @param delay 延时取消时间
     */
    private void dismissInner(long delay) {
        if (delay > 0) {
            if (mRootView != null) {
                mRootView.postDelayed(this::dismissRightNow, delay);
            }
        } else {
            dismissRightNow();
        }
    }

    private void setBackgroundOpacity(@FloatRange(from = 0.0f, to = 1.0f) float opacity) {
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
        dismissRightNow();
    }


    public static class Builder {

        private Activity activity;
        private boolean canceledOnTouchOutside = true;
        private float opacity = 0.5f;
        private int type = TYPE_TOAST;

        private String title;
        //Toast 小图标
        private int icon;
        private String message;
        private String cancel;
        private String confirm;
        private Runnable confirmAction;
        private Runnable cancelAction;

        public Builder(Activity activity) {
            this.activity = activity;
            //默认值
            confirm = activity.getString(android.R.string.ok);
            cancel = activity.getString(android.R.string.cancel);
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder setOpacity(float opacity) {
            this.opacity = opacity;
            return this;
        }

        public Builder setType(@DonType int type) {
            this.type = type;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(@StringRes int title) {
            this.title = activity.getString(title);
            return this;
        }

        public Builder setIcon(@DrawableRes int icon) {
            this.icon = icon;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(@StringRes int message) {
            this.message = activity.getString(message);
            return this;
        }

        public Builder setCancel(String cancel) {
            this.cancel = cancel;
            return this;
        }

        public Builder setCancel(@StringRes int cancel) {
            this.cancel = activity.getString(cancel);
            return this;
        }

        public Builder setConfirm(String confirm) {
            this.confirm = confirm;
            return this;
        }

        public Builder setConfirm(@StringRes int confirm) {
            this.confirm = activity.getString(confirm);
            return this;
        }

        public Builder onConfirm(Runnable runnable) {
            this.confirmAction = runnable;
            return this;
        }

        public Builder onCancel(Runnable runnable) {
            this.cancelAction = runnable;
            return this;
        }

        public Don build() {
            return new Don(this);
        }
    }
}
