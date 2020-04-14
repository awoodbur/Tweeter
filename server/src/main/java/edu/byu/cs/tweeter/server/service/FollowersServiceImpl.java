package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FollowersService;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;

public class FollowersServiceImpl extends ServiceImpl implements FollowersService {

    @Override
    public FollowersResponse getFollowers(FollowersRequest request) {
        validateToken(request.getToken());
        FollowsDAO dao = new FollowsDAO();
        return dao.getFollowers(request);
    }
}
