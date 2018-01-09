package engineer.echo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * FRAccelerateDecelerateInterpolator
 * Created by Plucky<plucky@echo.engineer> on 17/3/30 2017 下午6:02.
 */

public class FRAccelerateDecelerateInterpolator extends AccelerateDecelerateInterpolator {
    public FRAccelerateDecelerateInterpolator() {
        super();
    }

    public FRAccelerateDecelerateInterpolator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public float getInterpolation(float input) {
        Log.d("Plucky", "onAnimation input:" + input);
        return super.getInterpolation(input);
    }
}
