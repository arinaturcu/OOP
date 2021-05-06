import java.util.ArrayList;

public class CandyBag {
    private ArrayList<CandyBox> candyBoxes;

    public CandyBag(ArrayList<CandyBox> candyBoxes) {
        this.candyBoxes = candyBoxes;
    }

    public ArrayList<CandyBox> getCandyBoxes() {
        return candyBoxes;
    }
}
