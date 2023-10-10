package week2.helperFunction;

public class Triangle extends Shape implements Drawable{
    public Triangle(){
        obj.name="Triangle";
    }
    public Triangle(ShapeValues argValue){
        obj = argValue;
        obj.name="Triangle";
    }

    @Override
    public double area() {
        double s = (obj.side1 + obj.side2 + obj.side3) / 2;
        return Math.sqrt(s * (s - obj.side1) * (s - obj.side2) * (s - obj.side3));
    }

    @Override
    public double perimeter() {
        return obj.side1 + obj.side2 + obj.side3;
    }

    @Override
    public void draw() {
        System.out.println("Draw a Triangle");
    }
}
