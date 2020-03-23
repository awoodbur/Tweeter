package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.ShareTweetService;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;
import edu.byu.cs.tweeter.server.dao.ShareTweetDAO;
import edu.byu.cs.tweeter.server.dao.TweetsDAO;

public class ShareTweetServiceImpl implements ShareTweetService {

    @Override
    public ShareTweetResponse shareTweet(ShareTweetRequest request) {
        ShareTweetDAO dao = new ShareTweetDAO();
        return dao.shareTweet(request);
    }
}
