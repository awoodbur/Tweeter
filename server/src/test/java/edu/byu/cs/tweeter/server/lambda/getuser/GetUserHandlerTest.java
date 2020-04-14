package edu.byu.cs.tweeter.server.lambda.getuser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;
import edu.byu.cs.tweeter.server.dao.UsersDAO;

import static org.junit.jupiter.api.Assertions.*;

class GetUserHandlerTest {

    private GetUserHandler handler;
    private User user1;
    private String password;

    @BeforeEach
    void setUp() {
        handler = new GetUserHandler();

        user1 = new User("test99", "test99", "test99", "test99");
        password = "passowrd";
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.signUp(new SignUpRequest(user1.getFirstName(), user1.getLastName(), user1.getAlias(), password, user1.getImageUrl()));
    }

    @AfterEach
    void tearDown() {
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.deleteUser(user1);
    }

    @Test
    void getUser() {
        GetUserRequest request = new GetUserRequest(user1.getAlias(), "token");
        GetUserResponse response = handler.handleRequest(request, null);
        assertEquals(user1, response.getUser());
    }
}