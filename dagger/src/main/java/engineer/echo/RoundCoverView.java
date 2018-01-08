package engineer.echo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Round cover view
 * Created by glorin on 16/4/15.
 */
public class RoundCoverView extends View {
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mHoleRadius;
    private boolean mForcedHoleRadius;

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.TRANSPARENT);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        if (isInEditMode()) {
            setVisibility(GONE);
        }
    }

    public RoundCoverView(Context context) {
        super(context);
        init();
    }

    public RoundCoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundCoverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mWidth = right - left;
        mHeight = bottom - top;

        if (!mForcedHoleRadius) {
            mHoleRadius = Math.min(mWidth, mHeight) / 2;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        if (mWidth > 0 && mHeight > 0) {
            canvas.drawCircle(mWidth / 2, mHeight / 2, mHoleRadius, mPaint);
        }
    }

    public void setRadius(int radius) {
        mHoleRadius = radius;
        mForcedHoleRadius = true;
        postInvalidate();
    }
}
