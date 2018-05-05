package echo.engineer.oneactivity.cmpts.widget.don.impl;

import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.cmpts.widget.don.Don;
import echo.engineer.oneactivity.cmpts.widget.don.callback.DonProgress;
import echo.engineer.oneactivity.cmpts.widget.don.widget.DonProgressView;

/**
 * DonProgressImpl.java.java
 * Info: DonProgressImpl.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/2 - 21:54
 * More about me: http://www.1991th.com
 */
public class DonProgressImpl extends AbsDonImp implements DonProgress {

    private DonProgressView mProgressView;
    private TextView mMessageTV;

    public DonProgressImpl(@NonNull LayoutInflater inflater, @StyleRes int style) {
        super(inflater, style);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_don_status_progress;
    }

    @Override
    public void initView(View rootView, @StyleRes int style) {
        mProgressView = (DonProgressView) rootView.findViewById(R.id.don_status_progress_view);
        mMessageTV = (TextView) rootView.findViewById(R.id.don_status_message_tv);
    }

    @Override
    public void bindData(@NonNull DonEntity entity) {
        mMessageTV.setText(entity.message);
        if (entity.type == Don.TYPE_PROGRESS_FILL) {
            mProgressView.setType(DonProgressView.TYPE_FILL);
        } else if (entity.type == Don.TYPE_PROGRESS_STROKE) {
            mProgressView.setType(DonProgressView.TYPE_STROKE);
        }
    }

    @Override
    public void setProgress(int progress) {
        mProgressView.setProgress(progress);
    }
}
