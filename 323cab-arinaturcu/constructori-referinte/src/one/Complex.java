package one;

public class Complex {
    private int real;
    private int imaginary;

    public Complex(int real, int imaginary) {
        this.real      = real;
        this.imaginary = imaginary;
    }

    public Complex() {
        this(0, 0);
    }

    public Complex(Complex number) {
        this.real      = number.real;
        this.imaginary = number.imaginary;
    }

    public void addWithComplex(Complex toAdd) {
        this.real      += toAdd.real;
        this.imaginary += toAdd.imaginary;
    }

    public void showNumber() {
        System.out.println(this.real + " + " + this.imaginary + "*i");
    }

    public int getReal() {
        return real;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public int getImaginary() {
        return imaginary;
    }

    public void setImaginary(int imaginary) {
        this.imaginary = imaginary;
    }
}
