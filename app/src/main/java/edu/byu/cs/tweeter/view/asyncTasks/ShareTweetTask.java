package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.net.response.Response;
import edu.byu.cs.tweeter.presenter.TweetPresenter;

public class ShareTweetTask extends AsyncTask<Tweet, Void, Response> {

    private final TweetPresenter presenter;
    private final ShareTweetObserver observer;

    public interface ShareTweetObserver {
        void tweetShared(Response response);
    }

    public ShareTweetTask(TweetPresenter presenter, ShareTweetObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected Response doInBackground(Tweet... tweets) {
        Response response = presenter.shareTweet(tweets[0]);
        return response;
    }

    @Override
    protected void onPostExecute(Response response) {
        if (observer != null) {
            observer.tweetShared(response);
        }
    }
}
