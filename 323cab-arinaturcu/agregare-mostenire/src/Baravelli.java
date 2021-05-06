public class Baravelli extends CandyBox {
    private float radius;
    private float height;

    public Baravelli() {
        super();
        radius = 0;
        height = 0;
    }

    public Baravelli(float radius, float height, String flavor, String origin) {
        super(flavor, origin);
        this.radius = radius;
        this.height = height;
    }

    @Override
    public float getVolume() {
        return (float)Math.PI * radius * radius * height;
    }

    public void printBaravelliDim() {
        System.out.println("Baravelli = {Radius: " + radius + ", Height: " + height + "}");
    }

    @Override
    public void printCandy() {
        printBaravelliDim();
    }

    @Override
    public String toString() {
        return "The " + super.toString() + " Baravelli has volume " + getVolume();
    }
}
