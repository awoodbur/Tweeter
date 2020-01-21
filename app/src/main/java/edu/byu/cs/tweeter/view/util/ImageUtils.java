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

public class ImageUtils {

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
