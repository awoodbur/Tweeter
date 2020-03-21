package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.Response;

import static org.junit.jupiter.api.Assertions.*;

class UserPresenterTest {

    private User user1;
    private User user2;
    private Follow follow;
    private Follow follow1;
    UserPresenter presenter;
    FollowingPresenter following;

    @BeforeEach
    void setUp() {
        user1 = new User("Tester", "Testerson", "test1", "");
        user2 = new User("Tester", "Testerson", "test2", "");
        follow = new Follow(user1, user2);
        follow = new Follow(user2, user1);

        presenter = new UserPresenter(null);
        following = new FollowingPresenter(null);
    }

    @Test
    void followUser() {
        Response response = presenter.followUser(follow);
        assertTrue(response.isSuccess());
        FollowingResponse followingResponse = following.getFollowing(new FollowingRequest(user1, 1, null));
        assertEquals(user2, followingResponse.getFollowees().get(0));
    }

    @Test
    void unfollowUser() {
        Response response = presenter.unfollowUser(follow);
        assertTrue(response.isSuccess());
        FollowingResponse followingResponse = following.getFollowing(new FollowingRequest(user2, 1, null));
        assertEquals(0, followingResponse.getFollowees().size());
    }
}