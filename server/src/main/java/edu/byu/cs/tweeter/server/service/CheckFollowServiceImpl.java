package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.CheckFollowService;
import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;
import edu.byu.cs.tweeter.server.dao.FollowsDAO;

public class CheckFollowServiceImpl extends ServiceImpl implements CheckFollowService {

    @Override
    public CheckFollowResponse checkFollow(CheckFollowRequest request) {
        validateToken(request.getToken());
        FollowsDAO dao = new FollowsDAO();
        return dao.checkFollow(request);
    }
}
