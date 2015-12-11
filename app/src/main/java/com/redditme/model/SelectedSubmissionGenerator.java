package com.redditme.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redditme.R;
import com.redditme.model.SelectedSubmission;
import com.redditme.utils.ThumbnailGenerator;

import net.dean.jraw.models.Submission;

/**
 * Created by Johannes on 11-12-2015.
 */
public class SelectedSubmissionGenerator {
    View view;
    Submission submission;

    public SelectedSubmissionGenerator(View view, Submission submission) {
        this.view = view;
        this.submission = submission;
    }

    public SelectedSubmission build(){
        TextView postId = (TextView)view.findViewById(R.id.selected_submission_id);
        postId.setText(submission.getId());
        TextView postTitle = (TextView)view.findViewById(R.id.selected_submission_title);
        postTitle.setText(submission.getTitle());
        TextView postDescription = (TextView)view.findViewById(R.id.selected_submission_description);
        postDescription.setText(submission.getSelftext());
        ImageView postThumbnail = (ImageView)view.findViewById(R.id.selected_submission_thumbnail);
        ThumbnailGenerator tng = new ThumbnailGenerator();
        tng.execute(submission.getThumbnail());
        postThumbnail.setImageDrawable(tng.gimmeTheThumbnail());
        TextView postCommentsCount = (TextView)view.findViewById(R.id.selected_submission_commentsCount);
        postCommentsCount.setText(submission.getCommentCount().toString());

        return new SelectedSubmission(postId, postTitle, postDescription, postThumbnail, postCommentsCount);
    }
}
