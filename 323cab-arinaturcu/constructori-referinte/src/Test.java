import one.*;
import three.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("Task 1:");

        Complex comNum = new Complex(1, 2);
        Complex comNum2 = new Complex(2, 5);
        comNum.addWithComplex(comNum2);
        comNum.showNumber();

        System.out.println("Task 3:");

        Point p = new Point((float) 2.30, (float)4.20);
        p.changeCoords(1, 2);
        System.out.println(p);

        Polygon pol = new Polygon(new float[] {1, 2, 3, 4, 5, 6});
        System.out.println(pol);
    }
}
