package edu.byu.cs.tweeter.server.dao;

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

import static org.junit.jupiter.api.Assertions.*;

class FollowDAOTest {

    private FollowDAO dao;

    @BeforeEach
    void setUp() {
        dao = new FollowDAO();
    }

    @AfterEach
    void tearDown() {
        dao.reset();
    }

    @Test
    void getFollowing() {
        User follower = new User("kirk");
        FollowingRequest request = new FollowingRequest(follower, 10, null);
        FollowingResponse response = dao.getFollowing(request);

        List<User> followees = new ArrayList<>();
        followees.add(new User("spock"));
        followees.add(new User("bones"));
        followees.add(new User("scotty"));
        assertEquals(followees, response.getFollowees());
    }

    @Test
    void getFollowers() {
        User follower = new User("kirk");
        FollowersRequest request = new FollowersRequest(follower, 10, null);
        FollowersResponse response = dao.getFollowers(request);
        List<User> followers = new ArrayList<>();
        followers.add(new User("spock"));
        followers.add(new User("bones"));
        followers.add(new User("sulu"));
        followers.add(new User("uhura"));
        followers.add(new User("scotty"));
        followers.add(new User("chekov"));
        assertEquals(followers, response.getFollowers());
    }

    @Test
    void followUser() {
        User user1 = new User("kirk");
        User user2 = new User("test", "test", "test", "");
        FollowUserRequest request = new FollowUserRequest(user1, user2);
        FollowUserResponse response = dao.followUser(request);

        FollowingRequest check_following = new FollowingRequest(user1, 10, null);
        FollowingResponse check_following_resp = dao.getFollowing(check_following);
        List<User> followees = new ArrayList<>();
        followees.add(new User("spock"));
        followees.add(new User("bones"));
        followees.add(new User("scotty"));
        followees.add(new User("test"));
        assertEquals(followees, check_following_resp.getFollowees());

        FollowersRequest check_followers = new FollowersRequest(user2, 10, null);
        FollowersResponse check_followers_resp = dao.getFollowers(check_followers);
        List<User> followers = new ArrayList<>();
        followers.add(new User("kirk"));
        assertEquals(followers, check_followers_resp.getFollowers());
    }

    @Test
    void unfollowUser() {
        User user1 = new User("kirk");
        User user2 = new User("test", "test", "test", "");
        FollowUserRequest setup = new FollowUserRequest(user1, user2);
        FollowUserResponse setup_resp = dao.followUser(setup);

        UnfollowUserRequest request = new UnfollowUserRequest(user1, user2);
        UnfollowUserResponse response = dao.unfollowUser(request);

        FollowingRequest check_following = new FollowingRequest(user1, 10, null);
        FollowingResponse check_following_resp = dao.getFollowing(check_following);
        List<User> followees = new ArrayList<>();
        followees.add(new User("spock"));
        followees.add(new User("bones"));
        followees.add(new User("scotty"));
        assertEquals(followees, check_following_resp.getFollowees());

        FollowersRequest check_followers = new FollowersRequest(user2, 10, null);
        FollowersResponse check_followers_resp = dao.getFollowers(check_followers);
        List<User> followers = new ArrayList<>();
        assertEquals(followers, check_followers_resp.getFollowers());
    }

    @Test
    void checkFollow() {
        User user1 = new User("kirk");
        User user2 = new User("test", "test", "test", "");
        FollowUserRequest setup = new FollowUserRequest(user1, user2);
        FollowUserResponse setup_resp = dao.followUser(setup);

        CheckFollowRequest check = new CheckFollowRequest(user2, user1);
        CheckFollowResponse checked = dao.checkFollow(check);
        assertTrue(checked.isFollowing());

        UnfollowUserRequest setup2 = new UnfollowUserRequest(user1, user2);
        UnfollowUserResponse setup_resp2 = dao.unfollowUser(setup2);

        CheckFollowRequest check2 = new CheckFollowRequest(user2, user1);
        CheckFollowResponse checked2 = dao.checkFollow(check2);
        assertFalse(checked2.isFollowing());
    }
}