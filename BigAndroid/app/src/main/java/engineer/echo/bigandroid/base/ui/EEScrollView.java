package engineer.echo.bigandroid.base.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 分红全球购
 * 青岛芳林信息版权所有
 * author:Created by Plucky on 16/10/18-下午5:19.
 * 功能描述: 为了扩展滑动监听
 */
public class EEScrollView extends ScrollView {

    public EEScrollView(Context context) {
        this(context, null);
    }

    public EEScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EEScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            listener.onEEChange(l, t, oldl, oldt);
        }
    }

    private EEScrollChangeListener listener;

    public void setListener(EEScrollChangeListener listener) {
        this.listener = listener;
    }

    public interface EEScrollChangeListener {
        void onEEChange(int l, int t, int oldl, int oldt);
    }
}
