package echo.engineer.oneactivity.cmpts.widget.don;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import echo.engineer.oneactivity.R;

/**
 * DonFrameLayout
 * Created by Plucky<plucky@echo.engineer> on 2018/4/21 下午10:46.
 * more about me: http://www.1991th.com
 */

public class DonFrameLayout extends FrameLayout {

    private int mRadius;
    private int mLeftTopRadius;
    private int mRightTopRadius;
    private int mRightBottomRadius;
    private int mLeftBottomRadius;
    private Path mClipPath;

    public DonFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public DonFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DonFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = attrs != null ? context.obtainStyledAttributes(attrs, R.styleable.DonFrameLayout) : null;
        try {
            if (array != null) {
                mRadius = array.getDimensionPixelSize(R.styleable.DonFrameLayout_radius, mRadius);
                mLeftTopRadius = array.getDimensionPixelSize(R.styleable.DonFrameLayout_left_top_radius, mRadius);
                mRightTopRadius = array.getDimensionPixelSize(R.styleable.DonFrameLayout_right_top_radius, mRadius);
                mRightBottomRadius = array.getDimensionPixelSize(R.styleable.DonFrameLayout_right_bottom_radius, mRadius);
                mLeftBottomRadius = array.getDimensionPixelSize(R.styleable.DonFrameLayout_left_bottom_radius, mRadius);
            } else {
                mLeftTopRadius = mRadius;
                mRightTopRadius = mRadius;
                mRightBottomRadius = mRadius;
                mLeftBottomRadius = mRadius;
            }
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mClipPath = getClipPath(w, h, mLeftTopRadius, mRightTopRadius, mRightBottomRadius, mLeftBottomRadius);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (mClipPath != null) {
            canvas.clipPath(mClipPath);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG));
        }
        super.dispatchDraw(canvas);
    }

    private static Path getClipPathByArc(int width, int height, int leftTop, int rightTop, int rightBottom, int leftBottom) {
        Path path = new Path();
        path.arcTo(new RectF(0, 0, 2 * leftTop, 2 * leftTop), 180, 90);
        path.lineTo(width - rightTop, 0);
        path.arcTo(new RectF(width - 2 * rightTop, 0, width, 2 * rightTop), -90, 90);
        path.lineTo(width, height - rightBottom);
        path.arcTo(new RectF(width - 2 * rightBottom, height - 2 * rightBottom, width, height), 0, 90);
        path.lineTo(width - leftBottom, height);
        path.arcTo(new RectF(0, height - 2 * leftBottom, 2 * leftBottom, height), 90, 90);
        path.close();
        return path;
    }

    private static Path getClipPath(int width, int height, int leftTop, int rightTop, int rightBottom, int leftBottom) {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, width, height), new float[]{leftTop, leftTop, rightTop, rightTop, rightBottom, rightBottom, leftBottom, leftBottom}, Path.Direction.CW);
        return path;
    }
}
