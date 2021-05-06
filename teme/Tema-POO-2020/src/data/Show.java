package data;

import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Show class represents a video i.e a movie or a serial.
 * It keeps all the common features of movies and serials
 */
public abstract class Show {
    /**
     * Show's title
     */
    private final String title;
    /**
     * The year the show was released
     */
    private final int year;
    /**
     * Show casting
     */
    private final ArrayList<String> cast;
    /**
     * Show genres
     */
    private final ArrayList<String> genres;
    /**
     * Number of appearances of the show in the users favorite lists
     */
    private int numberInFavorite;
    /**
     * Number of views
     */
    private int numberOfViews;

    public Show(final String title, final int year,
                     final ArrayList<String> cast, final ArrayList<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
    }

    /**
     * Computes the average rating of the show
     * @return a double number representing the average rating
     */
    public abstract double getAverageRating();

    /**
     * Finds the total duration of the show
     * @return the duration
     */
    public abstract int findDuration();

    /**
     * Rate a show
     * @param action the action executing
     * @param user the user that gives the rating
     */
    public abstract void rate(ActionInputData action, User user);

    /**
     * Checks if a user already rated the show
     * @param user the user that could have rated
     * @return true if user already rated
     */
    public abstract boolean checkIfUserRated(int seasonNumber, User user);

    /**
     * Counts the total number of views
     * @param users the list of users
     */
    public void countViews(final List<User> users) {
        int count = 0;
        for (User user : users) {
            if (user.getHistory().containsKey(this.title)) {
                count += user.getHistory().get(this.title);
            }
        }
        this.numberOfViews = count;
    }

    /**
     * Computes the number of appearances of the show in the users
     * favorite lists
     * @param users users
     */
    public final void countNumberInFavorite(final List<User> users) {
        int count = 0;
        for (User user : users) {
            if (user.getFavoriteMovies().contains(this.title)) {
                count++;
            }
        }

        this.numberInFavorite = count;
    }

    /**
     * Counts the number of appearances of the show in the users
     * favorite lists
     * @return the total number
     */
    public final int getNumberInFavorite() {
        return numberInFavorite;
    }

    public final int getNumberOfViews() {
        return numberOfViews;
    }

    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }

    public final ArrayList<String> getCast() {
        return cast;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }
}
