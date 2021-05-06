package fourth;

public class ThrillerAlbum extends Album{
    @Override
    public void addSong(Song track) {
        if (track.getComposer().equals("Michael Jackson") && track.getId() % 2 == 0) {
            super.getTracks().add(track);
        }
    }
}
