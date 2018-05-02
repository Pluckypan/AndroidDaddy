package echo.engineer.oneactivity.cmpts.widget.don;

import android.content.Context;
import android.view.View;

import echo.engineer.oneactivity.R;

/**
 * DonLoadingImpl.java.java
 * Info: DonLoadingImpl.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/2 - 21:53
 * More about me: http://www.1991th.com
 */
public class DonLoadingImpl extends AbsDonImp {

    public DonLoadingImpl(Context context) {
        super(context);
    }

    @Override
    int getLayout() {
        return R.layout.layout_don_status_loading;
    }

    @Override
    void initView(View rootView) {

    }
}
