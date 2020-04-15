package edu.byu.cs.tweeter.server.lambda.story;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.server.service.StoryServiceImpl;

public class GetStoryHandler implements RequestHandler<StoryRequest, StoryResponse> {

    @Override
    public StoryResponse handleRequest(StoryRequest request, Context context) {
        if (request.getUser() == null || request.getLimit() <= 0) {
            throw new RuntimeException("400");
        }

        StoryServiceImpl service;
        try {
            service = new StoryServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.getStory(request);
    }
}
