package edu.byu.cs.tweeter.server.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;

public class Database {

    private static Database instance;

    private Map<User, String> usersTable;
    private List<Tweet> tweetsTable;
    private Map<User, List<User>> followersTable;
    private Map<User, List<User>> followingTable;
    private Map<User, String> authTable;

    private User kirk;
    private User spock;
    private User bones;
    private User sulu;
    private User uhura;
    private User scotty;
    private User chekov;

    private User test1;
    private User test2;
    private User test3;
    private User test4;
    private User test5;
    private User test6;
    private User test7;
    private User test8;
    private User test9;

    private final String DEFAULT_PASSWORD = "password";

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private Database() {
        resetDatabase();
    }

    public void resetDatabase() {
        initializeUsers();
        initializeTweets();
        initializeFollowers();
        initializeFollowing();
        initializeAuth();
    }

    private void initializeUsers() {
        usersTable = new HashMap<>();

        kirk = new User("James", "Kirk", "kirk", "https://www.writeups.org/wp-content/uploads/James-Tiberius-Kirk-Star-Trek-William-Shatner.jpg");
        spock = new User("S'chn", "Spock", "spock", "https://www.writeups.org/wp-content/uploads/Spock-Star-Trek-Leonard-Nimoy.jpg");
        bones = new User("Leonard", "McCoy", "bones", "https://www.writeups.org/wp-content/uploads/Leonard-Bones-McCoy-Star-Trek-DeForest-Kelley.jpg");
        sulu = new User("Hikaru", "Sulu", "sulu", "https://vignette.wikia.nocookie.net/memoryalpha/images/1/16/Hikaru_Sulu%2C_2266.jpg/revision/latest?cb=20110417163244&path-prefix=en");
        uhura = new User("Samara", "Uhura", "uhura", "https://www.startrek.com/sites/default/files/styles/content_full/public/images/2019-07/b8b12f949378552c21f28deff8ba8eb6.jpg?itok=jP0TJtmk");
        scotty = new User("James", "Montgomery", "scotty", "https://www.startrek.com/sites/default/files/styles/content_full/public/images/2019-07/51425b752a0b402ed3effc83fc4bbb74.jpg?itok=ogw5GJPk");
        chekov = new User("Pavel", "Chekov", "chekov", "https://vignette.wikia.nocookie.net/memoryalpha/images/b/b2/Pavel_Chekov%2C_2268.jpg/revision/latest?cb=20090225005414&path-prefix=en");

        test1 = new User("test1", "test1", "test1", "");
        test2 = new User("test2", "test2", "test2", "");
        test3 = new User("test3", "test3", "test3", "");
        test4 = new User("test4", "test4", "test4", "");
        test5 = new User("test5", "test5", "test5", "");
        test6 = new User("test6", "test6", "test6", "");
        test7 = new User("test7", "test7", "test7", "");
        test8 = new User("test8", "test8", "test8", "");
        test9 = new User("test9", "test9", "test9", "");


        usersTable.put(kirk, DEFAULT_PASSWORD);
        usersTable.put(spock, DEFAULT_PASSWORD);
        usersTable.put(bones, DEFAULT_PASSWORD);
        usersTable.put(sulu, DEFAULT_PASSWORD);
        usersTable.put(uhura, DEFAULT_PASSWORD);
        usersTable.put(scotty, DEFAULT_PASSWORD);
        usersTable.put(chekov, DEFAULT_PASSWORD);

        usersTable.put(test1, DEFAULT_PASSWORD);
        usersTable.put(test2, DEFAULT_PASSWORD);
        usersTable.put(test3, DEFAULT_PASSWORD);
        usersTable.put(test4, DEFAULT_PASSWORD);
        usersTable.put(test5, DEFAULT_PASSWORD);
        usersTable.put(test6, DEFAULT_PASSWORD);
        usersTable.put(test7, DEFAULT_PASSWORD);
        usersTable.put(test8, DEFAULT_PASSWORD);
        usersTable.put(test9, DEFAULT_PASSWORD);
    }

    private void initializeTweets() {
        tweetsTable = new ArrayList<>();

        for (Map.Entry<User, String> entry : usersTable.entrySet()) {
            User user = entry.getKey();
            for (int i = 0; i < 25; i++) {
                tweetsTable.add(new Tweet(user, user.getAlias() + " here with tweet #" + (i+1)));
            }
        }
    }

