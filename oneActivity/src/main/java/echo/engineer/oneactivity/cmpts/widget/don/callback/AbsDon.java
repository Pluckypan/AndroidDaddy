package echo.engineer.oneactivity.cmpts.widget.don.callback;

import android.support.annotation.NonNull;
import android.view.View;

import echo.engineer.oneactivity.cmpts.widget.don.Don;
import echo.engineer.oneactivity.cmpts.widget.don.impl.DonEntity;

/**
 * AbsDon.java.java
 * Info: AbsDon.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/3 - 10:52
 * More about me: http://www.1991th.com
 */

public interface AbsDon {

    void bindDon(Don don);

    View getView();

    //布局宽度 match_parent、wrap_content、或指定大小如400dp
    int getLayoutWidth();

    //布局高度 match_parent、wrap_content、或指定大小如400dp
    int getLayoutHeight();

    //布局排列位置
    int getGravity();

    void bindData(@NonNull DonEntity entity);
}
