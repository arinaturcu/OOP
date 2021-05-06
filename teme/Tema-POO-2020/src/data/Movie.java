package data;

import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that keeps all the information needed for movies
 */
public final class Movie extends Show {
    /**
     * Duration in minutes of a season
     */
    private final int duration;
    /**
     * Rating for the movie
     */
    private List<Double> ratings;
    /**
     * Users that rated the movie
     */
    private final List<User> usersThatRated;

    public Movie(final String title, final ArrayList<String> cast,
                          final ArrayList<String> genres, final int year,
                          final int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
        this.ratings = new ArrayList<>();
        this.usersThatRated = new ArrayList<>();
    }

    @Override
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
    public int findDuration() {
        return this.duration;
    }

    @Override
    public void rate(final ActionInputData action, final User user) {
        ratings.add(action.getGrade());
        usersThatRated.add(user);

        /* increment the number of time the user gave a rating */
        user.setNumberOfRatingsGiven(user.getNumberOfRatingsGiven() + 1);
    }

    @Override
    public boolean checkIfUserRated(final int seasonNumber, final User user) {
        return usersThatRated.contains(user);
    }

    public int getDuration() {
        return duration;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final List<Double> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }
}
