package engineer.echo.floatwindow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import engineer.echo.floatwindow.cmpts.floatpannel.FloatPannel;

/**
 * SecondActivity
 * Created by Plucky<plucky@echo.engineer> on 2018/1/8 - 16:31
 * more about me: http://www.1991th.com
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_second);
    }

    public static void gotoActivity(Context context) {
        context.startActivity(new Intent(context, SecondActivity.class));
    }

    @Override
    public void finish() {
        super.finish();
        FloatPannel.Log("Second:退出第二页");
    }
}
