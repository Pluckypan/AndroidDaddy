package echo.engineer.oneactivity.cmpts.widget.don;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;

import echo.engineer.oneactivity.cmpts.widget.don.annotation.DonType;

/**
 * Don.java.java
 * Info: Don.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/3 - 20:33
 * More about me: http://www.1991th.com
 */
public abstract class Don {
    // --------------- 对外类型 ----------------
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


    // ----------------- 对外接口 --------------

    abstract public void show();

    abstract public void dismiss();

    abstract public boolean isShowing();

    abstract public void setProgress(int progress);

    //----------------- Builder ---------------
    public static class Builder {

        Activity activity;
        boolean canceledOnTouchOutside = true;
        float opacity = 0.0f;
        int type = TYPE_TOAST;

        String title;
        //Toast 小图标
        int icon;
        String message;
        String cancel;
        String confirm;
        //TYPE_TOAST 自动消失
        int duration;
        Runnable confirmAction;
        Runnable cancelAction;

        @StyleRes
        int style;

        DonListener listener;

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

        public Builder setDuration(@IntRange(from = 1500, to = 3000) int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setListener(DonListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setStyle(@StyleRes int style) {
            this.style = style;
            return this;
        }

        public DonImpl build() {
            return new DonImpl(this);
        }
    }

    //Listener
    public interface DonListener {
        void onVisibileChanged(Don don, boolean show);
    }
}
