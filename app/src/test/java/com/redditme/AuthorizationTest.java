package com.redditme;

import com.redditme.redditservice.RedditService;

import net.dean.jraw.RedditClient;
import net.dean.jraw.models.CommentNode;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.Subreddit;
import net.dean.jraw.paginators.SubredditPaginator;

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
        redditClient = new RedditClient(RedditService.REDDITME_USERAGENT);
        redditClient.authenticate(redditClient.getOAuthHelper().easyAuth(RedditService.USERLESS));
    }

    @Test
    public void testAnonRandomSubreddit() throws Exception {
        Subreddit sub = redditClient.getRandomSubreddit();
        assertNotNull(sub);
        System.out.println(sub.getDisplayName());

//        Submission subm = redditService.getSubmission("3viu31");
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
        System.out.println("Top comment: \n" + cmt.getComment().getBody());
    }

    @Test
    public void testGetFrontPage() throws Exception {
        Boolean sf = redditClient.isAuthenticated();
        SubredditPaginator frontPage = new SubredditPaginator(redditClient);
        frontPage.setLimit(50);

        Listing<Submission> submissions = frontPage.next();
        for (Submission s : submissions) {
            System.out.printf("[/r/%s - %s karma] %s\n", s.getSubredditName(), s.getScore(), s.getTitle());
        }
    }
}