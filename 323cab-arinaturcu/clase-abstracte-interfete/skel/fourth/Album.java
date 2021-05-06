package fourth;

import java.util.ArrayList;

public abstract class Album {
    private final ArrayList<Song> tracks = new ArrayList<>();

    abstract public void addSong(Song track);

    public void removeSong(Song track) {
        tracks.remove(track);
    }

    @Override
    public String toString() {
        return "Album{" +
                "tracks=" + tracks +
                '}';
    }

    public ArrayList<Song> getTracks() {
        return tracks;
    }
}
