package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;

public class CheckFollowDAO {
    public CheckFollowResponse checkFollow(CheckFollowRequest request) {
        return new CheckFollowResponse(Database.getInstance().doesUserFollowUser(request.getFollowee(), request.getFollower()));
    }
}
