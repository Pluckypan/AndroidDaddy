package engineer.echo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import engineer.echo.scope.DaggerFRActivityComponent;
import engineer.echo.scope.DeviceStatus;
import engineer.echo.scope.FRActivityComponent;
import engineer.echo.scope.FRActivityModule;
import engineer.echo.scope.FRAppComponent;

public class ScopeActivity extends AppCompatActivity {

    FRAppComponent component;
    TextView tvDeviceStatus;
    @Inject
    DeviceStatus perAct1;
    @Inject
    DeviceStatus perAct2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scope);
        ButterKnife.bind(this);
        component = DaggerApp.getComponent(this);
        tvDeviceStatus = (TextView) findViewById(R.id.tvDeviceStatus);
        DeviceStatus status = component.getDeviceStatus();
        tvDeviceStatus.setText("ssid=" + status.ssid);

        DaggerFRActivityComponent.builder().fRActivityModule(new FRActivityModule()).build().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(value = {R.id.btnNew})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btnNew:
                Intent intent = new Intent(this, BaseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
