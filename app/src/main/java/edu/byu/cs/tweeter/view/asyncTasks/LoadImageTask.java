package edu.byu.cs.tweeter.view.asyncTasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.tweeter.view.util.ImageUtils;

public class LoadImageTask extends AsyncTask<String, Integer, Drawable []> {

    private final LoadImageObserver observer;

    public LoadImageTask(LoadImageObserver observer) {
        this.observer = observer;
    }

    public interface LoadImageObserver {

        void imageLoadProgressUpdated(Integer progress);
        void imagesLoaded(Drawable [] drawables);
    }

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

    @Override
    protected void onProgressUpdate(Integer... values) {

        if(observer != null) {
            observer.imageLoadProgressUpdated(values[0]);
        }
    }

    @Override
    protected void onPostExecute(Drawable [] drawables) {

        if(observer != null) {
            observer.imagesLoaded(drawables);
        }
    }
}
