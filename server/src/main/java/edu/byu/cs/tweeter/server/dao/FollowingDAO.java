package edu.byu.cs.tweeter.server.dao;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;

public class FollowingDAO {
    public FollowingResponse getFollowing(FollowingRequest request) {
        List<User> followees = Database.getInstance().getUserFollowing(request.getFollower());
        List<User> responseFollowees = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;
        if (request.getLimit() > 0 && followees != null) {
            int followeesIndex = Database.getInstance().getFolloweeIndex(request.getFollower(), request.getLastFollowee());

            for (int limitCounter = 0; followeesIndex < followees.size() && limitCounter < request.getLimit(); followeesIndex++, limitCounter++) {
                responseFollowees.add(followees.get(followeesIndex));
            }

            hasMorePages = followeesIndex < followees.size();
        }

        return new FollowingResponse(responseFollowees, hasMorePages);
    }
}
