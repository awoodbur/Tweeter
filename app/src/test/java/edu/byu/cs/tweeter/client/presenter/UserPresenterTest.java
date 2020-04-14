package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;

import static org.junit.jupiter.api.Assertions.*;

class UserPresenterTest {

    private User user1;
    private User user2;
    private FollowUserRequest follow;
    private UnfollowUserRequest unfollow;
    UserPresenter presenter;
    FollowingPresenter following;

    @BeforeEach
    void setUp() {
        user1 = new User("Tester", "Testerson", "test1", "");
        user2 = new User("Tester", "Testerson", "test2", "");
        follow = new FollowUserRequest(user1, user2, "token");
        unfollow = new UnfollowUserRequest(user2, user1, "token");

        presenter = new UserPresenter(null);
        following = new FollowingPresenter(null);
    }

    @Test
    void followUser() throws IOException  {
        Response response = presenter.followUser(follow);
        assertTrue(response.isSuccess());
        FollowingResponse followingResponse = following.getFollowing(new FollowingRequest(user1, 1, null, "token"));
        assertEquals(user2, followingResponse.getFollowees().get(0));
    }

    @Test
    void unfollowUser() throws IOException{
        Response response = presenter.unfollowUser(unfollow);
        assertTrue(response.isSuccess());
        FollowingResponse followingResponse = following.getFollowing(new FollowingRequest(user2, 1, null, "token"));
        assertEquals(0, followingResponse.getFollowees().size());
    }
}