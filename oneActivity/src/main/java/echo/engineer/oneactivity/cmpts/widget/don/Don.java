package echo.engineer.oneactivity.cmpts.widget.don;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    private boolean mCanceledOnTouchOutside;
    private float mOpacity;
    private int mType;

    //status widget
    private View mStatusRootView;
    private ImageView mStatusIconIV;
    private ProgressBar mStatusProgressBar;
    private DonProgressView mStatusProgressView;
    private TextView mStatusMessageTV;
    //dialog widget
    private View mDialogRootView;
    private TextView mDialogTitleTV;
    private TextView mDialogMessageTV;
    private TextView mDialogCancelTV;
    private View mDialogSpliter;
    private TextView mDialogConfirmTV;

    private String mTitle;
    private String mMessage;
    private String mConfirm;
    private String mCancel;
    private Runnable mConfirmAction;
    private Runnable mCancelAction;

    private AbsDonImp donImp;

    private Don() {

    }

    Don(Builder builder) {
        mCanceledOnTouchOutside = builder.canceledOnTouchOutside;
        mOpacity = builder.opacity;
        mType = builder.type;
        Activity activity = builder.activity;
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
        mTitle = builder.title;
        mMessage = builder.message;
        mConfirm = builder.confirm;
        mCancel = builder.cancel;
        mConfirmAction = builder.confirmAction;
        mCancelAction = builder.cancelAction;

        mDecorView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        mRootView = (FrameLayout) LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_don, mDecorView, false);
        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setBackgroundOpacity(mOpacity);
        mRootView.addView(donImp.getView());
        mRootView.setOnClickListener(v -> {
            if (mCanceledOnTouchOutside) {
                dismiss();
            }
        });

        injectView();
        initView();
    }

    private void attachRootView() {
        mDecorView.addView(mRootView);
    }

    private void detachRootView() {
        mDecorView.removeView(mRootView);
    }

    private void initView() {
        boolean showDialog = mType == TYPE_DIALOG;
        if (mDialogRootView != null) {
            mDialogRootView.setVisibility(showDialog ? View.VISIBLE : View.GONE);
        }
        if (mStatusRootView != null) {
            mStatusRootView.setVisibility(showDialog ? View.GONE : View.VISIBLE);
        }

        boolean hasShowTitle = !TextUtils.isEmpty(mTitle);
        if (mDialogTitleTV != null) {
            mDialogTitleTV.setVisibility(hasShowTitle ? View.VISIBLE : View.GONE);
            mDialogTitleTV.setText(mTitle);
        }
        if (mDialogCancelTV != null) {
            mDialogCancelTV.setOnClickListener(v -> {
                if (mCancelAction != null) {
                    mCancelAction.run();
                }
            });
            mDialogCancelTV.setText(mCancel);
        }

        if (mDialogConfirmTV != null) {
            mDialogConfirmTV.setOnClickListener(v -> {
                if (mConfirmAction != null) {
                    mConfirmAction.run();
                }
            });
            mDialogConfirmTV.setText(mConfirm);
        }

        if (mDialogConfirmTV != null && mDialogCancelTV != null && mDialogSpliter != null) {
            boolean showCancel = !TextUtils.isEmpty(mCancel);
            boolean showConfirm = !TextUtils.isEmpty(mConfirm);
            mDialogConfirmTV.setVisibility(showConfirm ? View.VISIBLE : View.GONE);
            mDialogCancelTV.setVisibility(showCancel ? View.VISIBLE : View.GONE);
            mDialogSpliter.setVisibility(showCancel && showConfirm ? View.VISIBLE : View.GONE);
        }

        if (mDialogMessageTV != null) {
            mDialogMessageTV.setText(mMessage);
        }

        //status
        if (mStatusMessageTV != null) {
            mStatusMessageTV.setText(mMessage);
        }
    }

    private void injectView() {
        if (mRootView != null) {
            mStatusRootView = mRootView.findViewById(R.id.don_status_root_ll);
            mStatusIconIV = (ImageView) mRootView.findViewById(R.id.don_status_icon_iv);
            mStatusProgressBar = (ProgressBar) mRootView.findViewById(R.id.don_status_progress_bar);
            mStatusProgressView = (DonProgressView) mRootView.findViewById(R.id.don_status_progress_view);
            mStatusMessageTV = (TextView) mRootView.findViewById(R.id.don_status_message_tv);

            mDialogRootView = mRootView.findViewById(R.id.don_dialog_root_ll);
            mDialogTitleTV = (TextView) mRootView.findViewById(R.id.don_dialog_title_tv);
            mDialogMessageTV = (TextView) mRootView.findViewById(R.id.don_dialog_message_tv);
            mDialogCancelTV = (TextView) mRootView.findViewById(R.id.don_dialog_cancel_tv);
            mDialogSpliter = mRootView.findViewById(R.id.don_dialog_spliter);
            mDialogConfirmTV = (TextView) mRootView.findViewById(R.id.don_dialog_confirm_tv);
        }
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
        private int type = TYPE_TOAST;

        private String title;
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
