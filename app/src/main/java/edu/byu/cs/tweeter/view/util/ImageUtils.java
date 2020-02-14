package edu.byu.cs.tweeter.view.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Contains utility methods for working with images in an Android application.
 */
public class ImageUtils {

    /**
     * Reads image data from the specified urlString and creates an Android Drawable object.
     *
     * @param urlString the url where the image data resides.
     * @return the image.
     * @throws IOException if an I/O error occurs while attempting to read the image data.
     */
    public static Drawable drawableFromUrl(String urlString) throws IOException {

        URL url = new URL(urlString);
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap == null ? null : new BitmapDrawable(Resources.getSystem(), bitmap);
            } else {
                throw new IOException("Unable to read from url. Response code: " + connection.getResponseCode());
            }
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}
