package edu.byu.cs.tweeter.server.lambda.signup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;
import edu.byu.cs.tweeter.server.dao.UsersDAO;

import static org.junit.jupiter.api.Assertions.*;

class SignUpHandlerTest {

    private SignUpHandler handler;
    private User user1;
    private User user2;
    private String password;
    private String hashed_password;

    @BeforeEach
    void setUp() {
        handler = new SignUpHandler();

        user1 = new User("test99", "test99", "test99", "test99");
        user2 = new User("test100", "test100", "test100", "test100");
        password = "password";
        hashed_password = "5f4dcc3b5aa765d61d8327deb882cf99";
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.signUp(new SignUpRequest(user1.getFirstName(), user1.getLastName(), user1.getAlias(), hashed_password, user1.getImageUrl()));
    }

    @AfterEach
    void tearDown() {
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.deleteUser(user1);
        usersDAO.deleteUser(user2);
    }

    @Test
    void signUpExists() {
        SignUpRequest request = new SignUpRequest(user1.getFirstName(), user1.getLastName(), user1.getAlias(), password, user1.getImageUrl());
        SignUpResponse response = handler.handleRequest(request, null);
        assertEquals("User already exists", response.getMessage());
    }

    @Test
    void signUp() {
        SignUpRequest request = new SignUpRequest(user2.getFirstName(), user2.getLastName(), user2.getAlias(), password, user2.getImageUrl());
        SignUpResponse response = handler.handleRequest(request, null);
        assertEquals(user2, response.getUser());
    }
}