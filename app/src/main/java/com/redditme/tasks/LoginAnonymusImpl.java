package com.redditme.tasks;

import android.os.AsyncTask;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;

/**
 * Created by Ferenc on 12/5/2015.
 */
public class LoginAnonymusImpl extends AsyncTask<Credentials, Void, OAuthData> implements LoginAnonymus {
    public static final UserAgent REDDITME_USERAGENT = UserAgent.of("android", PACKAGE_NAME, VERSION_NAME, "cburstarts");
    RedditClient redditClient;

    @Override
    protected OAuthData doInBackground(Credentials... credentials) {
        try {
            return redditClient.getOAuthHelper().easyAuth(credentials[0]);
        } catch (OAuthException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(OAuthData oAuthData) {
        redditClient.authenticate(oAuthData);
    }

    //interface methods

    @Override
    public RedditClient getAuthenticatedRedditClient() throws OAuthException {
        UserAgent myUserAgent = REDDITME_USERAGENT;
        redditClient = new RedditClient(myUserAgent);
        this.execute(USERLESS);
        return redditClient;
    }
}
