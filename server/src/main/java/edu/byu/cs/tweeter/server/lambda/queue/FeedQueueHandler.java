package edu.byu.cs.tweeter.server.lambda.queue;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

import edu.byu.cs.tweeter.model.json.Serializer;
import edu.byu.cs.tweeter.model.service.request.BatchShareTweetRequest;
import edu.byu.cs.tweeter.server.service.ShareTweetServiceImpl;

public class FeedQueueHandler implements RequestHandler<SQSEvent, Void> {
    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        ShareTweetServiceImpl service;
        try {
            service = new ShareTweetServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }

        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            System.out.println(msg.getBody());
            BatchShareTweetRequest body = Serializer.deserialize(msg.getBody(), BatchShareTweetRequest.class);

            service.batchFeedUpdate(body);
        }
        return null;
    }
}
