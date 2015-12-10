package com.redditme.redditservice;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkException;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.models.Submission;

import java.util.List;
import java.util.UUID;

/**
 * Created by Ferenc on 12/8/2015.
 */
public interface RedditService {
    public static final String VERSION_NAME = "0.1";
    public static final String PACKAGE_NAME = "com.redditme";
    public static final UserAgent REDDITME_USERAGENT = UserAgent.of("android", PACKAGE_NAME, VERSION_NAME, "cburstarts");
    public static final Credentials USERLESS = Credentials.userlessApp("hsro81jAMJgXhw", UUID.randomUUID());

    public void authenticateUserless() throws NetworkException;

    public void loadSubmissions();

    public RedditClient getRedditClient();

    public void setCurrentSubmissions(List<Submission> submissions);

    public List<Submission> getCurrentSubmissions();

    public Submission getSubmissionById(String id);
}
