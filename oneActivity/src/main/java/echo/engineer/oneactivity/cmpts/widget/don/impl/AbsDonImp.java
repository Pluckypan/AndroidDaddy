package echo.engineer.oneactivity.cmpts.widget.don.impl;

import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;

import echo.engineer.oneactivity.cmpts.widget.don.Don;
import echo.engineer.oneactivity.cmpts.widget.don.callback.AbsDon;

/**
 * AbsDonImp.java.java
 * Info: AbsDonImp.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/2 - 21:42
 * More about me: http://www.1991th.com
 */

public abstract class AbsDonImp implements AbsDon {

    private View mView;
    private Don mDon;

    public AbsDonImp(@NonNull LayoutInflater inflater, @StyleRes int style) {
        mView = inflater.inflate(getLayout(), null);
        initView(mView, style);
    }

    public abstract int getLayout();

    public abstract void initView(View rootView, @StyleRes int style);

    public void show() {
        if (mDon != null) {
            mDon.show();
        }
    }

    public void dismiss() {
        if (mDon != null) {
            mDon.dismiss();
        }
    }

    @Override
    public void bindDon(Don don) {
        this.mDon = don;
    }

    @Override

    public View getView() {
        return mView;
    }
}
