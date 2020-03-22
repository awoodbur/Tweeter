package edu.byu.cs.tweeter.server.lambda.checkfollow;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;
import edu.byu.cs.tweeter.server.service.CheckFollowServiceImpl;

public class CheckFollowHandler implements RequestHandler<CheckFollowRequest, CheckFollowResponse> {

    @Override
    public CheckFollowResponse handleRequest(CheckFollowRequest request, Context context) {
        CheckFollowServiceImpl service = new CheckFollowServiceImpl();
        return service.checkFollow(request);
    }
}
