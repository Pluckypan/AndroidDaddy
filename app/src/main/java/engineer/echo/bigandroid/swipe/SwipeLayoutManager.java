package engineer.echo.bigandroid.swipe;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SwipeLayoutManager extends LinearLayoutManager implements ItemTouchHelper.ViewDropHandler {
    public SwipeLayoutManager(Context context) {
        super(context);
    }

    public SwipeLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public SwipeLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void prepareForDrop(View view, View target, int x, int y) {
        Log.d("py", "move x=" + x + " y=" + y);
    }
}
