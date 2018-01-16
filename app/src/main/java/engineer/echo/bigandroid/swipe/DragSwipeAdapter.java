package engineer.echo.bigandroid.swipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import engineer.echo.bigandroid.Const;
import engineer.echo.bigandroid.R;

/**
 * DragSwipeAdapter.java.java
 * Info: DragSwipeAdapter.java
 * Created by Plucky<plucky@echo.engineer> on 2018/1/16 - 20:29
 * More about me: http://www.1991th.com
 */
public class DragSwipeAdapter extends RecyclerView.Adapter<DragSwipeAdapter.ItemViewHolder> implements DragSwipeListener {

    private Context mContext;

    public DragSwipeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_msg, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.itemView.setBackgroundResource(Const.getColor(position));
        holder.tvName.setText(String.valueOf(position + 1));
        String desc = "pos=" + position;
        holder.tvDesc.setText(desc);
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvDesc;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
        }
    }

    @Override
    public void onChange(int position) {
        notifyItemChanged(position);
    }

    @Override
    public void onDrag(int from, int to) {
        notifyItemMoved(from, to);
    }
}
