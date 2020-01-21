package edu.byu.cs.tweeter.model.services;

import edu.byu.cs.tweeter.net.Server;
import edu.byu.cs.tweeter.net.ServerProxy;
import edu.byu.cs.tweeter.net.request.FollowingRequest;
import edu.byu.cs.tweeter.net.response.FollowingResponse;

public class FollowingService {

    private static FollowingService instance;

    private final Server server;

    public static FollowingService getInstance() {
        if(instance == null) {
            instance = new FollowingService();
        }

        return instance;
    }

    private FollowingService() {
        server = new ServerProxy();
    }

    public FollowingResponse getFollowees(FollowingRequest request) {
        return server.getFollowees(request);
    }
}
