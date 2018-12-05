package engineer.echo.bigandroid.swipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engineer.echo.bigandroid.Const;
import engineer.echo.bigandroid.R;

/**
 * DragSwipeAdapter.java.java
 * Info: DragSwipeAdapter.java
 * Created by Plucky<plucky@echo.engineer> on 2018/1/16 - 20:29
 * More about me: http://www.1991th.com
 */
public class DragSwipeAdapter extends RecyclerView.Adapter<DragSwipeAdapter.ItemViewHolder> implements OnItemDragListener {

    private Context mContext;

    private List<Integer> DATA = new ArrayList<>();

    public DragSwipeAdapter(Context mContext) {
        this.mContext = mContext;
        DATA.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19));
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_msg, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        int item = DATA.get(position);
        holder.itemView.setBackgroundResource(Const.getColor(item));
        holder.tvName.setText(String.valueOf(item));
    }

    @Override
    public int getItemCount() {
        return DATA.size();
    }

    public void remove(int position) {
        DATA.remove(position);
        notifyItemRemoved(position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }

    @Override
    public void onItemDrag(int from, int to) {
        notifyItemMoved(from, to);
    }
}
