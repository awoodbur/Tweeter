package edu.byu.cs.tweeter.model.service.request;

import java.io.Serializable;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;

public class BatchShareTweetRequest implements Serializable {

    private List<User> followers;
    private Tweet tweet;
    private String token;

    private BatchShareTweetRequest() {}

    public BatchShareTweetRequest(List<User> followers, Tweet tweet, String token) {
        this.followers = followers;
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

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
