package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;

class MainPresenterTest {

    private User user1;
    private User user2;
    MainPresenter presenter;

    @BeforeEach
    void setUp() {
        user1 = new User("Tester", "Testerson", "test1", "");
        user2 = new User("Tester", "Testerson", "test2", "");

        UserPresenter follower = new UserPresenter(null);
        follower.followUser(new Follow(user1, user2));
        follower.followUser(new Follow(user2, user1));
    }
}