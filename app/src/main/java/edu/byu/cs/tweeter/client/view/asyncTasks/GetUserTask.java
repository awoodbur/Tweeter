package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.Presenter;
import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;

public class GetUserTask extends AsyncTask<GetUserRequest, Void, GetUserResponse> {

    private final Presenter presenter;
    private final GetUserObserver observer;

    private Exception exception;

    public interface GetUserObserver {
        void getUserComplete(GetUserResponse response);
        void handleException(Exception e);
    }

    public GetUserTask(Presenter presenter, GetUserObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected GetUserResponse doInBackground(GetUserRequest... getUserRequests) {
        GetUserResponse response = null;
        try {
            response = presenter.getUser(getUserRequests[0]);
        } catch (IOException e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(GetUserResponse response) {
        if (observer != null) {
            if (exception == null) {
                observer.getUserComplete(response);
            } else {
                observer.handleException(exception);
            }
        }
    }
}
