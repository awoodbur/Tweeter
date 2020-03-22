package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FollowUserService;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.server.dao.FollowDAO;

public class FollowUserServiceImpl implements FollowUserService {

    @Override
    public FollowUserResponse followUser(FollowUserRequest request) {
        FollowDAO dao = new FollowDAO();
        return dao.followUser(request);
    }
}
