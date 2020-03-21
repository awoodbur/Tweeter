package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;

import static org.junit.jupiter.api.Assertions.*;

class SignUpPresenterTest {

    private SignUpRequest signup;
    SignUpPresenter presenter;
    private String alias;

    @BeforeEach
    void setUp() {
        alias = "test7";
        signup = new SignUpRequest("Tester", "Testerson", alias, "password", "");

        presenter = new SignUpPresenter(null);
    }

    @Test
    void signUp() throws IOException {
        SignUpResponse response = presenter.signUp(signup);
        assertTrue(response.isSuccess());
        assertEquals(alias, presenter.getCurrentUser().getAlias());
    }
}