package edu.byu.cs.tweeter.view.response;

import java.util.List;

import edu.byu.cs.tweeter.model.net.response.PagedResponse;
import edu.byu.cs.tweeter.view.android.domain.User;

// TODO: This code is the same as FollowingResponse except for the import of User. Look into reducing to one by using a generic for the User
public class UIFollowingResponse extends PagedResponse {

    private List<User> followees;

    public UIFollowingResponse(String message) {
        super(false, message, false);
    }

    public UIFollowingResponse(List<User> followees, boolean hasMorePages) {
        super(true, hasMorePages);
        this.followees = followees;
    }

    public List<User> getFollowees() {
        return followees;
    }
}
