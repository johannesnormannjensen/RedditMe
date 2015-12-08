package com.redditme.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.redditme.R;
import com.redditme.model.PostCard;

import java.util.List;

/**
 * Created by Johannes on 06-12-2015.
 */
public class PostcardAdapter extends RecyclerView.Adapter<PostcardAdapter.PostCardViewHolder> {

    private List<PostCard> postCards;

    public PostcardAdapter(List<PostCard> postCards) {
        this.postCards = postCards;
    }

    @Override
    public int getItemCount() {
        return postCards.size();
    }

    @Override
    public PostCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.postcard, viewGroup, false);
        PostCardViewHolder pvh = new PostCardViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PostCardViewHolder postCardViewHolder, int position) {
        postCardViewHolder.postTitle.setText(postCards.get(position).getTitle());
        postCardViewHolder.postDescription.setText(postCards.get(position).getDescription());
        postCardViewHolder.postThumbnail.setImageDrawable(postCards.get(position).getThumbnail());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PostCardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView postTitle;
        TextView postDescription;
        ImageView postThumbnail;

        PostCardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            postTitle = (TextView)itemView.findViewById(R.id.post_title);
            postDescription = (TextView)itemView.findViewById(R.id.post_description);
            postThumbnail = (ImageView)itemView.findViewById(R.id.post_thumbnail);
        }
    }
}
