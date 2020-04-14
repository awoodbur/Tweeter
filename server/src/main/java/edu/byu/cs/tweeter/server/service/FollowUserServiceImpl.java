package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.FollowUserService;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;

public class FollowUserServiceImpl extends ServiceImpl implements FollowUserService {

    @Override
    public FollowUserResponse followUser(FollowUserRequest request) {
        validateToken(request.getToken());
        FollowsDAO dao = new FollowsDAO();
        return dao.followUser(request);
    }
}
