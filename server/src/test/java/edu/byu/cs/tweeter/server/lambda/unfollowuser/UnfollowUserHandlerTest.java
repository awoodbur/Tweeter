package edu.byu.cs.tweeter.server.lambda.unfollowuser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
import edu.byu.cs.tweeter.server.dao.FollowsDAO;
import edu.byu.cs.tweeter.server.lambda.followers.GetFollowersHandler;
import edu.byu.cs.tweeter.server.lambda.following.GetFollowingHandler;
import edu.byu.cs.tweeter.server.lambda.followuser.FollowUserHandler;

import static org.junit.jupiter.api.Assertions.*;

class UnfollowUserHandlerTest {

    private UnfollowUserHandler handler;
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        handler = new UnfollowUserHandler();

        user1 = new User("test99", "test99", "test99", "test99");
        user2 = new User("test100", "test100", "test100", "test100");
        FollowsDAO followsDAO = new FollowsDAO();
        FollowUserResponse setup_resp = followsDAO.followUser(new FollowUserRequest(user1, user2, "token"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void unfollowUser() {
        UnfollowUserRequest request = new UnfollowUserRequest(user1, user2, "token");
        UnfollowUserResponse response = handler.handleRequest(request, null);
        assertTrue(response.isSuccess());

        FollowsDAO followsDAO = new FollowsDAO();
        CheckFollowResponse check = followsDAO.checkFollow(new CheckFollowRequest(user2, user1, "token"));
        assertFalse(check.isFollowing());
    }
}