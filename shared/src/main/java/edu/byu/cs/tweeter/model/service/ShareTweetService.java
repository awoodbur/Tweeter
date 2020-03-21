package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;

public interface ShareTweetService {
    ShareTweetResponse shareTweet(ShareTweetRequest request) throws IOException;
}
