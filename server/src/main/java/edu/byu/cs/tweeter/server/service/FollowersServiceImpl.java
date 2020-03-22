package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FollowersService;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.dao.FollowDAO;

public class FollowersServiceImpl implements FollowersService {

    @Override
    public FollowersResponse getFollowers(FollowersRequest request) {
        FollowDAO dao = new FollowDAO();
        return dao.getFollowers(request);
    }
}
