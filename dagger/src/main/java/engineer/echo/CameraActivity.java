package engineer.echo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CameraActivity extends AppCompatActivity {

    @Bind(R.id.vCircle)
    View vCircle;
    private static final String EXTRA_POSITION = "EXTRA_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    public static void startActivity(Context context, Position position) {
        Intent intent = new Intent(context, CameraActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        context.startActivity(intent);
    }

    private static Position getPosition(Intent intent) {
        return intent.getParcelableExtra(EXTRA_POSITION);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
