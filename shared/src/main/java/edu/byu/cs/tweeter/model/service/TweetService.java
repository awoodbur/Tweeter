package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.service.response.Response;

public interface TweetService {
    Response shareTweet(Tweet request) throws IOException;
}
