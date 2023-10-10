package week2.helperFunction;

public class Rectangle extends Shape implements Drawable{
    public Rectangle(){
        obj.name="Rectangle";
    }
    public Rectangle(ShapeValues argValue){
        obj = argValue;
        obj.name="Rectangle";
    }

    @Override
    public double area() {
        double temp = obj.length*obj.breath;

        return temp;
    }

    @Override
    public double perimeter() {
        return 2*(obj.length+obj.breath);
    }

    @Override
    public void draw() {
        System.out.println("Draw a Rectangle");
    }
}
