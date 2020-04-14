package edu.byu.cs.tweeter.server.lambda.tweet;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

import edu.byu.cs.tweeter.model.json.Serializer;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;
import edu.byu.cs.tweeter.server.service.ShareTweetServiceImpl;

public class ShareTweetHandler implements RequestHandler<ShareTweetRequest, ShareTweetResponse> {

    @Override
    public ShareTweetResponse handleRequest(ShareTweetRequest request, Context context) {
        String queueURL = "https://sqs.us-west-2.amazonaws.com/754276193250/PostStatusQueue";

        if (request.getTweet() == null) {
            throw new RuntimeException("400");
        }

        ShareTweetServiceImpl service;
        try {
            service = new ShareTweetServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        ShareTweetResponse response = service.shareTweet(request);

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueURL)
                .withMessageBody(Serializer.serialize(request))
                .withDelaySeconds(5);
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
        System.out.println(sendMessageResult.getMessageId());

        return response;
    }
}
