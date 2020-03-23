package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;

public class FollowUserDAO {
    public FollowUserResponse followUser(FollowUserRequest request) {
        Database.getInstance().addUserToFollowing(request.getFollowee(), request.getFollower());
        Database.getInstance().addFolloweeToUser(request.getFollower(), request.getFollowee());
        return new FollowUserResponse();
    }
}
