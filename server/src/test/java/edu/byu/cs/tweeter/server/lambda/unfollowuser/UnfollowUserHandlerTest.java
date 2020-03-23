package edu.byu.cs.tweeter.server.lambda.unfollowuser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;
import edu.byu.cs.tweeter.server.lambda.followers.GetFollowersHandler;
import edu.byu.cs.tweeter.server.lambda.following.GetFollowingHandler;
import edu.byu.cs.tweeter.server.lambda.followuser.FollowUserHandler;

import static org.junit.jupiter.api.Assertions.*;

class UnfollowUserHandlerTest {

    private UnfollowUserHandler handler;

    @BeforeEach
    void setUp() {
        handler = new UnfollowUserHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void unfollowUser() {
        FollowUserHandler setup_handler = new FollowUserHandler();
        User user1 = new User("kirk");
        User user2 = new User("test", "test", "test", "");
        FollowUserRequest setup = new FollowUserRequest(user1, user2);
        FollowUserResponse setup_resp = setup_handler.handleRequest(setup, null);

        UnfollowUserRequest request = new UnfollowUserRequest(user1, user2);
        UnfollowUserResponse response = handler.handleRequest(request, null);

        GetFollowingHandler check_following_handler = new GetFollowingHandler();
        FollowingRequest check_following = new FollowingRequest(user1, 10, null);
        FollowingResponse check_following_resp = check_following_handler.handleRequest(check_following, null);
        List<User> followees = new ArrayList<>();
        followees.add(new User("spock"));
        followees.add(new User("bones"));
        followees.add(new User("scotty"));
        assertEquals(followees, check_following_resp.getFollowees());

        GetFollowersHandler check_followers_handler = new GetFollowersHandler();
        FollowersRequest check_followers = new FollowersRequest(user2, 10, null);
        FollowersResponse check_followers_resp = check_followers_handler.handleRequest(check_followers, null);
        List<User> followers = new ArrayList<>();
        assertEquals(followers, check_followers_resp.getFollowers());
    }
}