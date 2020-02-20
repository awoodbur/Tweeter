package edu.byu.cs.tweeter.net.response;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Tweet;

public class FeedResponse extends PagedResponse {

    private List<Tweet> tweets;

    public FeedResponse(String message) {
        super(false, message, false);
    }

    public FeedResponse(List<Tweet> tweets, boolean hasMorePages) {
        super(true, hasMorePages);
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() { return tweets; }
}
