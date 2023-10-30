package week2.helperFunction;

public class Circle extends Shape implements Drawable{

    public Circle(String name,String color){
        super(name,color);
    }

    @Override
    public double area(double radius){
        return Math.PI*radius*radius;
    }

    @Override
    public double perimeter(double radius){
        return 2*Math.PI*radius;
    }

    @Override
    public void draw() {
        System.out.println("Draw a Circle");
    }
}