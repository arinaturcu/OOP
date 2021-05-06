package task1;

import java.util.Objects;

public class BodyLotion {
    private final String brand;
    private final int sizeInMilliliters;
    private final String type;


    public BodyLotion(String brand, int sizeInMilliliters, String type) {
        this.brand = brand;
        this.sizeInMilliliters = sizeInMilliliters;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BodyLotion that = (BodyLotion) o;
        return sizeInMilliliters == that.sizeInMilliliters &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, sizeInMilliliters, type);
    }

    @Override
    public String toString() {
        return "Task1.BodyLotion{" +
                "brand='" + brand + '\'' +
                ", sizeInMilliliters=" + sizeInMilliliters +
                ", type='" + type + '\'' +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public int getSizeInMilliliters() {
        return sizeInMilliliters;
    }

    public String getType() {
        return type;
    }
}