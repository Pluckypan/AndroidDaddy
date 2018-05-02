package echo.engineer.oneactivity.cmpts.widget.don;

import android.content.Context;
import android.view.View;

import echo.engineer.oneactivity.R;

/**
 * DonProgressImpl.java.java
 * Info: DonProgressImpl.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/2 - 21:54
 * More about me: http://www.1991th.com
 */
public class DonProgressImpl extends AbsDonImp {

    public DonProgressImpl(Context context) {
        super(context);
    }

    @Override
    int getLayout() {
        return R.layout.layout_don_status_progress;
    }

    @Override
    void initView(View rootView) {

    }
}
