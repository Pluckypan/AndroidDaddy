package engineer.echo.bigandroid.swipe;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_DRAG;
import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE;

/**
 * SimpleTouchCallback.java.java
 * Info: SimpleTouchCallback.java
 * Created by Plucky<plucky@echo.engineer> on 2018/1/16 - 20:27
 * More about me: http://www.1991th.com
 */
public class SimpleTouchCallback extends ItemTouchHelper.Callback{

    private OnItemSwipeListener mSwipeListener;
    private OnItemDragListener mDragListener;

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
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (mSwipeListener != null) {
            mSwipeListener.onItemSwipe(viewHolder.getAdapterPosition());
        }
    }
}
