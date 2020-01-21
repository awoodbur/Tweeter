package edu.byu.cs.tweeter.model.net.response;

import java.util.List;

public class FollowingResponse<T> extends PagedResponse {

    private List<T> followees;

    public FollowingResponse(String message) {
        super(false, message, false);
    }

    public FollowingResponse(List<T> followees, boolean hasMorePages) {
        super(true, hasMorePages);
        this.followees = followees;
    }

    public List<T> getFollowees() {
        return followees;
    }
}
