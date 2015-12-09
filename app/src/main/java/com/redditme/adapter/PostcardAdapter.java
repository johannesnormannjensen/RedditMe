package com.redditme.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.VectorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;
import com.redditme.R;
import com.redditme.model.PostCard;

import net.dean.jraw.models.Submission;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Johannes on 06-12-2015.
 */
public class PostcardAdapter extends RecyclerView.Adapter<PostcardAdapter.SubmissionViewHolder> {

    private List<Submission> submissionList;
    private Context context;
    private final Bitmap defaultRedditThumbnail = BitmapFactory.decodeResource(context.getResources(), R.drawable.reddit_standard);

    public PostcardAdapter(List<Submission> submissionList, Context context) {
        this.submissionList = submissionList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return submissionList.size();
    }

    @Override
    public SubmissionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.postcard, viewGroup, false);
        SubmissionViewHolder pvh = new SubmissionViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(SubmissionViewHolder postCardViewHolder, int position) {
        postCardViewHolder.postTitle.setText(submissionList.get(position).getTitle());
        postCardViewHolder.postDescription.setText(submissionList.get(position).getSelftext());
        String sThumbnailURL = submissionList.get(position).getThumbnail();
        Bitmap thumbnail = null;
            try {
                if(sThumbnailURL.isEmpty()) {
                    thumbnail = defaultRedditThumbnail;
                } else {
                    URL thumbnailURL = new URL(sThumbnailURL);
                    thumbnail = BitmapFactory.decodeStream(thumbnailURL.openConnection().getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        postCardViewHolder.postThumbnail.setImageBitmap(thumbnail);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class SubmissionViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView postTitle;
        TextView postDescription;
        ImageView postThumbnail;

        SubmissionViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            postTitle = (TextView)itemView.findViewById(R.id.post_title);
            postDescription = (TextView)itemView.findViewById(R.id.post_description);
            postThumbnail = (ImageView)itemView.findViewById(R.id.post_thumbnail);
        }
    }
}
