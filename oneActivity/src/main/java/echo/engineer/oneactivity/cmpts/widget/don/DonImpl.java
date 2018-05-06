package echo.engineer.oneactivity.cmpts.widget.don;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.FloatRange;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private Animation mAnimIn;
    private Animation mAnimOut;
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

        if (builder.animIn > 0) {
            mAnimIn = AnimationUtils.loadAnimation(activity.getApplicationContext(), builder.animIn);
        }

        if (builder.animOut > 0) {
            mAnimOut = AnimationUtils.loadAnimation(activity.getApplicationContext(), builder.animOut);
            mAnimOut.setAnimationListener(new AnimationAdapter() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    detachRootView();
                }
            });
        }

        DonEntity entity = new DonEntity();
        entity.setType(mType);
        entity.setTitle(builder.title);
        entity.setCancel(builder.cancel);
        entity.setCancelAction(builder.cancelAction);
        entity.setConfirm(builder.confirm);
        entity.setConfirmAction(builder.confirmAction);
        entity.setMessage(builder.message);
        entity.setIcon(builder.icon);

        LayoutInflater layoutInflater = LayoutInflater.from(activity.getApplicationContext());
        mDecorView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        mRootView = (FrameLayout) layoutInflater.inflate(R.layout.layout_don, mDecorView, false);
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
        if (builder.radius >= 0) {
            mContainerView.setRadius(builder.radius);
        }

        if (mType == TYPE_CUSTOM && builder.customImpl == null) {
            //如果标明为TYPE_CUSTOM 但是又未实现自定义动作  则当做Doast处理
            mType = TYPE_TOAST;
        }
        switch (mType) {
            case TYPE_TOAST:
            case TYPE_TOAST_WITH_IMAGE:
            default:
                donImp = new DonToastImpl(layoutInflater, builder.style);
                break;
            case TYPE_LOADING:
                donImp = new DonLoadingImpl(layoutInflater, builder.style);
                break;
            case TYPE_PROGRESS_STROKE:
            case TYPE_PROGRESS_FILL:
                donImp = new DonProgressImpl(layoutInflater, builder.style);
                break;
            case TYPE_DIALOG:
                donImp = new DonDialogImpl(layoutInflater, builder.style);
                break;
            case TYPE_CUSTOM:
                donImp = builder.customImpl;
                break;
        }
        donImp.bindDon(this);
        mContainerView.addView(donImp.getView());
        donImp.bindData(entity);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(donImp.getLayoutWidth(), donImp.getLayoutHeight());
        layoutParams.gravity = donImp.getGravity();
        mContainerView.setLayoutParams(layoutParams);
    }

    private void attachRootView() {
        if (mDecorView.indexOfChild(mRootView) < 0) {
            mDecorView.addView(mRootView);

            if (mAnimIn != null) {
                mContainerView.startAnimation(mAnimIn);
            }

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

        if (mAnimIn != null && !mAnimIn.hasEnded()) {
            mAnimIn.cancel();
            detachRootView();
            return;
        }

        if (mAnimOut != null) {
            mAnimOut.cancel();
            mContainerView.startAnimation(mAnimOut);
        } else {
            detachRootView();
        }
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
