package com.redditme.model;

import net.dean.jraw.models.Comment;
import net.dean.jraw.models.Submission;

import java.util.List;

/**
 * Created by Johannes on 08-12-2015.
 */
public class RedditThread extends Post {

    List<Comment> commentList;

    public RedditThread(String title, String description, int commentsCount, String thumbnail, List<Comment> commentList) {
        super(title, description, commentsCount, thumbnail);
        this.commentList = commentList;
    }

}
