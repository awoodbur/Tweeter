package edu.byu.cs.tweeter.server.lambda.unfollowuser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;
import edu.byu.cs.tweeter.server.service.UnfollowUserServiceImpl;

public class UnfollowUserHandler implements RequestHandler<UnfollowUserRequest, UnfollowUserResponse> {

    @Override
    public UnfollowUserResponse handleRequest(UnfollowUserRequest request, Context context) {
        if (request.getFollower() == null || request.getFollowee() == null) {
            throw new RuntimeException("400");
        }

        UnfollowUserServiceImpl service;
        try {
            service = new UnfollowUserServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.unfollowUser(request);
    }
}
