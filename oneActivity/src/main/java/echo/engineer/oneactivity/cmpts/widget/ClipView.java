package echo.engineer.oneactivity.cmpts.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * ClipView.java
 * Useage: ClipView
 * Created by Plucky<plucky@echo.engineer> on 2017/8/18 - 13:58
 */

public class ClipView extends View implements View.OnClickListener {

    private static final String TAG = "ClipView";

    private Paint mPaintRed, mPaintBlue, mPaintAlpha, mPaintFont;
    private int mWidth, mHeight;
    private static final PorterDuff.Mode[] ALLS = new PorterDuff.Mode[]{Mode.CLEAR,
            Mode.SRC,
            Mode.DST,
            Mode.SRC_OVER,
            Mode.DST_OVER,
            Mode.SRC_IN,
            Mode.DST_IN,
            Mode.SRC_OUT,
            Mode.DST_OUT,
            Mode.SRC_ATOP,
            Mode.DST_ATOP,
            Mode.XOR,
            Mode.DARKEN,
            Mode.LIGHTEN,
            Mode.MULTIPLY,
            Mode.SCREEN,
            Mode.ADD,
            Mode.OVERLAY
    };
    private static final String[] ALL_NAMES = new String[]{
            "CLEAR",
            "SRC",
            "DST",
            "SRC_OVER",
            "DST_OVER",
            "SRC_IN",
            "DST_IN",
            "SRC_OUT",
            "DST_OUT",
            "SRC_ATOP",
            "DST_ATOP",
            "XOR",
            "DARKEN",
            "LIGHTEN",
            "MULTIPLY",
            "SCREEN",
            "ADD",
            "OVERLAY"
    };

    public ClipView(Context context) {
        this(context, null);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //必须开启硬件加速
        setLayerType(LAYER_TYPE_HARDWARE, null);
        mPaintRed = new Paint();
        mPaintRed.setColor(Color.RED);

        mPaintBlue = new Paint();
        mPaintBlue.setColor(Color.BLUE);

        mPaintFont = new Paint();
        mPaintFont.setTextSize(30);
        mPaintFont.setColor(Color.WHITE);

        mPaintAlpha = new Paint();
        mPaintAlpha.setColor(Color.BLACK);
        mPaintAlpha.setAlpha(60);

        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


    private static final int SPACE = 25;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, SPACE, 200, mHeight - SPACE, mPaintRed);
        canvas.drawRect(100, SPACE, mWidth, mHeight - SPACE, mPaintBlue);

        canvas.drawRect(250, 10, 300, mHeight - 10, mPaintAlpha);

        String string = ALL_NAMES[mMode];
        float w = mPaintFont.measureText(string);
        canvas.drawText(string, mWidth - w - 20, mHeight / 2, mPaintFont);
    }

    private int mMode;

    @Override
    public void onClick(View v) {
        mMode++;
        if (mMode > 15) mMode = 0;
        mPaintAlpha.setXfermode(new PorterDuffXfermode(ALLS[mMode]));
        //mPaintBlue.setXfermode(new PorterDuffXfermode(ALLS[mMode]));
        Log.d(TAG, "mMode=" + mMode);
        postInvalidate();
    }
}
