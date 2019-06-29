package engineer.echo.bigandroid.swipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static int TYPE_1 = 1;
    private static int TYPE_2 = 2;
    public int size = 300;

    private List<DragEntity> DATA = new ArrayList<>();

    public DragSwipeAdapter(Context mContext) {
        this.mContext = mContext;
        for (int i = 0; i < 20; i++) {
            DATA.add(new DragEntity(i, 1.0f));
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_msg, null);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 5) {
            return TYPE_2;
        } else {
            return TYPE_1;
        }
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        DragEntity entity = DATA.get(position);
        holder.itemView.setBackgroundResource(Const.getColor(entity.getIndex()));
        holder.tvName.setText(String.valueOf(entity.getIndex()));
        float scale = entity.getScale();
        int left = 0;
        int right = (int) (size * scale);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(right-left,size);
        holder.itemView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return DATA.size();
    }

    public DragEntity getItem(int index) {
        return DATA.get(index);
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
