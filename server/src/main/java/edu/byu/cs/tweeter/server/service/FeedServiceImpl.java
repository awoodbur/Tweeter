package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.dao.FeedsDAO;

public class FeedServiceImpl extends ServiceImpl implements FeedService {

    @Override
    public FeedResponse getFeed(FeedRequest request) {
        validateToken(request.getToken());
        FeedsDAO dao = new FeedsDAO();
        return dao.getFeed(request);
    }
}
