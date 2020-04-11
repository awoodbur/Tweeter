package edu.byu.cs.tweeter.server.lambda.checkfollow;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;
import edu.byu.cs.tweeter.server.service.CheckFollowServiceImpl;

public class CheckFollowHandler implements RequestHandler<CheckFollowRequest, CheckFollowResponse> {

    @Override
    public CheckFollowResponse handleRequest(CheckFollowRequest request, Context context) {
        if (request.getFollower() == null || request.getFollowee() == null) {
            throw new RuntimeException("400");
        }

        CheckFollowServiceImpl service;
        try {
            service = new CheckFollowServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.checkFollow(request);
    }
}
