package com.redditme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.redditme.R;
import com.redditme.model.SidemenuItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by Johannes on 07-12-2015.
 */
public class SidemenuDrawerAdapter extends RecyclerView.Adapter<SidemenuDrawerAdapter.MyViewHolder> {
    List<SidemenuItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public SidemenuDrawerAdapter(Context context, List<SidemenuItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sidemenu_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SidemenuItem current = data.get(position);
        holder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
