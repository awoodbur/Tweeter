package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.dao.FeedDAO;
import edu.byu.cs.tweeter.server.dao.TweetsDAO;

public class FeedServiceImpl implements FeedService {

    @Override
    public FeedResponse getFeed(FeedRequest request) {
        FeedDAO dao = new FeedDAO();
        return dao.getFeed(request);
    }
}
