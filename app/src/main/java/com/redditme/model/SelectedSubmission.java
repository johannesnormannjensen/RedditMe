package com.redditme.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redditme.R;

/**
 * Created by Johannes on 11-12-2015.
 */
public class SelectedSubmission {
    private TextView postId;
    private TextView postTitle;
    private TextView postDescription;
    private ImageView postThumbnail;
    private TextView postCommentsCount;

    public SelectedSubmission(TextView postId, TextView postTitle, TextView postDescription, ImageView postThumbnail, TextView postCommentsCount) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postThumbnail = postThumbnail;
        this.postCommentsCount = postCommentsCount;
    }

    public TextView getPostId() {
        return postId;
    }

    public void setPostId(TextView postId) {
        this.postId = postId;
    }

    public TextView getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(TextView postTitle) {
        this.postTitle = postTitle;
    }

    public TextView getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(TextView postDescription) {
        this.postDescription = postDescription;
    }

    public ImageView getPostThumbnail() {
        return postThumbnail;
    }

    public void setPostThumbnail(ImageView postThumbnail) {
        this.postThumbnail = postThumbnail;
    }

    public TextView getPostCommentsCount() {
        return postCommentsCount;
    }

    public void setPostCommentsCount(TextView postCommentsCount) {
        this.postCommentsCount = postCommentsCount;
    }
}
