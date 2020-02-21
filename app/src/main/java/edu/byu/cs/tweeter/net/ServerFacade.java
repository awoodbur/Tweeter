package edu.byu.cs.tweeter.net;

import android.util.Log;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.byu.cs.tweeter.BuildConfig;
import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.net.request.AuthRequest;
import edu.byu.cs.tweeter.net.request.FeedRequest;
import edu.byu.cs.tweeter.net.request.FollowersRequest;
import edu.byu.cs.tweeter.net.request.FollowingRequest;
import edu.byu.cs.tweeter.net.request.StoryRequest;
import edu.byu.cs.tweeter.net.response.FeedResponse;
import edu.byu.cs.tweeter.net.response.FollowersResponse;
import edu.byu.cs.tweeter.net.response.FollowingResponse;
import edu.byu.cs.tweeter.net.response.AuthResponse;
import edu.byu.cs.tweeter.net.response.StoryResponse;
import edu.byu.cs.tweeter.net.response.Response;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 */
public class ServerFacade {

    private static List<Tweet> allTweets;
    private static Map<User, List<User>> allFollowing;
    private static Map<User, List<User>> allFollowers;
    private static Map<User, String> allUsers;
    private static boolean initialized;

    private static final String HASH_ALGORITHM = "SHA-256";

    private static User kirk;
    private static User spock;
    private static User bones;
    private static User sulu;
    private static User uhura;
    private static User scotty;
    private static User chekov;

    public AuthResponse signIn(AuthRequest request) {
        initializeAll();

        for (Map.Entry<User, String> entry : allUsers.entrySet()) {
            User user = entry.getKey();
            if (user.getAlias().equals(request.getAlias())) {
                if (entry.getValue().equals(request.getPassword())) {
                    return new AuthResponse(user);
                } else {
                    return new AuthResponse("Invalid password");
                }
            }
        }
        return new AuthResponse("User not found");
    }

    public AuthResponse signUp(AuthRequest request) {
        initializeAll();

        User user = new User(request.getFirstName(), request.getLastName(), request.getAlias(), request.getImageURL());
        String pass = allUsers.get(user);
        if (pass != null && !pass.isEmpty()) {
            return new AuthResponse("User already exists");
        } else {
            allUsers.put(user, request.getPassword());
            return new AuthResponse(user);
        }
    }

    public User getUserByAlias(String alias) {
        initializeAll();

        for (Map.Entry<User, String> entry : allUsers.entrySet()) {
            if (entry.getKey().getAlias().equals(alias)) {
                return entry.getKey();
            }
        }

        return null;
    }

    public boolean doesUserFollowUser(User user1, User user2) {
        initializeAll();

        List<User> followers = allFollowing.get(user1);
        if (followers != null) {
            for (User user : followers) {
                if (user.equals(user2)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Response shareTweet(Tweet tweet) {
        initializeAll();

        allTweets.add(tweet);
        return new Response(true, "Tweet shared successfully.");
    }

    public Response followUser(Follow follow) {
        initializeAll();

        List<User> following = allFollowing.get(follow.getFollower());
        if (following != null) {
            following.add(follow.getFollowee());
        } else {
            following = new ArrayList<>();
            following.add(follow.getFollowee());
            allFollowing.put(follow.getFollower(), following);
        }

        List<User> followers = allFollowers.get(follow.getFollowee());
        if (followers != null) {
            followers.add(follow.getFollower());
        } else {
            followers = new ArrayList<>();
            followers.add(follow.getFollower());
            allFollowers.put(follow.getFollowee(), followers);
        }

        return new Response(true, "User followed successfully");
    }

    public Response unfollowUser(Follow follow) {
        initializeAll();

        List<User> following = allFollowing.get(follow.getFollower());
        if (following != null) {
            following.remove(follow.getFollowee());
        }

        List<User> followers = allFollowers.get(follow.getFollowee());
        if (followers != null) {
            followers.remove(follow.getFollower());
        }

        return new Response(true, "User unfollowed successfully");
    }

    public StoryResponse getStory(StoryRequest request) {

        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError("Invalid limit");
            }

            if(request.getUser() == null) {
                throw new AssertionError("Null user");
            }
        }

        initializeAll();
        sortTweets();

        List<Tweet> responseTweets = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if (request.getLimit() > 0) {
            int tweetIndex = getTweetsStartingIndex(request.getLastTweet());

            int limitCounter = 0;
            while (tweetIndex < allTweets.size() && limitCounter < request.getLimit()) {
                Tweet tweet = allTweets.get(tweetIndex);
                if (tweet.getAuthor().equals(request.getUser())) {
                    responseTweets.add(tweet);
                    limitCounter++;
                }
                tweetIndex++;
            }

            hasMorePages = tweetIndex < allTweets.size();
        }

        return new StoryResponse(responseTweets, hasMorePages);
    }

    public FeedResponse getFeed(FeedRequest request) {

        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError("Invalid limit");
            }

            if(request.getUser() == null) {
                throw new AssertionError("Null user");
            }
        }

        initializeAll();
        sortTweets();

        List<User> following = allFollowing.get(request.getUser());
        List<Tweet> responseTweets = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if (request.getLimit() > 0 && following != null) {
            int tweetIndex = getTweetsStartingIndex(request.getLastTweet());

            int limitCounter = 0;
            while (tweetIndex < allTweets.size() && limitCounter < request.getLimit()) {
                Tweet tweet = allTweets.get(tweetIndex);
                for (User user : following) {
                    if (tweet.getAuthor().equals(user)) {
                        responseTweets.add(tweet);
                        limitCounter++;
                        break;
                    }
                }

                tweetIndex++;
            }

            hasMorePages = tweetIndex < allTweets.size();
        }

        return new FeedResponse(responseTweets, hasMorePages);
    }

