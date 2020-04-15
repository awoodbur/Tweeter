package edu.byu.cs.tweeter.server.lambda.following;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.server.service.FollowingServiceImpl;

/**
 * An AWS lambda function that returns the users a user is following.
 */
public class GetFollowingHandler implements RequestHandler<FollowingRequest, FollowingResponse> {

    @Override
    public FollowingResponse handleRequest(FollowingRequest request, Context context) {
        if (request.getFollower() == null || request.getLimit() <= 0) {
            throw new RuntimeException("400");
        }

        FollowingServiceImpl service;
        try {
            service = new FollowingServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.getFollowees(request);
    }
}
