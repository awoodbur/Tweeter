package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.ShareTweetService;
import edu.byu.cs.tweeter.model.service.request.BatchShareTweetRequest;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;
import edu.byu.cs.tweeter.server.dao.FeedsDAO;
import edu.byu.cs.tweeter.server.dao.StoriesDAO;

public class ShareTweetServiceImpl extends ServiceImpl implements ShareTweetService {

    @Override
    public ShareTweetResponse shareTweet(ShareTweetRequest request) {
        validateToken(request.getToken());
        StoriesDAO dao = new StoriesDAO();
        return dao.shareTweet(request);
    }

    public void batchFeedUpdate(BatchShareTweetRequest request) {
        validateToken(request.getToken());
        FeedsDAO dao = new FeedsDAO();
        dao.batchShareTweet(request);
    }
}