    public FollowersResponse getFollowers(FollowersRequest request) {

        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError("Invalid limit");
            }

            if(request.getFollowee() == null) {
                throw new AssertionError("Null user");
            }
        }

        initializeAll();

        List<User> followers = allFollowers.get(request.getFollowee());
        List<User> responseFollowers = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if (request.getLimit() > 0) {
            if (followers != null) {
                int followersIndex = getFollowStartingIndex(request.getLastFollower(), followers);

                for(int limitCounter = 0; followersIndex < followers.size() && limitCounter < request.getLimit(); followersIndex++, limitCounter++) {
                    responseFollowers.add(followers.get(followersIndex));
                }

                hasMorePages = followersIndex < followers.size();
            }
        }

        return new FollowersResponse(responseFollowers, hasMorePages);
    }

    /**
     * Returns the allUsers that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. The current implementation
     * returns generated data and doesn't actually make a network request.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the followees.
     */
    public FollowingResponse getFollowing(FollowingRequest request) {

        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError("Invalid limit");
            }

            if(request.getFollower() == null) {
                throw new AssertionError("Null user");
            }
        }

        initializeAll();

        List<User> followees = allFollowing.get(request.getFollower());
        List<User> responseFollowees = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            if (followees != null) {
                int followeesIndex = getFollowStartingIndex(request.getLastFollowee(), followees);

                for(int limitCounter = 0; followeesIndex < followees.size() && limitCounter < request.getLimit(); followeesIndex++, limitCounter++) {
                    responseFollowees.add(followees.get(followeesIndex));
                }

                hasMorePages = followeesIndex < followees.size();
            }
        }

        return new FollowingResponse(responseFollowees, hasMorePages);
    }

    private int getTweetsStartingIndex(Tweet lastTweet) {

        int tweetIndex = 0;

        if (lastTweet != null) {
            for (int i = 0; i < allTweets.size(); ++i) {
                if (lastTweet.equals(allTweets.get(i))) {
                    tweetIndex = i + 1;
                }
            }
        }

        return tweetIndex;
    }

    /**
     * Determines the index for the first followee in the specified 'allFollows' list that should
     * be returned in the current request. This will be the index of the next followee after the
     * specified 'lastFollow'.
     *
     * @param lastFollow the last followee that was returned in the previous request or null if
     *                     there was no previous request.
     * @param allFollows the generated list of followees from which we are returning paged results.
     * @return the index of the first followee to be returned.
     */
    private int getFollowStartingIndex(User lastFollow, List<User> allFollows) {

        int followsIndex = 0;

        if(lastFollow != null) {
            // This is a paged request for something after the first page. Find the first item
            // we should return
            for (int i = 0; i < allFollows.size(); i++) {
                if(lastFollow.equals(allFollows.get(i))) {
                    // We found the index of the last item returned last time. Increment to get
                    // to the first one we should return
                    followsIndex = i + 1;
                }
            }
        }

        return followsIndex;
    }

    private void sortTweets() {
        Collections.sort(allTweets, Collections.<Tweet>reverseOrder());
    }

    private void initializeAll() {
        if (!initialized) {
            initializeUsers();
            initializeTweets();
            initializeFollowers();
            initializeFollowing();

            initialized = true;
        }
    }

    private void initializeUsers() {
        allUsers = new HashMap<>();

        kirk = new User("James", "Kirk", "kirk", "https://www.writeups.org/wp-content/uploads/James-Tiberius-Kirk-Star-Trek-William-Shatner.jpg");
        spock = new User("S'chn", "Spock", "spock", "https://www.writeups.org/wp-content/uploads/Spock-Star-Trek-Leonard-Nimoy.jpg");
        bones = new User("Leonard", "McCoy", "bones", "https://www.writeups.org/wp-content/uploads/Leonard-Bones-McCoy-Star-Trek-DeForest-Kelley.jpg");
        sulu = new User("Hikaru", "Sulu", "sulu", "https://vignette.wikia.nocookie.net/memoryalpha/images/1/16/Hikaru_Sulu%2C_2266.jpg/revision/latest?cb=20110417163244&path-prefix=en");
        uhura = new User("Samara", "Uhura", "uhura", "https://www.startrek.com/sites/default/files/styles/content_full/public/images/2019-07/b8b12f949378552c21f28deff8ba8eb6.jpg?itok=jP0TJtmk");
        scotty = new User("James", "Montgomery", "scotty", "https://www.startrek.com/sites/default/files/styles/content_full/public/images/2019-07/51425b752a0b402ed3effc83fc4bbb74.jpg?itok=ogw5GJPk");
        chekov = new User("Pavel", "Chekov", "chekov", "https://vignette.wikia.nocookie.net/memoryalpha/images/b/b2/Pavel_Chekov%2C_2268.jpg/revision/latest?cb=20090225005414&path-prefix=en");

        String password = encrpytPassword("password");

        allUsers.put(kirk, password);
        allUsers.put(spock, password);
        allUsers.put(bones, password);
        allUsers.put(sulu, password);
        allUsers.put(uhura, password);
        allUsers.put(scotty, password);
        allUsers.put(chekov, password);
    }

    private String encrpytPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            messageDigest.update(password.getBytes());
            return new String(messageDigest.digest());
        }
        catch (java.security.NoSuchAlgorithmException e) {
            // Never
        }
        return null;
    }

    private void initializeTweets() {
        allTweets = new ArrayList<>();

        for (Map.Entry<User, String> entry : allUsers.entrySet()) {
            User user = entry.getKey();
            for (int i = 0; i < 25; i++) {
                allTweets.add(new Tweet(user, user.getAlias() + " here with tweet #" + (i+1)));
            }
        }
    }

    private void initializeFollowers() {
        allFollowers = new HashMap<>();

        allFollowers.put(kirk, new ArrayList<>(Arrays.asList(spock, bones, sulu, uhura, scotty, chekov)));
        allFollowers.put(spock, new ArrayList<>(Arrays.asList(kirk, bones, uhura)));
        allFollowers.put(bones, new ArrayList<>(Arrays.asList(kirk, scotty)));
        allFollowers.put(sulu, new ArrayList<>(Arrays.asList(bones, chekov)));
        allFollowers.put(uhura, new ArrayList<>(Arrays.asList(spock, bones)));
        allFollowers.put(scotty, new ArrayList<>(Arrays.asList(kirk, bones, chekov)));
        allFollowers.put(chekov, new ArrayList<>(Arrays.asList(bones, sulu, scotty)));

    }

    private void initializeFollowing() {
        allFollowing = new HashMap<>();

        allFollowing.put(kirk, new ArrayList<>(Arrays.asList(spock, bones, scotty)));
        allFollowing.put(spock, new ArrayList<>(Arrays.asList(kirk, uhura)));
        allFollowing.put(bones, new ArrayList<>(Arrays.asList(kirk, spock, sulu, uhura, scotty, chekov)));
        allFollowing.put(sulu, new ArrayList<>(Arrays.asList(kirk, chekov)));
        allFollowing.put(uhura, new ArrayList<>(Arrays.asList(kirk, spock)));
        allFollowing.put(scotty, new ArrayList<>(Arrays.asList(kirk, bones, chekov)));
        allFollowing.put(chekov, new ArrayList<>(Arrays.asList(kirk, sulu, scotty)));
    }

//    /**
//     * Generates the followee data.
//     */
//    private Map<User, List<User>> initializeFollowees() {
//
//        Map<User, List<User>> allFollowing = new HashMap<>();
//
//        List<Follow> follows = getFollowGenerator().generateUsersAndFollows(100,
//                0, 50, FollowGenerator.Sort.FOLLOWER_FOLLOWEE);
//
//        // Populate a map of followees, keyed by follower so we can easily manage followee requests
//        for(Follow follow : follows) {
//            List<User> followees = allFollowing.get(follow.getFollower());
//
//            if(followees == null) {
//                followees = new ArrayList<>();
//                allFollowing.put(follow.getFollower(), followees);
//            }
//
//            followees.add(follow.getFollowee());
//        }
//
//        return allFollowing;
//    }

//    /**
//     * Returns an instance of FollowGenerator that can be used to generate Follow data. This is
//     * written as a separate method to allow mocking of the generator.
//     *
//     * @return the generator.
//     */
//    FollowGenerator getFollowGenerator() {
//        return FollowGenerator.getInstance();
//    }
}
