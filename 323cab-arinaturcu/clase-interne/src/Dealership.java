import java.util.Random;

public class Dealership {
    private static class BrandOffer implements Offer {

        @Override
        public int getDiscount(Car car) {
            return switch (car.getType()){
                case DACIA -> 5;
                case RENAULT -> 2;
                case VOLKSWAGEN -> 3;
            };
        }
    }

    private static class DealerOffer implements Offer {

        @Override
        public int getDiscount(Car car) {
            return (2020 - car.getYear()) * 100;
        }
    }

    private static class SpecialOffer implements Offer {

        @Override
        public int getDiscount(Car car) {
            return new Random().nextInt(300);
        }
    }

    public void getFinalPrice(Car car) {
        int totalDiscount = 0;
        int initialPrice = car.getPrice();

        float b = (float) new BrandOffer().getDiscount(car) / 100 * initialPrice;
        int d = new DealerOffer().getDiscount(car);
        int s = new SpecialOffer().getDiscount(car);

        System.out.println("Initial price: " + initialPrice + " euros");
        System.out.println("Applying Brand discount: " + b + " euros");
        System.out.println("Applying Dealer discount: " + d + " euros");
        System.out.println("Applying Special discount: " + s + " euros");

        totalDiscount += b + d + s;

        car.setPrice(initialPrice - totalDiscount);

    }

    public void negotiate(Car car, Offer offer) {
        int approve = new Random().nextInt(2);
        int initialPrice = car.getPrice();

        if (approve == 1) {
            System.out.println("Applying Client discount: " + offer.getDiscount(car) + " euros");
            car.setPrice(initialPrice - offer.getDiscount(car));
        } else {
            System.out.println("Client discount " + offer.getDiscount(car) + " euros not approved");
        }
    }
}
