package edu.byu.cs.tweeter.net.request;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedRequest {

    private final User user;
    private final int limit;
    private final Tweet lastTweet;

    public FeedRequest(User user, int limit, Tweet lastTweet) {
        this.user = user;
        this.limit = limit;
        this.lastTweet = lastTweet;
    }

    public User getUser() { return user; }

    public int getLimit() { return limit; }

    public Tweet getLastTweet() { return lastTweet; }
}
