package engineer.echo.bigandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 分红全球购
 * 青岛芳林信息版权所有
 * author:Created by Plucky on 16/8/16-上午10:03.
 * 功能描述: xxx
 */
public class DragSwipAdapter extends RecyclerView.Adapter<DragSwipAdapter.ItemViewHolder> implements DragSwipListener {

    private Context mContext;

    public DragSwipAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_msg, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tvDesc.setText("xx00 -->" + position + "<-- 00xx");
    }

    @Override
    public int getItemCount() {
        return 6;
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
    public void onItemDismiss(int position) {
        notifyItemRemoved(position);
    }

    @Override
    public void onDrag(int from, int to) {
        notifyItemMoved(from, to);
    }
}
