package data;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Class that keeps all the information needed for actors
 */
public final class Actor {
    /**
     * actor name
     */
    private String name;
    /**
     * description of the actor's career
     */
    private String careerDescription;
    /**
     * videos starring actor
     */
    private ArrayList<String> filmography;
    /**
     * list of videos objects starring actor
     */
    private List<Show> shows;
    /**
     * awards won by the actor
     */
    private final Map<ActorsAwards, Integer> awards;

    public Actor(final String name, final String careerDescription,
                          final ArrayList<String> filmography,
                          final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.shows = new ArrayList<>();
    }

    /**
     * Finds the average rating of all the movies and serials
     * the actor played in
     * @return the average rating
     */
    public double getAverageRating() {
        double sum = 0;
        int count  = 0;

        for (Show show : shows) {
            if (show.getCast().contains(this.name) && show.getAverageRating() != 0) {
                sum += show.getAverageRating();
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return sum / (double) count;
    }

    /**
     * Sorts the actors array by the number of awards and, in case
     * of equality, sorts alphabetically
     * @param actors the array of actors
     */
    public static void sortActorsByAwards(final List<Actor> actors) {
        actors.sort(Comparator.comparing(Actor::getNumberOfAwards));

        for (int i = 0; i < actors.size() - 1; ++i) {
            int start = i;
            int end   = i;

            while (i + 1 < actors.size() && actors.get(i + 1).getNumberOfAwards()
                    == actors.get(i).getNumberOfAwards()) {
                end++;
                i++;
            }

            if (start != end) {
                actors.subList(start, end + 1).sort(Comparator.comparing(Actor::getName));
            }
        }
    }

    /**
     * Builds a list of shows starring actor
     * @param data all the data from input
     */
    public void buildShows(final Data data) {
        /* populate with movies */
        for (Movie m : data.getMovies()) {
            if (filmography.contains(m.getTitle())) {
                shows.add(m);

            }
        }
        /* populate with series */
        for (Serial s : data.getSerials()) {
            if (filmography.contains(s.getTitle())) {
                shows.add(s);
            }
        }
    }

    /**
     * Counts the number of awards
     * @return the total number of awards
     */
    public int getNumberOfAwards() {
        int total = 0;
        for (ActorsAwards award : awards.keySet()) {
            total += awards.get(award);
        }

        return total;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(final List<Show> shows) {
        this.shows = shows;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    @Override
    public String toString() {
        return "ActorInputData{"
                + "name='" + name + '\''
                + ", careerDescription='"
                + careerDescription + '\''
                + ", filmography=" + filmography + '}';
    }
}
