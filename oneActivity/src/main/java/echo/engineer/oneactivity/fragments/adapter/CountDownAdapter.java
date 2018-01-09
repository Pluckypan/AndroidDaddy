package echo.engineer.oneactivity.fragments.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import echo.engineer.oneactivity.R;

/**
 * CountDownAdapter
 * Created by Plucky<plucky@echo.engineer> on 6/26/17 2017 14:54.
 */

public class CountDownAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.countdown_item, null);
        return new CountDownViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class CountDownViewHolder extends RecyclerView.ViewHolder {
        public CountDownViewHolder(View itemView) {
            super(itemView);
        }
    }
}
