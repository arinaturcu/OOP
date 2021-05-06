package task1;

public class TestTask1 {
    public static void main(String[] args) {
        MyHashMap<BodyLotion, Integer> stockBodyLotions = new MyHashMap<>(3);

        BodyLotion[] bodyLotions = new BodyLotion[3];
        bodyLotions[0] = new BodyLotion("Yves Rocher", 390,
                "Mango & Coriander");
        bodyLotions[1] = new BodyLotion("Nivea",
                250, "Cocoa butter");
        bodyLotions[2] = new BodyLotion("Cerave",
                355, "For Normal to Dry Skin");


        stockBodyLotions.put(bodyLotions[0], 5);
        stockBodyLotions.put(bodyLotions[1], 1);
        stockBodyLotions.put(bodyLotions[2], 3);
        System.out.println(stockBodyLotions);

        // update
        stockBodyLotions.put(bodyLotions[0], 4);
        System.out.println(stockBodyLotions);
    }
}