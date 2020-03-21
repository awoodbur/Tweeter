package edu.byu.cs.tweeter.client.net;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 */
public class ServerFacade {

    // TODO: Set this the the invoke URL of your API. Find it by going to your API in AWS, clicking
    //  on stages in the right-side menu, and clicking on the stage you deployed your API to.
    private static final String SERVER_URL = "Insert your API invoke URL here";

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the followees.
     */
    public FeedResponse getFeed(FeedRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, FeedResponse.class);
    }

    public FollowersResponse getFollowers(FollowersRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, FollowersResponse.class);
    }

    public FollowingResponse getFollowees(FollowingRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, FollowingResponse.class);
    }

    public StoryResponse getStory(StoryRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, StoryResponse.class);
    }

    public SignUpResponse signUp(SignUpRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, SignUpResponse.class);
    }

    public SignInResponse signIn(SignInRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, SignInResponse.class);
    }

    public SignOutResponse signOut(SignOutRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, SignOutResponse.class);
    }

    public ShareTweetResponse shareTweet(ShareTweetRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, ShareTweetResponse.class);
    }

    public FollowUserResponse followUser(FollowUserRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, FollowUserResponse.class);
    }

    public UnfollowUserResponse unfollowUser(UnfollowUserRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, UnfollowUserResponse.class);
    }

    public GetUserResponse getUser(GetUserRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, GetUserResponse.class);
    }

    public CheckFollowResponse checkFollow(CheckFollowRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, CheckFollowResponse.class);
    }

    /////////////////////////
    // Old Code
    ////////////////

//    private static List<Tweet> allTweets;
//    private static Map<User, List<User>> allFollowing;
//    private static Map<User, List<User>> allFollowers;
//    private static Map<User, String> allUsers;
//    private static boolean initialized;
//
//    private static final String HASH_ALGORITHM = "SHA-256";
//
//    private static User kirk;
//    private static User spock;
//    private static User bones;
//    private static User sulu;
//    private static User uhura;
//    private static User scotty;
//    private static User chekov;
//

//
//    public User getUserByAlias(String alias) {
//        initializeAll();
//
//        for (Map.Entry<User, String> entry : allUsers.entrySet()) {
//            if (entry.getKey().getAlias().equals(alias)) {
//                return entry.getKey();
//            }
//        }
//
//        return null;
//    }
//
//    public boolean doesUserFollowUser(User user1, User user2) {
//        initializeAll();
//
//        List<User> followers = allFollowing.get(user1);
//        if (followers != null) {
//            for (User user : followers) {
//                if (user.equals(user2)) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//
//    private void sortTweets() {
//        Collections.sort(allTweets, Collections.<Tweet>reverseOrder());
//    }
//
//    private void initializeAll() {
//        if (!initialized) {
//            initializeUsers();
//            initializeTweets();
//            initializeFollowers();
//            initializeFollowing();
//
//            initialized = true;
//        }
//    }
//
//    private void initializeUsers() {
//        allUsers = new HashMap<>();
//
//        kirk = new User("James", "Kirk", "kirk", "https://www.writeups.org/wp-content/uploads/James-Tiberius-Kirk-Star-Trek-William-Shatner.jpg");
//        spock = new User("S'chn", "Spock", "spock", "https://www.writeups.org/wp-content/uploads/Spock-Star-Trek-Leonard-Nimoy.jpg");
//        bones = new User("Leonard", "McCoy", "bones", "https://www.writeups.org/wp-content/uploads/Leonard-Bones-McCoy-Star-Trek-DeForest-Kelley.jpg");
//        sulu = new User("Hikaru", "Sulu", "sulu", "https://vignette.wikia.nocookie.net/memoryalpha/images/1/16/Hikaru_Sulu%2C_2266.jpg/revision/latest?cb=20110417163244&path-prefix=en");
//        uhura = new User("Samara", "Uhura", "uhura", "https://www.startrek.com/sites/default/files/styles/content_full/public/images/2019-07/b8b12f949378552c21f28deff8ba8eb6.jpg?itok=jP0TJtmk");
//        scotty = new User("James", "Montgomery", "scotty", "https://www.startrek.com/sites/default/files/styles/content_full/public/images/2019-07/51425b752a0b402ed3effc83fc4bbb74.jpg?itok=ogw5GJPk");
//        chekov = new User("Pavel", "Chekov", "chekov", "https://vignette.wikia.nocookie.net/memoryalpha/images/b/b2/Pavel_Chekov%2C_2268.jpg/revision/latest?cb=20090225005414&path-prefix=en");
//
//        String password = encrpytPassword("password");
//
//        allUsers.put(kirk, password);
//        allUsers.put(spock, password);
//        allUsers.put(bones, password);
//        allUsers.put(sulu, password);
//        allUsers.put(uhura, password);
//        allUsers.put(scotty, password);
//        allUsers.put(chekov, password);
//    }
//
//    private void initializeTweets() {
//        allTweets = new ArrayList<>();
//
//        for (Map.Entry<User, String> entry : allUsers.entrySet()) {
//            User user = entry.getKey();
//            for (int i = 0; i < 25; i++) {
//                allTweets.add(new Tweet(user, user.getAlias() + " here with tweet #" + (i+1)));
//            }
//        }
//    }
//
//    private void initializeFollowers() {
//        allFollowers = new HashMap<>();
//
//        allFollowers.put(kirk, new ArrayList<>(Arrays.asList(spock, bones, sulu, uhura, scotty, chekov)));
//        allFollowers.put(spock, new ArrayList<>(Arrays.asList(kirk, bones, uhura)));
//        allFollowers.put(bones, new ArrayList<>(Arrays.asList(kirk, scotty)));
//        allFollowers.put(sulu, new ArrayList<>(Arrays.asList(bones, chekov)));
//        allFollowers.put(uhura, new ArrayList<>(Arrays.asList(spock, bones)));
//        allFollowers.put(scotty, new ArrayList<>(Arrays.asList(kirk, bones, chekov)));
//        allFollowers.put(chekov, new ArrayList<>(Arrays.asList(bones, sulu, scotty)));
//
//    }
//
//    private void initializeFollowing() {
//        allFollowing = new HashMap<>();
//
//        allFollowing.put(kirk, new ArrayList<>(Arrays.asList(spock, bones, scotty)));
//        allFollowing.put(spock, new ArrayList<>(Arrays.asList(kirk, uhura)));
//        allFollowing.put(bones, new ArrayList<>(Arrays.asList(kirk, spock, sulu, uhura, scotty, chekov)));
//        allFollowing.put(sulu, new ArrayList<>(Arrays.asList(kirk, chekov)));
//        allFollowing.put(uhura, new ArrayList<>(Arrays.asList(kirk, spock)));
//        allFollowing.put(scotty, new ArrayList<>(Arrays.asList(kirk, bones, chekov)));
//        allFollowing.put(chekov, new ArrayList<>(Arrays.asList(kirk, sulu, scotty)));
//    }

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
}
