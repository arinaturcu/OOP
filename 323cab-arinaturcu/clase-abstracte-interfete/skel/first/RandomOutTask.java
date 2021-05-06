package first;

import java.util.Random;

public class RandomOutTask implements Task {
    private final int randomNumber;

    public RandomOutTask() {
        randomNumber = new Random().nextInt(100);
    }

    public void execute() {
        System.out.println(randomNumber);
    }
}
