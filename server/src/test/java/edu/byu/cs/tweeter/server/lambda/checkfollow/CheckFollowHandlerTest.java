package edu.byu.cs.tweeter.server.lambda.checkfollow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;
import edu.byu.cs.tweeter.server.lambda.followuser.FollowUserHandler;
import edu.byu.cs.tweeter.server.lambda.unfollowuser.UnfollowUserHandler;

import static org.junit.jupiter.api.Assertions.*;

class CheckFollowHandlerTest {

    private CheckFollowHandler handler;
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        handler = new CheckFollowHandler();

        FollowUserHandler setup_handler = new FollowUserHandler();
        user1 = new User("test99", "test99", "test99", "test99");
        user2 = new User("test100", "test100", "test100", "test100");
        FollowUserRequest setup = new FollowUserRequest(user1, user2, "token");
        FollowUserResponse setup_resp = setup_handler.handleRequest(setup, null);
        if (!setup_resp.isSuccess()) {
            throw new RuntimeException("Unable to setup follow");
        }
    }

    @AfterEach
    void tearDown() {
        UnfollowUserHandler setup2_handler = new UnfollowUserHandler();
        UnfollowUserRequest setup2 = new UnfollowUserRequest(user1, user2, "token");
        UnfollowUserResponse setup_resp2 = setup2_handler.handleRequest(setup2, null);

        CheckFollowRequest check2 = new CheckFollowRequest(user2, user1, "token");
        CheckFollowResponse checked2 = handler.handleRequest(check2, null);
        assertFalse(checked2.isFollowing());
    }

    @Test
    void checkFollow() {
        CheckFollowRequest check = new CheckFollowRequest(user2, user1, "token");
        CheckFollowResponse checked = handler.handleRequest(check, null);
        assertTrue(checked.isFollowing());
    }
}