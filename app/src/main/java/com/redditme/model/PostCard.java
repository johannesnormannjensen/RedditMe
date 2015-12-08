package com.redditme.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Johannes on 06-12-2015.
 */
public class PostCard {

    private String title;
    private String description;
    private Drawable thumbnail;
//    below not implemented view-fields
    private int commentsCount;

    public PostCard(String title, String description, int commentsCount, Drawable thumbnail) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.commentsCount = commentsCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Drawable getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Drawable thumbnail) {
        this.thumbnail = thumbnail;
    }
}
