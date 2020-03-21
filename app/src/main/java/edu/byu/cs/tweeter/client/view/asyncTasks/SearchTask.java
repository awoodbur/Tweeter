package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.client.presenter.MainPresenter;

public class SearchTask extends AsyncTask<String, Void, User> {

    private final MainPresenter presenter;
    private final SearchObserver observer;

    public interface SearchObserver {
        void searchComplete(User user);
    }

    public SearchTask(MainPresenter presenter, SearchObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected User doInBackground(String... strings) {
        return presenter.getUserByAlias(strings[0]);
    }

    @Override
    protected void onPostExecute(User user) {
        if (observer != null) {
            observer.searchComplete(user);
        }
    }
}
