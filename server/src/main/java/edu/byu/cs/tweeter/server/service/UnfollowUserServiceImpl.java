package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.UnfollowUserService;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;
import edu.byu.cs.tweeter.server.dao.FollowDAO;
import edu.byu.cs.tweeter.server.dao.UnfollowUserDAO;

public class UnfollowUserServiceImpl implements UnfollowUserService {

    @Override
    public UnfollowUserResponse unfollowUser(UnfollowUserRequest request) {
        UnfollowUserDAO dao = new UnfollowUserDAO();
        return dao.unfollowUser(request);
    }
}
