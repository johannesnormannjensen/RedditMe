package com.redditme.redditservice;

import android.os.AsyncTask;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.HttpAdapter;
import net.dean.jraw.http.LoggingMode;
import net.dean.jraw.http.NetworkException;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.SubredditPaginator;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Ferenc on 12/8/2015.
 */
public class AsyncRedditClient extends RedditClient implements RedditService {
    private List<Submission> currentSubmissions;

    public AsyncRedditClient() {
        super(REDDITME_USERAGENT);
        this.setLoggingMode(LoggingMode.ALWAYS);
    }

    public AsyncRedditClient(UserAgent userAgent, HttpAdapter<?> adapter) {
        super(userAgent, adapter);
    }

    @Override
    public void authenticateUserless() throws NetworkException {
        try {
            new AsyncTask<RedditClient, Void, OAuthData>() {
                @Override
                protected OAuthData doInBackground(RedditClient... params) {
                    OAuthData authData = null;
                    try {
                        authData = getOAuthHelper().easyAuth(USERLESS);
                    } catch (OAuthException e) {
                        e.printStackTrace();
                    }
                    authenticate(authData);
                    return authData;
                }
            }.execute(this).get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadSubmissions() {
        try {
            new AsyncTask<RedditService, Void, List<Submission>>() {
                @Override
                protected List<Submission> doInBackground(RedditService... params) {
                    SubredditPaginator paginator = new SubredditPaginator(getRedditClient());
                    paginator.setLimit(15);
                    List<Submission> submissions = paginator.next(); // it returns with a ListING object that implements List<T>
                    setCurrentSubmissions(submissions);
                    return submissions;
                }
            }.execute(this).get(10000, TimeUnit.MILLISECONDS); // if the asynctask doesn't complete within 10000 ms the main thread continues.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RedditClient getRedditClient() {
        return (RedditClient) this;
    }

    public void setCurrentSubmissions(List<Submission> currentSubmissions) {
        this.currentSubmissions = currentSubmissions;
    }

    @Override
    public List<Submission> getCurrentSubmissions() {
        return currentSubmissions;
    }
}
