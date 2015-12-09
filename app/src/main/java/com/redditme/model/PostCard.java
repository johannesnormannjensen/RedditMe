package com.redditme.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Johannes on 06-12-2015.
 */
public class PostCard extends Post implements Serializable{
    public PostCard(String title, String description, int commentsCount, String thumbnail) {
        super(title, description, commentsCount, thumbnail);
    }
}
