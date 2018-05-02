package echo.engineer.oneactivity.cmpts.widget.don;

import android.content.Context;
import android.view.View;

import echo.engineer.oneactivity.R;

/**
 * DonToastImpl.java.java
 * Info: DonToastImpl.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/2 - 21:48
 * More about me: http://www.1991th.com
 */

public class DonToastImpl extends AbsDonImp {

    public DonToastImpl(Context context) {
        super(context);
    }

    @Override
    int getLayout() {
        return R.layout.layout_don_status_toast;
    }

    @Override
    void initView(View rootView) {

    }
}
