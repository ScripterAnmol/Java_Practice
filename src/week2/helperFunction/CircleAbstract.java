package week2.helperFunction;

public class CircleAbstract extends ShapeAbstract{
    private double radius;

    // Constructor
    public CircleAbstract(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}
