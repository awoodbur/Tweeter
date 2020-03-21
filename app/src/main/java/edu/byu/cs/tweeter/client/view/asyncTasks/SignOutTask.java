package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.UserPresenter;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;

public class SignOutTask extends AsyncTask<SignOutRequest, Void, SignOutResponse> {

    private final UserPresenter presenter;
    private final LogoutObserver observer;

    private Exception exception;

    public interface LogoutObserver {
        void logoutComplete(SignOutResponse response);
        void handleException(Exception e);
    }

    public SignOutTask(UserPresenter presenter, LogoutObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected SignOutResponse doInBackground(SignOutRequest... signOutRequests) {
        SignOutResponse response = null;
        try {
            response = presenter.signOut(signOutRequests[0]);
        } catch (IOException e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(SignOutResponse response) {
        if (observer != null) {
            if (exception == null) {
                observer.logoutComplete(response);
            } else {
                observer.handleException(exception);
            }
        }
    }
}
