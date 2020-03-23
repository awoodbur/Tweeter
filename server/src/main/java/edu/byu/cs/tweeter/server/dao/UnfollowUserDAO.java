package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;

public class UnfollowUserDAO {
    public UnfollowUserResponse unfollowUser(UnfollowUserRequest request) {
        Database.getInstance().removeUserFromFollowing(request.getFollowee(), request.getFollower());
        Database.getInstance().removeFolloweeFromUser(request.getFollower(), request.getFollowee());
        return new UnfollowUserResponse();
    }
}
