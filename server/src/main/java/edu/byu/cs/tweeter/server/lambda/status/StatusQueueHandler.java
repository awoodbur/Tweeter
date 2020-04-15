package edu.byu.cs.tweeter.server.lambda.status;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.json.Serializer;
import edu.byu.cs.tweeter.model.service.request.BatchShareTweetRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.service.FollowersServiceImpl;

public class StatusQueueHandler implements RequestHandler<SQSEvent, Void> {

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        int page_size = 25;
        String queueURL = "https://sqs.us-west-2.amazonaws.com/754276193250/UpdateFeedQueue";

        FollowersServiceImpl service;
        try {
            service = new FollowersServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }

        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            System.out.println(msg.getBody());
            ShareTweetRequest body = Serializer.deserialize(msg.getBody(), ShareTweetRequest.class);

            User lastFollower = null;
            String token = body.getToken();
            User author = body.getTweet().getAuthor();
            while (true) {
                FollowersRequest request = new FollowersRequest(author, page_size, lastFollower, token);
                FollowersResponse response = service.getFollowers(request);
                if (!response.isSuccess()) {
                    throw new RuntimeException("500");
                }
                BatchShareTweetRequest batch = new BatchShareTweetRequest(response.getFollowers(), body.getTweet(), token);
                SendMessageRequest sendMessageRequest = new SendMessageRequest()
                        .withQueueUrl(queueURL)
                        .withMessageBody(Serializer.serialize(batch))
                        .withDelaySeconds(5);
                AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
                SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
                System.out.println(sendMessageResult.getMessageId());

                if (!response.getHasMorePages()) {
                    break;
                }
                try {
                    lastFollower = response.getFollowers().get(page_size-1);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
        return null;
    }
}
