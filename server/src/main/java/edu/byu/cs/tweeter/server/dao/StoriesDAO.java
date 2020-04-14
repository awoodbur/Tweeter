package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class StoriesDAO {

    private static final String TableName = "stories";

    private static final String HandleAttr = "alias";
    private static final String TimestampAttr = "timestamp";
    private static final String ContentAttr = "content";
    private static final String FNameAttr = "first_name";
    private static final String LNameAttr = "last_name";
    private static final String ImageAttr = "image_url";


    // DynamoDB client
    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-west-2")
            .build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public StoryResponse getStory(StoryRequest request) {
        Map<String, String> attrNames = new HashMap<>();
        attrNames.put("#handle", HandleAttr);

        Map<String, AttributeValue> attrValues = new HashMap<>();
        attrValues.put(":alias", new AttributeValue().withS(request.getUser().getAlias()));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName(TableName)
                .withKeyConditionExpression("#handle = :alias")
                .withExpressionAttributeNames(attrNames)
                .withExpressionAttributeValues(attrValues)
                .withLimit(request.getLimit());

        if (request.getLastTweet() != null) {
            Map<String, AttributeValue> startKey = new HashMap<>();
            startKey.put(HandleAttr, new AttributeValue().withS(request.getUser().getAlias()));
            startKey.put(TimestampAttr, new AttributeValue().withN(String.valueOf(request.getLastTweet().getDate())));

            queryRequest = queryRequest.withExclusiveStartKey(startKey);
        }

        List<Tweet> tweets = new ArrayList<>();
        QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        List<Map<String, AttributeValue>> items = queryResult.getItems();
        if (items != null) {
            for (Map<String, AttributeValue> item: items) {
                String alias = item.get(HandleAttr).getS();
                long timestamp = Long.parseLong(item.get(TimestampAttr).getN());
                String content = item.get(ContentAttr).getS();
                String first_name = item.get(FNameAttr).getS();
                String last_name = item.get(LNameAttr).getS();
                String image_url = item.get(ImageAttr).getS();

                User author = new User(first_name, last_name, alias, image_url);
                tweets.add(new Tweet(author, content, timestamp));
            }
        }

        boolean hasMore = false;
        Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
        if (lastKey != null) {
            hasMore = true;
        }

        return new StoryResponse(tweets, hasMore);
    }

    public ShareTweetResponse shareTweet(ShareTweetRequest request) {
        Table table = dynamoDB.getTable(TableName);

        User author = request.getTweet().getAuthor();

        Item item = new Item()
                .withPrimaryKey(HandleAttr, author.getAlias())
                .withString(FNameAttr, author.getFirstName())
                .withString(LNameAttr, author.getLastName())
                .withString(ImageAttr, author.getImageUrl())
                .withString(ContentAttr, request.getTweet().getContent())
                .withNumber(TimestampAttr, request.getTweet().getDate());
        table.putItem(item);

        return new ShareTweetResponse();
    }
}
