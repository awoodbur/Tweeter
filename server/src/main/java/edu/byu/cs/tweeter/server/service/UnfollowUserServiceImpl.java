package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.UnfollowUserService;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;
import edu.byu.cs.tweeter.server.dao.FollowDAO;

public class UnfollowUserServiceImpl implements UnfollowUserService {

    @Override
    public UnfollowUserResponse unfollowUser(UnfollowUserRequest request) {
        FollowDAO dao = new FollowDAO();
        return dao.unfollowUser(request);
    }
}
