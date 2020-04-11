package edu.byu.cs.tweeter.server.lambda.feed;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.service.FeedServiceImpl;

public class GetFeedHandler implements RequestHandler<FeedRequest, FeedResponse> {
    @Override
    public FeedResponse handleRequest(FeedRequest request, Context context) {
        if (request.getUser() == null || request.getLimit() <= 0 || request.getLimit() > 25) {
            throw new RuntimeException("400");
        }

        FeedServiceImpl service;
        try {
            service = new FeedServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.getFeed(request);
    }
}
