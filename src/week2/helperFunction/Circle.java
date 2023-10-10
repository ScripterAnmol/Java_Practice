package week2.helperFunction;

public class Circle extends Shape implements Drawable{
    public Circle(){
        obj.name="Circle";
    }

    public Circle(ShapeValues argValue){
        obj = argValue;
        obj.name="Circle";
    }

    @Override
    public double area(){
        double temp = Math.PI*obj.radius*obj.radius;

        return temp;
    }

    @Override
    public double perimeter() {
        return 2*Math.PI*obj.radius;
    }

    @Override
    public void draw() {
        System.out.println("Draw a Circle");
    }
}