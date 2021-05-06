package actions;

import common.Constants;
import data.Data;
import data.Show;
import data.User;
import fileio.ActionInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Command extends Action {

    public Command(final Data data, final JSONArray arrayResult, final Writer fileWriter) {
        super(data, arrayResult, fileWriter);
    }

    /**
     * Executes the command
     * @param action the action given
     * @throws IOException exception
     */
    @Override
    public void execute(final ActionInputData action) throws IOException {
        if (action.getType() != null) {
            User user = findUser(action.getUsername(), getData().getUsers());
            assert user != null;

            switch (action.getType()) {
                case Constants.FAVORITE -> commandFavorite(action, user);
                case Constants.VIEW -> commandView(action, user);
                case Constants.RATING -> commandRating(action, user);
                default -> { }
            }
        }
    }

    /*
     * Adds a video to user's favorite list
     */
    @SuppressWarnings("unchecked")
    private void commandFavorite(final ActionInputData action, final User user) throws IOException {
        if (!user.getHistory().containsKey(action.getTitle())) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "error -> " + action.getTitle() + " is not seen"));
            return;
        }

        if (user.getFavoriteMovies().contains(action.getTitle())) {
            getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "error -> " + action.getTitle() + " is already in favourite list"));
            return;
        }

        user.addToFavorite(action.getTitle());
        getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", "success -> " + action.getTitle() + " was added as favourite"));
    }

    /*
     * Adds a video in the user's history
     */
    @SuppressWarnings("unchecked")
    private void commandView(final ActionInputData action, final User user) throws IOException {
        user.addToHistory(action.getTitle());
        getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", "success -> " + action.getTitle()
                        + " was viewed with total views of "
                        + user.getHistory().get(action.getTitle())));
    }

    /*
     * Registers an user's rating
     */
    @SuppressWarnings("unchecked")
    private void commandRating(final ActionInputData action, final User user) throws IOException {
        if (!user.getHistory().containsKey(action.getTitle())) {
            super.getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", "error -> " + action.getTitle() + " is not seen"));
            return;
        }

        List<Show> videos = new ArrayList<>();
        videos.addAll(getData().getMovies());
        videos.addAll(getData().getSerials());

        Show videoToBeRated = findVideo(action.getTitle(), videos);

        assert videoToBeRated != null;
        if (videoToBeRated.checkIfUserRated(action.getSeasonNumber(), user)) {
            super.getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                    "", "error -> " + action.getTitle()
                            + " has been already rated"));
            return;
        }

        videoToBeRated.rate(action, user);
        super.getArrayResult().add(getFileWriter().writeFile(action.getActionId(),
                "", "success -> " + action.getTitle() + " was rated with "
                        + action.getGrade() + " by " + user.getUsername()));
    }

    /*
    Finds a video by its title
     */
    private Show findVideo(final String title, final List<Show> videos) {
        for (Show video : videos) {
            if (video.getTitle().equals(title)) {
                return video;
            }
        }
        return null;
    }

    /*
    Finds a user by its username
     */
    private static User findUser(final String username, final List<User> users) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
