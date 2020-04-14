package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedRequest {

    private User user;
    private int limit;
    private Tweet lastTweet;
    private String token;

    private FeedRequest() {}

    public FeedRequest(User user, int limit, Tweet lastTweet, String token) {
        this.user = user;
        this.limit = limit;
        this.lastTweet = lastTweet;
        this.token = token;
    }

    public User getUser() { return user; }

    public int getLimit() { return limit; }

    public Tweet getLastTweet() { return lastTweet; }

    public String getToken() {
        return token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setLastTweet(Tweet lastTweet) {
        this.lastTweet = lastTweet;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
