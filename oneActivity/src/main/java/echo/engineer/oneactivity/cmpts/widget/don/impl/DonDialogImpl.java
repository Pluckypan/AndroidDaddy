package echo.engineer.oneactivity.cmpts.widget.don.impl;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.cmpts.widget.don.callback.DonDialog;

/**
 * DonDialogImpl.java.java
 * Info: DonDialogImpl.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/2 - 21:56
 * More about me: http://www.1991th.com
 */

public class DonDialogImpl extends AbsDonImp implements DonDialog {

    private TextView mDialogTitleTV;
    private TextView mDialogMessageTV;
    private TextView mDialogCancelTV;
    private View mDialogSpliter;
    private TextView mDialogConfirmTV;

    public DonDialogImpl(@NonNull LayoutInflater inflater, @StyleRes int style) {
        super(inflater, style);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_don_dialog;
    }

    @Override
    public int getLayoutWidth() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public int getLayoutHeight() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public void initView(View rootView, @StyleRes int style) {
        mDialogTitleTV = (TextView) rootView.findViewById(R.id.don_dialog_title_tv);
        mDialogMessageTV = (TextView) rootView.findViewById(R.id.don_dialog_message_tv);
        mDialogCancelTV = (TextView) rootView.findViewById(R.id.don_dialog_cancel_tv);
        mDialogSpliter = rootView.findViewById(R.id.don_dialog_spliter);
        mDialogConfirmTV = (TextView) rootView.findViewById(R.id.don_dialog_confirm_tv);

        if (style > 0) {
            //如果有设置样式 则解析
            Context context = rootView.getContext();
            TypedArray array = context.obtainStyledAttributes(style, R.styleable.Don);
            try {
                if (array.hasValue(R.styleable.Don_dialog_cancel_button_color)) {
                    ColorStateList cancelColor = array.getColorStateList(R.styleable.Don_dialog_cancel_button_color);
                    if (cancelColor != null) {
                        mDialogCancelTV.setTextColor(cancelColor);
                    }
                }

                if (array.hasValue(R.styleable.Don_dialog_confirm_button_color)) {
                    ColorStateList confirmColor = array.getColorStateList(R.styleable.Don_dialog_confirm_button_color);
                    if (confirmColor != null) {
                        mDialogConfirmTV.setTextColor(confirmColor);
                    }
                }
            } finally {
                if (array != null) {
                    array.recycle();
                }
            }
        }
    }

    @Override
    public void bindData(@NonNull DonEntity entity) {
        reload(entity.title, entity.cancel, entity.cancelAction, entity.confirm, entity.confirmAction, entity.message);
    }

    private void reload(String title, String cancel, Runnable cancelAction, String confirm, Runnable confirmAction, String message) {
        //标题的处理
        boolean hasShowTitle = !TextUtils.isEmpty(title);
        mDialogTitleTV.setVisibility(hasShowTitle ? View.VISIBLE : View.GONE);
        mDialogTitleTV.setText(title);

        //取消按钮的处理
        mDialogCancelTV.setOnClickListener(v -> {
            if (cancelAction != null) {
                cancelAction.run();
            }
        });
        mDialogCancelTV.setText(cancel);

        //确定按钮的处理
        mDialogConfirmTV.setOnClickListener(v -> {
            if (confirmAction != null) {
                confirmAction.run();
            }
        });
        mDialogConfirmTV.setText(confirm);

        //控制两个按钮的显示逻辑
        boolean showCancel = !TextUtils.isEmpty(cancel);
        boolean showConfirm = !TextUtils.isEmpty(confirm);
        mDialogConfirmTV.setVisibility(showConfirm ? View.VISIBLE : View.GONE);
        mDialogCancelTV.setVisibility(showCancel ? View.VISIBLE : View.GONE);
        mDialogSpliter.setVisibility(showCancel && showConfirm ? View.VISIBLE : View.GONE);

        //设置Message
        mDialogMessageTV.setText(message);
    }
}
