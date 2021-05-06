package data;

import entertainment.Season;
import fileio.ActionInputData;

import java.util.ArrayList;

/**
 * Class that keeps all the information needed for serials
 */
public final class Serial extends Show {
    /**
     * Number of seasons
     */
    private final int numberOfSeasons;
    /**
     * Season list
     */
    private final ArrayList<Season> seasons;

    public Serial(final String title, final ArrayList<String> cast,
                           final ArrayList<String> genres,
                           final int numberOfSeasons, final ArrayList<Season> seasons,
                           final int year) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    @Override
    public double getAverageRating() {
        double sum = 0;

        for (Season season : seasons) {
            sum += season.getAverageRating();
        }
        return sum / (double) seasons.size();
    }

    @Override
    public int findDuration() {
        int total = 0;

        for (Season season : seasons) {
            total += season.getDuration();
        }

        return total;
    }

    @Override
    public void rate(final ActionInputData action, final User user) {
        Season season = seasons.get(action.getSeasonNumber() - 1);
        season.getRatings().add(action.getGrade());
        season.getUsersThatRated().add(user);

        /* increment the number of time the user gave a rating */
        user.setNumberOfRatingsGiven(user.getNumberOfRatingsGiven() + 1);
    }

    @Override
    public boolean checkIfUserRated(final int seasonNumber, final User user) {
        return seasons.get(seasonNumber - 1).getUsersThatRated().contains(user);
    }

    @Override
    public String toString() {
        return "SerialInputData{" + " title= "
                + super.getTitle() + " " + " year= "
                + super.getYear() + " cast {"
                + super.getCast() + " }\n" + " genres {"
                + super.getGenres() + " }\n "
                + " numberSeason= " + numberOfSeasons
                + ", seasons=" + seasons + "\n\n" + '}';
    }
}
