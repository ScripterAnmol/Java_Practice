package week2.helperFunction;

public class Rectangle extends Shape implements Drawable{
    public Rectangle(String name,String color){
        super(name,color);
    }

    @Override
    public double area(double length,double breath) {
        double temp = length*breath;

        return temp;
    }

    @Override
    public double perimeter(double length,double breath) {
        return 2*(length+breath);
    }

    @Override
    public void draw() {
        System.out.println("Draw a Rectangle");
    }
}
