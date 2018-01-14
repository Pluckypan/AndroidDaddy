package engineer.echo.keeper;

import android.os.Bundle;

import engineer.echo.keeper.app.main.fragment.MainFragment;
import engineer.echo.oneactivity.core.FragmentMaster;
import engineer.echo.oneactivity.core.MasterCompatActivity;
import engineer.echo.oneactivity.core.Request;

public class MainActivity extends MasterCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentMaster master=getFragmentMaster();
        master.install(R.id.app_main_container,new Request(MainFragment.class),true);
    }

}
