package actions;

import common.Constants;
import data.Actor;
import data.Data;
import data.Show;
import data.User;
import fileio.ActionInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Query extends Action {
    public Query(final Data data, final JSONArray arrayResult, final Writer fileWriter) {
        super(data, arrayResult, fileWriter);
    }

    /**
     * Executes the action
     * @param action parsed action
     * @throws IOException from writeFile
     */
    @Override
    public void execute(final ActionInputData action) throws IOException {
        switch (action.getObjectType()) {
            case Constants.ACTORS -> queryActors(action);
            case Constants.MOVIES, Constants.SHOWS -> queryVideos(action);
            case Constants.USERS  -> queryUsers(action);
            default -> { }
        }
    }

    private void queryActors(final ActionInputData action) throws IOException {
        /* stores all the actors in an arraylist of actors */
        List<Actor> actors = getData().getActors();

        /* finds all the shows that stare an actor by its title and put them in an array of Show */
        for (Actor a : actors) {
            a.buildShows(getData());
        }

        switch (action.getCriteria()) {
            case Constants.AVERAGE -> actorAverage(actors, action);
            case Constants.AWARDS  -> actorAwards(actors, action);
            case Constants.FILTER_DESCRIPTIONS -> actorFilterDescription(actors, action);
            default -> { }
        }
    }

    private void queryVideos(final ActionInputData action) throws IOException {
        /* puts the videos in a list of Shows */
        List<Show> videos = new ArrayList<>();

        if (action.getObjectType().equals(Constants.MOVIES)) {
            videos.addAll(getData().getMovies());
        } else if (action.getObjectType().equals(Constants.SHOWS)) {
            videos.addAll(getData().getSerials());
        }

        switch (action.getCriteria()) {
            case Constants.RATINGS     -> videosRating(action, videos);
            case Constants.FAVORITE    -> videosFavorite(action, videos);
            case Constants.LONGEST     -> videosLongest(action, videos);
            case Constants.MOST_VIEWED -> videosMostViewed(action, videos);
            default -> { }
        }
    }

    private void queryUsers(final ActionInputData action) throws IOException {
        List<User> users = new ArrayList<>(getData().getUsers());

        /* removes the user that didn't gave any rating */
        users.removeIf(user -> user.getNumberOfRatingsGiven() == 0);

        /* sorts users by the number they rated and, in case of equality, sorts by name*/
        users.sort(Comparator.comparing(User::getNumberOfRatingsGiven).
                thenComparing(User::getUsername));
        usersPutResultInFile(action, users);
    }

    /*
     * Sorts actors by their average rating
     */
    private void actorAverage(final List<Actor> initialActors,
                              final ActionInputData action) throws IOException {
        List<Actor> actors = new ArrayList<>(initialActors);

        /* sorts the actors array by their average rating and, in case of equality, sorts by name*/
        actors.sort(Comparator.comparing(Actor::getAverageRating).thenComparing(Actor::getName));
        actors.removeIf(actor -> actor.getAverageRating() == 0);
        actorsPutResultInFile(action, actors);
    }

    /*
     * Finds all actors that have the awards given as input
     */
    private void actorAwards(final List<Actor> initialActors,
                             final ActionInputData action) throws IOException {
        List<String> awards = action.getFilters().get(Constants.AWARDS_IN_FILTERS);
        List<Actor> actors = new ArrayList<>(initialActors);

        for (String award : awards) {
            actors.removeIf((a) -> !a.getAwards().containsKey(Utils.stringToAwards(award)));
        }

        /* sorts the actors array by the number of awards */
        Actor.sortActorsByAwards(actors);
        actorsPutResultInFile(action, actors);
    }

    /*
     * Finds all actors whose description contains the words given as input
     */
    private void actorFilterDescription(final List<Actor> initialActors,
                                        final ActionInputData action) throws IOException {
        List<String> words = action.getFilters().get(Constants.WORDS_IN_FILTERS);
        List<Actor> actors = new ArrayList<>(initialActors);

        for (String word : words) {
            /* toLowerCase is used for case insensitivity */
            actors.removeIf((a) -> {
                /* takes just the words from the description(no punctuation) with \W+ */
                String[] description = a.getCareerDescription().toLowerCase().split("\\W+");
                return !Arrays.asList(description).contains(word.toLowerCase());
            });
        }

        actors.sort(Comparator.comparing(Actor::getName));
        actorsPutResultInFile(action, actors);
    }

    /*
     * Sorts videos by their average rating
     */
    private void videosRating(final ActionInputData action,
                              final List<Show> videos) throws IOException {
        applyFiltersYearAndGenre(action, videos);

        /* removes unrated shows */
        videos.removeIf(s -> s.getAverageRating() == 0);

        /* sorts videos by rating */
        videos.sort(Comparator.comparing(Show::getAverageRating));
        videosPutResultInFile(action, videos);
    }

    /*
     * Sorts videos by the number of appearances in favorite lists
     */
    private void videosFavorite(final ActionInputData action,
                                final List<Show> videos) throws IOException {
        applyFiltersYearAndGenre(action, videos);

        /* sets the number of appearance in favorite lists */
        for (Show s : videos) {
            s.countNumberInFavorite(getData().getUsers());
        }

        /* removes shows that don't appear in any favorite list */
        videos.removeIf(show -> show.getNumberInFavorite() == 0);

        /* sorts videos by number of appearances and, in case of equality, sorts by title */
        videos.sort(Comparator.comparing(Show::getNumberInFavorite).thenComparing(Show::getTitle));
        videosPutResultInFile(action, videos);
    }

    /*
     * Sorts videos by their duration
     */
    private void videosLongest(final ActionInputData action,
                               final List<Show> videos) throws IOException {
        applyFiltersYearAndGenre(action, videos);

        /* sort videos by duration and, in case of equality, sort by title*/
        videos.sort(Comparator.comparing(Show::findDuration).thenComparing(Show::getTitle));
        videosPutResultInFile(action, videos);
    }

    private void videosMostViewed(final ActionInputData action,
                                  final List<Show> videos) throws IOException {
        applyFiltersYearAndGenre(action, videos);

        for (Show video : videos) {
            video.countViews(getData().getUsers());
        }

        /* removes videos with 0 views */
        videos.removeIf(v -> v.getNumberOfViews() == 0);
        /* sorts by views and, in case of equality, sorts by title */
        videos.sort(Comparator.comparing(Show::getNumberOfViews).thenComparing(Show::getTitle));
        videosPutResultInFile(action, videos);

    }

    /*
     * Removes the videos whose year and genre doesn't correspond
     */
    private void applyFiltersYearAndGenre(final ActionInputData action, final List<Show> videos) {
        List<String> years  = action.getFilters().get(Constants.YEAR_IN_FILTERS);
        List<String> genres = action.getFilters().get(Constants.GENRE_IN_FILTERS);

        for (String year : years) {
            if (year != null) {
                videos.removeIf(s -> s.getYear() != Integer.parseInt(year));
            }
        }
        for (String genre : genres) {
            if (genre != null) {
                videos.removeIf(s -> !s.getGenres().contains(genre));
            }
        }
    }

    /*
     * Selects actors to print in output file
     */
    @SuppressWarnings("unchecked")
    private void actorsPutResultInFile(final ActionInputData action, final List<Actor> actors)
            throws IOException {
        StringBuilder message = new StringBuilder("Query result: ");
        List<String> firstNActors = new ArrayList<>();

        /* ascendant order */
        if (action.getSortType().equals(Constants.ASCENDANT)) {
            for (int i = 0; i < Math.min(actors.size(), action.getNumber()); ++i) {
                firstNActors.add(actors.get(i).getName());
            }
        }
        /* descendant order */
        if (action.getSortType().equals(Constants.DESCENDANT)) {
            int number = action.getNumber();
            if (number > actors.size()) {
                number = actors.size();
            }
            for (int i = actors.size() - 1; i >= actors.size() - number; --i) {
                firstNActors.add(actors.get(i).getName());
            }
        }

        message.append(firstNActors.toString());
        getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", message.toString()));
    }

    /*
     * Selects videos to print in output file
     */
    @SuppressWarnings("unchecked")
    private void videosPutResultInFile(final ActionInputData action,
                                       final List<Show> videos) throws IOException {
        StringBuilder message = new StringBuilder("Query result: ");
        ArrayList<String> firstN = new ArrayList<>();

        if (action.getSortType().equals(Constants.ASCENDANT)) {
            /* ascendant order */
            for (int i = 0; i < Math.min(videos.size(), action.getNumber()); ++i) {
                firstN.add(videos.get(i).getTitle());
            }
        } else if (action.getSortType().equals(Constants.DESCENDANT)) {
            /* descendant order */
            int number = action.getNumber();
            if (number > videos.size()) {
                number = videos.size();
            }
            for (int i = videos.size() - 1; i >= videos.size() - number; --i) {
                firstN.add(videos.get(i).getTitle());
            }
        }

        message.append(firstN.toString());
        getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", message.toString()));

    }

    /*
     * Selects users to print in output file
     */
    @SuppressWarnings("unchecked")
    private void usersPutResultInFile(final ActionInputData action,
                                 final List<User> users) throws IOException {
        StringBuilder message = new StringBuilder("Query result: ");
        List<String> firstNActors = new ArrayList<>();

        /* ascendant order */
        if (action.getSortType().equals(Constants.ASCENDANT)) {
            for (int i = 0; i < Math.min(users.size(), action.getNumber()); ++i) {
                firstNActors.add(users.get(i).getUsername());
            }
        }
        /* descendant order */
        if (action.getSortType().equals(Constants.DESCENDANT)) {
            int number = action.getNumber();
            if (number > users.size()) {
                number = users.size();
            }
            for (int i = users.size() - 1; i >= users.size() - number; --i) {
                firstNActors.add(users.get(i).getUsername());
            }
        }

        message.append(firstNActors.toString());
        getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", message.toString()));
    }
}
