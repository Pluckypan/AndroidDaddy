package echo.engineer.oneactivity.cmpts.widget.don;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import echo.engineer.oneactivity.R;

/**
 * DonPregressView.java.java
 * Info: DonPregressView.java
 * Created by Plucky<plucky@echo.engineer> on 2018/4/30 - 22:17
 * More about me: http://www.1991th.com
 */
public class DonProgressView extends View {
    public static final int TYPE_STROKE = 0;
    public static final int TYPE_FILL = 1;

    private int mType;

    //描边颜色
    private int mStrokeColor = Color.parseColor("#4D000000");
    //进度条划过部分的颜色
    private int mProgressColor = Color.WHITE;
    //描边(外轨道)宽度
    private int mStrokeWidth = 20;

    private Paint mStrokePaint;
    private Paint mProgressPaint;
    //当前进度
    private int mProgress = 0;
    //绘制进度的区域
    private RectF mProgressRectF = new RectF();

    public DonProgressView(Context context) {
        this(context, null);
    }

    public DonProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DonProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = null;
        if (attrs != null) {
            try {
                array = context.obtainStyledAttributes(attrs, R.styleable.DonProgressView);
                mStrokeColor = array.getColor(R.styleable.DonProgressView_stroke_color, mStrokeColor);
                mProgressColor = array.getColor(R.styleable.DonProgressView_progress_color, mProgressColor);
                mStrokeWidth = array.getDimensionPixelSize(R.styleable.DonProgressView_stroke_width, mStrokeWidth);
                mType = array.getInt(R.styleable.DonProgressView_type, mType);
                mProgress = array.getInt(R.styleable.DonProgressView_progress, mProgress);
            } finally {
                if (array != null) {
                    array.recycle();
                }
            }
        }


        mStrokePaint = new Paint();
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setColor(mStrokeColor);
        mStrokePaint.setStrokeCap(Paint.Cap.ROUND);

        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setColor(mProgressColor);
    }

    public void setType(@DonProgressType int type) {
        this.mType = type;
        invalidate();
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        //半径需要减去轨道宽度
        float r = (Math.min(w, h) - mStrokeWidth) / 2.0f;
        float centerX = w / 2.0f;
        float centerY = h / 2.0f;

        int c = canvas.save();
        canvas.drawCircle(centerX, centerY, r, mStrokePaint);
        float sweep = mProgress * 360.0f / 100;
        float rectL, rectT, rectR, rectB;
        if (mType == TYPE_STROKE) {
            //与轨道保持一致 在上层叠加
            mProgressPaint.setStyle(Paint.Style.STROKE);
            mProgressPaint.setStrokeWidth(mStrokeWidth);
            mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
            rectL = centerX - r;
            rectT = centerY - r;
            rectR = centerX + r;
            rectB = centerY + r;
        } else {
            //需要空出轨道宽度 在内侧绘制
            mProgressPaint.setStyle(Paint.Style.FILL);
            rectL = centerX - r + mStrokeWidth / 2.0f;
            rectT = centerY - r + mStrokeWidth / 2.0f;
            rectR = centerX + r - mStrokeWidth / 2.0f;
            rectB = centerY + r - mStrokeWidth / 2.0f;
        }
        mProgressRectF.set(rectL, rectT, rectR, rectB);
        canvas.drawArc(mProgressRectF, -90, sweep, mType == TYPE_FILL, mProgressPaint);
        canvas.restoreToCount(c);
    }
}
