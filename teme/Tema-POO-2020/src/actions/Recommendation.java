package actions;

import common.Constants;
import data.Data;
import data.Show;
import data.User;
import entertainment.Genre;
import fileio.ActionInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

@SuppressWarnings("unchecked")
public final class Recommendation extends Action {
    public Recommendation(final Data data, final JSONArray arrayResult, final Writer fileWriter) {
        super(data, arrayResult, fileWriter);
    }

    @Override
    public void execute(final ActionInputData action) throws IOException {
        User user = findUser(getData().getUsers(), action.getUsername());

        /* puts all videos in an arraylist of Show in the order they appear in input */
        List<Show> videos = new ArrayList<>();
        videos.addAll(getData().getMovies());
        videos.addAll(getData().getSerials());

        switch (action.getType()) {
            case Constants.STANDARD    -> standard(action, user, videos);
            case Constants.BEST_UNSEEN -> bestUnseen(action, user, videos);
            case Constants.POPULAR     -> popular(action, user, videos);
            case Constants.FAVORITE    -> favorite(action, user, videos);
            case Constants.SEARCH      -> search(action, user, videos);
            default -> { }
        }
    }

    @SuppressWarnings("unchecked")
    private void standard(final ActionInputData action, final User user,
                          final List<Show> videos) throws IOException {
        if (user == null) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "StandardRecommendation cannot be applied!"));
            return;
        }

        videos.removeIf(video -> user.getHistory().containsKey(video.getTitle()));

        /* if no video is available */
        if (videos.isEmpty()) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "StandardRecommendation cannot be applied!"));
            return;
        }

        getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", "StandardRecommendation result: " + videos.get(0).getTitle()));
    }

    private void bestUnseen(final ActionInputData action, final User user,
                            final List<Show> videos) throws IOException {
        if (user == null) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "BestRatedUnseenRecommendation cannot be applied!"));
            return;
        }

        /* sorts videos by average rating */
        videos.sort(Comparator.comparing(Show::getAverageRating).reversed());
        videos.removeIf(video -> user.getHistory().containsKey(video.getTitle()));


        if (videos.isEmpty()) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "BestRatedUnseenRecommendation cannot be applied!"));
            return;
        }


        getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                        "", "BestRatedUnseenRecommendation result: " + videos.get(0).getTitle()));
    }

    private void popular(final ActionInputData action, final User user,
                         final List<Show> videos) throws IOException {
        if (user == null || !user.getSubscriptionType().equals(Constants.PREMIUM)) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "PopularRecommendation cannot be applied!"));
            return;
        }

        /* popularity for each genre */
        Map<Genre, Integer> genrePopularity = new HashMap<>();
        Map<Genre, List<Show>> genreAndVideos = new HashMap<>();
        List<Genre>  genres = new ArrayList<>();

        /* counts views for videos */
        for (Show video : videos) {
            video.countViews(getData().getUsers());
        }

        /* puts elements HashMaps */
        for (Show video : videos) {
            for (String g : video.getGenres()) {
                Genre genre = Utils.stringToGenre(g);
                /* put genre popularity */
                genrePopularity.merge(genre, video.getNumberOfViews(), Integer::sum);
                if (!genres.contains(genre)) {
                    genres.add(genre);
                }

                /* put videos in genres */
                if (genreAndVideos.get(genre) == null) {
                    genreAndVideos.put(genre, new ArrayList<>(Collections.singletonList(video)));
                } else {
                    genreAndVideos.get(genre).add(video);
                }
            }
        }

        List<Integer> genreViews = new ArrayList<>(genrePopularity.values());
        Collections.sort(genreViews);
        Collections.reverse(genreViews);

        /* searches for video to recommend */
        for (int views : genreViews) {
            for (Genre genre : genres) {
                if (genrePopularity.get(genre) == views) {
                    for (Show video : genreAndVideos.get(genre)) {
                        if (!user.getHistory().containsKey(video.getTitle())) {
                            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                                    "", "PopularRecommendation result: " + video.getTitle()));
                            return;
                        }
                    }
                }
            }
        }

        getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", "PopularRecommendation cannot be applied!"));
    }

    private void favorite(final ActionInputData action, final User user,
                          final List<Show> videos) throws IOException {
        if (user == null || !user.getSubscriptionType().equals(Constants.PREMIUM)) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "FavoriteRecommendation cannot be applied!"));
            return;
        }

        /* sets the number of appearances in favorite list for every video */
        for (Show video : videos) {
            video.countNumberInFavorite(getData().getUsers());
        }

        /* sorts by number of appearances in favorite lists but in descendant order */
        videos.sort(Comparator.comparing(Show::getNumberInFavorite).reversed());
        videos.removeIf(video -> user.getHistory().containsKey(video.getTitle()));
        if (videos.isEmpty()) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "FavoriteRecommendation cannot be applied!"));
            return;
        }

        getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", "FavoriteRecommendation result: " + videos.get(0).getTitle()));
    }

    private void search(final ActionInputData action, final User user,
                        final List<Show> videos) throws IOException {
        if (user == null || !user.getSubscriptionType().equals(Constants.PREMIUM)) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "SearchRecommendation cannot be applied!"));
            return;
        }

        /* sorts videos by their average rating and, in case of equality, by their title */
        videos.sort(Comparator.comparing(Show::getAverageRating).thenComparing(Show::getTitle));
        videos.removeIf(video -> user.getHistory().containsKey(video.getTitle()));
        videos.removeIf(video -> !video.getGenres().contains(action.getGenre()));

        /* builds a list of Strings that represents the titles of each video */
        List<String> titles = new ArrayList<>();
        for (Show video : videos) {
            titles.add(video.getTitle());
        }

        if (titles.isEmpty()) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "SearchRecommendation cannot be applied!"));
            return;
        }

        getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", "SearchRecommendation result: " + titles));
    }

    /*
     * Finds a user by its username
     */
    private static User findUser(final List<User> users, final String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }

        return null;
    }
}
