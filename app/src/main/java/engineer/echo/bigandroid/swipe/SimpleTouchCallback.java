package engineer.echo.bigandroid.swipe;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_DRAG;
import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE;

/**
 * SimpleTouchCallback.java.java
 * Info: SimpleTouchCallback.java
 * Created by Plucky<plucky@echo.engineer> on 2018/1/16 - 20:27
 * More about me: http://www.1991th.com
 */
public class SimpleTouchCallback extends ItemTouchHelper.Callback {

    private OnItemSwipeListener mSwipeListener;
    private OnItemDragListener mDragListener;
    private OnItemDeleteListener onItemDeleteListener;
    private View mDeleteArea;

    public SimpleTouchCallback(OnItemDragListener dragListener, OnItemSwipeListener swipeListener) {
        this.mSwipeListener = swipeListener;
        this.mDragListener = dragListener;
    }

    public SimpleTouchCallback(OnItemDragListener dragListener) {
        this.mDragListener = dragListener;
    }

    public SimpleTouchCallback(OnItemSwipeListener swipeListener) {
        this.mSwipeListener = swipeListener;
    }

    public void setOnItemDeleteListener(OnItemDeleteListener onItemDeleteListener) {
        this.onItemDeleteListener = onItemDeleteListener;
    }

    public void setDeleteArea(View area) {
        this.mDeleteArea = area;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;
        int dragFlags = ItemTouchHelper.START | ItemTouchHelper.END | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //如果要同时开启drag和swipe则调用 makeMovementFlags
        //
        if (mSwipeListener != null) {
            if (mDragListener != null) {
                //如果同时设置了Swipe和Drag
                return makeMovementFlags(dragFlags, swipFlags);
            } else {
                //如果只设置了Swipe
                return makeFlag(ACTION_STATE_SWIPE, swipFlags);
            }
        } else {
            if (mDragListener != null) {
                //如果只设置了Drag
                return makeFlag(ACTION_STATE_DRAG, dragFlags);
            } else {
                return 0;
            }
        }

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (mDragListener != null) {
            mDragListener.onItemDrag(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return true;
    }

    @Override
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
        Log.d("py", "onMoved x=" + x + " y=" + y);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (mSwipeListener != null) {
            mSwipeListener.onItemSwipe(viewHolder.getAdapterPosition());
        }
    }

    private Rect mDeleteRect = new Rect();

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        int[] current = new int[2];
        viewHolder.itemView.getLocationOnScreen(current);
        int px = current[0] + viewHolder.itemView.getWidth() / 2;
        int py = current[1] + viewHolder.itemView.getHeight() / 2;
        if (mDeleteRect.width() == 0 && mDeleteArea != null) {
            int[] delete = new int[2];
            mDeleteArea.getLocationOnScreen(delete);
            mDeleteRect.set(delete[0], delete[1], delete[0] + mDeleteArea.getWidth(), delete[1] + mDeleteArea.getHeight());
        }
        int currentPos = viewHolder.getAdapterPosition();
        Log.d("py", "onChildDraw px=" + px + " py=" + py + " mDeleteRect=" + mDeleteRect + " isCurrentlyActive=" + isCurrentlyActive + " currentPos=" + currentPos);
        if (mDeleteRect.contains(px, py) && !isCurrentlyActive && currentPos >= 0) {
            if (onItemDeleteListener != null) {
                viewHolder.itemView.setVisibility(View.INVISIBLE);
                onItemDeleteListener.onItemDelete(currentPos);
            }
        }
    }
}
