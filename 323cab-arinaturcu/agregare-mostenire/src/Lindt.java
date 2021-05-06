public class Lindt extends CandyBox{
    private float length;
    private float width;
    private float height;

    public Lindt() {
        super();
        length = 0;
        width  = 0;
        height = 0;
    }

    public Lindt(float length, float width, float height, String flavour, String origin) {
        super(flavour, origin);
        this.length = length;
        this.width  = width;
        this.height = height;
    }

    @Override
    public float getVolume() {
        return length * width * height;
    }

    public void printLindtDim() {
        System.out.println("Lindt = {Length: " + length + ", Width: " + width + ", Height: " + height + "}");
    }

    @Override
    public void printCandy() {
        printLindtDim();
    }

    @Override
    public String toString() {
        return "The " + super.toString() + " Lindt has volume " + getVolume();
    }
}
