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

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;

public class FollowsDAO {

    private static final String TableName = "follows";
    private static final String IndexName = "follows_index";

    private static final String FollowerHandleAttr = "follower_handle";
    private static final String FollowerFNameAttr = "follower_first_name";
    private static final String FollowerLNameAttr = "follower_last_name";
    private static final String FollowerImageAttr = "follower_image_url";

    private static final String FolloweeHandleAttr = "followee_handle";
    private static final String FolloweeFNameAttr = "followee_first_name";
    private static final String FolloweeLNameAttr = "followee_last_name";
    private static final String FolloweeImageAttr = "followee_image_url";


    // DynamoDB client
    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-west-2")
            .build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public FollowingResponse getFollowing(FollowingRequest request) {
        Map<String, String> attrNames = new HashMap<>();
        attrNames.put("#follower", FollowerHandleAttr);

        Map<String, AttributeValue> attrValues = new HashMap<>();
        attrValues.put(":follower_handle", new AttributeValue().withS(request.getFollower().getAlias()));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName(TableName)
                .withKeyConditionExpression("#follower = :follower_handle")
                .withExpressionAttributeNames(attrNames)
                .withExpressionAttributeValues(attrValues)
                .withLimit(request.getLimit());

        if (request.getLastFollowee() != null) {
            Map<String, AttributeValue> startKey = new HashMap<>();
            startKey.put(FollowerHandleAttr, new AttributeValue().withS(request.getFollower().getAlias()));
            startKey.put(FolloweeHandleAttr, new AttributeValue().withS(request.getLastFollowee().getAlias()));

            queryRequest = queryRequest.withExclusiveStartKey(startKey);
        }

        List<User> followees = new ArrayList<>();
        QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        List<Map<String, AttributeValue>> items = queryResult.getItems();
        if (items != null) {
            for (Map<String, AttributeValue> item: items) {
                String alias = item.get(FolloweeHandleAttr).getS();
                String first_name = item.get(FolloweeFNameAttr).getS();
                String last_name = item.get(FolloweeLNameAttr).getS();
                String image_url = item.get(FolloweeImageAttr).getS();
                followees.add(new User(first_name, last_name, alias, image_url));
            }
        }

        boolean hasMore = false;
        Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
        if (lastKey != null) {
            hasMore = true;
        }

        return new FollowingResponse(followees, hasMore);
    }

    public FollowersResponse getFollowers(FollowersRequest request) {
        Map<String, String> attrNames = new HashMap<>();
        attrNames.put("#followee", FolloweeHandleAttr);

        Map<String, AttributeValue> attrValues = new HashMap<>();
        attrValues.put(":followee_handle", new AttributeValue().withS(request.getFollowee().getAlias()));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName(TableName)
                .withIndexName(IndexName)
                .withKeyConditionExpression("#followee = :followee_handle")
                .withExpressionAttributeNames(attrNames)
                .withExpressionAttributeValues(attrValues)
                .withLimit(request.getLimit());

        if (request.getLastFollower() != null) {
            Map<String, AttributeValue> startKey = new HashMap<>();
            startKey.put(FolloweeHandleAttr, new AttributeValue().withS(request.getFollowee().getAlias()));
            startKey.put(FollowerHandleAttr, new AttributeValue().withS(request.getLastFollower().getAlias()));

            queryRequest = queryRequest.withExclusiveStartKey(startKey);
        }

        List<User> followers = new ArrayList<>();
        QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        List<Map<String, AttributeValue>> items = queryResult.getItems();
        if (items != null) {
            for (Map<String, AttributeValue> item: items) {
                String alias = item.get(FollowerHandleAttr).getS();
                String first_name = item.get(FollowerFNameAttr).getS();
                String last_name = item.get(FollowerLNameAttr).getS();
                String image_url = item.get(FollowerImageAttr).getS();
                followers.add(new User(first_name, last_name, alias, image_url));
            }
        }

        boolean hasMore = false;
        Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
        if (lastKey != null) {
            hasMore = true;
        }

        return new FollowersResponse(followers, hasMore);
    }

    public CheckFollowResponse checkFollow(CheckFollowRequest request) {
        Table table = dynamoDB.getTable(TableName);

        boolean follows = false;
        Item item = table.getItem(FollowerHandleAttr, request.getFollowee().getAlias(), FolloweeHandleAttr, request.getFollower().getAlias());
        if (item != null) {
            follows = true;
        }
        return new CheckFollowResponse(follows);
    }

    public FollowUserResponse followUser(FollowUserRequest request) {
        Table table = dynamoDB.getTable(TableName);

        Item item = new Item()
                .withPrimaryKey(FollowerHandleAttr, request.getFollower().getAlias(), FolloweeHandleAttr, request.getFollowee().getAlias())
                .withString(FollowerFNameAttr, request.getFollower().getFirstName())
                .withString(FollowerLNameAttr, request.getFollower().getLastName())
                .withString(FollowerImageAttr, request.getFollower().getImageUrl())
                .withString(FolloweeFNameAttr, request.getFollowee().getFirstName())
                .withString(FolloweeLNameAttr, request.getFollowee().getLastName())
                .withString(FolloweeImageAttr, request.getFollowee().getImageUrl());
        table.putItem(item);

        return new FollowUserResponse();
    }

    public UnfollowUserResponse unfollowUser(UnfollowUserRequest request) {
        Table table = dynamoDB.getTable(TableName);
        table.deleteItem(FollowerHandleAttr, request.getFollower().getAlias(), FolloweeHandleAttr, request.getFollowee().getAlias());
        return new UnfollowUserResponse();
    }
}
