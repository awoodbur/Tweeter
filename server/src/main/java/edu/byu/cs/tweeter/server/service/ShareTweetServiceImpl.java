package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.ShareTweetService;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;
import edu.byu.cs.tweeter.server.dao.TweetsDAO;

public class ShareTweetServiceImpl implements ShareTweetService {

    @Override
    public ShareTweetResponse shareTweet(ShareTweetRequest request) {
        TweetsDAO dao = new TweetsDAO();
        return dao.shareTweet(request);
    }
}
