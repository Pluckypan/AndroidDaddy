package engineer.echo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
public class CameraActivity1 extends AppCompatActivity {


    @Bind(R.id.tvRender)
    View tvRender;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera1);
        ButterKnife.bind(this);
    }
}
