package week2.helperFunction;

public class Shape {
    private String name;
    private String color;

    public Shape(String name, String color) {
        this.name=name;
        this.color=color;
    }

    public double area(double radius) {
        return 0;
    }

    public double area(double length,double breath) {
        return 0;
    }

    public double perimeter(double radius) {
        return 0;
    }

    public double perimeter(double length,double breadth) {
        return 0;
    }

    public void ShapeDescription(){
        System.out.println("Shape name: "+this.name);
        System.out.println("Shape color: "+this.color);
    }
}