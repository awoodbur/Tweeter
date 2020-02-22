package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.User;

import static org.junit.jupiter.api.Assertions.*;

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