package third;

public class Operation implements Minus, Plus, Div, Mult {
    private float number;

    public Operation(float number) {
        this.number = number;
    }

    @Override
    public void div(float x) {
        if (x == 0) return;
        number /= x;
    }

    @Override
    public void minus(float x) {
        number -= x;
    }

    @Override
    public void mult(float x) {
        number *= x;
    }

    @Override
    public void plus(float x) {
        number += x;
    }

    public float getNumber() {
        return number;
    }
}
