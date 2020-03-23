package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.GetUserService;
import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;
import edu.byu.cs.tweeter.server.dao.GetUserDAO;
import edu.byu.cs.tweeter.server.dao.UsersDAO;

public class GetUserServiceImpl implements GetUserService {

    @Override
    public GetUserResponse getUser(GetUserRequest request) {
        GetUserDAO dao = new GetUserDAO();
        return dao.getUser(request);
    }
}
