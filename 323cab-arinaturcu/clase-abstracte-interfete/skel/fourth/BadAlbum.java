package fourth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class BadAlbum extends Album {
    private static boolean isPalindrome(int number) {
        int tmp = number;
        ArrayList<Integer> digits = new ArrayList<>();

        while (tmp != 0) {
            digits.add(tmp % 10);
            tmp /= 10;
        }

        ArrayList<Integer> reversedDigits = new ArrayList<>(digits);
        Collections.reverse(reversedDigits);

        return reversedDigits.equals(digits);
    }

    @Override
    public void addSong(Song track) {
        if (isPalindrome(track.getId()) && track.getName().length() == 3) {
            super.getTracks().add(track);
        }
    }
}
