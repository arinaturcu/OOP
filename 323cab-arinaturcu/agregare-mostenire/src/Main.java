import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<CandyBox> candyBoxes = new ArrayList<>();

        candyBoxes.add(new Baravelli(1, 2, "Vanilla", "Romanian"));
        candyBoxes.add(new Lindt(1, 2, 3, "Chocolate", "Swedish"));
        candyBoxes.add(new ChocAmor(2, "Lemon", "Italian"));

        Area area = new Area(new CandyBag(candyBoxes), 38, "Lunga");
        area.getBirthdayCard();
    }
}
