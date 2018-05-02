package echo.engineer.oneactivity.cmpts.widget.don;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * AbsDonImp.java.java
 * Info: AbsDonImp.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/2 - 21:42
 * More about me: http://www.1991th.com
 */

public abstract class AbsDonImp {

    private View mView;

    public AbsDonImp(Context context) {
        mView = LayoutInflater.from(context).inflate(getLayout(), null);
        initView(mView);
    }

    abstract int getLayout();

    abstract void initView(View rootView);

    public View getView() {
        return mView;
    }
}
