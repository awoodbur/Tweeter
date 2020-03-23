package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;

public class ShareTweetDAO {
    public ShareTweetResponse shareTweet(ShareTweetRequest request) {
        Database.getInstance().addTweet(request.getTweet());
        return new ShareTweetResponse();
    }
}
