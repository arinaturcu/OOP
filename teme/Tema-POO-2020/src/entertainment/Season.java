package entertainment;

import data.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Information about a season of a tv show
 * <p>
 * DO NOT MODIFY
 */
public final class Season {
    /**
     * Number of current season
     */
    private final int currentSeason;
    /**
     * Duration in minutes of a season
     */
    private int duration;
    /**
     * List of ratings for each season
     */
    private List<Double> ratings;
    /**
     * List of users that rated
     */
    private final List<User> usersThatRated;

    public Season(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.ratings = new ArrayList<>();
        this.usersThatRated = new ArrayList<>();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final List<Double> ratings) {
        this.ratings = ratings;
    }

    public List<User> getUsersThatRated() {
        return usersThatRated;
    }

    /**
     * Computes the average rating from all the users
     * @return the average rating
     */
    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0;
        }
        double sum = 0;

        for (double rating : ratings) {
            sum += rating;
        }
        return sum / (double) ratings.size();
    }

    @Override
    public String toString() {
        return "Episode{"
                + "currentSeason="
                + currentSeason
                + ", duration="
                + duration
                + '}';
    }
}

