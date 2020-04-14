package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;

public class AuthsDAO {

    private static final String TableName = "auths";

    private static final String TokenAttr = "token";
    private static final String TimestampAttr = "creation_time";


    // DynamoDB client
    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-west-2")
            .build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public void addToken(String token, String timestamp) {
        Table table = dynamoDB.getTable(TableName);

        Item item = new Item()
                .withPrimaryKey(TokenAttr, token)
                .withString(TimestampAttr, timestamp);
        table.putItem(item);
    }

    public String validateToken(String token) {
        Table table = dynamoDB.getTable(TableName);

        Item item = table.getItem(TokenAttr, token);
        if (item == null) {
            return null;
        }

        return item.getString(TimestampAttr);
    }

    public void deleteToken(String token) {
        Table table = dynamoDB.getTable(TableName);
        table.deleteItem(TokenAttr, token);
    }
}
