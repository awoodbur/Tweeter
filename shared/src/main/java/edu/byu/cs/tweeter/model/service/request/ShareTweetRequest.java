package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Tweet;

public class ShareTweetRequest extends Request {

    private Tweet tweet;

    private ShareTweetRequest() {
        super("<token>");
    }

    public ShareTweetRequest(Tweet tweet, String token) {
        super(token);
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
