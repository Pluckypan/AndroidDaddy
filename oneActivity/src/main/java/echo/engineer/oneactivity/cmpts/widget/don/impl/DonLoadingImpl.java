package echo.engineer.oneactivity.cmpts.widget.don.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import echo.engineer.oneactivity.R;

/**
 * DonLoadingImpl.java.java
 * Info: DonLoadingImpl.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/2 - 21:53
 * More about me: http://www.1991th.com
 */
public class DonLoadingImpl extends AbsDonImp {

    private ProgressBar mLoadingView;
    private TextView mMessageTV;

    public DonLoadingImpl(Context context) {
        super(context);
    }

    @Override
    int getLayout() {
        return R.layout.layout_don_status_loading;
    }

    @Override
    void initView(View rootView) {
        mLoadingView = (ProgressBar) rootView.findViewById(R.id.don_status_progress_bar);
        mMessageTV = (TextView) rootView.findViewById(R.id.don_status_message_tv);
    }

    @Override
    public void bindData(@NonNull DonEntity entity) {
        mMessageTV.setText(entity.message);
    }
}
