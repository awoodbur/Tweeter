package edu.byu.cs.tweeter.server.lambda.followers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.service.FollowersServiceImpl;

public class GetFollowersHandler implements RequestHandler<FollowersRequest, FollowersResponse> {

    @Override
    public FollowersResponse handleRequest(FollowersRequest request, Context context) {
        FollowersServiceImpl service = new FollowersServiceImpl();
        return service.getFollowers(request);
    }
}
