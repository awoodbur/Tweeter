package edu.byu.cs.tweeter.server.lambda.followuser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.server.service.FollowUserServiceImpl;

public class FollowUserHandler implements RequestHandler<FollowUserRequest, FollowUserResponse> {

    @Override
    public FollowUserResponse handleRequest(FollowUserRequest request, Context context) {
        if (request.getFollower() == null || request.getFollowee() == null) {
            throw new RuntimeException("400");
        }

        FollowUserServiceImpl service;
        try {
            service = new FollowUserServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.followUser(request);
    }
}
