package edu.byu.cs.tweeter.view.cache;

import edu.byu.cs.tweeter.view.android.domain.User;

public class DataCache {

    private static DataCache instance;

    private User user;

    public static DataCache getInstance() {
        if(instance == null) {
            instance = new DataCache();
        }

        return instance;
    }

    private DataCache() {}


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
