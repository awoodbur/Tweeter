package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.client.presenter.SignInPresenter;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;

public class SignInTask extends AsyncTask<SignInRequest, Void, SignInResponse> {

    private final SignInPresenter presenter;
    private final SignInObserver observer;

    private Exception exception;

    public interface SignInObserver {
        void signInComplete(SignInResponse response);
        void handleException(Exception e);
    }

    public SignInTask(SignInPresenter presenter, SignInObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected SignInResponse doInBackground(SignInRequest... signInRequests) {
        SignInResponse response = null;
        try {
            response = presenter.signIn(signInRequests[0]);
        } catch (IOException e) {
            exception = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(SignInResponse response) {
        if (observer != null) {
            if (exception == null) {
                observer.signInComplete(response);
            } else {
                observer.handleException(exception);
            }
        }
    }
}
