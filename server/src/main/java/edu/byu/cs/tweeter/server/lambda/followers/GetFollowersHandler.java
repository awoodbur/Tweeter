package edu.byu.cs.tweeter.server.lambda.followers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.service.FollowersServiceImpl;

public class GetFollowersHandler implements RequestHandler<FollowersRequest, FollowersResponse> {

    @Override
    public FollowersResponse handleRequest(FollowersRequest request, Context context) {
        if (request.getFollowee() == null || request.getLimit() <= 0 || request.getLimit() > 25) {
            throw new RuntimeException("400");
        }

        FollowersServiceImpl service;
        try {
            service = new FollowersServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.getFollowers(request);
    }
}
