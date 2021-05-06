import java.util.ArrayList;
import java.util.List;

interface AppleStrategy {
    String applePrint(Apple apple);
}

class WeightStrategy implements AppleStrategy {
    @Override
    public String applePrint(Apple apple) {
        return Integer.toString(apple.getWeight());
    }
}

class CompareStrategy implements AppleStrategy {
    @Override
    public String applePrint(Apple apple) {
        return apple.getWeight() < 150 ? "Usor" : "Greu";
    }
}

class Apple {
    String color;
    int weight;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }
}

public class ApplePrint2020 {
    public static void prettyPrintApple(List<Apple> apples, AppleStrategy strategy) {
        for (Apple apple : apples) {
            System.out.println(strategy.applePrint(apple));
        }
    }

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple(100, "RED"));
        apples.add(new Apple(200, "GREEN"));
        apples.add(new Apple(165, "RED"));
        apples.add(new Apple(97, "GREEN"));

        prettyPrintApple(apples, new WeightStrategy());
        System.out.println();
        prettyPrintApple(apples, new CompareStrategy());
    }
}
