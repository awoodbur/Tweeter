package edu.byu.cs.tweeter.server.lambda.followuser;

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

import static org.junit.jupiter.api.Assertions.*;

class FollowUserHandlerTest {

    private FollowUserHandler handler;
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        handler = new FollowUserHandler();

        user1 = new User("test99", "test99", "test99", "test99");
        user2 = new User("test100", "test100", "test100", "test100");
    }

    @AfterEach
    void tearDown() {
        FollowsDAO followsDAO = new FollowsDAO();
        UnfollowUserResponse response = followsDAO.unfollowUser(new UnfollowUserRequest(user1, user2, "token"));
    }

    @Test
    void followUser() {
        FollowUserRequest request = new FollowUserRequest(user1, user2, "token");
        FollowUserResponse response = handler.handleRequest(request, null);
        assertTrue(response.isSuccess());

        FollowsDAO followsDAO = new FollowsDAO();
        CheckFollowResponse check = followsDAO.checkFollow(new CheckFollowRequest(user2, user1, "token"));
        assertTrue(check.isFollowing());
    }
}