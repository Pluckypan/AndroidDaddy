package echo.engineer.oneactivity.cmpts.widget.don.impl;

import android.content.Context;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;

import echo.engineer.oneactivity.cmpts.widget.don.callback.AbsDon;

/**
 * AbsDonImp.java.java
 * Info: AbsDonImp.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/2 - 21:42
 * More about me: http://www.1991th.com
 */

public abstract class AbsDonImp implements AbsDon {

    private View mView;

    public AbsDonImp(Context context, @StyleRes int style) {
        mView = LayoutInflater.from(context).inflate(getLayout(), null);
        initView(mView, style);
    }

    abstract int getLayout();

    abstract void initView(View rootView, @StyleRes int style);

    @Override
    public View getView() {
        return mView;
    }
}
