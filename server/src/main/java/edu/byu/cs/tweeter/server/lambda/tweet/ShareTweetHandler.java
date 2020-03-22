package edu.byu.cs.tweeter.server.lambda.tweet;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;
import edu.byu.cs.tweeter.server.service.ShareTweetServiceImpl;

public class ShareTweetHandler implements RequestHandler<ShareTweetRequest, ShareTweetResponse> {

    @Override
    public ShareTweetResponse handleRequest(ShareTweetRequest request, Context context) {
        ShareTweetServiceImpl service = new ShareTweetServiceImpl();
        return service.shareTweet(request);
    }
}
