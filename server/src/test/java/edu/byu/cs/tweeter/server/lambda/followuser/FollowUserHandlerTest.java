package edu.byu.cs.tweeter.server.lambda.followuser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.server.lambda.followers.GetFollowersHandler;
import edu.byu.cs.tweeter.server.lambda.following.GetFollowingHandler;

import static org.junit.jupiter.api.Assertions.*;

class FollowUserHandlerTest {

    private FollowUserHandler handler;

    @BeforeEach
    void setUp() {
        handler = new FollowUserHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void followUser() {
        User user1 = new User("kirk");
        User user2 = new User("test", "test", "test", "");
        FollowUserRequest request = new FollowUserRequest(user1, user2, "token");
        FollowUserResponse response = handler.handleRequest(request, null);

        GetFollowingHandler check_following_handler = new GetFollowingHandler();
        FollowingRequest check_following = new FollowingRequest(user1, 10, null, "token");
        FollowingResponse check_following_resp = check_following_handler.handleRequest(check_following, null);
        List<User> followees = new ArrayList<>();
        followees.add(new User("spock"));
        followees.add(new User("bones"));
        followees.add(new User("scotty"));
        followees.add(new User("test"));
        assertEquals(followees, check_following_resp.getFollowees());

        GetFollowersHandler check_followers_handler = new GetFollowersHandler();
        FollowersRequest check_followers = new FollowersRequest(user2, 10, null, "token");
        FollowersResponse check_followers_resp = check_followers_handler.handleRequest(check_followers, null);
        List<User> followers = new ArrayList<>();
        followers.add(new User("kirk"));
        assertEquals(followers, check_followers_resp.getFollowers());
    }
}