package com.redditme.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redditme.R;
import com.redditme.utils.ThumbnailGenerator;

import net.dean.jraw.models.Submission;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Johannes on 11-12-2015.
 */
public class SelectedSubmission {
    private TextView postId;
    private TextView postTitle;
    private TextView postDescription;
    private ImageView postThumbnail;
    private TextView postCommentsCount;
    private float deviceDensity;

    public SelectedSubmission(View view, Submission submission, Context context) {
        this.postId = (TextView)view.findViewById(R.id.selected_submission_id);
        this.postTitle = (TextView)view.findViewById(R.id.selected_submission_title);
        this.postDescription = (TextView)view.findViewById(R.id.selected_submission_description);
        this.postThumbnail = (ImageView)view.findViewById(R.id.selected_submission_thumbnail);
        this.postCommentsCount = (TextView)view.findViewById(R.id.selected_submission_commentsCount);
        this.deviceDensity = context.getResources().getDisplayMetrics().density;


        assignValues(submission, new ThumbnailGenerator(context));
        assignLayoutParams(view);
    }

    private void assignValues(Submission submission, ThumbnailGenerator thumbnailGenerator) {
        this.postId.setText(submission.getId());

        this.postTitle.setText(submission.getTitle());
        this.postDescription.setText(submission.getSelftext());
        try {
            thumbnailGenerator.execute(submission.getThumbnail()).get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        ;
        this.postThumbnail.setImageDrawable(thumbnailGenerator.gimmeTheThumbnail());
        this.postCommentsCount.setText("Comments: " + submission.getCommentCount().toString());
    }

    private void assignLayoutParams(View view) {
        int thumbnailWidth = (int) (postThumbnail.getDrawable().getIntrinsicWidth() * deviceDensity);
        int thumbnailHeight = (int) (postThumbnail.getDrawable().getIntrinsicHeight() * deviceDensity);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(thumbnailWidth, thumbnailHeight);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        this.postThumbnail.setLayoutParams(layoutParams);
    }

}
