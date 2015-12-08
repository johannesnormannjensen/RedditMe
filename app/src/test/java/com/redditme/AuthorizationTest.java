package com.redditme;

import net.dean.jraw.RedditClient;
import net.dean.jraw.models.CommentNode;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.Subreddit;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class AuthorizationTest {
    RedditClient redditClient;

    @Before
    public void setUp() throws Exception {
        redditClient = new MockLoginAnonymus().getAuthenticatedRedditClient();
    }

    @Test
    public void testAnonRandomSubreddit() throws Exception {
        Subreddit sub = redditClient.getRandomSubreddit();
        assertNotNull(sub);
        System.out.println(sub.getDisplayName());

//        Submission subm = redditClient.getSubmission("3viu31");
//        System.out.println("Submission title: " + subm.getTitle());
//        System.out.println("Submission number of comments: " + subm.getCommentCount());
//        CommentNode cnode = subm.getComments();
//        CommentNode cmt = cnode.get(0);
//        System.out.println("Top comment body text: " + cmt.getComment().getBody());
//
//        System.out.println(cnode.getDepth());
    }

    @Test
    public void testCommentFromSubmission() throws Exception {
        Submission subm = redditClient.getSubmission("3viu31");
        System.out.println("Submission title: " + subm.getTitle());
        System.out.println("Submission number of comments: " + subm.getCommentCount());
        CommentNode cnode = subm.getComments();
        CommentNode cmt = cnode.get(0);
        System.out.println("Author of top comment: " + cmt.getComment().getAuthor());
        System.out.println("Top comment: " + cmt.getComment().getBody());
    }
}