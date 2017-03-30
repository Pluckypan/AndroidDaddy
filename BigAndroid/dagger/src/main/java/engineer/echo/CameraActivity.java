package engineer.echo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;

import com.library.viewspread.helper.BaseViewHelper;

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
        startTranslation();
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

    BaseViewHelper helper;

    public void startTranslation() {

        /*int h = getResources().getDimensionPixelSize(R.dimen.circle_height);
        View vEnd = new View(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h);
        params.gravity = Gravity.CENTER;
        vEnd.setLayoutParams(params);
        vEnd.setBackgroundColor(Color.parseColor("#FF0000"));*/
//        GradientDrawable drawable=(GradientDrawable)vCircle.getBackground();
//        drawable.setCornerRadii(new float[] { topLeftRadius,
//                topLeftRadius, topRightRadius, topRightRadius,
//                bottomRightRadius, bottomRightRadius, bottomLeftRadius,
//                bottomLeftRadius });
        helper = new BaseViewHelper
                .Builder(this)
                //      .setEndView(vEnd)//如果是两个切换的视图  这里设定最终显示的视图
                .setTranslationView(vCircle)//设置过渡视图
                .isFullWindow(true)//是否全屏显示
                .isShowTransition(true)//是否显示过渡动画
                .setDimColor(Color.WHITE)//遮罩颜色
                .setDimAlpha(200)//遮罩透明度
                //.setRotation(360)//旋转
                //.setScaleX(0)//x轴缩放
                //.setScaleY(0)//y轴缩放
                //.setTranslationX(0)//x轴平移
                //.setTranslationY(0)//y轴平移
                .setDuration(1200)//过渡时长
                .setInterpolator(new FRAccelerateDecelerateInterpolator())//设置插值器
                //设置监听
                .setOnAnimationListener(new BaseViewHelper.OnAnimationListener() {
                    @Override
                    public void onAnimationStartIn() {
                        Log.e("Plucky", "onAnimationStartIn");
                    }

                    @Override
                    public void onAnimationEndIn() {
                        Log.e("Plucky", "onAnimationEndIn");
                    }

                    @Override
                    public void onAnimationStartOut() {
                        Log.e("Plucky", "onAnimationStartOut");
                    }

                    @Override
                    public void onAnimationEndOut() {
                        Log.e("Plucky", "onAnimationEndOut");
                    }

                })
                .create();//开始动画
    }

    @Override
    public void onBackPressed() {

        if (helper != null && helper.isShowing()) {
            helper.backActivity(this);
        } else {
            super.onBackPressed();
        }
    }
}
