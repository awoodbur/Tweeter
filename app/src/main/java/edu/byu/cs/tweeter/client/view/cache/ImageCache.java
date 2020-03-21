package edu.byu.cs.tweeter.client.view.cache;

import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.Map;

import edu.byu.cs.tweeter.model.domain.User;

/**
 * A cache for the user images.
 */
public class ImageCache {

    /**
     * The singleton instance.
     */
    private static ImageCache instance;

    /**
     * The collection that holds the cached images.
     */
    private final Map<User, Drawable> images = new HashMap<>();

    /**
     * Return the singleton instance of this class.
     *
     * @return the instance.
     */
    public static ImageCache getInstance() {
        if(instance == null) {
            instance = new ImageCache();
        }

        return instance;
    }

    /**
     * A private constructor created to ensure that this class is a singleton (i.e. that it
     * cannot be instantiated by external classes).
     */
    private ImageCache() {}

    /**
     * Returns the image for the specified user.
     *
     * @param user the user whose image is to be returned.
     * @return the image.
     */
    public Drawable getImageDrawable(User user) {
        return images.get(user);
    }

    /**
     * Caches the specified image for the specified user.
     *
     * @param user the user whose image is to be cached.
     * @param drawable the image.
     */
    public void cacheImage(User user, Drawable drawable) {
        images.put(user, drawable);
    }
}
