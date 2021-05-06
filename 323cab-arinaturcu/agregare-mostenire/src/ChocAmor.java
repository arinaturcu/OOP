public class ChocAmor extends CandyBox {
    private float length;

    public ChocAmor() {
        super();
        length = 0;
    }

    public ChocAmor(float length, String flavor, String origin) {
        super(flavor, origin);
        this.length = length;
    }

    @Override
    public float getVolume() {
        return length * length * length;
    }

    public void printChocAmorDim() {
        System.out.println("ChocAmor = {Length: " + length + "}");
    }

    @Override
    public void printCandy() {
        printChocAmorDim();
    }

    @Override
    public String toString() {
        return "The " + super.toString() + " ChocAmor has volume " + getVolume();
    }
}
