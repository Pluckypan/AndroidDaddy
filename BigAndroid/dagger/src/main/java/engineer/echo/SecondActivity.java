package engineer.echo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import engineer.echo.dependence.ActivityModule;
import engineer.echo.dependence.AppComponent;
import engineer.echo.dependence.AppModule;
import engineer.echo.dependence.DaggerActivityComponent;
import engineer.echo.dependence.DaggerAppComponent;
import engineer.echo.dependence.Person;
import engineer.echo.dependence.QualifierForContext;
import engineer.echo.dependence.QualifierForName;
import engineer.echo.inject.SubManager;
import engineer.echo.scope.DeviceStatus;
import engineer.echo.scope.FRAppComponent;

public class SecondActivity extends AppCompatActivity {

    @QualifierForContext
    @Inject
    Person person;
    @QualifierForName
    @Inject
    Person person2;

    @Bind(R.id.scrollView)
    CustomScrollView scrollView;
    SubManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        AppComponent component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        DaggerActivityComponent.builder().appComponent(component).activityModule(new ActivityModule()).build().inject(this);
        ButterKnife.bind(this);

        int offset = getResources().getDimensionPixelSize(R.dimen.msg_txt);
        scrollView.setOverscrollDistance(offset);

        manager = new SubManager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(value = {R.id.btnScope, R.id.btnSubmit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                manager.getAplsit();
                manager.getAplsitByName();
                Intent intent = new Intent(this, CoordinatorActivity.class);
                startActivity(intent);
                break;
            case R.id.btnScope:
                FRAppComponent component = DaggerApp.getComponent(this);
                DeviceStatus status = component.getDeviceStatus();
                status.ssid = "PYNet";

                Intent intent1 = new Intent(this, ScopeActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
