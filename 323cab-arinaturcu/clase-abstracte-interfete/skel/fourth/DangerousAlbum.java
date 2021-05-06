package fourth;

public class DangerousAlbum extends Album {
    private boolean isPrime(int number) {
        if (number == 0 || number == 1) return false;
        if (number == 2) return true;

        for (int i = 2; i < Math.sqrt(number); ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addSong(Song track) {
        if (isPrime(track.getId())) {
            super.getTracks().add(track);
        }
    }
}
