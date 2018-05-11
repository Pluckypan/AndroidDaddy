package com.partynow.camera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.app_surface_tv).setOnClickListener(this);
        findViewById(R.id.app_texture_tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.app_surface_tv:
                SurfaceCameraActivity.gotoActivity(this);
                break;
            case R.id.app_texture_tv:
                LiveCameraActivity.gotoActivity(this);
                break;
        }
    }
}
