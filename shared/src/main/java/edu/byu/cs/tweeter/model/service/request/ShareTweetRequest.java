package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;

public class ShareTweetRequest {

    private User owner;
    private Tweet tweet;
    private String token;

    private ShareTweetRequest() {}

    public ShareTweetRequest(Tweet tweet, String token) {
        this.tweet = tweet;
        this.token = token;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
