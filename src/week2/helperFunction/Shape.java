package week2.helperFunction;

public class Shape {
    ShapeValues obj = new ShapeValues();
    public void Shape(ShapeValues argVal){
        obj = argVal;
    }

    public double area() {
        return 0;
    }

    public double perimeter() {
        return 0;
    }

    public void ShapeDescription(){
        System.out.println("Shape name: "+obj.name);
        System.out.println("Shape color: "+obj.color);
    }
}