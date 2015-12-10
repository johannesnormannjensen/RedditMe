package com.redditme.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;
import com.redditme.MainActivity;
import com.redditme.R;
import com.redditme.ThumbnailGenerator;
import com.redditme.model.PostCard;

import net.dean.jraw.models.Submission;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Johannes on 06-12-2015.
 */
public class PostcardAdapter extends RecyclerView.Adapter<PostcardAdapter.SubmissionViewHolder> {

    private List<Submission> submissionList;

    public PostcardAdapter(List<Submission> submissionList) {
        this.submissionList = submissionList;
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
        Context context = MainActivity.getMainContext();
        postCardViewHolder.postTitle.setText(submissionList.get(position).getTitle());
        postCardViewHolder.postDescription.setText(submissionList.get(position).getSelftext());
        String sThumbnailURL = submissionList.get(position).getThumbnail();
        Drawable thumbnail;
        if(sThumbnailURL == null) {
            thumbnail = context.getResources().getDrawable(R.drawable.reddit_standard);
        } else {
            ThumbnailGenerator tng = new ThumbnailGenerator();
            try {
                tng.execute(sThumbnailURL).get(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            thumbnail = tng.gimmeTheThumbnail();
        }
        postCardViewHolder.postThumbnail.setImageDrawable(thumbnail);
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
