package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;

public class UsersDAO {

    private static final String TableName = "users";

    private static final String HandleAttr = "alias";
    private static final String FNameAttr = "first_name";
    private static final String LNameAttr = "last_name";
    private static final String ImageAttr = "image_url";
    private static final String PasswordAttr = "password";


    // DynamoDB client
    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-west-2")
            .build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public SignInResponse signIn(SignInRequest request) {
        Table table = dynamoDB.getTable(TableName);

        Item item = table.getItem(HandleAttr, request.getAlias());
        if (item == null) {
            return new SignInResponse("User not found");
        }

        String alias = item.getString(HandleAttr);
        String first_name = item.getString(FNameAttr);
        String last_name = item.getString(LNameAttr);
        String image_url = item.getString(ImageAttr);
        String password = item.getString(PasswordAttr);

        if (!password.equals(request.getPassword())) {
            return new SignInResponse("Invalid password");
        }

        return new SignInResponse(new User(first_name, last_name, alias, image_url), "token");
    }

    public SignUpResponse signUp(SignUpRequest request) {
        Table table = dynamoDB.getTable(TableName);

        Item check = table.getItem(HandleAttr, request.getAlias());
        if (check != null) {
            return new SignUpResponse("User already exists");
        }

        Item item = new Item()
                .withPrimaryKey(HandleAttr, request.getAlias())
                .withString(FNameAttr, request.getFirstName())
                .withString(LNameAttr, request.getLastName())
                .withString(ImageAttr, request.getImageURL())
                .withString(PasswordAttr, request.getPassword());
        table.putItem(item);

        User user = new User(request.getFirstName(), request.getLastName(), request.getAlias(), request.getImageURL());

        return new SignUpResponse(user, "token");
    }

    // TODO: Move this to auth DAO
    public SignOutResponse signOut(SignOutRequest request) {
        return new SignOutResponse();
    }

    public GetUserResponse getUser(GetUserRequest request) {
        Table table = dynamoDB.getTable(TableName);

        Item item = table.getItem(HandleAttr, request.getAlias());
        if (item == null) {
            return new GetUserResponse("Unable to find user");
        }

        String alias = item.getString(HandleAttr);
        String first_name = item.getString(FNameAttr);
        String last_name = item.getString(LNameAttr);
        String image_url = item.getString(ImageAttr);

        return new GetUserResponse(new User(first_name, last_name, alias, image_url));
    }
}
