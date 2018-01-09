package engineer.echo.floatwindow;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import engineer.echo.floatwindow.cmpts.floatpannel.FloatPannel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatPannel.register(this);
        findViewById(R.id.app_second_tv).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FloatPannel.unRegister();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_second_tv:
                SecondActivity.gotoActivity(this);
                break;
        }
    }
}