package com.redditme;

import com.redditme.tasks.LoginAnonymus;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthException;

/**
 * Created by Ferenc on 12/5/2015.
 */
public class MockLoginAnonymus implements LoginAnonymus{
    @Override
    public RedditClient getAuthenticatedRedditClient() throws OAuthException {
        UserAgent myUserAgent = UserAgent.of("desktop", "com.redditme", "0.1", "cburstarts");
        RedditClient redditClient  = new RedditClient(myUserAgent);
        Credentials credentials = USERLESS;

        redditClient.authenticate(redditClient.getOAuthHelper().easyAuth(credentials));
        return redditClient;
    }
}
