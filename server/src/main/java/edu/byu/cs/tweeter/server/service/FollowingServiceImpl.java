package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FollowingService;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;

/**
 * Contains the business logic for getting the users a user is following.
 */
public class FollowingServiceImpl extends ServiceImpl implements FollowingService {

    @Override
    public FollowingResponse getFollowees(FollowingRequest request) {
        validateToken(request.getToken());
        FollowsDAO dao = new FollowsDAO();
        return dao.getFollowing(request);
    }
}
