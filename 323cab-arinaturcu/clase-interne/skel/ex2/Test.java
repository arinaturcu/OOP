package ex2;

interface Engine {
    public int getFuelCapacity();
}

class Car {
    public Engine getEngine(int fuelCapacity) {
        return new Engine () {
            private int fuelCapacity = 11;

            public int getFuelCapacity() {
                return fuelCapacity;
            }
        };
    }
}

public class Test {
    public static void main(String[] args) {
        Car car = new Car();

        System.out.println(car.getEngine(13).getFuelCapacity());
    }
}

