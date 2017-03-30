package engineer.echo;

import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CameraActivity1 extends AppCompatActivity {


    @Bind(R.id.vRender)
    View vRender;

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
        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public View onCreateSnapshotView(Context context, Parcelable snapshot) {
                View view = new View(context);
                view.setBackground(new BitmapDrawable((Bitmap) snapshot));
                return view;
            }

            @Override
            public void onSharedElementStart(List<String> sharedElementNames,
                                             List<View> sharedElements,
                                             List<View> sharedElementSnapshots) {
                Log.d("Plucky","onSharedElementStart");
                for (int i = 0; i < sharedElements.size(); i++) {
                    if (sharedElements.get(i) == vRender) {
                        View snapshot = sharedElementSnapshots.get(i);
                        Drawable snapshotDrawable = snapshot.getBackground();
                        vRender.setBackground(snapshotDrawable);
                        forceSharedElementLayout(vRender);
                        break;
                    }
                }
            }

            private void forceSharedElementLayout(View sharedElement) {
                int widthSpec = View.MeasureSpec.makeMeasureSpec(sharedElement.getWidth(),
                        View.MeasureSpec.EXACTLY);
                int heightSpec = View.MeasureSpec.makeMeasureSpec(sharedElement.getHeight(),
                        View.MeasureSpec.EXACTLY);
                int left = sharedElement.getLeft();
                int top = sharedElement.getTop();
                int right = sharedElement.getRight();
                int bottom = sharedElement.getBottom();
                sharedElement.measure(widthSpec, heightSpec);
                sharedElement.layout(left, top, right, bottom);
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames,
                                           List<View> sharedElements,
                                           List<View> sharedElementSnapshots) {
                //vRender.setBackground(null);
            }
        });

    }
}
