import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Car car1 = new Car(8000, Car.CarType.RENAULT, 2010);
        Car car2 = new Car(10000, Car.CarType.VOLKSWAGEN, 2014);
        Car car3 = new Car(7000, Car.CarType.DACIA, 2005);

        Dealership dealership = new Dealership();

        dealership.getFinalPrice(car1);
        System.out.println("Final price: " + car1.getPrice());
        dealership.negotiate(car1, new Offer() {
            @Override
            public int getDiscount(Car car) {
                return 200;
            }
        });
        System.out.println("Final price after negotiation: " + car1.getPrice() + " euros\n");

        dealership.getFinalPrice(car2);
        System.out.println("Final price: " + car2.getPrice());
        dealership.negotiate(car2, new Offer() {
            @Override
            public int getDiscount(Car car) {
                return 500;
            }
        });
        System.out.println("Final price after negotiation: " + car2.getPrice() + " euros\n");

        dealership.getFinalPrice(car3);
        System.out.println("Final price: " + car3.getPrice());
        dealership.negotiate(car3, new Offer() {
            @Override
            public int getDiscount(Car car) {
                return 400;
            }
        });
        System.out.println("Final price after negotiation: " + car3.getPrice() + " euros");

        /* Dupa compilarea in linia de comanda s-a observat ca s-a generat cate
         * un fisier .class pentru fiecare clasa (interna sau externa), interfata si enum. Pentru
         * clasele interne numele fisierului este de forma clasaExterna$clasaInterna.class.
         * In cazul claselor anonime, fisierele sunt de forma clasaExterna$numarClasaAnonima.class,
         * ele fiind doar numerotate pentru ca nu au un nume.
         */

        System.out.println();
        Car car4 = new Car(12000, Car.CarType.DACIA, 2016);
        Car car5 = new Car(15000, Car.CarType.RENAULT, 2016);
        Car car6 = new Car(8000, Car.CarType.VOLKSWAGEN, 2016);
        Car car7 = new Car(9000, Car.CarType.DACIA, 2016);
        Car car8 = new Car(11000, Car.CarType.VOLKSWAGEN, 2016);

        ArrayList<Car> cars = new ArrayList<Car>(Arrays.asList(car1, car2, car3, car4, car5, car6, car7, car8));

        System.out.println("All cars:\n" + cars);
        cars.removeIf((c) -> c.getPrice() > 10000);
        System.out.println("Cars with prices below 10000 euros:\n" + cars);
    }
}
