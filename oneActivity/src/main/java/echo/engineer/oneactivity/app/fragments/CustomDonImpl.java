package echo.engineer.oneactivity.app.fragments;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.cmpts.widget.don.impl.AbsDonImp;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonEntity;

/**
 * CustomDonImpl
 * Created by Plucky<plucky@echo.engineer> on 2018/5/5 下午9:10.
 * more about me: http://www.1991th.com
 */

public class CustomDonImpl extends AbsDonImp {

    private Button mConfirmBtn;

    public CustomDonImpl(LayoutInflater inflater, int style) {
        super(inflater, style);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_don_custom;
    }

    @Override
    public void initView(View rootView, int style) {
        mConfirmBtn = (Button) rootView.findViewById(R.id.app_btn_confirm);
        mConfirmBtn.setOnClickListener(v -> {
            dismiss();
        });
    }

    @Override
    public void bindData(@NonNull DonEntity entity) {

    }
}