    private void initializeFollowers() {
        followersTable = new HashMap<>();

        followersTable.put(kirk, new ArrayList<>(Arrays.asList(spock, bones, sulu, uhura, scotty, chekov, test1, test2, test3, test4, test5, test6, test7, test8, test9)));
        followersTable.put(spock, new ArrayList<>(Arrays.asList(kirk, bones, uhura)));
        followersTable.put(bones, new ArrayList<>(Arrays.asList(kirk, scotty)));
        followersTable.put(sulu, new ArrayList<>(Arrays.asList(bones, chekov)));
        followersTable.put(uhura, new ArrayList<>(Arrays.asList(spock, bones)));
        followersTable.put(scotty, new ArrayList<>(Arrays.asList(kirk, bones, chekov)));
        followersTable.put(chekov, new ArrayList<>(Arrays.asList(bones, sulu, scotty)));

    }

    private void initializeFollowing() {
        followingTable = new HashMap<>();

        followingTable.put(kirk, new ArrayList<>(Arrays.asList(spock, bones, scotty)));
        followingTable.put(spock, new ArrayList<>(Arrays.asList(kirk, uhura)));
        followingTable.put(bones, new ArrayList<>(Arrays.asList(kirk, spock, sulu, uhura, scotty, chekov, test1, test2, test3, test4, test5, test6, test7, test8, test9)));
        followingTable.put(sulu, new ArrayList<>(Arrays.asList(kirk, chekov)));
        followingTable.put(uhura, new ArrayList<>(Arrays.asList(kirk, spock)));
        followingTable.put(scotty, new ArrayList<>(Arrays.asList(kirk, bones, chekov)));
        followingTable.put(chekov, new ArrayList<>(Arrays.asList(kirk, sulu, scotty)));
    }

    private void initializeAuth() {
        authTable = new HashMap<>();
    }

    /////////////////
    // Auth table
    /////////

    public void authUser(User user, String token) {
        authTable.put(user, token);
    }

    public void deauthUser(User user) {
        authTable.remove(user);
    }

    /////////////////
    // Tweets table
    /////////

    public void addTweet(Tweet tweet) {
        tweetsTable.add(tweet);
    }

    public int getNumTweets() {
        return tweetsTable.size();
    }

    public Tweet getTweet(int idx) {
        return tweetsTable.get(idx);
    }

    public int getTweetIndex(Tweet tweet) {
        if (tweet == null) {
            return 0;
        }
        int idx = tweetsTable.indexOf(tweet);
        if (idx == -1) {
            return 0;
        } else {
            return idx + 1;
        }
    }

    public void sortTweets() {
        Collections.sort(tweetsTable);
    }

    /////////////////
    // Following table
    /////////

    public List<User> getUserFollowing(User user) {
        return followingTable.get(user);
    }

    public int getFolloweeIndex(User user, User followee) {
        if (followee == null) {
            return 0;
        }
        int idx = followingTable.get(user).indexOf(followee);
        if (idx == -1) {
            return 0;
        } else {
            return idx + 1;
        }
    }

    public void addUserToFollowing(User user, User userToFollow) {
        List<User> following = followingTable.get(userToFollow);
        if (following != null) {
            following.add(user);
        } else {
            following = new ArrayList<>();
            following.add(user);
            followingTable.put(userToFollow, following);
        }
    }

    public void removeUserFromFollowing(User user, User userToUnfollow) {
        List<User> following = followingTable.get(userToUnfollow);
        if (following != null) {
            following.remove(user);
        }
    }

    public boolean doesUserFollowUser(User userFollowing, User userFollowed) {
        List<User> following = followingTable.get(userFollowing);
        if (following != null) {
            return following.contains(userFollowed);
        }
        return false;
    }

    /////////////////
    // Followers table
    /////////

    public List<User> getUserFollowers(User user) {
        return followersTable.get(user);
    }

    public int getFollowerIndex(User user, User follower) {
        if (follower == null) {
            return 0;
        }
        int idx = followersTable.get(user).indexOf(follower);
        if (idx == -1) {
            return 0;
        } else {
            return idx + 1;
        }
    }

    public void addFolloweeToUser(User followeeToAdd, User user) {
        List<User> followers = followersTable.get(user);
        if (followers != null) {
            followers.add(followeeToAdd);
        } else {
            followers = new ArrayList<>();
            followers.add(followeeToAdd);
            followersTable.put(user, followers);
        }
    }

    public void removeFolloweeFromUser(User followeeToRemove, User user) {
        List<User> followers = followersTable.get(user);
        if (followers != null) {
            followers.remove(followeeToRemove);
        }
    }

    /////////////////
    // Users table
    /////////

    public User getUser(String alias) {
        for (Map.Entry<User, String> entry : usersTable.entrySet()) {
            User user = entry.getKey();
            if (user.getAlias().equals(alias)) {
                return user;
            }
        }
        return null;
    }

    public String getUserPassword(User user) {
        return usersTable.get(user);
    }

    public void addUser(User user, String password) {
        usersTable.put(user, password);
    }
}
