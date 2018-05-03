package echo.engineer.oneactivity.cmpts.widget.don.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import echo.engineer.oneactivity.R;

/**
 * DonToastImpl.java.java
 * Info: DonToastImpl.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/2 - 21:48
 * More about me: http://www.1991th.com
 */

public class DonToastImpl extends AbsDonImp {

    private ImageView mIconIV;
    private TextView mMessageTV;

    public DonToastImpl(Context context) {
        super(context);
    }

    @Override
    int getLayout() {
        return R.layout.layout_don_status_toast;
    }

    @Override
    void initView(View rootView) {
        mIconIV = (ImageView) rootView.findViewById(R.id.don_status_icon_iv);
        mMessageTV = (TextView) rootView.findViewById(R.id.don_status_message_tv);
    }

    @Override
    public void bindData(@NonNull DonEntity entity) {
        if (entity.icon > 0) {
            mIconIV.setVisibility(View.VISIBLE);
            mIconIV.setImageResource(entity.icon);
        } else {
            mIconIV.setVisibility(View.GONE);
        }
        mMessageTV.setText(entity.message);
    }
}
