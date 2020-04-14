package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.UnfollowUserService;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;

public class UnfollowUserServiceImpl extends ServiceImpl implements UnfollowUserService {

    @Override
    public UnfollowUserResponse unfollowUser(UnfollowUserRequest request) {
        validateToken(request.getToken());
        FollowsDAO dao = new FollowsDAO();
        return dao.unfollowUser(request);
    }
}
