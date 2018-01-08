package engineer.echo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * RecyclerAdapter
 * Created by Plucky<plucky.pan@ubnt.com> on 17/3/30 2017 下午1:40.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {


    private Context context;

    private int count = 20;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_recycler, null);
        return new ItemViewHolder(view);
    }

    public void addCount() {
        count += 10;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
