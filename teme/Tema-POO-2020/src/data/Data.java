package data;

import fileio.ActionInputData;
import java.util.List;

/**
 * A class that copies all the input information
 */
public final class Data {
    /**
     * List of actors
     */
    private final List<Actor> actors;
    /**
     * List of users
     */
    private final List<User> users;
    /**
     * List of commands
     */
    private final List<ActionInputData> actions;
    /**
     * List of movies
     */
    private final List<Movie> movies;
    /**
     * List of serials aka tv shows
     */
    private final List<Serial> serials;

    public Data() {
        this.actors = null;
        this.users = null;
        this.actions = null;
        this.movies = null;
        this.serials = null;
    }

    public Data(final List<Actor> actors, final List<User> users,
                 final List<ActionInputData> commands,
                 final List<Movie> movies,
                 final List<Serial> serials) {
        this.actors = actors;
        this.users = users;
        this.actions = commands;
        this.movies = movies;
        this.serials = serials;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<ActionInputData> getActions() {
        return actions;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Serial> getSerials() {
        return serials;
    }
}
