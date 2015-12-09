package com.redditme.model;

/**
 * Created by Johannes on 08-12-2015.
 */
public abstract class Post {

    private String title;
    private String description;
    private String thumbnailURL;
    //    below not implemented view-fields
    private int commentsCount;

    public Post(String title, String description, int commentsCount, String thumbnail) {
        this.title = title;
        this.description = description;
        this.thumbnailURL = thumbnail;
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

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
