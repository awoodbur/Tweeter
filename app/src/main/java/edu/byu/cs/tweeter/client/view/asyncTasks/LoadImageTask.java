package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.tweeter.client.view.util.ImageUtils;

/**
 * An {@link AsyncTask} for loading images from a set of URLs.
 */
public class LoadImageTask extends AsyncTask<String, Integer, Drawable []> {

    private final LoadImageObserver observer;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface LoadImageObserver {

        void imageLoadProgressUpdated(Integer progress);
        void imagesLoaded(Drawable [] drawables);
    }

    /**
     * Creates an instance.
     *
     * @param observer the observer who wants to be notified when this task completes.
     */
    public LoadImageTask(LoadImageObserver observer) {
        this.observer = observer;
    }

    /**
     * The method that is invoked on the background thread to retrieve images.
     *
     * @param urls the urls from which images should be retrieved.
     * @return the images.
     */
    @Override
    protected Drawable [] doInBackground(String... urls) {

        Drawable [] drawables = new Drawable [urls.length];

        for(int i = 0; i < urls.length; i++) {

            try {
                drawables[i] = ImageUtils.drawableFromUrl(urls[0]);
            } catch (IOException e) {
                Log.e(this.getClass().getName(), "Error loading image. " + e);
            }

            publishProgress((i / urls.length) * 100);
        }

        return drawables;
    }

    /**
     * Notifies the observer (on the UI thread) after each image is retrieved.
     *
     * @param values the progress indicator, represented as a percentage (between 0 and 100) of the
     *               total number of images to be loaded.
     */
    @Override
    protected void onProgressUpdate(Integer... values) {

        if(observer != null) {
            observer.imageLoadProgressUpdated(values[0]);
        }
    }

    /**
     * Notifies the observer (on the UI thread) when the task completes.
     *
     * @param drawables the images that were retrieved by the task.
     */
    @Override
    protected void onPostExecute(Drawable [] drawables) {

        if(observer != null) {
            observer.imagesLoaded(drawables);
        }
    }
}
