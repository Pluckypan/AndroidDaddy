package engineer.echo;

import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.library.viewspread.helper.BaseViewHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import engineer.echo.pulltorefresh.library.PullToRefreshBase;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class BaseActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener {

    @Bind(R.id.frameCircle)
    FrameLayout frameCircle;
    @Bind(R.id.app_bar)
    AppBarLayout app_bar;
    @Bind(R.id.refreshView)
    PullToRefreshRecycleView refreshView;
    @Bind(R.id.tvHomeName)
    TextView tvHomeName;
    @Bind(R.id.llQuick)
    LinearLayout llQuick;
    @Bind(R.id.llTrans)
    LinearLayout llTrans;
    @Bind(R.id.vRender)
    View vRender;

    RecyclerAdapter adapter;
    int barHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);


        refreshView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        refreshView.setOnRefreshListener(this);
        refreshView.setScrollingWhileRefreshingEnabled(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        refreshView.getRefreshableView().setLayoutManager(manager);
        adapter = new RecyclerAdapter(this);
        refreshView.getRefreshableView().setAdapter(adapter);
        barHeight = getResources().getDimensionPixelSize(R.dimen.toor_bar);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int height = appBarLayout.getHeight() - barHeight * 2;
                float ratio = (height + verticalOffset) / (height * 1.0f);
                frameCircle.setScaleX(ratio);
                frameCircle.setScaleY(ratio);
                frameCircle.setAlpha(ratio);

                float overH = ratio * barHeight - barHeight;
                llQuick.setTranslationY(overH);
                tvHomeName.setTranslationY(overH);

                Log.d("Plucky", "verticalOffset:" + verticalOffset + " ratio:" + ratio + " height:" + height + " barHeight:" + barHeight);
            }
        });
    }


    @Override
    public void onRefresh(PullToRefreshBase view) {
        adapter.addCount();
        refreshView.onRefreshComplete();
    }

    private void doRotate() {
        RotateAnimation animation;
        animation = new RotateAnimation(-90, 90, RELATIVE_TO_SELF, 0.5f,
                RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        //vRotate.startAnimation(animation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(value = {R.id.frameCircle, R.id.iv_home_setting})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.frameCircle:
                Intent intent = new Intent(this, CameraActivity1.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(vRender, "vRender"));
                startActivity(intent, options.toBundle());
                break;
            case R.id.iv_home_setting:
                Intent intent1 = new Intent(this, Camera2Activity.class);
                ActivityOptions options1 = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(vRender, "vRender"));
                intent1.putExtra("POS", Position.from(vRender));
                startActivity(intent1, options1.toBundle());
                break;
        }
    }
}
