import java.util.Random;

public class PasswordMaker {
    private final int MAGIC_NUMBER = new Random().nextInt(20);
    private final String MAGIC_STRING = new RandomStringGenerator(25, "abcdefghijklmnopqrstuvwxyz").next();
    private final String name;

    public PasswordMaker(String name) {
        this.name = name;
    }

    public String getPassword() {
        String alphabet = new RandomStringGenerator(10, MAGIC_STRING).next();
        String password = new RandomStringGenerator(MAGIC_NUMBER, alphabet).next();

        int number = name.length() + new Random().nextInt(100);
        password = password + Integer.toString(number);

        return password;
    }
}
