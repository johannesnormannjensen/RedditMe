package com.redditme;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.models.LoggedInAccount;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class AuthorizationTest {
    @Test
    public void testLogin() throws Exception
    {
        UserAgent myUserAgent = UserAgent.of("desktop", "com.kea.johannes.redditprototype", "0.1", "thisdoesnotmatter");
        RedditClient redditClient = new RedditClient(myUserAgent);

//        redditClient.setLoggingMode(LoggingMode.ALWAYS);

        Credentials credentials = Credentials.script("cburstarts", "cloudburstarts", "M5E32UMDH6Dmvw", "RdNUf_ARr300FPiZuY9zVLXZ-oc");

        OAuthData authData = redditClient.getOAuthHelper().easyAuth(credentials);

        redditClient.authenticate(authData);

        LoggedInAccount lia = redditClient.me();

        System.out.println(lia.getFullName());
    }
}