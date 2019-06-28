package engineer.echo.bigandroid.swipe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.iammert.rangeview.library.RangeView;

import engineer.echo.bigandroid.R;

public class DragSwipeActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RangeView rangeView;

    private float mOffsetX = 0;
    private ScrollCallback mCallback = new ScrollCallback();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_swipe);
        mRecyclerView = findViewById(R.id.recyclerView);
        rangeView = findViewById(R.id.rangeView);
        View deleteView = findViewById(R.id.deleteView);
        final SwipeLayoutManager manager = new SwipeLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(manager);

        final DragSwipeAdapter adapter = new DragSwipeAdapter(this);
        mRecyclerView.setAdapter(adapter);

        final int size = getResources().getDimensionPixelSize(R.dimen.drag_item_size);
        final Paint headerPaint = new Paint();
        headerPaint.setColor(Color.RED);

        final Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        final RectF rectF = new RectF(0, 0, size, size);
        mRecyclerView.addOnScrollListener(mCallback);

        RecyclerView.ItemDecoration decoration = new RecyclerView.ItemDecoration() {


            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);
                if (position == 0) {
                    outRect.left =  0;
                } else {
                    outRect.left = 20;
                }
            }

            private float mFirstX = -1f;

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                headerPaint.setColor(Color.RED);
                int pos = manager.findFirstVisibleItemPosition();
                c.drawRect(rectF, headerPaint);
                c.drawText("悬浮头=" + pos, rectF.centerX(), rectF.centerY(), textPaint);
                RecyclerView.ViewHolder holder = parent.findViewHolderForAdapterPosition(2);
                if (holder != null && mFirstX == -1f) {
                    mFirstX = holder.itemView.getX()-60;
                }
                rangeView.setTranslationX(-mOffsetX + mFirstX);
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                int count = parent.getChildCount();
                headerPaint.setColor(Color.GREEN);
                for (int i = 0; i < count; i++) {
                    View child = parent.getChildAt(i);
                    c.drawRect(child.getLeft(), child.getTop() - 20, child.getRight(), child.getBottom() - 20, headerPaint);
                }
            }
        };

        SimpleTouchCallback callback = new SimpleTouchCallback(adapter);
        callback.setDeleteArea(deleteView);
        callback.setOnItemDeleteListener(new OnItemDeleteListener() {
            @Override
            public void onItemDelete(int position) {
                adapter.remove(position);
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.addItemDecoration(decoration);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerView.removeOnScrollListener(mCallback);
    }

    private class ScrollCallback extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mOffsetX += dx;
        }
    }

}
