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

import engineer.echo.bigandroid.R;

public class DragSwipeActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_swipe);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
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

        RecyclerView.ItemDecoration decoration = new RecyclerView.ItemDecoration() {


            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);
                if (position == 0) {
                    //第一个item预留空间
                    outRect.left = size;
                }
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                c.drawRect(rectF, headerPaint);
                int pos = manager.findFirstVisibleItemPosition();
                c.drawText("悬浮头=" + pos, rectF.centerX(), rectF.centerY(), textPaint);
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
}
