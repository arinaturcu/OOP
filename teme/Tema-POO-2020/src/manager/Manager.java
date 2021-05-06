package manager;

import actions.Command;
import actions.Query;
import actions.Recommendation;
import common.Constants;
import data.Actor;
import data.Data;
import data.Movie;
import data.User;
import data.Serial;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.UserInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.Input;
import fileio.Writer;
import org.json.simple.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that runs the actions
 */
public final class Manager {
    /**
     * for coding style
     */
    private Manager() {
    }

    /**
     * Executes all the actions given
     * @param input all the input
     * @param arrayResult the JSONArray with the result
     * @param fileWriter to write the result
     * @throws IOException exception
     */
    public static void run(final Input input, final JSONArray arrayResult,
                           final Writer fileWriter) throws IOException {
        Data data = transferData(input);

        for (ActionInputData actionInput : data.getActions()) {
            switch (actionInput.getActionType()) {
                case Constants.COMMAND:
                    (new Command(data, arrayResult, fileWriter)).execute(actionInput);
                    break;
                case Constants.QUERY:
                    (new Query(data, arrayResult, fileWriter)).execute(actionInput);
                    break;
                case Constants.RECOMMENDATION:
                    (new Recommendation(data, arrayResult, fileWriter)).execute(actionInput);
                default:
                    break;
            }
        }
    }

    /**
     * Transfers all the data from Input class to Data class
     * @param input from where all the data is transferred
     * @return the Data object that contains all the data
     */
    private static Data transferData(final Input input) {
        List<Actor> actors = new ArrayList<>();
        for (ActorInputData actor : input.getActors()) {
            actors.add(new Actor(actor.getName(), actor.getCareerDescription(),
                    actor.getFilmography(), actor.getAwards()));
        }

        List<User> users = new ArrayList<>();
        for (UserInputData user : input.getUsers()) {
            users.add(new User(user.getUsername(), user.getSubscriptionType(),
                    user.getHistory(), user.getFavoriteMovies()));
        }

        List<Movie> movies = new ArrayList<>();
        for (MovieInputData movie : input.getMovies()) {
            movies.add(new Movie(movie.getTitle(), movie.getCast(),
                    movie.getGenres(), movie.getYear(), movie.getDuration()));
        }

        List<Serial> serials = new ArrayList<>();
        for (SerialInputData serial : input.getSerials()) {
            serials.add(new Serial(serial.getTitle(), serial.getCast(),
                    serial.getGenres(), serial.getNumberSeason(), serial.getSeasons(),
                    serial.getYear()));
        }

        return new Data(actors, users, input.getCommands(), movies, serials);
    }
}
