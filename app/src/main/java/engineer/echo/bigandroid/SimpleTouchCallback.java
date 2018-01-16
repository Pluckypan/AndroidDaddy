package engineer.echo.bigandroid;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import engineer.echo.bigandroid.swipe.DragSwipeListener;

/**
 * SimpleTouchCallback.java.java
 * Info: SimpleTouchCallback.java
 * Created by Plucky<plucky@echo.engineer> on 2018/1/16 - 20:27
 * More about me: http://www.1991th.com
 */
public class SimpleTouchCallback extends ItemTouchHelper.Callback {

    private DragSwipeListener listener;

    public SimpleTouchCallback(DragSwipeListener listener) {
        this.listener = listener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;
        int dragFlags = ItemTouchHelper.START | ItemTouchHelper.END | ItemTouchHelper.UP | ItemTouchHelper.DOWN;

        return makeMovementFlags(dragFlags, swipFlags);
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
        listener.onDrag(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onChange(viewHolder.getAdapterPosition());
    }
}
