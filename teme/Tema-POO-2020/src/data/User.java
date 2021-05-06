package data;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class that keeps all the information needed for users
 */
public final class User {
    /**
     * User's username
     */
    private final String username;
    /**
     * Subscription Type
     */
    private final String subscriptionType;
    /**
     * The history of the movies seen
     */
    private final Map<String, Integer> history;
    /**
     * Movies added to favorites
     */
    private final ArrayList<String> favoriteMovies;
    /**
     * Total number this user ratings
     */
    private int numberOfRatingsGiven;

    public User(final String username, final String subscriptionType,
                         final Map<String, Integer> history,
                         final ArrayList<String> favoriteMovies) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.favoriteMovies = favoriteMovies;
        this.history = history;
    }

    /**
     * Adds the given title to users favorites list
     * @param title of the video
     */
    public void addToFavorite(final String title) {
        favoriteMovies.add(title);
    }

    /**
     * Adds the given title to history
     * @param title of the video
     */
    public void addToHistory(final String title) {
        history.merge(title, 1, Integer::sum);
    }

    public void setNumberOfRatingsGiven(final int numberOfRatingsGiven) {
        this.numberOfRatingsGiven = numberOfRatingsGiven;
    }

    public int getNumberOfRatingsGiven() {
        return numberOfRatingsGiven;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    @Override
    public String toString() {
        return "UserInputData{" + "username='"
                + username + '\'' + ", subscriptionType='"
                + subscriptionType + '\'' + ", history="
                + history + ", favoriteMovies="
                + favoriteMovies + '}';
    }
}
