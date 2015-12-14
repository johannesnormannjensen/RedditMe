package com.redditme.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redditme.MainActivity;
import com.redditme.R;
import com.redditme.utils.ThumbnailGenerator;

import net.dean.jraw.models.Submission;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Johannes on 06-12-2015.
 */
public class PostcardAdapter extends RecyclerView.Adapter<PostcardAdapter.SubmissionViewHolder> {

    private List<Submission> submissionList;
    private Context context;

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
        attachThumbnail(postCardViewHolder, position);
        postCardViewHolder.postCommentsCount.setText("Comments: " + submissionList.get(position).getCommentCount().toString());
        postCardViewHolder.postId.setText(submissionList.get(position).getId());
    }

    private void attachThumbnail(SubmissionViewHolder postCardViewHolder, int position) {
        Drawable thumbnail;

        String sThumbnailURL = submissionList.get(position).getThumbnail();
        ThumbnailGenerator tng = new ThumbnailGenerator(context);
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
        postCardViewHolder.postThumbnail.setImageDrawable(thumbnail);

//        int thumbnailWidth = (int) (postCardViewHolder.postThumbnail.getDrawable().getIntrinsicWidth() * context.getResources().getDisplayMetrics().density);
//        int thumbnailHeight = (int) (postCardViewHolder.postThumbnail.getDrawable().getIntrinsicHeight() * context.getResources().getDisplayMetrics().density);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(thumbnailWidth, thumbnailHeight);
//        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//
//        postCardViewHolder.postThumbnail.setLayoutParams(layoutParams);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class SubmissionViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView postId;
        TextView postTitle;
        TextView postDescription;
        ImageView postThumbnail;
        TextView postCommentsCount;

        SubmissionViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            postId = (TextView)itemView.findViewById(R.id.post_id);
            postTitle = (TextView)itemView.findViewById(R.id.post_title);
            postDescription = (TextView)itemView.findViewById(R.id.post_description);
            postThumbnail = (ImageView)itemView.findViewById(R.id.post_thumbnail);
            postCommentsCount = (TextView)itemView.findViewById(R.id.post_commentsCount);
        }
    }
}
