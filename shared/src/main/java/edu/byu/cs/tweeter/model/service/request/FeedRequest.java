package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedRequest {

    private User user;
    private int limit;
    private Tweet lastTweet;

    private FeedRequest() {}

    public FeedRequest(User user, int limit, Tweet lastTweet) {
        this.user = user;
        this.limit = limit;
        this.lastTweet = lastTweet;
    }

    public User getUser() { return user; }

    public int getLimit() { return limit; }

    public Tweet getLastTweet() { return lastTweet; }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setLastTweet(Tweet lastTweet) {
        this.lastTweet = lastTweet;
    }
}
