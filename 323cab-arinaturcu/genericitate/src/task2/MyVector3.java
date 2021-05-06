package task2;

import java.util.Arrays;

/**
 * Size 3 array
 */
public class MyVector3 implements Sumabil {
    private final int[] vector;

    public MyVector3() {
        vector = new int[3];
    }

    public MyVector3(int[] vector) {
        this.vector = vector;
    }

    @Override
    public void addValue(Sumabil value) {
        try {
            vector[0] += ((MyVector3)value).getVector()[0];
            vector[1] += ((MyVector3)value).getVector()[1];
            vector[2] += ((MyVector3)value).getVector()[2];
        } catch (Exception e) {
            System.out.println("An array of size 3 is required");
        }
    }

    public int[] getVector() {
        return vector;
    }

    @Override
    public String toString() {
        return "MyVector3{" +
                "vector=" + Arrays.toString(vector) +
                '}';
    }
}