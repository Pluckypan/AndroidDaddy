package engineer.echo.bigandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * RatDrawView.java.java
 * Info: RatDrawView.java
 * Created by Plucky<plucky@echo.engineer> on 2018/1/30 - 20:51
 * More about me: http://www.1991th.com
 */

public class RatDrawView extends android.support.v7.widget.AppCompatImageView {
    // ===========================================================
    // Constants
    // ===========================================================
    private static final String TAG = "RatDrawView";

    // ===========================================================
    // Fields
    // ===========================================================
    private Paint mPaint;

    public RatDrawView(Context context) {
        this(context, null);
    }

    public RatDrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#0099FF"));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.FILL);
    }

    // ===========================================================
    // Override Methods
    // ===========================================================

    // ===========================================================
    // Define Methods
    // ===========================================================


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        int _x = w / 2;
        int _y = h / 2;
        int radius = 40;
        canvas.save();//保存画布状态,后续绘制的都是在新画布坐标
        canvas.rotate(90, _x, _y);//画布以中心点旋转90度
        mPaint.setColor(Color.RED);
        canvas.drawCircle(_x, _y, radius, mPaint);
        canvas.restore();//恢复画布状态
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(_x, _y, radius, mPaint);
    }
}
