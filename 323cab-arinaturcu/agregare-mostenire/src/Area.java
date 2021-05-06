import java.util.ArrayList;

public class Area {
    private CandyBag candyBag;
    private int number;
    private String street;

    public Area() {
        candyBag = new CandyBag(new ArrayList<CandyBox>());
        number   = 0;
        street   = "unknown";
    }

    public Area(CandyBag candyBag, int number, String street) {
        this.candyBag = candyBag;
        this.number   = number;
        this.street   = street;
    }

    public void getBirthdayCard() {
        System.out.println("str. " + street + ", nr. " + number + "\n");
        System.out.println("La multi ani!\n");

        for (CandyBox candyBox : candyBag.getCandyBoxes()) {
            System.out.println(candyBox);
        }
        System.out.println();

//        // Using instanceof
//        for (CandyBox candyBox : candyBag.getCandyBoxes()) {
//            if (candyBox instanceof Lindt) {
//                ((Lindt)candyBox).printLindtDim();
//            } else if (candyBox instanceof Baravelli){
//                ((Baravelli) candyBox).printBaravelliDim();
//            } else if (candyBox instanceof ChocAmor) {
//                ((ChocAmor) candyBox).printChocAmorDim();
//            }
//        }

        // Avoiding instanceof
        for (CandyBox candyBox : candyBag.getCandyBoxes()) {
            candyBox.printCandy();
        }
    }
}
