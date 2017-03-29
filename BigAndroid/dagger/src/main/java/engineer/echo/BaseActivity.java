package engineer.echo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseActivity extends AppCompatActivity {

    @Bind(R.id.frameCircle)
    FrameLayout frameCircle;
    @Bind(R.id.app_bar)
    AppBarLayout app_bar;
    @Bind(R.id.vRotate)
    View vRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);


        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float ratio = (1080 + verticalOffset) / 1080.f;
                frameCircle.setScaleX(ratio);
                frameCircle.setScaleY(ratio);
                Log.d("Plucky", "verticalOffset:" + verticalOffset + " ratio:" + ratio);
            }
        });


    }


    private void doRotate() {
        RotateAnimation animation;
        animation = new RotateAnimation(-90, 90, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        vRotate.startAnimation(animation);
        //animation.startNow();
    }

    @OnClick(value = {R.id.btnRotate})
    public void onViewClick(View view) {
        Log.d("Plucky", "btnRotate click");
        doRotate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
