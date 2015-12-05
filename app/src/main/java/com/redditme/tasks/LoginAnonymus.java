package com.redditme.tasks;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthException;

import java.util.UUID;

/**
 * Created by Ferenc on 12/5/2015.
 */
public interface LoginAnonymus {
    public static final Credentials USERLESS = Credentials.userless("M5E32UMDH6Dmvw", "RdNUf_ARr300FPiZuY9zVLXZ-oc", UUID.randomUUID());
    public static final String VERSION_NAME =  "0.1";
    public static final String PACKAGE_NAME =  "com.redditme";
    public RedditClient getAuthenticatedRedditClient() throws OAuthException;
}
