package week2.helperFunction;

public class RectangleAbstract extends ShapeAbstract{
    private double length;
    private double width;

    public RectangleAbstract(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
}
