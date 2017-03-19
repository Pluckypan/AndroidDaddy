package engineer.echo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import engineer.echo.dependence.ActivityModule;
import engineer.echo.dependence.AppComponent;
import engineer.echo.dependence.AppModule;
import engineer.echo.dependence.DaggerActivityComponent;
import engineer.echo.dependence.DaggerAppComponent;
import engineer.echo.dependence.Person;
import engineer.echo.dependence.QualifierForContext;
import engineer.echo.dependence.QualifierForName;
import engineer.echo.scope.DeviceStatus;
import engineer.echo.scope.FRAppComponent;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @QualifierForContext
    @Inject
    Person person;
    @QualifierForName
    @Inject
    Person person2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btnScope).setOnClickListener(this);
        //dependence
        AppComponent component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        DaggerActivityComponent.builder().appComponent(component).activityModule(new ActivityModule()).build().inject(this);
    }

    @Override
    public void onClick(View v) {
        FRAppComponent component=DaggerApp.getComponent(this);
        DeviceStatus status=component.getDeviceStatus();
        status.ssid="PYNet";

        Intent intent = new Intent(this, ScopeActivity.class);
        startActivity(intent);
    }
}
