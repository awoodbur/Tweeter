package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.service.request.AuthRequest;
import edu.byu.cs.tweeter.model.service.response.AuthResponse;

import static org.junit.jupiter.api.Assertions.*;

class SignUpPresenterTest {

    private AuthRequest signup;
    SignUpPresenter presenter;
    private String alias;

    @BeforeEach
    void setUp() {
        alias = "test7";
        signup = new AuthRequest("Tester", "Testerson", alias, "password", "");

        presenter = new SignUpPresenter(null);
    }

    @Test
    void signUp() {
        AuthResponse response = presenter.signUp(signup);
        assertTrue(response.isSuccess());
        assertEquals(alias, presenter.getCurrentUser().getAlias());
    }
}