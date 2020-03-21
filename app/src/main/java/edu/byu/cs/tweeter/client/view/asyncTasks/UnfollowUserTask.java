package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.client.presenter.UserPresenter;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;

public class UnfollowUserTask extends AsyncTask<UnfollowUserRequest, Void, UnfollowUserResponse> {

    private final UserPresenter presenter;
    private final UnfollowUserObserver observer;

    private Exception exception;

    public interface UnfollowUserObserver {
        void unfollowUserComplete(UnfollowUserResponse response);
        void handleException(Exception e);
    }

    public UnfollowUserTask(UserPresenter presenter, UnfollowUserObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected UnfollowUserResponse doInBackground(UnfollowUserRequest... unfollowUserRequests) {
        UnfollowUserResponse response = null;
        try {
            response = presenter.unfollowUser(unfollowUserRequests[0]);
        } catch (IOException e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(UnfollowUserResponse response) {
        if (observer != null) {
            if (exception == null) {
                observer.unfollowUserComplete(response);
            } else {
                observer.handleException(exception);
            }
        }
    }
}
