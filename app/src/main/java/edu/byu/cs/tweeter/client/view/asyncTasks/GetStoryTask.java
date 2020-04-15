package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.client.presenter.StoryPresenter;
import edu.byu.cs.tweeter.client.view.cache.ImageCache;
import edu.byu.cs.tweeter.client.view.util.ImageUtils;

/**
 * An {@link AsyncTask} for retrieving tweets for a user.
 */
public class GetStoryTask extends AsyncTask<StoryRequest, Void, StoryResponse> {

    private final StoryPresenter presenter;
    private final GetTweetsObserver observer;

    private Exception exception;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface GetTweetsObserver {
        void tweetsRetrieved(StoryResponse storyResponse);
        void handleException(Exception e);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter from whom this task should retrieve tweets.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public GetStoryTask(StoryPresenter presenter, GetTweetsObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    /**
     * The method that is invoked on the background thread to retrieve tweets.
     *
     * @param storyRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected StoryResponse doInBackground(StoryRequest... storyRequests) {
        StoryResponse response = null;
        try {
            response = presenter.getStory(storyRequests[0]);
            loadImages(response);
        } catch (IOException e) {
            exception = e;
        }
        return response;
    }

    /**
     * Loads the image associated with each tweet included in the response.
     *
     * @param response the response from the tweet request.
     */
    private void loadImages(StoryResponse response) {
        if (response != null) {
            for (Tweet tweet : response.getTweets()) {

                User user = tweet.getAuthor();

                Drawable drawable;

                try {
                    drawable = ImageUtils.drawableFromUrl(user.getImageUrl());
                } catch (IOException e) {
                    Log.e(this.getClass().getName(), e.toString(), e);
                    drawable = null;
                }

                ImageCache.getInstance().cacheImage(user, drawable);
            }
        }
    }

    /**
     * Notifies the observer (on the UI thread) when the task completes.
     *
     * @param storyResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(StoryResponse storyResponse) {

        if(observer != null) {
            if (exception == null) {
                observer.tweetsRetrieved(storyResponse);
            } else {
                observer.handleException(exception);
            }
        }
    }
}
