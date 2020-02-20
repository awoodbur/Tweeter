//package edu.byu.cs.tweeter.view.main.feed;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import edu.byu.cs.tweeter.R;
//import edu.byu.cs.tweeter.model.domain.User;
//import edu.byu.cs.tweeter.view.cache.ImageCache;
//
//public class FeedFragment extends Fragment implements FeedPresenter.View {
//
//    private static final int LOADING_DATA_VIEW = 0;
//    private static final int ITEM_VIEW = 1;
//
//    private static final int PAGE_SIZE = 10;
//
//    private FeedPresenter presenter;
//
//    private FeedRecyclerViewAdapter feedRecyclerViewAdapter;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_recycler_list, container, false);
//
//        presenter = new FeedPresenter(this);
//
//        RecyclerView feedRecyclerView = view.findViewById(R.id.recycler_view);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
//        feedRecyclerView.setLayoutManager(layoutManager);
//
//        feedRecyclerViewAdapter = new FeedRecyclerViewAdapter();
//        feedRecyclerView.setAdapter(feedRecyclerViewAdapter);
//
//        feedRecyclerView.addOnScrollListener(new FeedRecyclerViewPaginationScrollListener(layoutManager));
//
//        return view;
//    }
//
//    /**
//     * The ViewHolder for the RecyclerView that displays the Feeding data.
//     */
//    private class FeedingHolder extends RecyclerView.ViewHolder {
//
//        private final ImageView userImage;
//        private final TextView userAlias;
//        private final TextView userName;
//        private final TextView tweetDate;
//        private final TextView tweetContent;
//
//        FeedingHolder(@NonNull View itemView) {
//            super(itemView);
//
//            userImage = itemView.findViewById(R.id.recycler_item_icon);
//            userAlias = itemView.findViewById(R.id.recycler_item_alias);
//            userName = itemView.findViewById(R.id.recycler_item_name);
//            tweetDate = itemView.findViewById(R.id.recycler_item_date);
//            tweetContent = itemView.findViewById(R.id.recycler_item_content);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getContext(), "You selected '" + userName.getText() + "'.", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//        void bindUser(User user) {
//            userImage.setImageDrawable(ImageCache.getInstance().getImageDrawable(user));
//            userAlias.setText(user.getAlias());
//            userName.setText(user.getName());
//            tweetDate.setText("Aug 14");
//            tweetContent.setText("my first tweet");
//        }
//    }
//
//    /**
//     * The adapter for the RecyclerView that displays the Feeding data.
//     */
//    private class FeedRecyclerViewAdapter extends RecyclerView.Adapter<FeedHolder> implements GetFeedTask.GetFolloweesObserver {
//
//        private final List<User> users = new ArrayList<>();
//
//        private edu.byu.cs.tweeter.model.domain.User lastFollowee;
//
//        private boolean hasMorePages;
//        private boolean isLoading = false;
//
//        /**
//         * Creates an instance and loads the first page of feed data.
//         */
//        FeedRecyclerViewAdapter() {
//            loadMoreItems();
//        }
//
//        /**
//         * Adds new users to the list from which the RecyclerView retrieves the users it displays
//         * and notifies the RecyclerView that items have been added.
//         *
//         * @param newUsers the users to add.
//         */
//        void addItems(List<User> newUsers) {
//            int startInsertPosition = users.size();
//            users.addAll(newUsers);
//            this.notifyItemRangeInserted(startInsertPosition, newUsers.size());
//        }
//
//        /**
//         * Adds a single user to the list from which the RecyclerView retrieves the users it
//         * displays and notifies the RecyclerView that an item has been added.
//         *
//         * @param user the user to add.
//         */
//        void addItem(User user) {
//            users.add(user);
//            this.notifyItemInserted(users.size() - 1);
//        }
//
//        /**
//         * Removes a user from the list from which the RecyclerView retrieves the users it displays
//         * and notifies the RecyclerView that an item has been removed.
//         *
//         * @param user the user to remove.
//         */
//        void removeItem(User user) {
//            int position = users.indexOf(user);
//            users.remove(position);
//            this.notifyItemRemoved(position);
//        }
//
//        /**
//         *  Creates a view holder for a followee to be displayed in the RecyclerView or for a message
//         *  indicating that new rows are being loaded if we are waiting for rows to load.
//         *
//         * @param parent the parent view.
//         * @param viewType the type of the view (ignored in the current implementation).
//         * @return the view holder.
//         */
//        @NonNull
//        @Override
//        public FeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = LayoutInflater.from(FeedFragment.this.getContext());
//            View view;
//
//            if(viewType == LOADING_DATA_VIEW) {
//                view =layoutInflater.inflate(R.layout.loading_row, parent, false);
//
//            } else {
//                view = layoutInflater.inflate(R.layout.recycler_item, parent, false);
//            }
//
//            return new FeedHolder(view);
//        }
//
//        /**
//         * Binds the followee at the specified position unless we are currently loading new data. If
//         * we are loading new data, the display at that position will be the data loading footer.
//         *
//         * @param feedHolder the ViewHolder to which the followee should be bound.
//         * @param position the position (in the list of followees) that contains the followee to be
//         *                 bound.
//         */
//        @Override
//        public void onBindViewHolder(@NonNull FeedHolder feedHolder, int position) {
//            if(!isLoading) {
//                feedHolder.bindUser(users.get(position));
//            }
//        }
//
//        /**
//         * Returns the current number of followees available for display.
//         * @return the number of followees available for display.
//         */
//        @Override
//        public int getItemCount() {
//            return users.size();
//        }
//
//        /**
//         * Returns the type of the view that should be displayed for the item currently at the
//         * specified position.
//         *
//         * @param position the position of the items whose view type is to be returned.
//         * @return the view type.
//         */
//        @Override
//        public int getItemViewType(int position) {
//            return (position == users.size() - 1 && isLoading) ? LOADING_DATA_VIEW : ITEM_VIEW;
//        }
//
//        /**
//         * Causes the Adapter to display a loading footer and make a request to get more feed
//         * data.
//         */
//        void loadMoreItems() {
//            isLoading = true;
//            addLoadingFooter();
//
//            GetFeedTask getFeedTask = new GetFeedTask(presenter, this);
//            FeedRequest request = new FeedRequest(presenter.getCurrentUser(), PAGE_SIZE, lastFollowee);
//            getFeedTask.execute(request);
//        }
//
//        /**
//         * A callback indicating more feed data has been received. Loads the new followees
//         * and removes the loading footer.
//         *
//         * @param feedResponse the asynchronous response to the request to load more items.
//         */
//        @Override
//        public void followeesRetrieved(FeedResponse feedResponse) {
//            List<User> followees = feedResponse.getFollowees();
//
//            lastFollowee = (followees.size() > 0) ? followees.get(followees.size() -1) : null;
//            hasMorePages = feedResponse.hasMorePages();
//
//            isLoading = false;
//            removeLoadingFooter();
//            feedRecyclerViewAdapter.addItems(followees);
//        }
//
//        /**
//         * Adds a dummy user to the list of users so the RecyclerView will display a view (the
//         * loading footer view) at the bottom of the list.
//         */
//        private void addLoadingFooter() {
//            addItem(new User("Dummy", "User", ""));
//        }
//
//        /**
//         * Removes the dummy user from the list of users so the RecyclerView will stop displaying
//         * the loading footer at the bottom of the list.
//         */
//        private void removeLoadingFooter() {
//            removeItem(users.get(users.size() - 1));
//        }
//    }
//
//    /**
//     * A scroll listener that detects when the user has scrolled to the bottom of the currently
//     * available data.
//     */
//    private class FeedRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {
//
//        private final LinearLayoutManager layoutManager;
//
//        /**
//         * Creates a new instance.
//         *
//         * @param layoutManager the layout manager being used by the RecyclerView.
//         */
//        FeedRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
//            this.layoutManager = layoutManager;
//        }
//
//        /**
//         * Determines whether the user has scrolled to the bottom of the currently available data
//         * in the RecyclerView and asks the adapter to load more data if the last load request
//         * indicated that there was more data to load.
//         *
//         * @param recyclerView the RecyclerView.
//         * @param dx the amount of horizontal scroll.
//         * @param dy the amount of vertical scroll.
//         */
//        @Override
//        public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//
//            int visibleItemCount = layoutManager.getChildCount();
//            int totalItemCount = layoutManager.getItemCount();
//            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
//
//            if (!feedRecyclerViewAdapter.isLoading && feedRecyclerViewAdapter.hasMorePages) {
//                if ((visibleItemCount + firstVisibleItemPosition) >=
//                        totalItemCount && firstVisibleItemPosition >= 0) {
//                    feedRecyclerViewAdapter.loadMoreItems();
//                }
//            }
//        }
//    }
//}
