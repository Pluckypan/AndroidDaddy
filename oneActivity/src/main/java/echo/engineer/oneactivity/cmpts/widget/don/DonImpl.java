package echo.engineer.oneactivity.cmpts.widget.don;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.FloatRange;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.cmpts.widget.don.callback.AbsDon;
import echo.engineer.oneactivity.cmpts.widget.don.callback.DonProgress;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonDialogImpl;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonEntity;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonLoadingImpl;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonProgressImpl;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonToastImpl;
import echo.engineer.oneactivity.cmpts.widget.don.widget.DonFrameLayout;

/**
 * DonImpl
 * Created by Plucky<plucky@echo.engineer> on 2018/4/21 下午4:39.
 * more about me: http://www.1991th.com
 */

public class DonImpl extends Don {
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
    private int mDuration;
    private DonListener mListener;

    private AbsDon donImp;

    private DonImpl() {

    }

    DonImpl(Builder builder) {

        Activity activity = builder.activity;
        mCanceledOnTouchOutside = builder.canceledOnTouchOutside;
        mOpacity = builder.opacity;
        mType = builder.type;
        mDuration = builder.duration;
        mListener = builder.listener;

        DonEntity entity = new DonEntity();
        entity.setType(mType);
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
        //TYPE_TOAST 不允许点击取消
        if (!isTypeToast()) {
            mRootView.setOnClickListener(v -> {
                if (mCanceledOnTouchOutside) {
                    dismiss();
                }
            });
        }
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
        if (mDecorView.indexOfChild(mRootView) < 0) {
            mDecorView.addView(mRootView);
            if (mListener != null) {
                mListener.onVisibileChanged(this, true);
            }

            if (mDuration > 0) {
                //如果是Toast类型的则需要自动消失
                if (isTypeToast()) {
                    dismissInner(mDuration);
                }
            }
        }
    }

    private void detachRootView() {
        if (mDecorView.indexOfChild(mRootView) >= 0) {
            mDecorView.removeView(mRootView);
            if (mListener != null) {
                mListener.onVisibileChanged(this, false);
            }
        }
    }


    private void dismissRightNow() {
        if (mDecorView == null) return;
        if (mRootView == null) return;
        detachRootView();
    }

    private boolean isTypeToast() {
        return mType == TYPE_TOAST || mType == TYPE_TOAST_WITH_IMAGE;
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
        if (mRootView != null) {
            mRootView.setBackgroundColor(Color.argb((int) (opacity * 255), 0, 0, 0));
        }
    }

    @Override
    public boolean isShowing() {
        return mRootView != null && mRootView.getParent() != null;
    }

    @Override
    public void show() {
        if (mDecorView == null) return;
        if (mRootView == null) return;
        attachRootView();
    }

    @Override
    public void setProgress(int progress) {
        if (donImp instanceof DonProgress) {
            ((DonProgress) donImp).setProgress(progress);
        }
    }

    @Override
    public void dismiss() {
        dismissRightNow();
    }
}